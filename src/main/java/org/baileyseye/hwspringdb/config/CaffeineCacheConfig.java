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
        saveStatToFile(stats);

        return caffeineCacheManager;
    }

    @Bean
    public Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .recordStats();
    }

    private void saveStatToFile(CacheStats stats) {
        List<String> statsList = Arrays.asList(
                "Hit rate: " + stats.hitRate(),
                "Miss rate: " + stats.missRate(),
                "Average load penalty: " + stats.averageLoadPenalty(),
                "Eviction count: " + stats.evictionCount()
        );

        try {
            String filePath = "src/main/resources/statistic/stat.txt";
            Files.write(Paths.get(filePath), new ArrayList<>(statsList),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ioException) {
            String errorMessage = "Произошла ошибка при записи данных в файл stat.txt: " +
                    ioException.getMessage();
            throw new RuntimeException(errorMessage, ioException);
        }
    }

}