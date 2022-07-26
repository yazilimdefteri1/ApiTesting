package get_requests;
import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiPojos.DataPojo;
import pojos.DummyRestApiPojos.ResponseDataPojo;
import test_data.DummyTestData;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Get17 extends DummyRestApiBaseUrl {

    /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */

       /*
    TEST CASEMİZ

    Given
        https://dummy.restapiexample.com/api/v1/employee/1
    When
        User send GET Request to URL
    Then
        Status Code 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void get01() {
        //1. Step: Set the Url
        spec.pathParams("first", "employee", "second", 1);

        //2. Step: Set the expected data

        //3. Step: Send the Get Request and get the Response
        DataPojo expectedDataPojo = new DataPojo("Tiger Nixon",320800,61,"");
        ResponseDataPojo expectedPojo= new ResponseDataPojo("success",expectedDataPojo,"Successfully! Record has been fetched.");

        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        // 4. Step Do Assertion

        // i  Status Code 200
        response.then().assertThat().statusCode(200);

        ResponseDataPojo actualPojo= JsonUtil.convertJsonToObject(response.asString(),ResponseDataPojo.class);
        System.out.println(actualPojo);
        System.out.println(expectedPojo);

        // vi. "message" is "Successfully! Record has been fetched."
        assertEquals(expectedPojo.getMessage(),actualPojo.getMessage());
        // vii. "statuse" is "success"
        assertEquals(expectedPojo.getStatus(),actualPojo.getStatus());

        // ii. "employee_name" is "Tiger Nixon"
      assertEquals(expectedPojo.getData().getEmployee_name(),actualPojo.getData().getEmployee_name());
        // iii. "employee_salary" is 320800
       assertEquals(expectedPojo.getData().getEmployee_salary(),actualPojo.getData().getEmployee_salary());
        // iv. "employee_age" is 61
        assertEquals(expectedPojo.getData().getEmployee_age(),actualPojo.getData().getEmployee_age());
        // v. "employee_profilImage is ""
        assertEquals(expectedPojo.getData().getProfile_image(),actualPojo.getData().getProfile_image());

    }


    // DUMMYTESTDATA  dan oop ile obje oluşturup ve method çağırarak Çözümü
    @Test
    public  void  get02(){
        //1.Step : Set the url
        spec.pathParams("first","employee","second",1);

        //2.Step : Set the expected data
        DummyTestData dummyTestData=new DummyTestData();
        String expectedPojo=dummyTestData.expectedDataInString("success","Tiger Nixon",320800,61,"","Successfully! Record has been fetched.");

        ResponseDataPojo expectedData=JsonUtil.convertJsonToObject(expectedPojo,ResponseDataPojo.class);
        //3.Step : Send the get request and get the response
        Response response= given().spec(spec).when().get("/{first}/{second}");


        //4.Step : Do Assertion
       ResponseDataPojo actualData=JsonUtil.convertJsonToObject(response.asString(),ResponseDataPojo.class);

        // i  Status Code 200
        assertEquals(200,response.getStatusCode());
        // ii. "employee_name" is "Tiger Nixon"
        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        // iii. "employee_salary" is 320800
        assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        // iv. "employee_age" is 61
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        // v. "employee_profilImage is ""
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());

        // vi. "statuse" is "success"
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        // vii. "statuse" is "success"
        assertEquals(expectedData.getMessage(),actualData.getMessage());






    }
}