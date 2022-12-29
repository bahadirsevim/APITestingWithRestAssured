package test;

import TestDataDeposu.JsonPlaceHolderTestData;
import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class API_BaseURL_TestDataKullanimi extends JsonPlaceHolderBaseURL {

    /* https://jsonplaceholder.typicode.com/posts/22 URL adresine bir GET
        Request yollandığında döen response'un;
        - Status Code: 200
        - Response Body:
            {
                "userId":3,
                "id":22,
                "title":"dolor sint quo a velit explicabo quia nam",
                "body":"eos que et ipsum ipsam suscipit aut sed omnis non odio expedita earum mollitia molesiae aut atque rem suscipit nam impedit esse"
            }
            şeklinde olduğunu test ediniz.
     */

    @Test
    public void test01(){
        // 1- URL ve Request body oluştur
        specJsonPlace.pathParam("pp1",22);

        // 2- Expected data oluştur
        JsonPlaceHolderTestData jsonPlaceHolder = new JsonPlaceHolderTestData();
        JSONObject expBody = jsonPlaceHolder.createExpectedData();

        // 3- Respons'u kaydet
        Response response = given().spec(specJsonPlace).when().get("{pp1}");

        // 4- Assertion
        JsonPath actBody = response.jsonPath();
        Assert.assertEquals(jsonPlaceHolder.SuccessStatusCode, response.getStatusCode());
        Assert.assertEquals(expBody.getInt("userId"), actBody.getInt("userId"));
        Assert.assertEquals(expBody.getInt("id"), actBody.getInt("id"));
        Assert.assertEquals(expBody.getString("title"), actBody.getString("title"));
        Assert.assertEquals(expBody.getString("body"), actBody.getString("body"));

    }

}
