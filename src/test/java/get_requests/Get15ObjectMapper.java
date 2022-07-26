package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.HerokuappPojos.BookingPojo;
import test_data.HerOkuAppTestData;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;



public class Get15ObjectMapper extends HerOkuAppBaseUrl {

    /*
    Given
	            https://restful-booker.herokuapp.com/booking/2
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                {
                "firstname": "Oliver",
                "lastname": "Smith",
                "totalprice": 100,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-07-18",
                    "checkout": "2022-07-19"
                },
                "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void get01(){
        // 1. Step Set the Url

        spec.pathParams("first","booking","second",22);

        // 2. Step: Set the expected Data

        String expectedData = " {\n" +
                "                \"firstname\": \"Oliver\",\n" +
                "                \"lastname\": \"Smith\",\n" +
                "                \"totalprice\": 100,\n" +
                "                \"depositpaid\": true,\n" +
                "                \"bookingdates\": {\n" +
                "                    \"checkin\": \"2022-07-18\",\n" +
                "                    \"checkout\": \"2022-07-19\"\n" +
                "                },\n" +
                "                \"additionalneeds\": \"Breakfast\"\n" +
                "            }";

        String expectedData1 = HerOkuAppTestData.expectedDataInString("Oliver","Smith",100,true,"2022-07-18","2022-07-19","Breakfast");

        BookingPojo expectedDataPojo = JsonUtil.convertJsonToObject(expectedData1, BookingPojo.class);

        //3. Step Send the Get Request  get the Response

        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. Step Do Assertion

        BookingPojo actualDataPojo = JsonUtil.convertJsonToObject(response.asString(), BookingPojo.class);

        // Hard Assertion
        /*
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
        assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());
        assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());
   */

    // Soft Assertion
    // 1. Adım: SoftAssert Objesi olustur
    SoftAssert softAssert=new SoftAssert();

        // 2. Adım: Assertion Yap
    softAssert.assertEquals(200,response.getStatusCode());
    softAssert.assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname(),"Firstname uyusmadi");
    softAssert.assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname(),"Lastname uyusmadi");
    softAssert.assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice(),"Total Price uyusmadi");
    softAssert.assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid(), "Depositpaid uyusmadi");
    softAssert.assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds(), "Additionalneeds uyusmadi");
    softAssert.assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin(), "Checkin uyusmadi");
    softAssert.assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout(),"Checkin uyusmadi");
        // 3. Adım assertAll() methodunu calistir.
    softAssert.assertAll();





    }
}
