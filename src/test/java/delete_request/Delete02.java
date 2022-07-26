package delete_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiPojos.DataPojo;
import pojos.DummyRestApiPojos.DummyDeletePojo;
import pojos.DummyRestApiPojos.ResponseDataPojo;
import utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestApiBaseUrl {

    /*
    URL: https://dummy.restapiexample.com/api/v1/delete/2
   HTTP Request Method: DELETE Request
   Test Case: Type by using Gherkin Language
   Assert:     i) Status code is 200
           ii) "status" is "success"
          iii) "data" is "2"
           iv) "message" is "Successfully! Record has been deleted"
 */

     /*
    TEST CASEMİZ

    Given
        https://dummy.restapiexample.com/api/v1/delete/2
    When
        User Sends DELETE Request the
    Then
        Status code is 200
    And
        "status" is "success"
    And
        "data" is "2"
    And
        "message" is "Successfully! Record has been deleted"
     */
     @Test
     public void delete01() {

         // 1. Step  Set the URL
         spec.pathParams("first", "delete", "second", 2);

         // 2. Step Set the Expected Data
         DummyDeletePojo expectedData= new DummyDeletePojo("success","2","Successfully! Record has been deleted");

         // 3. Step Send the PUT Request and get the Response
         Response response = given().spec(spec).contentType(ContentType.JSON).when().delete("/{first}/{second}");
         response.prettyPrint();

         // 4. Step Do Assertion

         DummyDeletePojo actualData = JsonUtil.convertJsonToObject(response.asString(), DummyDeletePojo.class);
         System.out.println(actualData);

         // i  Status Code 200
         assertEquals(200, response.getStatusCode());

         // ii. "statuse" is "success"
         assertEquals(expectedData.getStatus(),actualData.getStatus());

         // iii.  "data" is "2"
         assertEquals(expectedData.getData(),actualData.getData());

         // iv. "message" is "Successfully! Record has been deleted"
         assertEquals(expectedData.getMessage(),actualData.getMessage());

     }
}
