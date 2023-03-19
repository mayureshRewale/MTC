package neo.mtc.mtccommons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtils {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public String getValue(String key){
        try {
            return Objects.requireNonNull(redisTemplate.opsForValue().get(key)).toString();
        } catch (Exception ex) {
            log.error("Failed to fetch redis key: {}", ex.getMessage());
            return null;
        }
    }

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setValue(String key, String value, long expiry) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire( key, expiry, TimeUnit.SECONDS );
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    public Long getTtl(String key){
        try {
            return redisTemplate.getExpire(key);
        } catch (NullPointerException ex){
            log.error("Failed To get TTL for key: {}",ex.getMessage());
            return null;
        }
    }
    public void deletePattern(String pattern){
        Set<String> keys = getPattern(pattern);
        if(keys!=null){
            redisTemplate.delete(keys);
        }
    }
    public Set<String> getPattern(String pattern){
        return redisTemplate.keys(pattern);
    }

}
