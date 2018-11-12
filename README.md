# practo-spring
This appliation is built using **Spring 4.1.1**, **Java 1.7** and **MySQL**.

The following are the steps to run this application.
**STEP 1: Requirements**
* Spring Tool Suite 4 which has embedded Maven.
* JDk 1.7
* MySQL
* Any version of Apache-Tomcat greater then 6 can be used(recommended version 7).

**STEP 2: Setup**
* Create a database in MySQL.
* Clone the github repository.
* Import the project in STS by selecting **File -> Import -> Existing Maven Projects -> Select the project folder**.
* The imported project has to be updated by selecting **File -> Maven -> Update Project**.

The application can either be exported or run in local.
**STEP 3:** 
**Exporting the project**
* Right click on the project name and select **Export -> WAR file**.
* Logs can be seen in the **catalina.out** in apache-tomcat folder.

**Running in local**
* Right click on the project name and select **Run as -> Run on Server -> (select apache-tomcat server)**.

**STEP 4: Database Table creation**
* If the project is successfully started in http://localhost:8080 , create the table structure and import the data from the path /sync_credit (say http://localhost:8080/sync_credit).
* Similarly type /sync_debit.
