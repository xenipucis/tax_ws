# tax_ws

In order to start running the project, you need to execute:

"mvn spring-boot:run"

During the execution of the project, you can verify the SOAP part using: 

"curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws"
(An request.xml example can be found in the home folder of the project)
For SOAP part, as you will see, you could insert data for multiple jobs in the same request.xml.

Also, the REST part could be verified using in browser:

"http://localhost:8080/swagger-ui.html"

Then, you need to select the controller, and you should be able to introduce tax values.


General:

The validations should work for both implementations (Rest & SOAP).
I've did unit tests for controller & service levels.
The calculations are guaranteed for first 4 decimals after the comma. (but that is configurable; if you want to know more about, there is the variable NUMBER_OF_DECIMALS from Job)
