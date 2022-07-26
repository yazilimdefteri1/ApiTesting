package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuappPojos.BookingDatesPojo;
import pojos.HerokuappPojos.BookingPojo;

import pojos.HerokuappPojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends HerOkuAppBaseUrl {
    /*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }

     */
    @Test
    public void post01() {
        //1. Step: Set the Url
        spec.pathParam("first", "booking");

        //2. Step: set the request body
        BookingDatesPojo bookingdates = new BookingDatesPojo("2021-09-21", "2021-12-21");
        BookingPojo bookingPojo = new BookingPojo("Ali", "Can", 999, true, bookingdates, "Breakfast with white tea, Dragon juice");


        //3. Step : send the post request, get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{first}");
        response.prettyPrint();

        BookingResponseBodyPojo actualBody = response.as(BookingResponseBodyPojo.class);

        //4. Step: Do Assertion
        assertEquals(200, response.getStatusCode());

        //1.YOL
       assertEquals(bookingPojo.toString(), actualBody.getBooking().toString());

        //2.YOL

        assertEquals(bookingPojo.getFirstname(), actualBody.getBooking().getFirstname());
        assertEquals(bookingPojo.getLastname(), actualBody.getBooking().getLastname());
        assertEquals(bookingPojo.getTotalprice(), actualBody.getBooking().getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(), actualBody.getBooking().getDepositpaid());
        assertEquals(bookingPojo.getAdditionalneeds(), actualBody.getBooking().getAdditionalneeds());

       assertEquals(bookingPojo.getBookingdates().getCheckin(), actualBody.getBooking().getBookingdates().getCheckin());
       assertEquals(bookingPojo.getBookingdates().getCheckout(), actualBody.getBooking().getBookingdates().getCheckout());


    }


}