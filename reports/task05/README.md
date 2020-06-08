# Полусинхронная репликация

  1) Настроить 2 слейва и 1 мастер.
  2) Включить row-based репликацию.
  3) Включить GTID.
  4) Настроить полусинхронную репликацию.
  5) Создать нагрузку на запись в любую тестовую таблицу. На стороне, которой нагружаем считать, сколько строк мы успешно записали.
  6) С помощью kill -9 убиваем мастер MySQL.
  7) Заканчиваем нагрузку на запись.
  8) Выбираем самый свежий слейв. Промоутим его до мастера. Переключаем на него второй слейв.
  9) Проверяем, есть ли потери транзакций.

## Настройка полусинхронной репликации

Шаг 1. Настройка Мастера

На сервере, который будет выступать мастером, необходимо внести правки в my.cnf:

```
# выбираем ID сервера, произвольное число
server-id = 1
# путь к бинарному логу
log_bin = /var/lib/mysql/mysql-bin.log
# формат лога
binlog_format = ROW
# название базы данных, которая будет реплицироваться
binlog_do_db = sn
# включаем GTID
gtid-mode = on
enforce-gtid-consistency = ON
```

Шаг 2. Настройка Слейвов

В настройках my.cnf на каждом Слейве необходимо указать такие параметры:

```
# ID Слейва для slave_1 = 2, для slave_2 = 3
server-id = 2
# Путь к relay логу
relay-log = /var/lib/mysql/mysql-relay-bin.log
# Путь к bin логу на Мастере
log_bin = /var/lib/mysql/mysql-bin.log
# База данных для репликации
binlog_do_db = sn
# формат лога
binlog_format = ROW
# GTID
gtid-mode = on
enforce-gtid-consistency = ON
```

Шаг 3. Далее на мастере включаем полусинхронную репликацию:

```sql
INSTALL PLUGIN rpl_semi_sync_master SONAME 'semisync_master.so';
SET GLOBAL rpl_semi_sync_master_enabled = 1;
SHOW VARIABLES LIKE 'rpl_semi_sync%';
```

В результате 

```
|rpl_semi_sync_master_enabled             |ON        |
|rpl_semi_sync_master_timeout             |10000     |
|rpl_semi_sync_master_trace_level         |32        |
|rpl_semi_sync_master_wait_for_slave_count|1         |
|rpl_semi_sync_master_wait_no_slave       |ON        |
|rpl_semi_sync_master_wait_point          |AFTER_SYNC|
```

Шаг 4. Далее на мастере создаем и назначаем права пользователю для реплики:

```sql
create user "sn_slave_user"@"%" identified by "sn_slave_pwd"; 
GRANT REPLICATION SLAVE ON *.* TO "sn_slave_user"@"%"; 
FLUSH PRIVILEGES;
```

Проверяем статус Мастер-сервера:

```sql
SHOW MASTER STATUS;
```

Шаг 5. Далее на каждом слейве включаем полусинхронную репликацию:

```sql
INSTALL PLUGIN rpl_semi_sync_slave SONAME 'semisync_slave.so';
SET GLOBAL rpl_semi_sync_slave_enabled = 1;
SHOW VARIABLES LIKE 'rpl_semi_sync%';
```

В результате 

```
|rpl_semi_sync_slave_enabled              |ON        |
|rpl_semi_sync_slave_trace_level          |32        |
```

Шаг 6. Далее необходимо настроить подключение к мастеру и запуск репликации на каждом из слейвов:

```sql
CHANGE MASTER TO MASTER_HOST='172.30.0.2',MASTER_USER='sn_slave_user',MASTER_PASSWORD='sn_slave_pwd',MASTER_AUTO_POSITION = 1; 
START SLAVE;
```

Проверяем статус репликации на слейве:

```sql
SHOW SLAVE STATUS;
```

