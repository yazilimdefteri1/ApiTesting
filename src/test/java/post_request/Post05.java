package post_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiPojos.DataPojo;
import pojos.DummyRestApiPojos.ResponseDataPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post05 extends DummyRestApiBaseUrl {

     /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body:
       Test Case: Type by using Gherkin Language
       Assert:
                    {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                     }
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    /*
    TEST CASEMİZ

    Given
        https://dummy.restapiexample.com/api/v1/create
        {
         "employee_name": "Tom Hanks",
         "employee_salary": 111111,
         "employee_age": 23,
         "profile_image": "Perfect image",
          "id": 4891
         }
     When
        User sends the Post Request
     Then
        Status code is 200
     And
        Response body should be like the following
         {
          "status": "success",
          "data": {
          "employee_name": "Tom Hanks",
          "employee_salary": 111111,
          "employee_age": 23,
          "profile_image": "Perfect image",
          "id": 4891
          },
           "message": "Successfully! Record has been added."
                    }

     */

    @Test
    public void post01(){

        // 1. Step  Set the URL
        spec.pathParam("first","create");

        // 2. Step Set the Expected Data
        DataPojo dataPojo= new DataPojo("Tom Hanks",111111,23,"Perfect image");
        ResponseDataPojo expectedData= new ResponseDataPojo("success", dataPojo,"Successfully! Record has been added.");


        // 3. Step Send the POST Request and get the Response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(dataPojo).when().post("/{first}");
        response.prettyPrint();

        // 4. Step Do Assertion

        ResponseDataPojo actualData= JsonUtil.convertJsonToObject(response.asString(),ResponseDataPojo.class);
        System.out.println(actualData);

        // i  Status Code 200
        assertEquals(200,response.getStatusCode());

        // ii. "statuse" is "success"
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        // iii. "message" is "Successfully! Record has been fetched."
        assertEquals(expectedData.getMessage(),actualData.getMessage());

        // Vi. "employee_name" is "Tom Hanks"
        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        // v. "employee_salary" is 111111
        assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        // vi. "employee_age" is 23
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        // vii. "profile_image": "Perfect image",
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());
    }

}
