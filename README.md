The application can be started with 'mvn spring-boot:run' command.


Available paths:
#### POST localhost:8080/api/v1/clients
* For registering clients
* In the request body goes the 'name' and the 'email'
* Send back an api key in the response

#### POST localhost:8080/api/v1/positions
* For adding new positions
* In the request body goes the 'apiKey', 'jobTitle' and the 'location'
* Send back an url

#### GET localhost:8080/api/v1/positions/{id}
* For querying a single position
* In the request body goes the 'apiKey'

#### POST localhost:8080/api/v1/positions?keyword={keyword}&location={location}
* For searching positions
* In the request body goes the 'apiKey'
* Must add the keyword and location in the path