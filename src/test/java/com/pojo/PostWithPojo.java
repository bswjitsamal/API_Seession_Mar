package com.pojo;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.complex.Address;
import com.complex.ComplexJson;
import com.complex.PhoneNumbers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostWithPojo {

	//Posts requestBody = new Posts("Sunita", 007);

	

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:3000";
	}

	@Test
	public void postRequest() {
		
		
		List<PhoneNumbers> ph= new ArrayList<PhoneNumbers>();
		PhoneNumbers ph1 = new PhoneNumbers();
		ph1.setType("iPhone");
		ph1.setNumber("0123-4567-8888");
		
		PhoneNumbers ph2 = new PhoneNumbers();
		ph2.setType("home");
		ph2.setNumber("0123-4567-8910");
		
		ph.add(ph1);
		ph.add(ph2);
		
		
		Address add = new Address();
		add.setStreetAddress("naist street");
		add.setCity("Nara");
		add.setPostalCode("630-0192");
		
		ComplexJson cj = new ComplexJson();
		cj.setFirstName("John");
		cj.setLastName("doe");
		cj.setAge(26);
		cj.setAddress(add);
		cj.setPhoneNumbers(ph);
		
		Response response = RestAssured.given()
				.header("Content-type", "application/json")
				.and()
				.body(cj)
				.contentType(ContentType.JSON)
				.when()
				.post("/posts")
				.then()
				.extract()
				.response();

		Assert.assertEquals(201, response.statusCode());
		Assert.assertEquals("John", response.jsonPath().getString("firstName"));
		System.out.println(response.asPrettyString());

	}

}
