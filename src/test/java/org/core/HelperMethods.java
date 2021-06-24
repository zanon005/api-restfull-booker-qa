package org.core;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;

public class HelperMethods extends BaseTest{

    public static String getAuthToken(){
        JSONObject auth = new JSONObject();
        auth.put("username", "admin");
        auth.put("password", "password123");

        return given()
                .contentType(ContentType.JSON)
                .body(auth.toString())
        .when()
                .post("/auth")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("", hasKey("token"))
                .body("token", instanceOf(String.class))
                .extract().path("token")
        ;
    }

    public static int createNewBookingAndReturnBookingID(){
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
        return given()
                .contentType(ContentType.JSON)
                .body(new_booking.toString())
        .when()
                .post("/booking")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("bookingid")
        ;
    }

    //Not working
    public static Response createNewBookingAndReturnBooking(){
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
        return given()
                .contentType(ContentType.JSON)
                .body(new_booking.toString())
        .when()
                .post("/booking")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response()
        ;
    }
}
