package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;


import static io.restassured.RestAssured.given;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    @Test
    public void test001() { // Test to verify the total number of records displayed on a page
        response.body(("size()"), equalTo(10));
    }

    @Test
    public void test002() { // Test to verify the title of from ID
        response.body("findAll{it.id == 40105}.title", hasItem("Aptus amoveo benevolentia deinde cilicium universe."));
    }

    @Test
    public void test003() { // Test to verify that a particular ID exists or not
        response.body("findAll{it}.id", hasItem(40097));
    }

    @Test
    public void test004() { // Test to verify That multiple IDs exist
        response.body("findAll{it}.id", hasItems(40105, 40097, 40098));
    }

    @Test
    public void test005() { // Test to verify the body of an ID
        response.body("findAll{it.id == 40105}.body", hasItem("Cohaero at cavus. Administratio callide voluptates. Blandior conservo virga. Tunc aer vorax. Commodi vomer aeger. Clam numquam texo. Viridis aggredior anser. Utilis sollicito communis. Armarium communis considero. Vulgo asper unde. Apparatus aegre cilicium. Vulgus dapifer bardus. Supra tego demens."));
    }
}