В результате запроса должны быть строки:
```
Name                         |Value                                                 |
-----------------------------|------------------------------------------------------|
Slave_IO_State               |Waiting for master to send event                      |
Master_Host                  |172.28.0.2                                            |
Master_User                  |sn_slave_user                                         |
Master_Port                  |3306                                                  |
Connect_Retry                |60                                                    |
Master_Log_File              |mysql-bin.000003                                      |
Read_Master_Log_Pos          |883                                                   |
Relay_Log_File               |mysql-relay-bin.000003                                |
Relay_Log_Pos                |322                                                   |
Relay_Master_Log_File        |mysql-bin.000003                                      |
Slave_IO_Running             |Yes                                                   |
Slave_SQL_Running            |Yes                                                   |
Retrieved_Gtid_Set           |                                                      |
Executed_Gtid_Set            |bb86de2b-a909-11ea-8abf-0242ac1c0003:1                |
```

## docker-compose с настройками репликации 

см ../../docker/docker-compose.yml

- Добавлен master (настройки /conf/mysql.conf)
- Добавлен slave1 (настройки /conf/mysql.conf)
- Добавлен slave2 (настройки /conf/mysql.conf)

Для запуска контейнеров использовать скрипт ./docker/build.sh, 
который настраивает мастер и слейв на полусинхронную репликацию.


## Тестирование полусинхронной репликации

- Запустить докер с master, slave1, slave2: ./docker/build.sh 
- Удалить в файле ./backend/src/test/kotlin/dev/lysov/sn/Report5Test.kt аннотацию @Disabled
- Выполнить команду из корня проекта: 

```shell script
./gradlew test -i --tests "*Report5Test"
```

В результате начнется вставка данных в мастер.

- В процессе вставки, остановим io_thread у slave1 для того, чтобы имитировать разное количество реплицированных данных на разных слейвах: 

```sql
stop slave io_thread;
```

- Убиваем мастер:

```shell script
docker kill mysql_master
```  

- Включаем обратно io_thread на slave1:

```sql
start slave io_thread;
```

- Скрипт Report5Test прекратит свою работу и выведет кол-во строк, успешно вставленных в БД:
Например: count rows = 2119

- Анализируем slave1

```sql
show slave status;
```

```text
Name                         |Value                                                                              |
-----------------------------|-----------------------------------------------------------------------------------|
Slave_IO_State               |Connecting to master                                                               |
Master_Host                  |172.30.0.2                                                                         |
Master_User                  |sn_slave_user                                                                      |
Master_Port                  |3306                                                                               |
Connect_Retry                |60                                                                                 |
Master_Log_File              |mysql-bin.000003                                                                   |
Read_Master_Log_Pos          |676757                                                                             |
Relay_Log_File               |mysql-relay-bin.000003                                                             |
Relay_Log_Pos                |676971                                                                             |
Relay_Master_Log_File        |mysql-bin.000003                                                                   |
Slave_IO_Running             |Connecting                                                                         |
Slave_SQL_Running            |Yes                                                                                |
Replicate_Do_DB              |                                                                                   |
Replicate_Ignore_DB          |                                                                                   |
Replicate_Do_Table           |                                                                                   |
Replicate_Ignore_Table       |                                                                                   |
Replicate_Wild_Do_Table      |                                                                                   |
Replicate_Wild_Ignore_Table  |                                                                                   |
Last_Errno                   |0                                                                                  |
Last_Error                   |                                                                                   |
Skip_Counter                 |0                                                                                  |
Exec_Master_Log_Pos          |676757                                                                             |
Relay_Log_Space              |677588                                                                             |
Until_Condition              |None                                                                               |
Until_Log_File               |                                                                                   |
Until_Log_Pos                |0                                                                                  |
Master_SSL_Allowed           |No                                                                                 |
Master_SSL_CA_File           |                                                                                   |
Master_SSL_CA_Path           |                                                                                   |
Master_SSL_Cert              |                                                                                   |
Master_SSL_Cipher            |                                                                                   |
Master_SSL_Key               |                                                                                   |
Seconds_Behind_Master        |                                                                                   |
Master_SSL_Verify_Server_Cert|No                                                                                 |
Last_IO_Errno                |0                                                                                  |
Last_IO_Error                |                                                                                   |
Last_SQL_Errno               |0                                                                                  |
Last_SQL_Error               |                                                                                   |
Replicate_Ignore_Server_Ids  |                                                                                   |
Master_Server_Id             |1                                                                                  |
Master_UUID                  |b7912f4d-a919-11ea-a3e9-0242ac1e0002                                               |
Master_Info_File             |mysql.slave_master_info                                                            |
SQL_Delay                    |0                                                                                  |
SQL_Remaining_Delay          |                                                                                   |
Slave_SQL_Running_State      |Slave has read all relay log; waiting for more updates                             |
Master_Retry_Count           |86400                                                                              |
Master_Bind                  |                                                                                   |
Last_IO_Error_Timestamp      |                                                                                   |
Last_SQL_Error_Timestamp     |                                                                                   |
Master_SSL_Crl               |                                                                                   |
Master_SSL_Crlpath           |                                                                                   |
Retrieved_Gtid_Set           |b7912f4d-a919-11ea-a3e9-0242ac1e0002:1-1485                                        |
Executed_Gtid_Set            |b7912f4d-a919-11ea-a3e9-0242ac1e0002:1-1485,¶b7dc7b13-a919-11ea-b5c4-0242ac1e0003:1|
Auto_Position                |1                                                                                  |
Replicate_Rewrite_DB         |                                                                                   |
Channel_Name                 |                                                                                   |
Master_TLS_Version           |                                                                                   |
Master_public_key_path       |                                                                                   |
Get_master_public_key        |0                                                                                  |
Network_Namespace            |                                                                                   |
```

