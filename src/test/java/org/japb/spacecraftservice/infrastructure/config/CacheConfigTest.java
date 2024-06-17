package org.japb.spacecraftservice.infrastructure.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CacheConfigTest {

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCacheManagerCreation() throws Exception {
        assertNotNull(cacheManager, "Cache manager should be created");

        String cacheName1 = "charactersCache";
        String cacheName2 = "allCharactersCache";

        Cache cache1 = cacheManager.getCache(cacheName1);
        Cache cache2 = cacheManager.getCache(cacheName2);

        assertNotNull(cache1, "Cache '" + cacheName1 + "' should exist");
        assertNotNull(cache2, "Cache '" + cacheName2 + "' should exist");


    }
}