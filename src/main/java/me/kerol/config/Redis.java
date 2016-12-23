package me.kerol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by kevin on 23/12/2016.
 */
@Component
public class Redis {

    @Autowired
    private JedisPool jedisPool;

    // set key value
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }
        finally {
            jedis.close();
        }
    }

    // get key value
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }
        finally {
            jedis.close();
        }
    }
}