```sql
SELECT count(*) from account a;
```

```text
Name    |Value|
--------|-----|
count(*)|1473 |
```

- Анализируем slave2

```sql
show slave status;
```

```text
Name                         |Value                                                                                                                                                |
-----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
Slave_IO_State               |Reconnecting after a failed master event read                                                                                                        |
Master_Host                  |172.30.0.2                                                                                                                                           |
Master_User                  |sn_slave_user                                                                                                                                        |
Master_Port                  |3306                                                                                                                                                 |
Connect_Retry                |60                                                                                                                                                   |
Master_Log_File              |mysql-bin.000003                                                                                                                                     |
Read_Master_Log_Pos          |972162                                                                                                                                               |
Relay_Log_File               |mysql-relay-bin.000003                                                                                                                               |
Relay_Log_Pos                |972376                                                                                                                                               |
Relay_Master_Log_File        |mysql-bin.000003                                                                                                                                     |
Slave_IO_Running             |Connecting                                                                                                                                           |
Slave_SQL_Running            |Yes                                                                                                                                                  |
Replicate_Do_DB              |                                                                                                                                                     |
Replicate_Ignore_DB          |                                                                                                                                                     |
Replicate_Do_Table           |                                                                                                                                                     |
Replicate_Ignore_Table       |                                                                                                                                                     |
Replicate_Wild_Do_Table      |                                                                                                                                                     |
Replicate_Wild_Ignore_Table  |                                                                                                                                                     |
Last_Errno                   |0                                                                                                                                                    |
Last_Error                   |                                                                                                                                                     |
Skip_Counter                 |0                                                                                                                                                    |
Exec_Master_Log_Pos          |972162                                                                                                                                               |
Relay_Log_Space              |972993                                                                                                                                               |
Until_Condition              |None                                                                                                                                                 |
Until_Log_File               |                                                                                                                                                     |
Until_Log_Pos                |0                                                                                                                                                    |
Master_SSL_Allowed           |No                                                                                                                                                   |
Master_SSL_CA_File           |                                                                                                                                                     |
Master_SSL_CA_Path           |                                                                                                                                                     |
Master_SSL_Cert              |                                                                                                                                                     |
Master_SSL_Cipher            |                                                                                                                                                     |
Master_SSL_Key               |                                                                                                                                                     |
Seconds_Behind_Master        |                                                                                                                                                     |
Master_SSL_Verify_Server_Cert|No                                                                                                                                                   |
Last_IO_Errno                |2003                                                                                                                                                 |
Last_IO_Error                |error reconnecting to master 'sn_slave_user@172.30.0.2:3306' - retry-time: 60 retries: 2 message: Can't connect to MySQL server on '172.30.0.2' (113)|
Last_SQL_Errno               |0                                                                                                                                                    |
Last_SQL_Error               |                                                                                                                                                     |
Replicate_Ignore_Server_Ids  |                                                                                                                                                     |
Master_Server_Id             |1                                                                                                                                                    |
Master_UUID                  |b7912f4d-a919-11ea-a3e9-0242ac1e0002                                                                                                                 |
Master_Info_File             |mysql.slave_master_info                                                                                                                              |
SQL_Delay                    |0                                                                                                                                                    |
SQL_Remaining_Delay          |                                                                                                                                                     |
Slave_SQL_Running_State      |Slave has read all relay log; waiting for more updates                                                                                               |
Master_Retry_Count           |86400                                                                                                                                                |
Master_Bind                  |                                                                                                                                                     |
Last_IO_Error_Timestamp      |200607 23:56:01                                                                                                                                      |
Last_SQL_Error_Timestamp     |                                                                                                                                                     |
Master_SSL_Crl               |                                                                                                                                                     |
Master_SSL_Crlpath           |                                                                                                                                                     |
Retrieved_Gtid_Set           |b7912f4d-a919-11ea-a3e9-0242ac1e0002:1-2131                                                                                                          |
Executed_Gtid_Set            |b7912f4d-a919-11ea-a3e9-0242ac1e0002:1-2131,¶b82bd549-a919-11ea-901c-0242ac1e0004:1                                                                  |
Auto_Position                |1                                                                                                                                                    |
Replicate_Rewrite_DB         |                                                                                                                                                     |
Channel_Name                 |                                                                                                                                                     |
Master_TLS_Version           |                                                                                                                                                     |
Master_public_key_path       |                                                                                                                                                     |
Get_master_public_key        |0                                                                                                                                                    |
Network_Namespace            |                                                                                                                                                     |
```

