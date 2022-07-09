# Events
This is spring Boot application. This application stores and processes traffic events.

1.  After application starts up, a custom application event is published to add different traffic event.
    There are respective suscriber for the every type of event. 
    As the event is publised, the subscriber will process and create violation entity.
    Rest Apis end points are available to get summary of violations and associated fines.

		Install maven globally, if not already installed.
		
		Steps to start the events
			1. on command prompt navigate to the folder location
			2. run command: mvn clean install
			3. run command: java -jar  target/events-0.0.1-SNAPSHOTT.jar
			4. Enter the swagger url http://localhost:8080/swagger-ui/index.html to access the api
