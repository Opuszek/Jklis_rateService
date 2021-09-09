# Jklis_rateService

The application can be build with command mvn install

Before starting the application you should provide working token for service exchangerateapi.io. It is enough to create an "application.properties" file in the same directory as jar file with variable
exchangerate.token=here goes the token

The data should be loaded after hitting endpoing /loadData with http method get.
In order to access the data you need to hit endpoint /rate/yyyy-MM-dd with http method get. Example request: HTTP GET http://localhost:8080/rate/2020-10-01 .
IMPORTANT: only first day of every month of the last year will be loaded.
