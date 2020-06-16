package com.yjx.limiter;

import com.yjx.limiter.service.LimiterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LimiterAutoConfiguration.class})
@TestPropertySource("classpath:test.properties")
public class LimiterTest {

    @Autowired
    private LimiterService limiterService;

    @Test
    public void testWrap() {
        limiterService.wrap("122");
    }

}
