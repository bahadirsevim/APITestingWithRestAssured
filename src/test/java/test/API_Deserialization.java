package test;

import TestDataDeposu.JsonPlaceHolderTestData;
import baseURLDeposu.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import javax.management.ObjectName;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_Deserialization extends JsonPlaceHolderBaseURL {
    @Test
    public void test01(){
        specJsonPlace.pathParam("pp1",70);

        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        Map<String, Object> reqBodyMap = jsonPlaceHolderTestData.createRequestBodyMap();

        Map<String, Object> expDataMap = jsonPlaceHolderTestData.createRequestBodyMap();

        Response response = given().
                contentType(ContentType.JSON).
                spec(specJsonPlace).
                when().
                body(reqBodyMap).
                put("{pp1}");

        Map<String, Object> respBodyMap = response.as(HashMap.class);
        assertEquals(jsonPlaceHolderTestData.SuccessStatusCode, response.getStatusCode());
        assertEquals(expDataMap.get("title"), respBodyMap.get("title"));
        assertEquals(expDataMap.get("body"), respBodyMap.get("body"));
        assertEquals(expDataMap.get("userId"), respBodyMap.get("userId"));
        assertEquals(expDataMap.get("id"), respBodyMap.get("id"));
    }
}
