package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*; // yıldız koyarak restAssured 'ten given,when,then and hepsini import ettik

public class Get01 {

    /*
          1) Postman manual API testi için kullanılır
          2) API otomasyon için Rest-Assured Library kullanıyoruz
          3) Otomasyon kodlarının yazımı için şu adımları izliyoruz
                a) Gereksinimleri anlama
                b) Test Case yazılır
                       i) Test Case yazıı için Gherkin Language kullanıyoruz
                            Gherkin bazı keywordlere sahiptir
                                x) Given: ön koşullar için kullanılır
                               xx) When : Aksiyonlar için kullanılır--> Get, Put, ...
                              xxx) Then : Çıktılar veya dönütler için kullanılır--Doğrulama, response, ...
                             xxxx) And  : Çoklu işlemler için kullanılır

                c) Test kodunun yazımı
                     x) Set the URL:
                    xx) Set the Expected Data(POST-PUT-PATCH) -->
                   xxx) TYPE CODE TO SEND REQUEST-->
                  xxxx) Do Assertion  --> Doğrulama Yapma
     */

    /*
        Given https://restful-booker.herokuapp.com/booking/3 --> endpoint
        When  User sends a GET Request to the url--> url Den 3 numaralı datayı get yaparak çağıracağım
        Then  HTTP Status Code should be 200 --> Status code'n 200 olduğunu kontrol edecem
        And   Content Type should be JSON--Z  içerik tipinin JSON olup olmadığını kontrol edecem
        And   Status Line should be HTTP/1.1 200 OK -->
     */


    @Test
    public void get01(){
     //    x) Set the URL:
        String url ="https://restful-booker.herokuapp.com/booking/555";

     //   xx) Set the Expected Data(POST-PUT-PATCH) -->

     //  xxx) TYPE CODE TO SEND REQUEST-->
       Response response = given().when().get(url);
       response.prettyPrint();

     // xxxx) Do Assertion  --> Doğrulama Yapma

        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
        //response.then().assertThat().statusCode(400).contentType("application/json").statusLine("HTTP/1.1 200 OK");
        // status code u 400 yapınca
        // java.lang.AssertionError: 1 expectation failed.
        //Expected status code <400> but was <200>.  hatasını veriyor

         // response.then().assertThat().statusCode(200).contentType("application/xml").statusLine("HTTP/1.1 200 OK");
        // contentType ı xml yapınca
        // java.lang.AssertionError: 1 expectation failed.
        //Expected content-type "application/xml" doesn't match actual content-type "application/json; charset=utf-8".  hatası veriyor

        // 'Status Code' nasıl yazdırılır
        System.out.println("Status Code : "+ response.statusCode());


        // 'Content Type' nasıl yazdırılır
        System.out.println("Content Type : "+ response.contentType());

        // 'Status Line' nasıl yazdırılır
        System.out.println("Status Line : "+ response.statusLine());

        // Header nasıl yazdırılır
        System.out.println("Header : "+ response.header("User-Agent"));

        // 'Headers' nasıl yazdırılır
        System.out.println("Headers :\n"+ response.headers());

        // 'Time' nasıl yazdırılır
        System.out.println("Time :"+ response.getTime());
    }
}
