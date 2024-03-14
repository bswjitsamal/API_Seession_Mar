package com.pojo;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostWithPojo {

	Posts requestBody = new Posts("Manju", 007);

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:3000";
	}

	@Test
	public void postRequest() {
		Response response = RestAssured.given()
				.header("Content-type", "application/json")
				.and()
				.body(requestBody)
				.contentType(ContentType.JSON)
				.when()
				.post("/posts")
				.then()
				.extract()
				.response();

		Assert.assertEquals(201, response.statusCode());
		Assert.assertEquals("Manju", response.jsonPath().getString("title"));
		System.out.println(response.asPrettyString());

	}

}
