package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {

     /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language

           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    /*
    TEST CASEMİZ

    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User send GET Request to URL
    Then
        Status Code 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        vi) Total salary of all employees is 6,644,770
     */

    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParam("first","employees");

        //2. Step: Set the expected data

        //3. Step: Send the Get Request and get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}");
        // response.prettyPrint();

        //4. Step: Do Assertion

        response.then().
                assertThat().
                statusCode(200).//i) Status code is 200
                body("data.id",hasSize(24),//ii) There are 24 employees
                "data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));// iii) "Tiger Nixon" and "Garrett Winters" are among the employees

        // iv) The greatest age is 66
        JsonPath json = response.jsonPath();
        List<Integer> ageList = json.getList("data.findAll{it.id>0}.employee_age");
        System.out.println(ageList);
        Collections.sort(ageList);
        System.out.println(ageList);
        assertEquals(66, (int) ageList.get(ageList.size() - 1));

        /*//Assert that the greatest age is 66;  BASKA BİR YOL İLE COZUMU
        JsonPath json = response.jsonPath();
        List<Integer> ageList = json.getList("data.findAll{it.id>0}.employee_age");
        System.out.println(ageList);
        Collections.sort(ageList);
        System.out.println(ageList);
        Assert.assertEquals(66, (int) ageList.get(ageList.size() - 1));
*/
        /*
        //The greatest age is 66   BASKA BİR YOL İLE COZUMU
        List<Integer> ageList = response.jsonPath().getList("data.findAll{it.id>0}employee_age");
        int actualMaxAge = ageList.stream().reduce(Integer.MIN_VALUE, (x, y) -> x > y ? x : y);
        assertEquals(66, actualMaxAge);
         */

        //******************************************************************************************************

        //  v) The name of the lowest age is "Tatyana Fitzpatrick"
        String grovyString ="data.findAll{it.employee_age == " + ageList.get(0)+ "}.employee_name";
        String minAgeEmployeeName= json.getString(grovyString);
        System.out.println(minAgeEmployeeName);

        assertEquals("[Tatyana Fitzpatrick]", minAgeEmployeeName);

        /*  BASKA BİR YOL İLE COZUMU
        List<String> nameList=json.getList("data.findAll{it.employee_age==19}.employee_name");
        System.out.println(nameList);
        assertEquals("Tatyana Fitzpatrick",nameList.get(0));
         */

    /*
         //Assert that the name of the lowest age is "Tatyana Fitzpatrick";  BASKA BİR YOL İLE COZUMU
        String minAgeEmployeeName = json.getString("data.findAll{it.employee_age==19}.employee_name");
        System.out.println("min. age employee name : " + minAgeEmployeeName);
    */
        /*
        //The name of the lowest age is "Tatyana Fitzpatrick"    BASKA BİR YOL İLE COZUMU
        int actualMinAge = ageList.stream().reduce(Integer.MAX_VALUE, (x, y) -> x > y ? y : x);
        String actualMinAgeName = response.jsonPath().getString("data.findAll{it.employee_age==" + actualMinAge + "}.employee_name").replace("[", "").replace("]", "");
        assertEquals("Tatyana Fitzpatrick", actualMinAgeName);
        */

        //******************************************************************************************************

        //vi) Total salary of all employees is 6,644,770
        List<Integer> salaryList=json.getList("data.findAll{it.employee_age>0}.employee_salary");
        int sum=0;
        for (Integer w:salaryList) {
            sum+=w;
        }
        System.out.println(sum);

        assertEquals(6644770,sum);

        // int sumLambda= salaryList.stream().reduce(0,(t,u)->t+u);
        int sumLambda= salaryList.stream().reduce(0,Math::addExact); //Math Class ile
        assertEquals(6644770,sumLambda);

        /*
        //Asert that total salary of all employees is 6,644,770  BASKA BİR YOL İLE COZUMU

        List<Integer> salaryList = json.getList("data.findAll{it.id>0}.employee_salary");

        //=========1. WAY================\\
        int sum = 0;
        for (Integer w : salaryList) {
            sum = sum + w;
        }
        System.out.println(sum);
        Assert.assertEquals(6644770, sum);

        //=========2. WAY (LAMBDA)================\\
        int sum2=salaryList.stream().reduce(0,(x,y)-> x+y);
        Assert.assertEquals(6644770, sum2);

        //=========3. WAY (METHOD REFERENCE)================\\
        int sum3 = salaryList.stream().reduce(0, Math::addExact);
        Assert.assertEquals(6644770, sum3);
        */

        /*
        /* //Total salary of all employees is 6,644,770
        List<Integer> salaryList = response.jsonPath().getList("data.findAll{it.id>0}.employee_salary");
        int actualTotalSalary = salaryList.stream().reduce(0, Integer::sum);
        assertEquals(6644770, actualTotalSalary);
        */

    }
}
