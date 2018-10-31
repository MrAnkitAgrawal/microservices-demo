Catalog App Dockerization
==========================
Docker Image Type
------------------
	Linux OS
	Java 8
	catalog-service\target\catalog-service-0.0.1-SNAPSHOT.jar

Create MySql Container
-----------------------
 - Create DB:
   docker run --detach --name mysql-app -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=db_catalog -e MYSQL_USER=catalog_user -e MYSQL_PASSWORD=catalog123 -d mysql

 - Verify:
   docker run -it --link mysql-app:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'



Steps to build docker image and run container
----------------------------------------------
  - Run and verify if catalog app is running properly
  - keep app build ready (target\catalog-service-0.0.1-SNAPSHOT.jar)
  - create Dockerfile (catalog-service\Dockerfile)
	FROM java:8
	WORKDIR /
	ADD target/catalog-service-0.0.1-SNAPSHOT.jar //
	EXPOSE 8080
	ENTRYPOINT [ "java", "-jar", "/catalog-service-0.0.1-SNAPSHOT.jar"]
  - create image 
	- Open "Docker QuickStart Terminal"
	- cd to directory which contains Dockerfile (catalog-service\)
	- docker build -t catalog-img .
  - verify imgae 
	docker images
  - create container 
	docker run --name catalog-app -p8080:8080 -d catalog-img



About Docker:
-------------
- Docker for Windows is designed to configure Docker development environments on Windows 10 and on Windows Server 2016.
- You can develop both Docker Linux containers and Docker Windows containers with Docker for Windows.
- Docker for Windows requires 64bit Windows 10 Pro with Hyper-V available.