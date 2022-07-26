package get_requests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02 {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void get02() throws io.restassured.internal.http.HttpResponseException  {


        // x) Set the URL:
        String url="https://restful-booker.herokuapp.com/booking/1";

       //xx) Set the Expected Data(POST-PUT-PATCH) -->

       //  xxx) TYPE CODE TO SEND REQUEST-->
        Response response = given().when().get(url);
        response.prettyPrint();
       //   xxxx) Do Assertion  --> Doğrulama Yapma

        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //Response body'de bulunan spesifik bir veri nasıl assert edilir:
        //assertTrue() methodu parantezin içindeki değer true is testi geçirir.
        assertTrue(response.asString().contains("Not Found"));


        //Response body'de spesifik bir veri bulunmadığını nasıl assert edilir:
        //assertFalse() methodu parantezin içindeki değer false is testi geçirir.
        assertFalse(response.asString().contains("TechProEd"));

        //assertEquals() methodu parantez içindeki iki değer eşit ise testi geçirir.
        assertEquals("Cowboy",response.header("Server"));
    }
}
