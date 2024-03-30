package org.baileyseye.hwspringdb.config;

import org.junit.jupiter.api.Test;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SwaggerConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void publicApiBeanExists() {
        GroupedOpenApi publicApi = applicationContext.getBean(GroupedOpenApi.class);
        assertNotNull(publicApi, "The publicApi bean should not be null");
        assertEquals("springshop-public", publicApi.getGroup(),
                "The publicApi bean should have the correct group name");
    }
}