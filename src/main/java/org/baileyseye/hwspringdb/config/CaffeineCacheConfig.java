package org.baileyseye.hwspringdb.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.caffeine.CaffeineCacheManager;

@Configuration
@EnableCaching
public class CaffeineCacheConfig {

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeineCacheBuilder());

        caffeineCacheManager.setCacheNames(Arrays.asList
                ("productsCache", "randomProductCache", "randomProductsCache"));

        Cache cache = caffeineCacheManager.getCache("productsCache");
        assert cache != null;
        CacheStats stats = ((CaffeineCache) cache).getNativeCache().stats();
        return caffeineCacheManager;
    }

    @Bean
    public Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(10000000)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .recordStats();
    }
}