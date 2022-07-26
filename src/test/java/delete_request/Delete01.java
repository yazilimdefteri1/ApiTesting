package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {

   /* Given
    https://jsonplaceholder.typicode.com/todos/198
    When
    I send DELETE Request to the Url
            Then
    Status code is 200
    And Response body is { }


    k. emre göral
  8:45 PM
    {
        "userId": 10,
            "id": 198,
            "title": "quis eius est sint explicabo",
            "completed": true
    }

    */

    @Test
    public void delete01(){

        // 1. Step Set the Url
        spec.pathParams("first","todos", "second",198);

        // 2. Step Set the expected data
         Map<String, Object> expectedDataMap=new HashMap<>();

         //3. Step Send DELETE Requast and get the response
        Response response= given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        // 4. Step  Do Assertion
        //1.YOL
        Map<String, Object> actualMap= response.as(HashMap.class);
        response.then().assertThat().statusCode(200);
        assertEquals(actualMap,expectedDataMap);

        //2.YOL
        assertTrue(actualMap.size()==0);  // map'in uznluğunun olmaması onun boş olduğunu yani silindiğini gözterir
        assertTrue(actualMap.isEmpty());  // map'in  boş olduğuna baktık yani silindiğini gözterir
        // TAVSİYE EDİLEN İSEMPTY'DİR YANİ BOŞ OLDUĞUNU KONTROL ETMEK
        // (SİZE BAZI DURUMLARDA TAM NET SONUÇ VERMEYEBİLİR.

        //DELETE REQUST YAPMADAN ÖNCE "POST REQUEST "  YAPTIKTAN SONRA dLETE YAPIP SİLMEKİYİZ
    }

}
