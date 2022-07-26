package put_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiPojos.DataPojo;
import pojos.DummyRestApiPojos.ResponseDataPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111122,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111122,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

     /*
    TEST CASEMİZ

    Given
        https://dummy.restapiexample.com/api/v1/update/21
        {
         "employee_name": "Ali Cans",
         "employee_salary": 111122,
         "employee_age": 23,
         "profile_image": "Perfect image",
          "id": 4891
         }
     When
        User Sends PUT Request the (HTTP Request Method:)
     Then
        Status code is 200
     And
       Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111122,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

    @Test
    public void put01(){

        // 1. Step  Set the URL
        spec.pathParams("first","update","second",21);

        // 2. Step Set the Expected Data
        DataPojo dataPojo= new DataPojo("Ali Can",111122,23,"Perfect image");
        ResponseDataPojo expectedData= new ResponseDataPojo("success", dataPojo,"Successfully! Record has been updated.");


        // 3. Step Send the PUT Request and get the Response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(dataPojo).when().put("/{first}/{second}");
        response.prettyPrint();

        // 4. Step Do Assertion

        ResponseDataPojo actualData= JsonUtil.convertJsonToObject(response.asString(),ResponseDataPojo.class);
        System.out.println(actualData);

        // i  Status Code 200
        assertEquals(200,response.getStatusCode());

        // ii. "statuse" is "success"
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        // iii. "message" is "Successfully! Record has been updated."
        assertEquals(expectedData.getMessage(),actualData.getMessage());

        // iv. "employee_name" is "Ali Can"
        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        // v. "employee_salary" is 111122
        assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        // vi. "employee_age" is 23
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        // vii. "profile_image": "Perfect image",
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());
    }


}
