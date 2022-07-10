# Events
This is a Spring Boot application. This application stores and processes traffic events.

1.  After the application starts up, a custom application event is published to add different traffic events.
    There are respective suscriber for the every type of event. 
    As the event is published, the subscriber will process and create violation entity.
    Rest Apis end points are available to get summary of violations and associated fines.

		Install maven globally, if not already installed.
		
		Steps to start the events
			a. On command prompt in Windows or OS X Terminal in MacOS or Unix Command line Shell in Linux, 
			   navigate to the checked out repository root folder location
			b. Run command: mvn clean install
			c. run command: java -jar  target/events-0.0.1-SNAPSHOTT.jar
			d. Enter the Swagger url in the Web Browser - http://localhost:8080/swagger-ui/index.html to access the API
