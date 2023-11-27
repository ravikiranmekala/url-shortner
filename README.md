# url-mapper
If a long URL is passed, application returns a short encoded URL. If the encoded URL is passed the original long URL is returned.

Primary database: PostGre
For caching : redis

POST: If long url is passed and endpoint /generate is triggered then short encoded URL is returned

GET: If encoded URL is passed, long URL is returned form database

For hasing used murmur hashing provided in google guava. 
In order to ensure a new hash is generated everytime , even if the long URL may be same, timestamp is added to the hasing logic alongwith murmur algorithm.
Database schema is designed to store the data with encoded url as id



PostGre SQl is run using Docker image.
Using Redis server kept tracked of redis cache getting added.
separating the logic into various packages: model, DAO, Datasource, config, service,API


Learning: 
Efficient Hashing to hash  huge number of URL requests. Hask key should be atleast 7 characters.
SpringBoot Annotations, dependency injection
Configuring various databases in SB
Learned troubleshooting for SpringBoot, docker ,postgres, and flyway
MVC architecture
Caching using Redis
