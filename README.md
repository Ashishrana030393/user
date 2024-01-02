**To get started with Redis Stack using Docker, you first need to select a Docker image:**
- redis/redis-stack contains both Redis Stack server and RedisInsight. This container is best for local development because you can use RedisInsight to visualize your data.
- redis/redis-stack-server provides Redis Stack but excludes RedisInsight. This container is best for production deployment.

docker pull redis/redis-stack:latest
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 -e REDIS_ARGS="--requirepass mypassword" redis/redis-stack:latest

docker exec -it redis-stack redis-cli



Spring Boot Redis Cache

Spring Boot
This page will walk through Spring Boot Redis cache example. RedisCacheManager is the CacheManager backed by Redis. If Redis is available and configured in our Spring Boot application, RedisCacheManager will be auto-configured. Redis connections are obtained from Lettuce or Jedis Java Redis clients. Redis dependencies are resolved by spring-boot-starter-data-redis starter. In Spring Boot 2.0 Lettuce are resolved by default instead of Jedis. To work with Jedis, we need to include jedis dependency in our build file.
Spring @EnableCaching enables Spring cache management capability in our application. It is annotated with @SpringBootApplication annotation.
1. Using Lettuce Configurations
   Spring Boot 2.0 starter spring-boot-starter-data-redis resolves Lettuce by default. Spring provides LettuceConnectionFactory to get connections. To get pooled connection factory we need to provide commons-pool2 on the classpath. To work with Lettuce we need following Maven dependencies.
   <dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-redis</artifactId>
   </dependency>		
   <dependency>
   <groupId>org.apache.commons</groupId>
   <artifactId>commons-pool2</artifactId>
   </dependency> To configure Lettuce pool we need to use spring.redis.* prefix with Lettuce pool connection properties. Find the Lettuce pool sample configurations.
   application.properties
   spring.redis.host=localhost
   spring.redis.port=6379
   spring.redis.password=

spring.redis.lettuce.pool.max-active=7
spring.redis.lettuce.pool.max-idle=7
spring.redis.lettuce.pool.min-idle=2
spring.redis.lettuce.pool.max-wait=-1ms  
spring.redis.lettuce.shutdown-timeout=200ms We can override default Redis host, port and password configurations. Use max-wait a negative value if we want to block indefinitely.

2. Using Jedis Configurations
   By default Spring Boot 2.0 starter spring-boot-starter-data-redis uses Lettuce. To use Jedis we need to exclude Lettuce dependency and include Jedis. Find the Maven dependencies to use Jedis.
   <dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-redis</artifactId>
   <exclusions>
   <exclusion>
   <groupId>io.lettuce</groupId>
   <artifactId>lettuce-core</artifactId>
   </exclusion>
   </exclusions>		    
   </dependency>		
   <dependency>
   <groupId>redis.clients</groupId>
   <artifactId>jedis</artifactId>
   </dependency> jedis dependency will automatically resolve commons-pool2 on the classpath.
   To configure Jedis pool we need to use spring.redis.* prefix with Jedis pool connection properties. Find the Jedis pool sample configurations.
   application.properties
   spring.redis.host=localhost
   spring.redis.port=6379
   spring.redis.password=

spring.redis.jedis.pool.max-active=7
spring.redis.jedis.pool.max-idle=7
spring.redis.jedis.pool.min-idle=2
spring.redis.jedis.pool.max-wait=-1ms
3. RedisCacheManager
   In Spring Boot, RedisCacheManager is auto-configured. Here we will discuss how to configure Spring Boot Redis cache properties to change its default value for auto-configured RedisCacheManager and then we will create a sample own RedisCacheManager to get full control on configurations.

1. Auto-configured RedisCacheManager
   If Redis is available and configured in our Spring Boot application, RedisCacheManager will be auto-configured. We can control Spring cache configurations using spring.cache.* property.
   spring.cache.type: Defines cache type. If we do not configure this property, It will be auto-detected to the environment. For Redis cache its value is redis .
   spring.cache.cache-names: Creates additional caches on startup.

Redis cache defaults can be configured by spring.cache.redis.* .
spring.cache.redis.cache-null-values: It accepts Boolean value. When the value is true, it will allow caching null values otherwise not.
spring.cache.redis.time-to-live: Cache expiration time.
spring.cache.redis.use-key-prefix: It accepts Boolean value. If true then key prefix will be used while writing to Redis. Default value is true
spring.cache.redis.key-prefix: Defines key prefix. By default a key prefix is added to avoid overlapping keys when two separate caches uses same key.


spring.cache.redis.cache-null-values=false
spring.cache.redis.time-to-live=600000
spring.cache.redis.use-key-prefix=true

spring.cache.type=redis
spring.cache.cache-names=articleCache,allArticlesCache

The caches articleCache and allArticlesCache will be alive for 10 minutes.