package org.delete_tests;

import org.apache.http.HttpStatus;
import org.core.BaseTest;
import org.core.HelperMethods;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeleteTests extends BaseTest {

    @Test
    public void testDeleteSpecificBooking(){
        int test_booking_id = HelperMethods.createNewBookingAndReturnBookingID();
        given()
                .header("Cookie", "token="+ HelperMethods.getAuthToken())
                .when()
                .delete("/booking/{test_booking_id}",test_booking_id)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
        ;

        given()
                .header("Cookie", "token="+ HelperMethods.getAuthToken())
                .when()
                .delete("/booking/{test_booking_id}",test_booking_id)
                .then()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
        ;
    }
}
