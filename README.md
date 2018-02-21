# filmPortal

Spring-Boot project to demonstrate:
-Spring-Boot
-JSP
-Thymeleaf
-JS & JQ
-PostgreSQL JDBC (MySQL brach -> MySQL)
-Hibernate, JPA-s, Auto-DDL locally & at the container
-Maven config
-Docker & docker-compose


User made with Spring Security, but created at app.java. Verification e-mails, automated user creation with same logic here: https://github.com/giorgiDologov/edu_e-commerce/tree/master/src/main/java/com/education
(this is a bigger app, I am still working on it, there is messaging app, media streaming, and everything else there, bt it's not ready)

# how to run & use:

1.: download + unzip
2.: cd ~/yourInfr/filmPortal-master/
3.: mvn clean package docker:build
4.: cd ~/yourInfr/filmPortal-master/target
5.: docker-compose up
6.: localhost:8080
7.: username: robi.raj & pw: p
8.: create films, users, than rent out films, finish, delete etc...

