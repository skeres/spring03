POC LIQUIBASE


** Network for docker  
docker network create --driver=bridge --ip-range=172.28.1.0/24 --subnet=172.28.0.0/16 --gateway=172.28.1.254 my_custom_network  
docker network ls  
docker network inspect my_custom_network

** Volume for docker  
docker volume create postgres-volume-testlb

** Run container  
docker-compose -f docker-compose_postgres.yaml up -d  
( or : docker start pg_container_testlb if container already exists )  
docker logs pg_container_testlb

psql -h 172.28.0.7 -U postgres  
Password for user postgres: postgres  
postgres=# \l
List of databases
Name    |  Owner   | Encoding |  Collate   |   Ctype    |   Access privileges   
-----------+----------+----------+------------+------------+-----------------------
(truncated )
testlb    | postgres | UTF8     | en_US.utf8 | en_US.utf8 |
(4 rows)

postgres=# quit

docker ps  
docker stop pg_container_testlb


sources for LIQUIBASE :  
https://www.liquibase.org/
https://www.liquibase.org/get-started/quickstart
https://docs.liquibase.com/start/home.html?_ga=2.155360632.2115252066.1675156425-996656958.1675156425
https://docs.liquibase.com/tools-integrations/maven/home.html
https://docs.liquibase.com/tools-integrations/maven/maven-pom-file.html

