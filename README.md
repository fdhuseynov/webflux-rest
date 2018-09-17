---------------------ENG---------------------
Simple demo RESTful API using Spring WebFlux with Reactive MongoDB.

Why WebFlux?

Imagine standard implementation of a RESTful API with SpringMVC. Say, in our controller, a method calls a repository which sends a request to the database. Then, the thread allocated for this particular request from the thread pool by the JVM will be blocked until the database sends the response back. Not really good for scaling big projects. 
With reactive Java, we can switch to reactive programming paradigm - dealing with asynchronous data streams. That is, there will be an event loop (I just did not know how to call it differently) between, say, the thread and the database. After the thread sends a request to the database, it will be unblocked and take care of another request. When the database is done with the request, it sends an event to the loop and the response is sent to any free thread... And so on.
Well, it is really difficult to implement it by yourself. This is when Spring WebFlux comes in. Everything is ready. The only problem is that it support only MongoDB, Apache Cassandra and Redis repositories. More popular databases are coming for sure. 
