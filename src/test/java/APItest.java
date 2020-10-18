import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class APItest {

    private static final String URI = "https://api.github.com/repos";
    private static final String METROLAB = "/metrolab";
    private static final String SINGLEDATE = "/SingleDateAndTimePicker";
    private static final String PATH_TO_JSON = "src/test/resources/response.json";

    @Test
    void TC001() throws IOException {

        FileWriter fileWriter = new FileWriter(PATH_TO_JSON);
        Response response = RestAssured.get(URI + METROLAB + SINGLEDATE);

        fileWriter.write(response.getBody().prettyPrint());

        given()
                .get(URI + METROLAB + SINGLEDATE)
                .then()
                .body("id", equalTo(132619461))
                .body("node_id", equalTo("MDEwOlJlcG9zaXRvcnkxMzI2MTk0NjE="))
                .body("name", equalTo("SingleDateAndTimePicker"))
                .body("full_name", equalTo("seatcode/SingleDateAndTimePicker"))
                .body("private", equalTo(false))
                .body("owner.login", equalTo("seatcode"))
                .body("owner.id", equalTo(28804024))
                .body("owner.node_id", equalTo("MDEyOk9yZ2FuaXphdGlvbjI4ODA0MDI0"))
                .body("owner.avatar_url", equalTo("https://avatars3.githubusercontent.com/u/28804024?v=4"))
                .body("owner.gravatar_id", equalTo(""))
                .body("owner.url", equalTo("https://api.github.com/users/seatcode"))
                .body("owner.html_url", equalTo("https://github.com/seatcode"))
                .body("owner.followers_url", equalTo("https://api.github.com/users/seatcode/followers"))
                .body("owner.following_url", equalTo("https://api.github.com/users/seatcode/following{/other_user}"))
                .body("owner.gists_url", equalTo("https://api.github.com/users/seatcode/gists{/gist_id}"))
                .body("owner.starred_url", equalTo("https://api.github.com/users/seatcode/starred{/owner}{/repo}"))
                .body("owner.subscriptions_url", equalTo("https://api.github.com/users/seatcode/subscriptions"))
                .body("owner.organizations_url", equalTo("https://api.github.com/users/seatcode/orgs"))
                .body("owner.repos_url", equalTo("https://api.github.com/users/seatcode/repos"))
                .body("owner.events_url", equalTo("https://api.github.com/users/seatcode/events{/privacy}"))
                .body("owner.received_events_url", equalTo("https://api.github.com/users/seatcode/received_events"))
                .body("owner.type", equalTo("Organization"))
                .body("owner.site_admin", equalTo(false))
                .log().all();
    }

    @Test
    void TC002() {

        given()
                .get(URI + METROLAB + SINGLEDATE)
                .then()
                .body("owner.login", equalTo("seatcode")) //metrolab doesn't exists on response
                .log().all();
    }

}
