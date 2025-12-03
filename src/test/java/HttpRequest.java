import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
public class HttpRequest {

    int id;
    //get verb
    @Test(priority=1)
    public void getUsers(){
        given() .headers("x-api-key","reqres_74f57b5f1159436a89c1798547099587")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data[1].id",equalTo(8))
                .log().all();
    }
    @Test(priority=2)
    public void createUser(){
        HashMap data = new HashMap();
        data.put("name","israa");
        data.put("job","QA Engineer");
        Response response = given().headers("x-api-key","reqres_74f57b5f1159436a89c1798547099587")
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("israa"))
                .body("job", equalTo("QA Engineer"))
                .log().all()
                .extract().response();
        id = response.jsonPath().getInt("id");
        System.out.println("User ID: " + id);

        //JsonPath JsonPathVal = response.jsonPath();







    }




    @Test(priority=3,dependsOnMethods = "createUser")
    public void updateUserInfo(){

        HashMap data = new HashMap();
        data.put("name","Reham");
        data.put("job","SW");
        given().headers("x-api-key","reqres_74f57b5f1159436a89c1798547099587")
                .contentType("application/json")
                .body(data)
                .pathParam("id",id)
                .when()
                .put("https://reqres.in/api/users/{id}")
                .then()
                .statusCode(200)
                .body("name",equalTo("Reham")).body("job",equalTo("SW"))
                .log().all();

    }

    @Test (priority = 4)
    public void delete_User(){

        given().headers("x-api-key","reqres_74f57b5f1159436a89c1798547099587")
                .pathParam("id",id)
                .when()
                .delete("https://reqres.in/api/users/{id}")
                .then().statusCode(204)
                .log().all();
    }
    @Test(priority=5)
    public void getUserVerifyDeletion(){
        given() .headers("x-api-key","reqres_74f57b5f1159436a89c1798547099587")
                .pathParam("id",id)
                .when()
                .get("https://reqres.in/api/users/{id}")
                .then()
                .statusCode(404)
                .log().all();
    }
}
