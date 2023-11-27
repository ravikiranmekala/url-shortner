The application operates as follows: when a long URL is provided, the system generates and returns a short encoded URL. Conversely, if an encoded URL is provided, the original long URL is retrieved.

The primary database employed is PostGre, while Redis serves as the caching mechanism.

For the POST method, when a long URL is submitted to the "/generate" endpoint, the system responds with the corresponding short encoded URL. On the other hand, for the GET method, providing an encoded URL results in the retrieval of the corresponding long URL from the database.

Murmur hashing from Google Guava is utilized for hashing, and to ensure a unique hash for each request, a timestamp is incorporated into the hashing logic alongside the Murmur algorithm. The database schema is designed to store data with the encoded URL serving as the identifier.

PostGre SQL is executed using a Docker image, and the Redis server monitors the addition of entries to the Redis cache. The codebase is organized into distinct packages such as model, DAO, Datasource, config, service, and API.

Key learnings from the project include implementing efficient hashing techniques for handling a large number of URL requests, ensuring a minimum length of 7 characters for hash keys. Additionally, the project involved gaining expertise in SpringBoot annotations, dependency injection, configuring various databases within SpringBoot, and troubleshooting issues related to SpringBoot, Docker, Postgres, and Flyway. The project also explored the use of the MVC architecture and implemented caching using Redis.
