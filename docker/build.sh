#!/bin/bash

docker-compose down
docker-compose build
docker-compose up -d

until docker exec mysql_master sh -c 'export MYSQL_PWD=passw0rd; mysql -u root -e ";"'
do
    echo "Waiting for mysql_master database connection..."
    sleep 4
done

echo "MASTER:"
semi_sync_master='INSTALL PLUGIN rpl_semi_sync_master SONAME "semisync_master.so";SET GLOBAL rpl_semi_sync_master_enabled = 1;SHOW VARIABLES LIKE "rpl_semi_sync%";'
docker exec mysql_master sh -c "export MYSQL_PWD=passw0rd; mysql -u root -e '$semi_sync_master'"

priv_stmt='create user "sn_slave_user"@"%" identified by "sn_slave_pwd"; GRANT REPLICATION SLAVE ON *.* TO "sn_slave_user"@"%"; FLUSH PRIVILEGES;'
docker exec mysql_master sh -c "export MYSQL_PWD=passw0rd; mysql -u root -e '$priv_stmt'"

until docker-compose exec mysql_slave_1 sh -c 'export MYSQL_PWD=passw0rd; mysql -u root -e ";"'
do
    echo "Waiting for mysql_slave_1 database connection..."
    sleep 4
done

docker-ip() {
    docker inspect --format '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' "$@"
}

semi_sync_slave='INSTALL PLUGIN rpl_semi_sync_slave SONAME "semisync_slave.so";SET GLOBAL rpl_semi_sync_slave_enabled = 1;SHOW VARIABLES LIKE "rpl_semi_sync%";'
start_slave_stmt="CHANGE MASTER TO MASTER_HOST='$(docker-ip mysql_master)',MASTER_USER='sn_slave_user',MASTER_PASSWORD='sn_slave_pwd',MASTER_AUTO_POSITION = 1; START SLAVE;"
start_slave_cmd='export MYSQL_PWD=passw0rd; mysql -u root -e "'
start_slave_cmd+="$start_slave_stmt"
start_slave_cmd+='"'

echo "SLAVE 1:"
docker exec mysql_slave_1 sh -c "export MYSQL_PWD=passw0rd; mysql -u root -e '$semi_sync_slave'"
docker exec mysql_slave_1 sh -c "$start_slave_cmd"
docker exec mysql_slave_1 sh -c "export MYSQL_PWD=passw0rd; mysql -u root -e 'SHOW SLAVE STATUS \G'"


until docker-compose exec mysql_slave_2 sh -c 'export MYSQL_PWD=passw0rd; mysql -u root -e ";"'
do
    echo "Waiting for mysql_slave_2 database connection..."
    sleep 4
done

echo "SLAVE 2:"
docker exec mysql_slave_2 sh -c "export MYSQL_PWD=passw0rd; mysql -u root -e '$semi_sync_slave'"
docker exec mysql_slave_2 sh -c "$start_slave_cmd"
docker exec mysql_slave_2 sh -c "export MYSQL_PWD=passw0rd; mysql -u root -e 'SHOW SLAVE STATUS \G'"