import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class APItest {

    private static final String URI = "https://api.github.com/repos";
    private static final String METROLAB = "/metrolab";
    private static final String SINGLEDATE = "/SingleDateAndTimePicker";
    private static final String PATH_TO_CURRENT_JSON = "src/test/resources/output/response.json";
    private static final String PATH_TO_BASE_JSON = "src/test/resources/BaseResponse.json";


    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    @Test
    void TC001() throws Exception {

        Response response = RestAssured.get(URI + METROLAB + SINGLEDATE);

        try {
            Files.deleteIfExists(Paths.get(PATH_TO_CURRENT_JSON));
        } catch (Exception e) {
            // Exception ignored
        }

        FileWriter fileWriter = new FileWriter(PATH_TO_CURRENT_JSON);
        fileWriter.write(response.getBody().prettyPrint());

        String baseJson = readFileAsString(PATH_TO_BASE_JSON);
        String currentJson = readFileAsString(PATH_TO_CURRENT_JSON);

        if (baseJson.equals(currentJson)) {
            System.out.println("Base JSON and obtained JSON match");
        } else {
            System.out.println("Base JSON and obtained JSON NOT match");
        }

    }

    @Test
    void TC002() {

        given()
                .get(URI + METROLAB + SINGLEDATE)
                .then()
                .body("owner.login", equalTo("seatcode")) //metrolab doesn't exists on owner.login
                .log().all();
    }

}
