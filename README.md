# geladinha_api

This is the Geladinha API! With this API you can create point of sales (POS) and find the closest POS of a given locality.

## How to build and deploy this API

This API uses [SpringBoot](https://spring.io/projects/spring-boot) with embedded [Tomcat](http://tomcat.apache.org/) and embedded [MongoDB](https://www.mongodb.com/). So you don't need to waste your time with complicated builds or deploys. But if you want to use MongoDB in your own server, you can setup your DB in the application.properties file.

Besides that, [Jersey](https://jersey.github.io/) and [SpringData](https://spring.io/projects/spring-data) are also used in this API to make your REST development and data management really easy.

#### Build and run

Maven is managing everything here. So you can just execute the following command in the root directory of the project to get the application up and running:

```
mvn spring-boot:run
```

Another way is to package and run the application with:

```
mvn package
cd target
java -jar geladinha-api-0.0.1-SNAPSHOT.jar
```

#### Deploy

This application are packaged into a self-contained executable JAR file that includes application code, configuration and dependencies. So you can just deploy on your preferred cloud.

If you want a WAR file, you need to change a few things in your pom.xml:
- change from a jar build to a war build
- comment out the declaration of the spring-boot-maven-plugin
- change the Maven packaging type to war

And one last thing, the SpringBootApplication init class (GeladinhaApiApplication.class) needs to extends SpringBootServletInitializer.


## How to use this API


#### Create POS

Create a POS in the database and returns the created POS. Document (CNPJ) must be unique and if you send a payload with the same id the data will be updated in database.

POST /create/pos

```
http://localhost:8080/create/pos
```

##### Headers
```
Content-Type	application/json
```


##### Body raw (application/json)
```
{
        "id": 1, 
        "tradingName": "Adega da Cerveja - Pinheiros",
        "ownerName": "Zé da Silva",
        "document": "1432132123891/0001",
        "coverageArea": { 
          "type": "MultiPolygon", 
          "coordinates": [
            [
            	[
            		[30, 20], 
            		[45, 40], 
            		[10, 40], 
            		[30, 20]
            	]
            ], 
            [[[15, 5], [40, 10], [10, 20], [5, 10], [15, 5]]]
          ]
        },
        "address": { 
          "type": "Point",
          "coordinates": [33, 35]
        }
}

```

##### Example request

```
curl --location --request POST "http://localhost:8080/create/pos" \
  --header "Content-Type: application/json" \
  --data "{
        \"id\": 2, 
        \"tradingName\": \"Adega da Cerveja - Pinheiros\",
        \"ownerName\": \"Zé da Silva\",
        \"document\": \"1432132123891/0001\",
        \"coverageArea\": { 
          \"type\": \"MultiPolygon\", 
          \"coordinates\": [
            [
            	[
            		[30, 20], 
            		[45, 40], 
            		[10, 40], 
            		[30, 20]
            	]
            ], 
            [[[15, 5], [40, 10], [10, 20], [5, 10], [15, 5]]]
          ]
        },
        \"address\": { 
          \"type\": \"Point\",
          \"coordinates\": [-46.57421, -21.785741]
        }
}"
```


#### Find POS by ID

Returns a POS that matches the id, otherwise returns HTTP 404 not found.

GET /search/{id}

```
http://localhost:8080/search/1
```

##### Example request

```
curl --location --request GET "http://localhost:8080/search/1"
```


#### Find the closest POS given the coordinates of a point

Returns a JSON array with the nearest POS by the given point or an empty array in case there is no POS close. The max distance for a POS  to be considered close is 10 km.

GET /search/location?x={x}&y={y}

```
http://localhost:8080/search/location?x=33.001&y=35
```

##### Params
```
x	33.001
y	35
```

##### Example request

```
curl --location --request GET "http://localhost:8080/search/location?x=33.001&y=35" \
  --header "Content-Type: application/json" \
  --data "{
            \"x\": 33.011,
            \"y\": 35
}"
```

