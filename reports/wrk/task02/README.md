# Результаты нагрузочного тестирования
Таблица account содержит 1 000 000 записей.

Для тестирования используется утилита wrk2 (https://github.com/giltene/wrk2). 
Запросы при тестировании формируются динамически, в соответствии с файлом scripts/script.lua

Для построения графиков используется: http://hdrhistogram.github.io/HdrHistogram/plotFiles.html

<details>
  <summary>Железо и настройки</summary>
  
  **БД - mysql (докер образ), версия 8.0.19**
  
  <details>
    <summary>show variables</summary>
    
| Variable\_name | Value |
| :--- | :--- |
| activate\_all\_roles\_on\_login | OFF |
| admin\_address |  |
| admin\_port | 33062 |
| auto\_generate\_certs | ON |
| auto\_increment\_increment | 1 |
| auto\_increment\_offset | 1 |
| autocommit | ON |
| automatic\_sp\_privileges | ON |
| avoid\_temporal\_upgrade | OFF |
| back\_log | 151 |
| basedir | /usr/ |
| big\_tables | OFF |
| bind\_address | \* |
| binlog\_cache\_size | 32768 |
| binlog\_checksum | CRC32 |
| binlog\_direct\_non\_transactional\_updates | OFF |
| binlog\_encryption | OFF |
| binlog\_error\_action | ABORT\_SERVER |
| binlog\_expire\_logs\_seconds | 2592000 |
| binlog\_format | ROW |
| binlog\_group\_commit\_sync\_delay | 0 |
| binlog\_group\_commit\_sync\_no\_delay\_count | 0 |
| binlog\_gtid\_simple\_recovery | ON |
| binlog\_max\_flush\_queue\_time | 0 |
| binlog\_order\_commits | ON |
| binlog\_rotate\_encryption\_master\_key\_at\_startup | OFF |
| binlog\_row\_event\_max\_size | 8192 |
| binlog\_row\_image | FULL |
| binlog\_row\_metadata | MINIMAL |
| binlog\_row\_value\_options |  |
| binlog\_rows\_query\_log\_events | OFF |
| binlog\_stmt\_cache\_size | 32768 |
| binlog\_transaction\_dependency\_history\_size | 25000 |
| binlog\_transaction\_dependency\_tracking | COMMIT\_ORDER |
| block\_encryption\_mode | aes-128-ecb |
| bulk\_insert\_buffer\_size | 8388608 |
| caching\_sha2\_password\_auto\_generate\_rsa\_keys | ON |
| caching\_sha2\_password\_private\_key\_path | private\_key.pem |
| caching\_sha2\_password\_public\_key\_path | public\_key.pem |
| character\_set\_client | utf8mb4 |
| character\_set\_connection | utf8mb4 |
| character\_set\_database | utf8mb4 |
| character\_set\_filesystem | binary |
| character\_set\_results | utf8mb4 |
| character\_set\_server | utf8mb4 |
| character\_set\_system | utf8 |
| character\_sets\_dir | /usr/share/mysql-8.0/charsets/ |
| check\_proxy\_users | OFF |
| collation\_connection | utf8mb4\_0900\_ai\_ci |
| collation\_database | utf8mb4\_0900\_ai\_ci |
| collation\_server | utf8mb4\_0900\_ai\_ci |
| completion\_type | NO\_CHAIN |
| concurrent\_insert | AUTO |
| connect\_timeout | 10 |
| core\_file | OFF |
| create\_admin\_listener\_thread | OFF |
| cte\_max\_recursion\_depth | 1000 |
| datadir | /var/lib/mysql/ |
| default\_authentication\_plugin | mysql\_native\_password |
| default\_collation\_for\_utf8mb4 | utf8mb4\_0900\_ai\_ci |
| default\_password\_lifetime | 0 |
| default\_storage\_engine | InnoDB |
| default\_table\_encryption | OFF |
| default\_tmp\_storage\_engine | InnoDB |
| default\_week\_format | 0 |
| delay\_key\_write | ON |
| delayed\_insert\_limit | 100 |
| delayed\_insert\_timeout | 300 |
| delayed\_queue\_size | 1000 |
| disabled\_storage\_engines |  |
| disconnect\_on\_expired\_password | ON |
| div\_precision\_increment | 4 |
| end\_markers\_in\_json | OFF |
| enforce\_gtid\_consistency | OFF |
| eq\_range\_index\_dive\_limit | 200 |
| error\_count | 0 |
| event\_scheduler | ON |
| expire\_logs\_days | 0 |
| explicit\_defaults\_for\_timestamp | ON |
| external\_user |  |
| flush | OFF |
| flush\_time | 0 |
| foreign\_key\_checks | ON |
| ft\_boolean\_syntax | + -&gt;&lt;\(\)\~\*:""&\| |
| ft\_max\_word\_len | 84 |
| ft\_min\_word\_len | 4 |
| ft\_query\_expansion\_limit | 20 |
| ft\_stopword\_file | \(built-in\) |
| general\_log | OFF |
| general\_log\_file | /var/lib/mysql/d8ddf409345d.log |
| generated\_random\_password\_length | 20 |
| group\_concat\_max\_len | 1024 |
| group\_replication\_consistency | EVENTUAL |
| gtid\_executed |  |
| gtid\_executed\_compression\_period | 1000 |
| gtid\_mode | OFF |
| gtid\_next | AUTOMATIC |
| gtid\_owned |  |
| gtid\_purged |  |
| have\_compress | YES |
| have\_dynamic\_loading | YES |
| have\_geometry | YES |
| have\_openssl | YES |
| have\_profiling | YES |
| have\_query\_cache | NO |
| have\_rtree\_keys | YES |
| have\_ssl | YES |
| have\_statement\_timeout | YES |
| have\_symlink | DISABLED |
| histogram\_generation\_max\_mem\_size | 20000000 |
| host\_cache\_size | 279 |
| hostname | d8ddf409345d |
| identity | 0 |
| immediate\_server\_version | 999999 |
| information\_schema\_stats\_expiry | 86400 |
| init\_connect |  |
| init\_file |  |
| init\_slave |  |
| innodb\_adaptive\_flushing | ON |
| innodb\_adaptive\_flushing\_lwm | 10 |
| innodb\_adaptive\_hash\_index | ON |
| innodb\_adaptive\_hash\_index\_parts | 8 |
| innodb\_adaptive\_max\_sleep\_delay | 150000 |
| innodb\_api\_bk\_commit\_interval | 5 |
| innodb\_api\_disable\_rowlock | OFF |
| innodb\_api\_enable\_binlog | OFF |
| innodb\_api\_enable\_mdl | OFF |
| innodb\_api\_trx\_level | 0 |
| innodb\_autoextend\_increment | 64 |
| innodb\_autoinc\_lock\_mode | 2 |
| innodb\_buffer\_pool\_chunk\_size | 134217728 |
| innodb\_buffer\_pool\_dump\_at\_shutdown | ON |
| innodb\_buffer\_pool\_dump\_now | OFF |
| innodb\_buffer\_pool\_dump\_pct | 25 |
| innodb\_buffer\_pool\_filename | ib\_buffer\_pool |
| innodb\_buffer\_pool\_in\_core\_file | ON |
| innodb\_buffer\_pool\_instances | 1 |
| innodb\_buffer\_pool\_load\_abort | OFF |
| innodb\_buffer\_pool\_load\_at\_startup | ON |
| innodb\_buffer\_pool\_load\_now | OFF |
| innodb\_buffer\_pool\_size | 134217728 |
| innodb\_change\_buffer\_max\_size | 25 |
| innodb\_change\_buffering | all |
| innodb\_checksum\_algorithm | crc32 |
| innodb\_cmp\_per\_index\_enabled | OFF |
| innodb\_commit\_concurrency | 0 |
| innodb\_compression\_failure\_threshold\_pct | 5 |
| innodb\_compression\_level | 6 |
| innodb\_compression\_pad\_pct\_max | 50 |
| innodb\_concurrency\_tickets | 5000 |
| innodb\_data\_file\_path | ibdata1:12M:autoextend |
| innodb\_data\_home\_dir |  |
| innodb\_deadlock\_detect | ON |
| innodb\_dedicated\_server | OFF |
| innodb\_default\_row\_format | dynamic |
| innodb\_directories |  |
| innodb\_disable\_sort\_file\_cache | OFF |
| innodb\_doublewrite | ON |
| innodb\_fast\_shutdown | 1 |
| innodb\_file\_per\_table | ON |
| innodb\_fill\_factor | 100 |
| innodb\_flush\_log\_at\_timeout | 1 |
| innodb\_flush\_log\_at\_trx\_commit | 1 |
| innodb\_flush\_method | fsync |
| innodb\_flush\_neighbors | 0 |
| innodb\_flush\_sync | ON |
| innodb\_flushing\_avg\_loops | 30 |
| innodb\_force\_load\_corrupted | OFF |
| innodb\_force\_recovery | 0 |
| innodb\_fsync\_threshold | 0 |
| innodb\_ft\_aux\_table |  |
| innodb\_ft\_cache\_size | 8000000 |
| innodb\_ft\_enable\_diag\_print | OFF |
| innodb\_ft\_enable\_stopword | ON |
| innodb\_ft\_max\_token\_size | 84 |
| innodb\_ft\_min\_token\_size | 3 |
| innodb\_ft\_num\_word\_optimize | 2000 |
| innodb\_ft\_result\_cache\_limit | 2000000000 |
| innodb\_ft\_server\_stopword\_table |  |
| innodb\_ft\_sort\_pll\_degree | 2 |
| innodb\_ft\_total\_cache\_size | 640000000 |
| innodb\_ft\_user\_stopword\_table |  |
| innodb\_idle\_flush\_pct | 100 |
| innodb\_io\_capacity | 200 |
| innodb\_io\_capacity\_max | 2000 |
| innodb\_lock\_wait\_timeout | 50 |
| innodb\_log\_buffer\_size | 16777216 |
| innodb\_log\_checksums | ON |
| innodb\_log\_compressed\_pages | ON |
| innodb\_log\_file\_size | 50331648 |
| innodb\_log\_files\_in\_group | 2 |
| innodb\_log\_group\_home\_dir | ./ |
| innodb\_log\_spin\_cpu\_abs\_lwm | 80 |
| innodb\_log\_spin\_cpu\_pct\_hwm | 50 |
| innodb\_log\_wait\_for\_flush\_spin\_hwm | 400 |
| innodb\_log\_write\_ahead\_size | 8192 |
| innodb\_lru\_scan\_depth | 1024 |
| innodb\_max\_dirty\_pages\_pct | 90.000000 |
| innodb\_max\_dirty\_pages\_pct\_lwm | 10.000000 |
| innodb\_max\_purge\_lag | 0 |
| innodb\_max\_purge\_lag\_delay | 0 |
| innodb\_max\_undo\_log\_size | 1073741824 |
| innodb\_monitor\_disable |  |
| innodb\_monitor\_enable |  |
| innodb\_monitor\_reset |  |
| innodb\_monitor\_reset\_all |  |
| innodb\_numa\_interleave | OFF |
| innodb\_old\_blocks\_pct | 37 |
| innodb\_old\_blocks\_time | 1000 |
| innodb\_online\_alter\_log\_max\_size | 134217728 |
| innodb\_open\_files | 4000 |
| innodb\_optimize\_fulltext\_only | OFF |
| innodb\_page\_cleaners | 1 |
| innodb\_page\_size | 16384 |
| innodb\_parallel\_read\_threads | 4 |
| innodb\_print\_all\_deadlocks | OFF |
| innodb\_print\_ddl\_logs | OFF |
| innodb\_purge\_batch\_size | 300 |
| innodb\_purge\_rseg\_truncate\_frequency | 128 |
| innodb\_purge\_threads | 4 |
| innodb\_random\_read\_ahead | OFF |
| innodb\_read\_ahead\_threshold | 56 |
| innodb\_read\_io\_threads | 4 |
| innodb\_read\_only | OFF |
| innodb\_redo\_log\_archive\_dirs |  |
| innodb\_redo\_log\_encrypt | OFF |
| innodb\_replication\_delay | 0 |
| innodb\_rollback\_on\_timeout | OFF |
| innodb\_rollback\_segments | 128 |
| innodb\_sort\_buffer\_size | 1048576 |
| innodb\_spin\_wait\_delay | 6 |
| innodb\_spin\_wait\_pause\_multiplier | 50 |
| innodb\_stats\_auto\_recalc | ON |
| innodb\_stats\_include\_delete\_marked | OFF |
| innodb\_stats\_method | nulls\_equal |
| innodb\_stats\_on\_metadata | OFF |
| innodb\_stats\_persistent | ON |
| innodb\_stats\_persistent\_sample\_pages | 20 |
| innodb\_stats\_transient\_sample\_pages | 8 |
| innodb\_status\_output | OFF |
| innodb\_status\_output\_locks | OFF |
| innodb\_strict\_mode | ON |
| innodb\_sync\_array\_size | 1 |
| innodb\_sync\_spin\_loops | 30 |
| innodb\_table\_locks | ON |
| innodb\_temp\_data\_file\_path | ibtmp1:12M:autoextend |
| innodb\_temp\_tablespaces\_dir | ./#innodb\_temp/ |
| innodb\_thread\_concurrency | 0 |
| innodb\_thread\_sleep\_delay | 10000 |
| innodb\_tmpdir |  |
| innodb\_undo\_directory | ./ |
| innodb\_undo\_log\_encrypt | OFF |
| innodb\_undo\_log\_truncate | ON |
| innodb\_undo\_tablespaces | 2 |
| innodb\_use\_native\_aio | ON |
| innodb\_version | 8.0.19 |
| innodb\_write\_io\_threads | 4 |
| insert\_id | 0 |
| interactive\_timeout | 28800 |
| internal\_tmp\_mem\_storage\_engine | TempTable |
| join\_buffer\_size | 262144 |
| keep\_files\_on\_create | OFF |
| key\_buffer\_size | 8388608 |
| key\_cache\_age\_threshold | 300 |
| key\_cache\_block\_size | 1024 |
| key\_cache\_division\_limit | 100 |
| keyring\_operations | ON |
| large\_files\_support | ON |
| large\_page\_size | 0 |
| large\_pages | OFF |
| last\_insert\_id | 0 |
| lc\_messages | en\_US |
| lc\_messages\_dir | /usr/share/mysql-8.0/ |
| lc\_time\_names | en\_US |
| license | GPL |
| local\_infile | OFF |
| lock\_wait\_timeout | 31536000 |
| locked\_in\_memory | OFF |
| log\_bin | ON |
| log\_bin\_basename | /var/lib/mysql/binlog |
| log\_bin\_index | /var/lib/mysql/binlog.index |
| log\_bin\_trust\_function\_creators | OFF |
| log\_bin\_use\_v1\_row\_events | OFF |
| log\_error | stderr |
| log\_error\_services | log\_filter\_internal; log\_sink\_internal |
| log\_error\_suppression\_list |  |
| log\_error\_verbosity | 2 |
| log\_output | FILE |
| log\_queries\_not\_using\_indexes | OFF |
| log\_raw | OFF |
| log\_slave\_updates | ON |
| log\_slow\_admin\_statements | OFF |
| log\_slow\_extra | OFF |
| log\_slow\_slave\_statements | OFF |
| log\_statements\_unsafe\_for\_binlog | ON |
| log\_throttle\_queries\_not\_using\_indexes | 0 |
| log\_timestamps | UTC |
| long\_query\_time | 10.000000 |
| low\_priority\_updates | OFF |
| lower\_case\_file\_system | OFF |
| lower\_case\_table\_names | 0 |
| mandatory\_roles |  |
| master\_info\_repository | TABLE |
| master\_verify\_checksum | OFF |
| max\_allowed\_packet | 67108864 |
| max\_binlog\_cache\_size | 18446744073709547520 |
| max\_binlog\_size | 1073741824 |
| max\_binlog\_stmt\_cache\_size | 18446744073709547520 |
| max\_connect\_errors | 100 |
| max\_connections | 151 |
| max\_delayed\_threads | 20 |
| max\_digest\_length | 1024 |
| max\_error\_count | 1024 |
| max\_execution\_time | 0 |
| max\_heap\_table\_size | 16777216 |
| max\_insert\_delayed\_threads | 20 |
| max\_join\_size | 18446744073709551615 |
| max\_length\_for\_sort\_data | 4096 |
| max\_points\_in\_geometry | 65536 |
| max\_prepared\_stmt\_count | 16382 |
| max\_relay\_log\_size | 0 |
| max\_seeks\_for\_key | 18446744073709551615 |
| max\_sort\_length | 1024 |
| max\_sp\_recursion\_depth | 0 |
| max\_user\_connections | 0 |
| max\_write\_lock\_count | 18446744073709551615 |
| min\_examined\_row\_limit | 0 |
| myisam\_data\_pointer\_size | 6 |
| myisam\_max\_sort\_file\_size | 9223372036853727232 |
| myisam\_mmap\_size | 18446744073709551615 |
| myisam\_recover\_options | OFF |
| myisam\_repair\_threads | 1 |
| myisam\_sort\_buffer\_size | 8388608 |
| myisam\_stats\_method | nulls\_unequal |
| myisam\_use\_mmap | OFF |
| mysql\_native\_password\_proxy\_users | OFF |
| mysqlx\_bind\_address | \* |
| mysqlx\_compression\_algorithms | DEFLATE\_STREAM,LZ4\_MESSAGE,ZSTD\_STREAM |
| mysqlx\_connect\_timeout | 30 |
| mysqlx\_document\_id\_unique\_prefix | 0 |
| mysqlx\_enable\_hello\_notice | ON |
| mysqlx\_idle\_worker\_thread\_timeout | 60 |
| mysqlx\_interactive\_timeout | 28800 |
| mysqlx\_max\_allowed\_packet | 67108864 |
| mysqlx\_max\_connections | 100 |
| mysqlx\_min\_worker\_threads | 2 |
| mysqlx\_port | 33060 |
| mysqlx\_port\_open\_timeout | 0 |
| mysqlx\_read\_timeout | 30 |
| mysqlx\_socket | /var/run/mysqld/mysqlx.sock |
| mysqlx\_ssl\_ca |  |
| mysqlx\_ssl\_capath |  |
| mysqlx\_ssl\_cert |  |
| mysqlx\_ssl\_cipher |  |
| mysqlx\_ssl\_crl |  |
| mysqlx\_ssl\_crlpath |  |
| mysqlx\_ssl\_key |  |
| mysqlx\_wait\_timeout | 28800 |
| mysqlx\_write\_timeout | 60 |
| net\_buffer\_length | 16384 |
| net\_read\_timeout | 30 |
| net\_retry\_count | 10 |
| net\_write\_timeout | 600 |
| new | OFF |
| ngram\_token\_size | 2 |
| offline\_mode | OFF |
| old | OFF |
| old\_alter\_table | OFF |
| open\_files\_limit | 1048576 |
| optimizer\_prune\_level | 1 |
| optimizer\_search\_depth | 62 |
| optimizer\_switch | index\_merge=on,index\_merge\_union=on,index\_merge\_sort\_union=on,index\_merge\_intersection=on,engine\_condition\_pushdown=on,index\_condition\_pushdown=on,mrr=on,mrr\_cost\_based=on,block\_nested\_loop=on,batched\_key\_access=off,materialization=on,semijoin=on,loosescan=on,firstmatch=on,duplicateweedout=on,subquery\_materialization\_cost\_based=on,use\_index\_extensions=on,condition\_fanout\_filter=on,derived\_merge=on,use\_invisible\_indexes=off,skip\_scan=on,hash\_join=on |
| optimizer\_trace | enabled=off,one\_line=off |
| optimizer\_trace\_features | greedy\_search=on,range\_optimizer=on,dynamic\_range=on,repeated\_subselect=on |
| optimizer\_trace\_limit | 1 |
| optimizer\_trace\_max\_mem\_size | 1048576 |
| optimizer\_trace\_offset | -1 |
| original\_commit\_timestamp | 36028797018963968 |
| original\_server\_version | 999999 |
| parser\_max\_mem\_size | 18446744073709551615 |
| partial\_revokes | OFF |
| password\_history | 0 |
| password\_require\_current | OFF |
| password\_reuse\_interval | 0 |
| performance\_schema | ON |
| performance\_schema\_accounts\_size | -1 |
| performance\_schema\_digests\_size | 10000 |
| performance\_schema\_error\_size | 4641 |
| performance\_schema\_events\_stages\_history\_long\_size | 10000 |
| performance\_schema\_events\_stages\_history\_size | 10 |
| performance\_schema\_events\_statements\_history\_long\_size | 10000 |
| performance\_schema\_events\_statements\_history\_size | 10 |
| performance\_schema\_events\_transactions\_history\_long\_size | 10000 |
| performance\_schema\_events\_transactions\_history\_size | 10 |
| performance\_schema\_events\_waits\_history\_long\_size | 10000 |
| performance\_schema\_events\_waits\_history\_size | 10 |
| performance\_schema\_hosts\_size | -1 |
| performance\_schema\_max\_cond\_classes | 100 |
| performance\_schema\_max\_cond\_instances | -1 |
| performance\_schema\_max\_digest\_length | 1024 |
| performance\_schema\_max\_digest\_sample\_age | 60 |
| performance\_schema\_max\_file\_classes | 80 |
| performance\_schema\_max\_file\_handles | 32768 |
| performance\_schema\_max\_file\_instances | -1 |
| performance\_schema\_max\_index\_stat | -1 |
| performance\_schema\_max\_memory\_classes | 450 |
| performance\_schema\_max\_metadata\_locks | -1 |
| performance\_schema\_max\_mutex\_classes | 300 |
| performance\_schema\_max\_mutex\_instances | -1 |
| performance\_schema\_max\_prepared\_statements\_instances | -1 |
| performance\_schema\_max\_program\_instances | -1 |
| performance\_schema\_max\_rwlock\_classes | 60 |
| performance\_schema\_max\_rwlock\_instances | -1 |
| performance\_schema\_max\_socket\_classes | 10 |
| performance\_schema\_max\_socket\_instances | -1 |
| performance\_schema\_max\_sql\_text\_length | 1024 |
| performance\_schema\_max\_stage\_classes | 175 |
| performance\_schema\_max\_statement\_classes | 218 |
| performance\_schema\_max\_statement\_stack | 10 |
| performance\_schema\_max\_table\_handles | -1 |
| performance\_schema\_max\_table\_instances | -1 |
| performance\_schema\_max\_table\_lock\_stat | -1 |
| performance\_schema\_max\_thread\_classes | 100 |
| performance\_schema\_max\_thread\_instances | -1 |
| performance\_schema\_session\_connect\_attrs\_size | 512 |
| performance\_schema\_setup\_actors\_size | -1 |
| performance\_schema\_setup\_objects\_size | -1 |
| performance\_schema\_users\_size | -1 |
| persist\_only\_admin\_x509\_subject |  |
| persisted\_globals\_load | ON |
| pid\_file | /var/run/mysqld/mysqld.pid |
| plugin\_dir | /usr/lib/mysql/plugin/ |
| port | 3306 |
| preload\_buffer\_size | 32768 |
| print\_identified\_with\_as\_hex | OFF |
| profiling | OFF |
| profiling\_history\_size | 15 |
| protocol\_compression\_algorithms | zlib,zstd,uncompressed |
| protocol\_version | 10 |
| proxy\_user |  |
| pseudo\_slave\_mode | OFF |
| pseudo\_thread\_id | 960 |
| query\_alloc\_block\_size | 8192 |
| query\_prealloc\_size | 8192 |
| rand\_seed1 | 0 |
| rand\_seed2 | 0 |
| range\_alloc\_block\_size | 4096 |
| range\_optimizer\_max\_mem\_size | 8388608 |
| rbr\_exec\_mode | STRICT |
| read\_buffer\_size | 131072 |
| read\_only | OFF |
| read\_rnd\_buffer\_size | 262144 |
| regexp\_stack\_limit | 8000000 |
| regexp\_time\_limit | 32 |
| relay\_log | d8ddf409345d-relay-bin |
| relay\_log\_basename | /var/lib/mysql/d8ddf409345d-relay-bin |
| relay\_log\_index | /var/lib/mysql/d8ddf409345d-relay-bin.index |
| relay\_log\_info\_file | relay-log.info |
| relay\_log\_info\_repository | TABLE |
| relay\_log\_purge | ON |
| relay\_log\_recovery | OFF |
| relay\_log\_space\_limit | 0 |
| report\_host |  |
| report\_password |  |
| report\_port | 3306 |
| report\_user |  |
| require\_row\_format | OFF |
| require\_secure\_transport | OFF |
| resultset\_metadata | FULL |
| rpl\_read\_size | 8192 |
| rpl\_stop\_slave\_timeout | 31536000 |
| schema\_definition\_cache | 256 |
| secondary\_engine\_cost\_threshold | 100000.000000 |
| secure\_file\_priv | /var/tmp/ |
| server\_id | 1 |
| server\_id\_bits | 32 |
| server\_uuid | 39ff07a4-4f65-11ea-9ccc-0242c0a88002 |
| session\_track\_gtids | OFF |
| session\_track\_schema | ON |
| session\_track\_state\_change | OFF |
| session\_track\_system\_variables | time\_zone,autocommit,character\_set\_client,character\_set\_results,character\_set\_connection |
| session\_track\_transaction\_info | OFF |
| sha256\_password\_auto\_generate\_rsa\_keys | ON |
| sha256\_password\_private\_key\_path | private\_key.pem |
| sha256\_password\_proxy\_users | OFF |
| sha256\_password\_public\_key\_path | public\_key.pem |
| show\_create\_table\_skip\_secondary\_engine | OFF |
| show\_create\_table\_verbosity | OFF |
| show\_old\_temporals | OFF |
| skip\_external\_locking | ON |
| skip\_name\_resolve | ON |
| skip\_networking | OFF |
| skip\_show\_database | OFF |
| slave\_allow\_batching | OFF |
| slave\_checkpoint\_group | 512 |
| slave\_checkpoint\_period | 300 |
| slave\_compressed\_protocol | OFF |
| slave\_exec\_mode | STRICT |
| slave\_load\_tmpdir | /tmp |
| slave\_max\_allowed\_packet | 1073741824 |
| slave\_net\_timeout | 60 |
| slave\_parallel\_type | DATABASE |
| slave\_parallel\_workers | 0 |
| slave\_pending\_jobs\_size\_max | 134217728 |
| slave\_preserve\_commit\_order | OFF |
| slave\_rows\_search\_algorithms | INDEX\_SCAN,HASH\_SCAN |
| slave\_skip\_errors | OFF |
| slave\_sql\_verify\_checksum | ON |
| slave\_transaction\_retries | 10 |
| slave\_type\_conversions |  |
| slow\_launch\_time | 2 |
| slow\_query\_log | OFF |
| slow\_query\_log\_file | /var/lib/mysql/d8ddf409345d-slow.log |
| socket | /var/run/mysqld/mysqld.sock |
| sort\_buffer\_size | 262144 |
| sql\_auto\_is\_null | OFF |
| sql\_big\_selects | ON |
| sql\_buffer\_result | OFF |
| sql\_log\_bin | ON |
| sql\_log\_off | OFF |
| sql\_mode | ONLY\_FULL\_GROUP\_BY,STRICT\_TRANS\_TABLES,NO\_ZERO\_IN\_DATE,NO\_ZERO\_DATE,ERROR\_FOR\_DIVISION\_BY\_ZERO,NO\_ENGINE\_SUBSTITUTION |
| sql\_notes | ON |
| sql\_quote\_show\_create | ON |
| sql\_require\_primary\_key | OFF |
| sql\_safe\_updates | OFF |
| sql\_select\_limit | 18446744073709551615 |
| sql\_slave\_skip\_counter | 0 |
| sql\_warnings | OFF |
| ssl\_ca | ca.pem |
| ssl\_capath |  |
| ssl\_cert | server-cert.pem |
| ssl\_cipher |  |
| ssl\_crl |  |
| ssl\_crlpath |  |
| ssl\_fips\_mode | OFF |
| ssl\_key | server-key.pem |
| stored\_program\_cache | 256 |
| stored\_program\_definition\_cache | 256 |
| super\_read\_only | OFF |
| sync\_binlog | 1 |
| sync\_master\_info | 10000 |
| sync\_relay\_log | 10000 |
| sync\_relay\_log\_info | 10000 |
| system\_time\_zone | UTC |
| table\_definition\_cache | 2000 |
| table\_encryption\_privilege\_check | OFF |
| table\_open\_cache | 4000 |
| table\_open\_cache\_instances | 16 |
| tablespace\_definition\_cache | 256 |
| temptable\_max\_ram | 1073741824 |
| temptable\_use\_mmap | ON |
| thread\_cache\_size | 9 |
| thread\_handling | one-thread-per-connection |
| thread\_stack | 286720 |
| time\_zone | SYSTEM |
| timestamp | 1581882387.287585 |
| tls\_ciphersuites |  |
| tls\_version | TLSv1,TLSv1.1,TLSv1.2 |
| tmp\_table\_size | 16777216 |
| tmpdir | /tmp |
| transaction\_alloc\_block\_size | 8192 |
| transaction\_allow\_batching | OFF |
| transaction\_isolation | REPEATABLE-READ |
| transaction\_prealloc\_size | 4096 |
| transaction\_read\_only | OFF |
| transaction\_write\_set\_extraction | XXHASH64 |
| unique\_checks | ON |
| updatable\_views\_with\_limit | YES |
| use\_secondary\_engine | ON |
| version | 8.0.19 |
| version\_comment | MySQL Community Server - GPL |
| version\_compile\_machine | x86\_64 |
| version\_compile\_os | Linux |
| version\_compile\_zlib | 1.2.11 |
| wait\_timeout | 28800 |
| warning\_count | 0 |
| windowing\_use\_high\_precision | ON |

  </details>
  
  **Web server Apache Tomcat**
  Заданы явно настройки:
  ```
  server:
    tomcat:
      # Максимально кол-во рабочих потоков
      max-threads: 200
      # Максимальное кол-во соединений, которые сервер принимает и обрабатывает
      max-connections: 10000
      # Максимальная длина очереди для входящих запросов
      accept-count: 100
      # Время ожидания обработки запроса
      connection-timeout: 20000
  ```
  **hikari connection pool**
  ```
  spring:
    datasource:      
      hikari:
        # время ожидания подключения из пула, мс
        connectionTimeout: 30000
        # Врем бездействия соединения в пуле, после которого оно удалится
        idleTimeout: 600000
        # Максимальное время соединения в пуле
        maxLifetime: 1800000
        # Минимальное кол-во незанятых соединений
        minimumIdle: 2
        # Максимальное число соединений в пуле
        maximumPoolSize: 100
  ```
  
  **top при запущенном сервере приложений и БД**
  ``` 
  top - 22:34:46 up  6:34,  0 users,  load average: 0.52, 0.58, 0.59
  Tasks:   6 total,   1 running,   5 sleeping,   0 stopped,   0 zombie
  %Cpu0  :  1.0 us,  3.5 sy,  0.0 ni, 93.2 id,  0.0 wa,  2.3 hi,  0.0 si,  0.0 st
  %Cpu1  :  2.6 us,  2.3 sy,  0.0 ni, 95.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu2  :  3.0 us,  4.0 sy,  0.0 ni, 93.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu3  :  0.7 us,  1.6 sy,  0.0 ni, 97.1 id,  0.0 wa,  0.7 hi,  0.0 si,  0.0 st
  %Cpu4  :  1.7 us,  3.6 sy,  0.0 ni, 94.7 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu5  :  0.0 us,  0.0 sy,  0.0 ni,100.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu6  :  0.7 us,  0.3 sy,  0.0 ni, 99.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu7  :  1.7 us,  1.3 sy,  0.0 ni, 97.0 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  KiB Mem :  8295652 total,  1088848 free,  6977452 used,   229352 buff/cache
  KiB Swap: 25165824 total, 23138684 free,  2027140 used.  1184468 avail Mem  
  ```

  **top при тестировании (wrk -c100 -t2 -d60s --latency -R100)**
  ```
  top - 22:36:49 up  6:36,  0 users,  load average: 0.52, 0.58, 0.59
  Tasks:   7 total,   1 running,   6 sleeping,   0 stopped,   0 zombie
  %Cpu0  :  3.6 us,  9.1 sy,  0.0 ni, 87.3 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu1  :  4.8 us,  6.6 sy,  0.0 ni, 88.6 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu2  :  3.0 us, 56.6 sy,  0.0 ni, 40.4 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu3  :  0.0 us, 40.6 sy,  0.0 ni, 59.4 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu4  :  3.6 us, 77.6 sy,  0.0 ni, 18.8 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu5  :  0.0 us,  4.8 sy,  0.0 ni, 95.2 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu6  :  6.0 us,  4.8 sy,  0.0 ni, 89.2 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  %Cpu7  :  1.8 us,  5.5 sy,  0.0 ni, 92.6 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
  KiB Mem :  8295652 total,  1005840 free,  7060460 used,   229352 buff/cache
  KiB Swap: 25165824 total, 23164024 free,  2001800 used.  1101460 avail Mem
  ```
</details>

## Тестирование функционала поиска анкет по префиксу имени и фамилии (таблица account без индексов на полях first_name и last_name)

#### Индексы

```show indexes FROM account;```

| Table | Non\_unique | Key\_name | Seq\_in\_index | Column\_name | Collation | Cardinality | Sub\_part | Packed | Index\_type | Null | Comment | Index\_comment | Visible | Expression |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| account | 0 | PRIMARY | 1 | id | A | 986902 | NULL | NULL | BTREE |  |  |  | YES | NULL |
| account | 0 | username | 1 | username | A | 954417 | NULL | NULL | BTREE |  |  |  | YES | NULL |

#### Explain

```explain format = json select * from account where first_name like 'Ива%' or last_name like 'Ива%';```

```
{
     "query_block": {
       "select_id": 1,
       "cost_info": {
         "query_cost": "105703.03"
       },
       "table": {
         "table_name": "account",
         "access_type": "ALL",
         "rows_examined_per_scan": 986902,
         "rows_produced_per_join": 207108,
         "filtered": "20.99",
         "cost_info": {
           "read_cost": "84992.22",
           "eval_cost": "20710.81",
           "prefix_cost": "105703.03",
           "data_read_per_join": "971M"
         },
         "used_columns": [
           "id",
           "username",
           "password",
           "first_name",
           "last_name",
           "age",
           "gender",
           "city",
           "description"
         ],
         "attached_condition": "((`sn`.`account`.`first_name` like 'Ива%') or (`sn`.`account`.`last_name` like 'Ива%'))"
       }
     }
   }
```

```explain analyze select * from account where first_name like 'Ива%' or last_name like 'Ива%';```

```
-> Filter: ((`account`.first_name like 'Ива%') or (`account`.last_name like 'Ива%'))  (cost=105703.03 rows=207108) (actual time=0.270..747.737 rows=13420 loops=1)
       -> Table scan on account  (cost=105703.03 rows=986902) (actual time=0.027..631.415 rows=1000001 loops=1)
```

#### wrk 

![Result 01](result/report01.jpg)

Max throughput = 144.58 rps

<details>
  <summary>wrk -c100 -t2 -d30s --latency -R10 -s script.lua http://192.168.1.6:8080</summary>
  
  ```
  Running 30s test @ http://192.168.1.6:8080
    2 threads and 100 connections
    Thread calibration: mean lat.: 378.663ms, rate sampling interval: 1246ms
    Thread calibration: mean lat.: 398.158ms, rate sampling interval: 1220ms
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency   418.27ms  155.31ms 770.05ms   66.16%
      Req/Sec     5.10     13.24    40.00     87.10%
    Latency Distribution (HdrHistogram - Recorded Latency)
   50.000%  416.25ms
   75.000%  528.38ms
   90.000%  623.62ms
   99.000%  746.50ms
   99.900%  770.56ms
   99.990%  770.56ms
   99.999%  770.56ms
  100.000%  770.56ms
  
    Detailed Percentile spectrum:
         Value   Percentile   TotalCount 1/(1-Percentile)
  
        68.735     0.000000            1         1.00
       222.079     0.100000           20         1.11
       279.039     0.200000           40         1.25
       328.447     0.300000           60         1.43
       383.999     0.400000           80         1.67
       416.255     0.500000           99         2.00
       432.639     0.550000          109         2.22
       445.695     0.600000          119         2.50
       475.135     0.650000          129         2.86
       499.967     0.700000          139         3.33
       528.383     0.750000          149         4.00
       540.671     0.775000          154         4.44
       549.887     0.800000          159         5.00
       565.759     0.825000          164         5.71
       583.167     0.850000          169         6.67
       605.695     0.875000          174         8.00
       621.055     0.887500          176         8.89
       631.295     0.900000          179        10.00
       649.727     0.912500          181        11.43
       669.183     0.925000          184        13.33
       681.983     0.937500          186        16.00
       692.223     0.943750          187        17.78
       701.439     0.950000          189        20.00
       712.703     0.956250          190        22.86
       718.335     0.962500          191        26.67
       730.111     0.968750          192        32.00
       735.231     0.971875          193        35.56
       737.279     0.975000          194        40.00
       737.279     0.978125          194        45.71
       740.863     0.981250          195        53.33
       740.863     0.984375          195        64.00
       746.495     0.985938          196        71.11
       746.495     0.987500          196        80.00
       746.495     0.989062          196        91.43
       770.047     0.990625          197       106.67
       770.047     0.992188          197       128.00
       770.047     0.992969          197       142.22
       770.047     0.993750          197       160.00
       770.047     0.994531          197       182.86
       770.559     0.995313          198       213.33
       770.559     1.000000          198          inf
  #[Mean    =      418.271, StdDeviation   =      155.310]
  #[Max     =      770.048, Total count    =          198]
  #[Buckets =           27, SubBuckets     =         2048]
  ----------------------------------------------------------
    302 requests in 30.07s, 5.45MB read
    Socket errors: connect 0, read 0, write 0, timeout 1200
  Requests/sec:     10.04
  Transfer/sec:    185.67KB
  ```
</details>

<details>
  <summary>wrk -c100 -t2 -d30s --latency -R100 -s script.lua http://192.168.1.6:8080</summary>
  
  ```
  Running 30s test @ http://192.168.1.6:8080
    2 threads and 100 connections
    Thread calibration: mean lat.: 402.304ms, rate sampling interval: 1260ms
    Thread calibration: mean lat.: 397.301ms, rate sampling interval: 1241ms
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency   361.35ms  148.39ms 771.58ms   67.85%
      Req/Sec    50.10     10.12    75.00     83.33%
    Latency Distribution (HdrHistogram - Recorded Latency)
   50.000%  360.19ms
   75.000%  467.20ms
   90.000%  556.54ms
   99.000%  669.18ms
   99.900%  745.98ms
   99.990%  772.10ms
   99.999%  772.10ms
  100.000%  772.10ms
  
    Detailed Percentile spectrum:
         Value   Percentile   TotalCount 1/(1-Percentile)
  
        13.951     0.000000            1         1.00
       164.223     0.100000          200         1.11
       243.071     0.200000          399         1.25
       286.207     0.300000          600         1.43
       325.631     0.400000          798         1.67
       360.191     0.500000          999         2.00
       377.343     0.550000         1099         2.22
       399.103     0.600000         1197         2.50
       419.839     0.650000         1297         2.86
       444.415     0.700000         1396         3.33
       467.199     0.750000         1497         4.00
       481.279     0.775000         1547         4.44
       492.543     0.800000         1596         5.00
       503.551     0.825000         1646         5.71
       519.679     0.850000         1698         6.67
       537.599     0.875000         1746         8.00
       544.255     0.887500         1770         8.89
       556.543     0.900000         1795        10.00
       564.223     0.912500         1820        11.43
       576.511     0.925000         1846        13.33
       589.311     0.937500         1870        16.00
       594.943     0.943750         1882        17.78
       605.183     0.950000         1895        20.00
       613.375     0.956250         1907        22.86
       628.735     0.962500         1920        26.67
       635.903     0.968750         1933        32.00
       640.511     0.971875         1938        35.56
       648.191     0.975000         1947        40.00
       650.239     0.978125         1952        45.71
       654.847     0.981250         1958        53.33
       657.919     0.984375         1963        64.00
       660.479     0.985938         1966        71.11
       666.111     0.987500         1971        80.00
       668.671     0.989062         1973        91.43
       678.911     0.990625         1976       106.67
       684.031     0.992188         1979       128.00
       686.591     0.992969         1980       142.22
       691.711     0.993750         1982       160.00
       702.463     0.994531         1984       182.86
       702.975     0.995313         1985       213.33
       711.167     0.996094         1987       256.00
       711.167     0.996484         1987       284.44
       714.751     0.996875         1988       320.00
       717.311     0.997266         1989       365.71
       724.991     0.997656         1990       426.67
       733.695     0.998047         1991       512.00
       733.695     0.998242         1991       568.89
       733.695     0.998437         1991       640.00
       745.983     0.998633         1992       731.43
       745.983     0.998828         1992       853.33
       762.367     0.999023         1993      1024.00
       762.367     0.999121         1993      1137.78
       762.367     0.999219         1993      1280.00
       762.367     0.999316         1993      1462.86
       762.367     0.999414         1993      1706.67
       772.095     0.999512         1994      2048.00
       772.095     1.000000         1994          inf
  #[Mean    =      361.349, StdDeviation   =      148.391]
  #[Max     =      771.584, Total count    =         1994]
  #[Buckets =           27, SubBuckets     =         2048]
  ----------------------------------------------------------
    3002 requests in 30.05s, 54.21MB read
  Requests/sec:     99.88
  Transfer/sec:      1.80MB
  ```
</details>  

<details>
  <summary>wrk -c100 -t2 -d30s --latency -R1000 -s script.lua http://192.168.1.6:8080</summary>
  
  ```
  Running 30s test @ http://192.168.1.6:8080
    2 threads and 100 connections
    Thread calibration: mean lat.: 4519.673ms, rate sampling interval: 15638ms
    Thread calibration: mean lat.: 4541.386ms, rate sampling interval: 15818ms
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency    17.07s     4.88s   26.57s    57.83%
      Req/Sec    73.00      0.00    73.00    100.00%
    Latency Distribution (HdrHistogram - Recorded Latency)
   50.000%   17.09s
   75.000%   21.28s
   90.000%   23.81s
   99.000%   25.41s
   99.900%   25.97s
   99.990%   26.59s
   99.999%   26.59s
  100.000%   26.59s
  
    Detailed Percentile spectrum:
         Value   Percentile   TotalCount 1/(1-Percentile)
  
      8028.159     0.000000            1         1.00
     10305.535     0.100000          290         1.11
     11968.511     0.200000          579         1.25
     13697.023     0.300000          868         1.43
     15327.231     0.400000         1159         1.67
     17088.511     0.500000         1447         2.00
     18038.783     0.550000         1593         2.22
     18808.831     0.600000         1739         2.50
     19578.879     0.650000         1882         2.86
     20479.999     0.700000         2026         3.33
     21282.815     0.750000         2176         4.00
     21757.951     0.775000         2243         4.44
     22134.783     0.800000         2320         5.00
     22560.767     0.825000         2389         5.71
     23003.135     0.850000         2460         6.67
     23412.735     0.875000         2532         8.00
     23609.343     0.887500         2569         8.89
     23805.951     0.900000         2607        10.00
     24051.711     0.912500         2640        11.43
     24231.935     0.925000         2681        13.33
     24444.927     0.937500         2717        16.00
     24543.231     0.943750         2735        17.78
     24625.151     0.950000         2754        20.00
     24690.687     0.956250         2768        22.86
     24821.759     0.962500         2786        26.67
     24936.447     0.968750         2803        32.00
     24985.599     0.971875         2812        35.56
     25051.135     0.975000         2822        40.00
     25133.055     0.978125         2833        45.71
     25182.207     0.981250         2841        53.33
     25247.743     0.984375         2849        64.00
     25280.511     0.985938         2854        71.11
     25313.279     0.987500         2857        80.00
     25378.815     0.989062         2862        91.43
     25460.735     0.990625         2866       106.67
     25559.039     0.992188         2871       128.00
     25575.423     0.992969         2873       142.22
     25608.191     0.993750         2875       160.00
     25690.111     0.994531         2878       182.86
     25722.879     0.995313         2881       213.33
     25772.031     0.996094         2883       256.00
     25772.031     0.996484         2883       284.44
     25788.415     0.996875         2885       320.00
     25804.799     0.997266         2886       365.71
     25837.567     0.997656         2887       426.67
     25903.103     0.998047         2888       512.00
     25903.103     0.998242         2888       568.89
     25968.639     0.998437         2890       640.00
     25968.639     0.998633         2890       731.43
     25968.639     0.998828         2890       853.33
     25985.023     0.999023         2891      1024.00
     25985.023     0.999121         2891      1137.78
     25985.023     0.999219         2891      1280.00
     26165.247     0.999316         2892      1462.86
     26165.247     0.999414         2892      1706.67
     26165.247     0.999512         2892      2048.00
     26165.247     0.999561         2892      2275.56
     26165.247     0.999609         2892      2560.00
     26591.231     0.999658         2893      2925.71
     26591.231     1.000000         2893          inf
  #[Mean    =    17073.153, StdDeviation   =     4877.356]
  #[Max     =    26574.848, Total count    =         2893]
  #[Buckets =           27, SubBuckets     =         2048]
  ----------------------------------------------------------
    4342 requests in 30.03s, 78.35MB read
  Requests/sec:    144.58
  Transfer/sec:      2.61MB
  ```
</details> 

<details>
  <summary>wrk -c1000 -t8 -d30s --latency -R1000 -s script.lua http://192.168.1.6:8080</summary>
  
  ```
  Running 30s test @ http://192.168.1.6:8080
    8 threads and 1000 connections
    Thread calibration: mean lat.: 4210.305ms, rate sampling interval: 16244ms
    Thread calibration: mean lat.: 4311.887ms, rate sampling interval: 16023ms
    Thread calibration: mean lat.: 5640.781ms, rate sampling interval: 14073ms
    Thread calibration: mean lat.: 5686.288ms, rate sampling interval: 13869ms
    Thread calibration: mean lat.: 4116.614ms, rate sampling interval: 15941ms
    Thread calibration: mean lat.: 2901.608ms, rate sampling interval: 13746ms
    Thread calibration: mean lat.: 4273.372ms, rate sampling interval: 16080ms
    Thread calibration: mean lat.: 5549.588ms, rate sampling interval: 13451ms
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency    16.76s     4.88s   25.76s    59.57%
      Req/Sec    17.88      0.78    19.00    100.00%
    Latency Distribution (HdrHistogram - Recorded Latency)
   50.000%   16.62s
   75.000%   20.96s
   90.000%   23.79s
   99.000%   25.35s
   99.900%   25.69s
   99.990%   25.77s
   99.999%   25.77s
  100.000%   25.77s
  
    Detailed Percentile spectrum:
         Value   Percentile   TotalCount 1/(1-Percentile)
  
      8519.679     0.000000            1         1.00
     10125.311     0.100000          275         1.11
     11952.127     0.200000          550         1.25
     13139.967     0.300000          826         1.43
     14794.751     0.400000         1100         1.67
     16621.567     0.500000         1377         2.00
     17514.495     0.550000         1517         2.22
     18268.159     0.600000         1649         2.50
     19136.511     0.650000         1789         2.86
     20021.247     0.700000         1925         3.33
     20955.135     0.750000         2062         4.00
     21381.119     0.775000         2130         4.44
     21856.255     0.800000         2200         5.00
     22315.007     0.825000         2270         5.71
     22773.759     0.850000         2336         6.67
     23347.199     0.875000         2405         8.00
     23560.191     0.887500         2439         8.89
     23789.567     0.900000         2477        10.00
     23986.175     0.912500         2510        11.43
     24182.783     0.925000         2543        13.33
     24395.775     0.937500         2578        16.00
     24477.695     0.943750         2595        17.78
     24543.231     0.950000         2612        20.00
     24625.151     0.956250         2628        22.86
     24739.839     0.962500         2647        26.67
     24838.143     0.968750         2663        32.00
     24887.295     0.971875         2675        35.56
     24903.679     0.975000         2680        40.00
     25001.983     0.978125         2689        45.71
     25100.287     0.981250         2697        53.33
     25214.975     0.984375         2706        64.00
     25247.743     0.985938         2711        71.11
     25296.895     0.987500         2715        80.00
     25329.663     0.989062         2719        91.43
     25362.431     0.990625         2723       106.67
     25411.583     0.992188         2727       128.00
     25444.351     0.992969         2729       142.22
     25477.119     0.993750         2731       160.00
     25509.887     0.994531         2734       182.86
     25542.655     0.995313         2737       213.33
     25559.039     0.996094         2740       256.00
     25559.039     0.996484         2740       284.44
     25559.039     0.996875         2740       320.00
     25575.423     0.997266         2742       365.71
     25575.423     0.997656         2742       426.67
     25673.727     0.998047         2743       512.00
     25690.111     0.998242         2745       568.89
     25690.111     0.998437         2745       640.00
     25690.111     0.998633         2745       731.43
     25690.111     0.998828         2745       853.33
     25706.495     0.999023         2746      1024.00
     25706.495     0.999121         2746      1137.78
     25706.495     0.999219         2746      1280.00
     25722.879     0.999316         2747      1462.86
     25722.879     0.999414         2747      1706.67
     25722.879     0.999512         2747      2048.00
     25722.879     0.999561         2747      2275.56
     25722.879     0.999609         2747      2560.00
     25772.031     0.999658         2748      2925.71
     25772.031     1.000000         2748          inf
  #[Mean    =    16755.567, StdDeviation   =     4879.904]
  #[Max     =    25755.648, Total count    =         2748]
  #[Buckets =           27, SubBuckets     =         2048]
  ----------------------------------------------------------
    4168 requests in 30.23s, 75.22MB read
    Socket errors: connect 0, read 0, write 0, timeout 9302
  Requests/sec:    137.87
  Transfer/sec:      2.49MB
  ```
</details> 

## Тестирование функционала поиска анкет по префиксу имени и фамилии (в таблице account добавлены BTREE индексы на поля first_name и last_name)

#### Индексы

```
create index account_first_name_idx on account(first_name);
create index account_last_name_idx on account(last_name);
```

```show indexes FROM account;```

| Table | Non\_unique | Key\_name | Seq\_in\_index | Column\_name | Collation | Cardinality | Sub\_part | Packed | Null | Index\_type | Comment | Index\_comment | Visible | Expression |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| account | 0 | PRIMARY | 1 | id | A | 986902 | NULL | NULL |  | BTREE |  |  | YES | NULL |
| account | 0 | username | 1 | username | A | 954417 | NULL | NULL |  | BTREE |  |  | YES | NULL |
| account | 1 | account\_first\_name\_idx | 1 | first\_name | A | 109 | NULL | NULL | YES | BTREE |  |  | YES | NULL |
| account | 1 | account\_last\_name\_idx | 1 | last\_name | A | 501 | NULL | NULL | YES | BTREE |  |  | YES | NULL |

#### Explain

```explain format = json select * from account where first_name like 'Ива%' or last_name like 'Ива%';```

```
{
  "query_block": {
    "select_id": 1,
    "cost_info": {
      "query_cost": "33809.67"
    },
    "table": {
      "table_name": "account",
      "access_type": "index_merge",
      "possible_keys": [
        "account_first_name_idx",
        "account_last_name_idx"
      ],
      "key": "sort_union(account_first_name_idx,account_last_name_idx)",
      "key_length": "203,203",
      "rows_examined_per_scan": 21490,
      "rows_produced_per_join": 21490,
      "filtered": "100.00",
      "cost_info": {
        "read_cost": "31660.67",
        "eval_cost": "2149.00",
        "prefix_cost": "33809.67",
        "data_read_per_join": "100M"
      },
      "used_columns": [
        "id",
        "username",
        "password",
        "first_name",
        "last_name",
        "age",
        "gender",
        "city",
        "description"
      ],
      "attached_condition": "((`sn`.`account`.`first_name` like 'Ива%') or (`sn`.`account`.`last_name` like 'Ива%'))"
    }
  }
}
```

```explain analyze select * from account where first_name like 'Ива%' or last_name like 'Ива%';```

```
-> Filter: ((`account`.first_name like 'Ива%') or (`account`.last_name like 'Ива%'))  (cost=33809.67 rows=21490) (actual time=154.124..232.348 rows=13420 loops=1)
    -> Index range scan on account using sort_union(account_first_name_idx,account_last_name_idx)  (cost=33809.67 rows=21490) (actual time=154.120..229.844 rows=13420 loops=1)
```

#### wrk 

![Result 02](result/report02.jpg)

Max throughput = 4.99 rps

<details>
  <summary>wrk -c100 -t2 -d30s --latency -R10 -s script.lua http://192.168.1.6:8080</summary>
  
  ```
  Running 30s test @ http://192.168.1.6:8080
    2 threads and 100 connections
    Thread calibration: mean lat.: 6672.204ms, rate sampling interval: 18989ms
    Thread calibration: mean lat.: 6385.712ms, rate sampling interval: 18513ms
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency    13.55s     5.54s   29.84s    73.64%
      Req/Sec     2.00      0.00     2.00    100.00%
    Latency Distribution (HdrHistogram - Recorded Latency)
   50.000%   14.52s
   75.000%   16.72s
   90.000%   18.50s
   99.000%   29.82s
   99.900%   29.85s
   99.990%   29.85s
   99.999%   29.85s
  100.000%   29.85s
  
    Detailed Percentile spectrum:
         Value   Percentile   TotalCount 1/(1-Percentile)
  
      4575.231     0.000000            1         1.00
      5296.127     0.100000           11         1.11
      7065.599     0.200000           22         1.25
     10715.135     0.300000           33         1.43
     12935.167     0.400000           44         1.67
     14524.415     0.500000           55         2.00
     14827.519     0.550000           61         2.22
     15376.383     0.600000           66         2.50
     16039.935     0.650000           72         2.86
     16285.695     0.700000           77         3.33
     16719.871     0.750000           83         4.00
     17006.591     0.775000           86         4.44
     17252.351     0.800000           88         5.00
     17727.487     0.825000           91         5.71
     17825.791     0.850000           94         6.67
     18055.167     0.875000           97         8.00
     18350.079     0.887500           98         8.89
     18497.535     0.900000           99        10.00
     18907.135     0.912500          101        11.43
     18956.287     0.925000          102        13.33
     19726.335     0.937500          104        16.00
     19726.335     0.943750          104        17.78
     22773.759     0.950000          105        20.00
     23412.735     0.956250          106        22.86
     23412.735     0.962500          106        26.67
     28540.927     0.968750          107        32.00
     28540.927     0.971875          107        35.56
     29179.903     0.975000          108        40.00
     29179.903     0.978125          108        45.71
     29179.903     0.981250          108        53.33
     29818.879     0.984375          109        64.00
     29818.879     0.985938          109        71.11
     29818.879     0.987500          109        80.00
     29818.879     0.989062          109        91.43
     29818.879     0.990625          109       106.67
     29851.647     0.992188          110       128.00
     29851.647     1.000000          110          inf
  #[Mean    =    13554.204, StdDeviation   =     5536.740]
  #[Max     =    29835.264, Total count    =          110]
  #[Buckets =           27, SubBuckets     =         2048]
  ----------------------------------------------------------
    151 requests in 30.26s, 2.73MB read
    Socket errors: connect 0, read 0, write 0, timeout 1349
  Requests/sec:      4.99
  Transfer/sec:     92.31KB
  ```
</details> 

<details>
  <summary>wrk -c100 -t2 -d30s --latency -R100 -s script.lua http://192.168.1.6:8080</summary>
  
  ```
  Running 30s test @ http://192.168.1.6:8080
    2 threads and 100 connections
    Thread calibration: mean lat.: 7293.220ms, rate sampling interval: 14811ms
    Thread calibration: mean lat.: 7324.435ms, rate sampling interval: 14901ms
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency    17.42s     4.67s   26.80s    70.48%
      Req/Sec     3.00      0.00     3.00    100.00%
    Latency Distribution (HdrHistogram - Recorded Latency)
   50.000%   15.14s
   75.000%   21.58s
   90.000%   25.66s
   99.000%   26.69s
   99.900%   26.82s
   99.990%   26.82s
   99.999%   26.82s
  100.000%   26.82s
  
    Detailed Percentile spectrum:
         Value   Percentile   TotalCount 1/(1-Percentile)
  
     11108.351     0.000000            1         1.00
     11280.383     0.100000           11         1.11
     13869.055     0.200000           21         1.25
     14860.287     0.300000           32         1.43
     14983.167     0.400000           44         1.67
     15138.815     0.500000           53         2.00
     17252.351     0.550000           58         2.22
     17432.575     0.600000           63         2.50
     17629.183     0.650000           69         2.86
     21512.191     0.700000           74         3.33
     21577.727     0.750000           80         4.00
     21594.111     0.775000           82         4.44
     21610.495     0.800000           84         5.00
     21708.799     0.825000           88         5.71
     21774.335     0.850000           90         6.67
     24641.535     0.875000           92         8.00
     25657.343     0.887500           96         8.89
     25657.343     0.900000           96        10.00
     25657.343     0.912500           96        11.43
     25690.111     0.925000           98        13.33
     25706.495     0.937500           99        16.00
     25739.263     0.943750          101        17.78
     25739.263     0.950000          101        20.00
     25739.263     0.956250          101        22.86
     25788.415     0.962500          102        26.67
     25788.415     0.968750          102        32.00
     25821.183     0.971875          103        35.56
     25821.183     0.975000          103        40.00
     25821.183     0.978125          103        45.71
     26689.535     0.981250          104        53.33
     26689.535     0.984375          104        64.00
     26689.535     0.985938          104        71.11
     26689.535     0.987500          104        80.00
     26689.535     0.989062          104        91.43
     26820.607     0.990625          105       106.67
     26820.607     1.000000          105          inf
  #[Mean    =    17416.231, StdDeviation   =     4668.821]
  #[Max     =    26804.224, Total count    =          105]
  #[Buckets =           27, SubBuckets     =         2048]
  ----------------------------------------------------------
    132 requests in 30.26s, 2.38MB read
    Socket errors: connect 0, read 0, write 0, timeout 1368
  Requests/sec:      4.36
  Transfer/sec:     80.61KB
  ```
</details> 

<details>
  <summary>wrk -c1000 -t8 -d30s --latency -R1000 -s script.lua http://192.168.1.6:8080</summary>
  
  ```
  Running 30s test @ http://192.168.1.6:8080
    8 threads and 1000 connections
    Thread calibration: mean lat.: 7841.792ms, rate sampling interval: 15859ms
    Thread calibration: mean lat.: 9223372036854776.000ms, rate sampling interval: 10ms
    Thread calibration: mean lat.: 9223372036854776.000ms, rate sampling interval: 10ms
    Thread calibration: mean lat.: 9223372036854776.000ms, rate sampling interval: 10ms
    Thread calibration: mean lat.: 9223372036854776.000ms, rate sampling interval: 10ms
    Thread calibration: mean lat.: 9223372036854776.000ms, rate sampling interval: 10ms
    Thread calibration: mean lat.: 9223372036854776.000ms, rate sampling interval: 10ms
    Thread calibration: mean lat.: 9223372036854776.000ms, rate sampling interval: 10ms
    Thread Stats   Avg      Stdev     Max   +/- Stdev
      Latency    18.62s     5.03s   28.80s    62.38%
      Req/Sec     0.25      5.19   200.00     99.75%
    Latency Distribution (HdrHistogram - Recorded Latency)
   50.000%   17.10s
   75.000%   22.30s
   90.000%   26.05s
   99.000%   28.39s
   99.900%   28.82s
   99.990%   28.82s
   99.999%   28.82s
  100.000%   28.82s
  
    Detailed Percentile spectrum:
         Value   Percentile   TotalCount 1/(1-Percentile)
  
     11223.039     0.000000            1         1.00
     11378.687     0.100000           12         1.11
     14106.623     0.200000           21         1.25
     16990.207     0.300000           33         1.43
     17022.975     0.400000           41         1.67
     17104.895     0.500000           53         2.00
     19087.359     0.550000           56         2.22
     19611.647     0.600000           61         2.50
     20070.399     0.650000           66         2.86
     21168.127     0.700000           71         3.33
     22298.623     0.750000           76         4.00
     22511.615     0.775000           79         4.44
     23068.671     0.800000           81         5.00
     23756.799     0.825000           84         5.71
     23904.255     0.850000           86         6.67
     25329.663     0.875000           89         8.00
     25657.343     0.887500           90         8.89
     26050.559     0.900000           91        10.00
     27721.727     0.912500           93        11.43
     27836.415     0.925000           94        13.33
     27852.799     0.937500           95        16.00
     27885.567     0.943750           96        17.78
     27885.567     0.950000           96        20.00
     27967.487     0.956250           97        22.86
     28147.711     0.962500           98        26.67
     28147.711     0.968750           98        32.00
     28377.087     0.971875           99        35.56
     28377.087     0.975000           99        40.00
     28377.087     0.978125           99        45.71
     28393.471     0.981250          100        53.33
     28393.471     0.984375          100        64.00
     28393.471     0.985938          100        71.11
     28393.471     0.987500          100        80.00
     28393.471     0.989062          100        91.43
     28819.455     0.990625          101       106.67
     28819.455     1.000000          101          inf
  #[Mean    =    18623.255, StdDeviation   =     5033.669]
  #[Max     =    28803.072, Total count    =          101]
  #[Buckets =           27, SubBuckets     =         2048]
  ----------------------------------------------------------
    124 requests in 30.64s, 2.24MB read
    Socket errors: connect 0, read 0, write 0, timeout 13635
  Requests/sec:      4.05
  Transfer/sec:     75.01KB
  ```
</details> 

## Итоги:
- Для полей first_name и last_name добавлен BTREE индекс для возможности поиска по префиксу.
- throughput после добавления индексов оказался ниже (4.99), чем до добавления индексов (144.58) (??? не ясно почему)
- при этом explain после добавления индексов выглядит лучше, стоимость запроса в 3 раза меньше
    - Table scan on account  (cost=105703.03 rows=986902) (actual time=0.027..631.415 rows=1000001 loops=1)
    - Index range scan on account using sort_union(account_first_name_idx,account_last_name_idx)  (cost=33809.67 rows=21490) (actual time=154.120..229.844 rows=13420 loops=1)