```sql
SELECT count(*) from account a;
```

```text
Name    |Value|
--------|-----|
count(*)|2119 |
```

Принимаем решение промоутить slave2 до мастера.

- Промоутим slave2 до мастера

Повторяем шаги 3 - 4 по настройке мастера , но уже для slave2:

```sql
STOP SLAVE;

INSTALL PLUGIN rpl_semi_sync_master SONAME 'semisync_master.so';
SET GLOBAL rpl_semi_sync_master_enabled = 1;
SHOW VARIABLES LIKE 'rpl_semi_sync%';

create user 'sn_slave_user'@'%' identified by 'sn_slave_pwd';
GRANT REPLICATION SLAVE ON *.* TO 'sn_slave_user'@'%';
FLUSH PRIVILEGES;

SHOW MASTER STATUS;
```

В результате:
```text
Name             |Value                                                                                |
-----------------|-------------------------------------------------------------------------------------|
File             |mysql-bin.000003                                                                     |
Position         |977401                                                                               |
Binlog_Do_DB     |sn                                                                                   |
Binlog_Ignore_DB |                                                                                     |
Executed_Gtid_Set|b7912f4d-a919-11ea-a3e9-0242ac1e0002:1-2131,¶b82bd549-a919-11ea-901c-0242ac1e0004:1-4|
```

- Переключаем slave1 на новый мастер:

```sql
STOP SLAVE; 
CHANGE MASTER TO MASTER_HOST='172.30.0.4',MASTER_USER='sn_slave_user',MASTER_PASSWORD='sn_slave_pwd',MASTER_AUTO_POSITION = 1;
START SLAVE;
```

- Анализируем slave1:

```sql
show slave status;
```

