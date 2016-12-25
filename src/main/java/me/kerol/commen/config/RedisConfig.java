package me.kerol.commen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by kevin on 23/12/2016.
 */
@Configuration
public class RedisConfig {

    // Redis configuration from application.properties
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;
    @Value("${spring.redis.pool.max-idle}")
    private int redisPoolMaxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private int redisPoolMinIdle;
    @Value("${spring.redis.pool.max-total}")
    private int redisPoolMaxTotal;
    @Value("${spring.redis.pool.max-waitmillis}")
    private int redisPoolMaxWaitMillis;

    @Bean(name="jedis.pool.config")
    JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisPoolMaxIdle);
        config.setMinIdle(redisPoolMinIdle);
        config.setMaxTotal(redisPoolMaxTotal);
        config.setMaxWaitMillis(redisPoolMaxWaitMillis);
        return config;
    }

    @Bean(name="jedis.pool")
    @Autowired
    @Qualifier("jedis.pool.config")
    public JedisPool jedisPool(JedisPoolConfig config) {
        return new JedisPool(config, redisHost, redisPort);
    }

}
