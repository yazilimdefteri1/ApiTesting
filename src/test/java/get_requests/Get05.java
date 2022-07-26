package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Adamz" and last name is "Dear"
     */

    @Test
    public void get05(){
        //1.Step: Set the Url
        spec.pathParam("first","booking").
                queryParams("firstname","Aaron", "lastname","Chen");

        // 2.Step:  Set the expected Data

        // 3.Step  SEND REQUEST and get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        //4. Step:  Do Assertion

        response.then().
                assertThat().
                statusCode(200);
        assertTrue(response.asString().contains("bookingid"));







    }

}
