# Full Stack with OAuth

### About the project:

* auth-server -> authorization server using Spring Authorization-server
* api-service -> acts as a client and resource server. It connects to the front-end(front-service)
    and core-service(back-end)
* core-service -> connects to the api-service and to a PostgreSQL database
* front-service -> font-end  connects to api-service

### Technologies used:
    - front-service: Angular 16, HTML & CSS
    - api-service, core-service, auth-server: SpringBoot 3.1.1, java 17
    - core-service: JPA/Hibernate
    - PostgreSQL

### Microservices schema:
![micro-services.png](..%2Fmicro-services.png)

<br>
<br>

### UI/UX:
<br>

![front-login.png](..%2Ffront-login.png)
<br>
![server-login.png](..%2Fserver-login.png)
<br>
![home.png](..%2Fhome.png)



