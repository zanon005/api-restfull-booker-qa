package org.get_tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.core.BaseTest;
import org.core.HelperMethods;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class GetTests extends BaseTest {

    @Test
    public void testReturningListOfBookings(){
        given()
        .when()
                .get("/booking")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("[0]", hasKey("bookingid"))
                .body("[0].bookingid", instanceOf(Integer.class))
        ;
    }

    @Test
    public void testReturningSpecificBooking(){
        JSONObject new_booking = new JSONObject();
        new_booking.put("firstname", "James");
        new_booking.put("lastname", "Brown");
        new_booking.put("totalprice", "111");
        new_booking.put("depositpaid", "true");
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");
        new_booking.put("bookingdates", bookingdates);
        new_booking.put("additionalneeds", "Breakfast");

        // Create specific booking
        int test_id = given()
                .contentType(ContentType.JSON)
                .body(new_booking.toString())
        .when()
                .post("/booking")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("bookingid")
        ;

        // Check Get specific booking created
        given()
        .when()
                .get("/booking/{test_id}", test_id)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("firstname", is(equalTo(new_booking.get("firstname"))))
                .body("lastname", is(equalTo(new_booking.get("lastname"))))
        ;
    }

    @Test
    public void testReturningSpecificBookingV2(){
        JsonPath resp_json = HelperMethods.createNewBookingAndReturnBooking().jsonPath();
        int id = resp_json.get("id");
        // Check Get specific booking created
        given()
                .when()
                .get("/booking/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("firstname", is(equalTo( resp_json.get("firstname"))))
                .body("lastname", is(equalTo( resp_json.get("lastname"))))
        ;
    }
}
