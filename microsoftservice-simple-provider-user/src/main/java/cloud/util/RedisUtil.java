package cloud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     *
     * @param key
     * @param value
     */
    public void set(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     *
     * @param key
     */
    public String get(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
}
