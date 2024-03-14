package com.fitea.restcall;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetMethodWithPathParam {
	
	 @BeforeClass
	    public static void setup() {
	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	    }

	    @Test
	    public void getRequestWithQueryParam() {
	        Response response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .param("postId", "2")
	                .when()
	                .get("/comments")
	                .then()
	                .extract().response();

	        Assert.assertEquals(200, response.statusCode());
	        Assert.assertEquals("Meghan_Littel@rene.us", response.jsonPath().getString("email[3]"));
	    }

}
