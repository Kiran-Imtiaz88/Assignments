package org.example;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AddItems {

    public String RequestBody =  "{\n" +
            " \"firstname\" : \"testFirstName\",\n" +
            " \"lastname\" : \"lastName\",\n" +
            " \"totalprice\" : 10.11,\n" +
            " \"depositpaid\" : true,\n" +
            " \"bookingdates\" : {\n" +
            " \"checkin\" : \"2022-01-01\",\n" +
            " \"checkout\" : \"2024-01-01\"\n" +
            " },\n" +
            " \"additionalneeds\" : \"testAdd\"\n" +
            "}";
    public Response response;
    public String BookingID;
    public int SpecificeBookingID;
    public void testResponse(){

        try {
            response = given()
                    .auth().none()
                    .contentType(ContentType.JSON)
                    .body(RequestBody)
                    .post("https://restful-booker.herokuapp.com/booking/");
            BookingID = response.jsonPath().getString("bookingid");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
