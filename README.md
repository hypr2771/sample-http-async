# Async HTTP Sample

This is a sample project to check the potential integration of async HTTP client into an existing non reactive Spring Boot application.

We wanted to check whether an async HTTP client behaves in a production-like environment, having callbacks writing to a database.

It is actually as simple as with a sync HTTP client.

The workflow of this app is :

1. `MovieController.getById`
2. Checks whether we have a record of movie in `MovieRepository.findByImdbId`
3. If there is, return a `CompletableFuture.completedFuture` of the `movie`
4. If not, call `http://www.omdbapi.com/` asynchronously and let Spring handle the result using a registered `MovieHandler`

In the 4th point, we can see the interesting point is that while we are calling the HTTP actions, we can do some other computation, as the CPU is completely free.

You can test using :
```
curl 'http://localhost:8080/tt3896180' --compressed
```