Redis can be used as a cache. You may not want to setup an HA Redis for caching purposes. 

This demo shows how to use Spring Cache with Redis combined with Hystrix so as to be resilient to failures of the Redis server. 

## Run

Start Redis: 

```
docker run -d -p 6379:6379 --name test-redis redis:4 
```

Start the application: 

```
mvn spring-boot:run
```

While the application is running, stop Redis: 

```
docker stop test-redis
```

### What happen

While Redis is available, caching works fine. 

When redis is stopped, connection fails and the command switch to fallback mode.

The command will, **once in a while**, attempt to use redis. 
This is why after starting redis it takes a few seconds for the system not to use the fallback method anymore. 


## Can't we use annotations?

Unfortunately, `@Cacheable` and `@HystrixCommqnd` annotations applied to the same method 
don't play well together. 

Directly defining Hystrix Commands ourselves also allows for greater flexibility. 