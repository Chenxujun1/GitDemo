package cloud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtil {
    @Autowired
    @Qualifier("customJedisPool")
    private JedisPool jedisPool;

    /**
     *
     * @param key
     * @param value
     */
    public void set(String key, String value, int indexDb){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(indexDb);
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            jedisPool.close();
        }
    }

}
