package com.fitea.restcall;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostMethod {
	
	private static String requestBody = "{\"title\":\"biswa samal\",\"views\":105}";
	
	public String id = "";
	

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:3000";
	}

	@Test
	public void postRequest() {
		Response response = RestAssured.given()
				.header("Content-type", "application/json")
				.and().body(requestBody)
				.contentType(ContentType.JSON)
				.when()
				.post("/posts")
				.then()
				.extract()
				.response();

		Assert.assertEquals(201, response.statusCode());
		Assert.assertEquals("biswa samal", response.jsonPath().getString("title"));
		System.out.println(response.asPrettyString());
		
		JsonPath jp =  response.jsonPath();
		String id  = jp.getString("id");
		System.out.println(jp.getString("id"));
		
		
		String requestBody1 = "{\"title\":\"biswajit samal\",\"views\":105}";
		
		Response response1 = RestAssured.given()
				.header("Content-type", "application/json")
				.and().body(requestBody1)
				.when()
				.put("/posts/"+id)
				.then()
				.extract()
				.response();
		
		System.out.println(response1.asPrettyString());
		
	}

}
