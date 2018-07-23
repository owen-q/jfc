package io.owen.jfc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by owen_q on 2018. 7. 23..
 */
@Component
public class Cache {
    private Logger logger = LoggerFactory.getLogger(Cache.class);

    private Map<String, Object> storage = null;

    public Cache() {
        this.storage = new ConcurrentHashMap<>();
    }

    public void set(String key, Object value){
        this.storage.put(key, value);
    }

    public Object get(String key){
        return (Object) this.storage.get(key);
    }

    public boolean containsKey(String key){
        return this.storage.containsKey(key);
    }

}