```text
Name                         |Value                                                                                                                        |
-----------------------------|-----------------------------------------------------------------------------------------------------------------------------|
Slave_IO_State               |Waiting for master to send event                                                                                             |
Master_Host                  |172.30.0.4                                                                                                                   |
Master_User                  |sn_slave_user                                                                                                                |
Master_Port                  |3306                                                                                                                         |
Connect_Retry                |60                                                                                                                           |
Master_Log_File              |mysql-bin.000003                                                                                                             |
Read_Master_Log_Pos          |977401                                                                                                                       |
Relay_Log_File               |mysql-relay-bin.000003                                                                                                       |
Relay_Log_Pos                |297903                                                                                                                       |
Relay_Master_Log_File        |mysql-bin.000003                                                                                                             |
Slave_IO_Running             |Yes                                                                                                                          |
Slave_SQL_Running            |Yes                                                                                                                          |
Replicate_Do_DB              |                                                                                                                             |
Replicate_Ignore_DB          |                                                                                                                             |
Replicate_Do_Table           |                                                                                                                             |
Replicate_Ignore_Table       |                                                                                                                             |
Replicate_Wild_Do_Table      |                                                                                                                             |
Replicate_Wild_Ignore_Table  |                                                                                                                             |
Last_Errno                   |0                                                                                                                            |
Last_Error                   |                                                                                                                             |
Skip_Counter                 |0                                                                                                                            |
Exec_Master_Log_Pos          |977401                                                                                                                       |
Relay_Log_Space              |298520                                                                                                                       |
Until_Condition              |None                                                                                                                         |
Until_Log_File               |                                                                                                                             |
Until_Log_Pos                |0                                                                                                                            |
Master_SSL_Allowed           |No                                                                                                                           |
Master_SSL_CA_File           |                                                                                                                             |
Master_SSL_CA_Path           |                                                                                                                             |
Master_SSL_Cert              |                                                                                                                             |
Master_SSL_Cipher            |                                                                                                                             |
Master_SSL_Key               |                                                                                                                             |
Seconds_Behind_Master        |0                                                                                                                            |
Master_SSL_Verify_Server_Cert|No                                                                                                                           |
Last_IO_Errno                |0                                                                                                                            |
Last_IO_Error                |                                                                                                                             |
Last_SQL_Errno               |0                                                                                                                            |
Last_SQL_Error               |                                                                                                                             |
Replicate_Ignore_Server_Ids  |                                                                                                                             |
Master_Server_Id             |3                                                                                                                            |
Master_UUID                  |b82bd549-a919-11ea-901c-0242ac1e0004                                                                                         |
Master_Info_File             |mysql.slave_master_info                                                                                                      |
SQL_Delay                    |0                                                                                                                            |
SQL_Remaining_Delay          |                                                                                                                             |
Slave_SQL_Running_State      |Slave has read all relay log; waiting for more updates                                                                       |
Master_Retry_Count           |86400                                                                                                                        |
Master_Bind                  |                                                                                                                             |
Last_IO_Error_Timestamp      |                                                                                                                             |
Last_SQL_Error_Timestamp     |                                                                                                                             |
Master_SSL_Crl               |                                                                                                                             |
Master_SSL_Crlpath           |                                                                                                                             |
Retrieved_Gtid_Set           |b7912f4d-a919-11ea-a3e9-0242ac1e0002:1486-2131,¶b82bd549-a919-11ea-901c-0242ac1e0004:1-4                                     |
Executed_Gtid_Set            |b7912f4d-a919-11ea-a3e9-0242ac1e0002:1-2131,¶b7dc7b13-a919-11ea-b5c4-0242ac1e0003:1,¶b82bd549-a919-11ea-901c-0242ac1e0004:1-4|
Auto_Position                |1                                                                                                                            |
Replicate_Rewrite_DB         |                                                                                                                             |
Channel_Name                 |                                                                                                                             |
Master_TLS_Version           |                                                                                                                             |
Master_public_key_path       |                                                                                                                             |
Get_master_public_key        |0                                                                                                                            |
Network_Namespace            |                                                                                                                             |
```

```sql
SELECT count(*) from account a;
```

```text
Name    |Value|
--------|-----|
count(*)|2119 |
```

Итого: данные реплицировались в полном объеме. 