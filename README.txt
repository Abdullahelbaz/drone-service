******************Environment***************************
Technologies and TOOLS

1- Spring BOOT
2- Spring validation
3- JPA
4- RESTFULL API
5- Swagger
6- Postgress
7- Impeded Server (Tomcat)
8- MAVEN
9- Java 8
10- Maven build tool
11- STS / Eclips
12-  DBeaver 
13 - Spring ExceptionHandler  (ControllerAdvice)

 
******************Some Business Assumptions***************************

1- Drones can load  more than one type of medication however the same type of medication can be loaded from more than one drone 
therefore, the raltion between drone and medication will be Many-To-Many

2- Drone will be available for loading when  
  * the drone has free weight , the medications loaded less than the drone weight limit
  * the drone is IDLE
(Available Drone for loading Service)
  
3- the drone will load the medication and change the state to be loading by Request Json, but 
    * when you load the medication not saved in DB before, will save this in db in medication table before loading to drone
	* when you load the medication saved in DB before, will load to drone only
	* project check the medication by name to know if it saved before or not
	
****************DB discription*********************************

drone_model table for drone models 
drone_state table for drone states
drone table for drones definition
medication for medication definition
drone_medication table fot loading and relation between drones and medication (Many To Many)
drone_tranasction_type type of services 
drone_transaction_history history for saving any transactions  which succeeded only 

********************Before Run and Build***********************

1- application.properties file contains some constant values, 
   * server port : 8052
   * DB port : 5432
   * DB url = jdbc:postgresql://localhost:5432/drone_db
   and some other configration
   
2- you should run posgress DB environment 
   
******************* For Run and Build the project ***************
--   DB
1- create postgress database by below script

   CREATE DATABASE CREATE DATABASE dbname;
   
2- run ddl & dml which provided in the package 
     file name : DB ddl && dml  .txt

-- for build the package 
1- bound and add JDK 8 in build path
2- update maven then clean the package then install maven
3- build mvn by 'clean package'
4- the package will build and the jar in target directory
5- the package has impeded tomcat server 
6- run the jar by below command
     nohup java -jar Drone-Service-1.0.jar 
or use any build tool like STS
7-you can use swagger by below link and use the JSON requests
	JSON request file name : Requests JSON.txt
   	Swagger Link  : http://localhost:8052/swagger-ui.html#/

