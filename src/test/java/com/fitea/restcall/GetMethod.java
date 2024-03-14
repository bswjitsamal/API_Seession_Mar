package com.fitea.restcall;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetMethod {
	
	@BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
	
	@Test
    public void getRequest() {
        Response response = RestAssured.given() //precondition
                .contentType(ContentType.JSON)
                .when() //action
                .get("/posts")
                .then() //result
                .extract().response();

        
        //soft assertion or custom assertion
        if(response.statusCode()==201) {
        	System.out.println("correct output");
        }else {
        	System.out.println("there is an issue.");
        }
        
        
        if(response.jsonPath().getString("title[1]").contains("qui est esse")) {
        	System.out.println("correct output");
        }else {
        	System.out.println("there is an issue.");
        }
        
      //Hard assertion 
        Assert.assertEquals(201 , response.statusCode() ); //failed
        System.out.println("====");
        Assert.assertEquals("qui est esse", response.jsonPath().getString("title[1]"));  
    }

}
