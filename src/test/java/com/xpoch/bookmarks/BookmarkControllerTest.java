package com.xpoch.bookmarks;

import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.hamcrest.CoreMatchers;

import static io.restassured.RestAssured.given;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookmarkControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldGetBookmarkById() {
        given()
            .when()
            .get("/api/bookmarks/1")
            .then()
            .statusCode(200)
            .body("title", CoreMatchers.equalTo("SivaLabs Blog"));
    }


    @Test
    public void shouldCreateBookmarkSuccessfully() {
        String payload = """
            {
                "url": "https://www.sivalabs.in",
                "title": "SivaLabs Website"
            }
            """;
        given()
            .contentType("application/json")
            .body(payload)
            .when()
            .post("/api/bookmarks")
            .then()
            .statusCode(201);
    }
}