package org.put_tests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.core.BaseTest;
import org.core.HelperMethods;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PutTests extends BaseTest {

    @Test
    public void testUpdateSpecificBooking(){
        JSONObject old_booking = new JSONObject();
        old_booking.put("firstname", "James");
        old_booking.put("lastname", "Brown");
        old_booking.put("totalprice", "111");
        old_booking.put("depositpaid", "true");
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");
        old_booking.put("bookingdates", bookingdates);
        old_booking.put("additionalneeds", "Breakfast");

        // Create specific booking
        int test_booking_id = given()
                .contentType(ContentType.JSON)
                .body(old_booking.toString())
        .when()
                .post("/booking")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().path("bookingid")
        ;

        JSONObject updated_booking = new JSONObject();
        updated_booking.put("firstname", "NewJames");
        updated_booking.put("lastname", "NewBrown");
        updated_booking.put("totalprice", "111");
        updated_booking.put("depositpaid", "true");
        JSONObject updated_bookingdates = new JSONObject();
        updated_bookingdates.put("checkin", "2018-01-01");
        updated_bookingdates.put("checkout", "2019-01-01");
        updated_booking.put("bookingdates", updated_bookingdates);
        updated_booking.put("additionalneeds", "Breakfast");

        // Check PUT(updating) specific booking created
        given()
                .header("Cookie", "token="+ HelperMethods.getAuthToken())
                //.cookie("token="+HelperMethods.getAuthToken()) NAO FUNCIONOU!
                .contentType(ContentType.JSON)
                .body(updated_booking.toString())
        .when()
                .put("/booking/{test_booking_id}", test_booking_id)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("firstname", is(equalTo("New" + old_booking.get("firstname"))))
                .body("lastname", is(equalTo( "New" + old_booking.get("lastname"))))
        ;
    }
}
