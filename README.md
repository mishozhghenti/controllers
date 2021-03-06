# Device controller application

### Used Technologies:
* Java 8
* Spring Boot
* JDBC
* MySQL
* Maven


### Build Application
    mvn clean install -o
### Run Application
    mvn spring-boot:run

### Project Details:

* Runs on http://localhost:8080/

### Gateways - http://localhost:8080/api/gateway
Add New Gateway: 				`/add`<br/>
Get Single Gateway Info:		`/getGatewayInfo`<br/>
Get All Gateways Info:		    `/getAllGatewaysInfo`<br/>
eg: http://localhost:8080/api/gateway/add
### Devices - http://localhost:8080/api/device
Add New Device:					`/add`<br/>
Delete Device:					`/delete`<br/>
Change Device Status:			`/changeStatus`<br/>
Add Device To Gateway:			`/addToGateway`<br/>
Remove Device From Gateway:		`/removeDeviceFromGateway`<br/>
eg: http://localhost:8080/api/device/add

### Business Logic Restriction
Each gateways could have no more than 10 devices connected<br/>
(After insertion trigger added)

### DataBase (MySQL):
Schema Structure: `src/main/java/com/example/demo/sql/architecture.sql`<br/>
Test Data: `src/main/java/com/example/demo/sql/content.sql`<br/>
