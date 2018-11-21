## Briefly about solution:
#### Stack:
* Spring Boot
* Spring Boot test
* H2 database
* Spring JPA
* Spring Security
* Maven

#### Some information about article service:
1. default api key stored in `application.yml`
2. home entrypoint of the rest controller by default is `your_server_address/articles:8888`
3. Article controller functionality:
* `CRUD` functionality
* `GET` all article by given period
* `GET` all article by given author id

#### Some information about keyword service:
1. default api key stored in `application.yml`
2. home entrypoint of the rest controller by default is `your_server_address/keywords:8889`
3. Implemented functionality:
 * `GET` all keywords
 * `GET` all keywords by Aricle ID
 * `GET` info by given keyword ID
 * `Create` new keyword
 * `Delete` keyword with given ID
 * Remove given article id from all keywords

#### Some information about author service:
It was not implemented because it would be full copy of keyword service.

#### How to run

Copy project and run in project root `mvn package` it will build fat jars for each service. 
Then run in project root `java -jar svc-article/target/svc-article-0.0.1-SNAPSHOT.jar` for article service and `java -jar svc-article/target/svc-keyword-0.0.1-SNAPSHOT.jar`

## Coding Assignment was:
#### This is the situation:
We are a publishing company that created an app for reading news articles.

To serve data to the app we need a backend to provide RESTful APIs for the following use cases:
 - allow an editor to create/update/delete an article
 - display one article
 - list all articles for a given author
 - list all articles for a given period
 - find all articles for a specific keyword

Each API should only return the data that is really needed to fulfill the use case.
An article usually consists of the following information:

* header
* short description
* text
* publish date
* author(s)
* keyword(s)

Hints:
* Use the Java technology you are most comfortable with (e.g. spring-boot).
* The data does not need to be persisted after the application is shut down.