package com.xpoch.bookmarks;

import org.springframework.boot.SpringApplication;

public class TestSpringBookmarksApiApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpringBookmarksApiApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
