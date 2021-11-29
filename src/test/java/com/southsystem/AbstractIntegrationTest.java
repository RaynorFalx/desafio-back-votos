package com.southsystem;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = {"classpath:test.properties"})
public class AbstractIntegrationTest {
}
