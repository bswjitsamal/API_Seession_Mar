package com.schema;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidation {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:3000";
	}

	@Test
	public void checkPostsSchema() {
		// @formatter:off
       RestAssured.given().
        when().
            get("/posts/1").
        then().
            log().ifValidationFails().
            assertThat().
            statusCode(200).
        and().
            contentType(ContentType.JSON).
    body(JsonSchemaValidator.matchesJsonSchemaInClasspath("posts_schema_error.json"));
    }
}
