# Starting the web service

We have different options for starting the backend (web service).

## Tomcat Setup

I normally use Tomcat as container for java web applications. For development purposes I have all tomcat versions installed under

`$HOME/development/servers/tomcat/apache-tomcat-X`

I'll refer to this folder as `$DEVTOMCATHOME`.

## From an IDE

This backend is optimized for IntelliJ, but should also work with Eclipse. YMMV.

In IntelliJ: Just start the application (after setting up Tomcat) in Run or Debug mode and the web service will be available for clients.

## From local Tomcat

### Create WAR file and deploy to local Tomcat

We can either 
- double click the Maven `package` option from IntelliJ (in the Maven View) or 
- run `mvn package` from the command line.

Both will generate the file `$DEVTOMCATHOME/ngdemo.war`.

### Start Tomcat

Mac/Linux: `./$DEVTOMCATHOME/bin/startup.sh`

Windows: `./$DEVTOMCATHOME/bin/startup.bat`

# Test WebService

I'll use [curl](http://curl.haxx.se/) to test the HTTP request to our web service:

`
curl -i -X GET -H 'Content-Type: application/json' http://localhost:8080/ngdemo/web/users
`

This should output something similar to:

`
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 05 Aug 2013 19:41:27 GMT

[{"id":1,"firstName":"Foo1","lastName":"Bar1"},{"id":2,"firstName":"Foo2","lastName":"Bar2"},{"id":3,"firstName":"Foo3","lastName":"Bar3"},{"id":4,"firstName":"Foo4","lastName":"Bar4"},{"id":5,"firstName":"Foo5","lastName":"Bar5"},{"id":6,"firstName":"Foo6","lastName":"Bar6"},{"id":7,"firstName":"Foo7","lastName":"Bar7"},{"id":8,"firstName":"Foo8","lastName":"Bar8"},{"id":9,"firstName":"Foo9","lastName":"Bar9"},{"id":10,"firstName":"Foo10","lastName":"Bar10"}]
`
