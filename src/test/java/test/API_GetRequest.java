package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class API_GetRequest {

    /* https://jsonplaceholder.typicode.com/posts/44 adresine Get request gönder.
        Request sonucunda dönen response'un:
        - Status code: 200
        - Content Type: JSON
        - Response Body userID: 5
        - Response Body title: "optio dolor molestias sit"
        olması gerektiğini doğrulayın.
     */

    @Test
    public void get01(){
        // 1- Request URL ve Body oluştur
        String url = "https://jsonplaceholder.typicode.com/posts/44";

        // 2- Expected data oluştur
        JSONObject expBody = new JSONObject();
        expBody.put("userId", 5);
        expBody.put("title", "optio dolor molestias sit");

        // 3- Respons'u kaydet
        Response response = given().when().get(url);
        //response.prettyPrint(); -Kaynakları yormayalım

        // 4- Assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        JsonPath actBody = response.jsonPath();
        Assert.assertEquals(expBody.get("userId"),actBody.get("userId"));
        Assert.assertEquals(expBody.get("title"),actBody.get("title"));

    }

}
