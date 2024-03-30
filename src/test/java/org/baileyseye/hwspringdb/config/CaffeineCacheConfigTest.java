package org.baileyseye.hwspringdb.config;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import static org.assertj.core.api.Assertions.assertThat;

class CaffeineCacheConfigTest {

    private CaffeineCacheConfig caffeineCacheConfig;

    @BeforeEach
    void setUp() {
        caffeineCacheConfig = new CaffeineCacheConfig();
    }

    @Test
    void caffeineCacheManager_ShouldContainExpectedCaches() {
        CacheManager cacheManager = caffeineCacheConfig.caffeineCacheManager();
        assertThat(cacheManager).isInstanceOf(CaffeineCacheManager.class);
        assertThat(cacheManager.getCacheNames()).containsExactlyInAnyOrder
                ("productsCache", "randomProductCache", "randomProductsCache");
    }

    @Test
    void caffeineCacheBuilder_ShouldHaveExpectedInitialConfiguration() {
        var cacheBuilder = caffeineCacheConfig.caffeineCacheBuilder();
        assertThat(cacheBuilder).isNotNull();
    }
}