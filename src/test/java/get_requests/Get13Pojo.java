package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GorestPojos.GoRestDataPojo;
import pojos.GorestPojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
/*
    Given
    https://gorest.co.in/public/v1/users/2508
    When
    User send GET Request to the URL
            Then
    Status Code should be 200
    And
    Response body should be like
    {
    "meta": null,
    "data": {
        "id": 2508,
        "name": "Akshita Nehru",
        "email": "nehru_akshita@jast.info",
        "gender": "female",
        "status": "active"
    }
    }

 */

    @Test
    public void getPojo01(){

        // 1. Step Set The Url
        spec.pathParams("first","users","second",2508);

        //2. Step Set the Expected Data
        GoRestDataPojo goRestDataPojo= new GoRestDataPojo(2508,"Akshita Nehru","nehru_akshita@jast.info","female","active");
        GoRestResponseBodyPojo responsePojo= new GoRestResponseBodyPojo(null,goRestDataPojo);


        // 3. Step Send the GET Request and get the Response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        // 4. Do Assertion
       // GoRestDataPojo actualDataPojo= response.as(GoRestDataPojo.class);
        GoRestResponseBodyPojo actualResponsePojo= response.as(GoRestResponseBodyPojo.class);

        assertEquals(200,response.getStatusCode());

        assertEquals(responsePojo.getMeta(),actualResponsePojo.getMeta());

        assertEquals(responsePojo.getData().getId(),actualResponsePojo.getData().getId());
        assertEquals(responsePojo.getData().getName(),actualResponsePojo.getData().getName());
        assertEquals(responsePojo.getData().getEmail(),actualResponsePojo.getData().getEmail());
        assertEquals(responsePojo.getData().getGender(),actualResponsePojo.getData().getGender());
        assertEquals(responsePojo.getData().getStatus(),actualResponsePojo.getData().getStatus());

    }
}
