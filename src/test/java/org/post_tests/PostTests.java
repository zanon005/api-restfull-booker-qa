package org.post_tests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.core.BaseTest;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.instanceOf;

public class PostTests extends BaseTest {

    @Test
    public void testGetAuthToken(){
        JSONObject auth = new JSONObject();
        auth.put("username", "admin");
        auth.put("password", "password123");

        given()
                .contentType(ContentType.JSON)
                .body(auth.toString())
        .when()
                .post("/auth")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("", hasKey("token"))
                .body("token", instanceOf(String.class))
        ;
    }

    @Test
    public void testCreateBooking(){
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

        given()
                .contentType(ContentType.JSON)
                .body(new_booking.toString())
                .when()
                .post("/booking")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("booking.firstname", is(equalTo(new_booking.get("firstname"))))
                .body("booking.lastname", is(equalTo(new_booking.get("lastname"))))
        ;
    }
}
