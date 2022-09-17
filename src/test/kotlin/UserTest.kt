import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

@QuarkusTest
class UserTest {
    /*@Test
    fun `Test user registration`() {
        given()
            .contentType("application/json")
            .body("{\"user\": {\n" +
                    "  \"username\": \"tomas-soukup\",\n" +
                    "  \"email\": \"soukup@gmail.com\",\n" +
                    "  \"password\": \"pass\"\n" +
                    "}}").
        `when`()
            .post("/users").
        then()
            .statusCode(201)
            .body("user.username", equalTo("tomas-soukup"))
    }*/

    @Test
    fun `Test Login`() {
        given()
            .contentType("application/json")
            .body(
                "{\"user\": {\n" +
                        "  \"email\": \"teacher@teacher.cz\",\n" +
                        "  \"password\": \"pass\"\n" +
                        "}}"
            ).`when`()
            .post("/users/login").then()
            .statusCode(200)
            .body("user.email", equalTo("teacher@teacher.cz"))
            .extract().body().jsonPath().getString("user.token")

    }

    fun getToken(email: String, password: String): String = given()
            .contentType("application/json")
            .body(
                "{\"user\": {\n" +
                        "  \"email\": \"$email\",\n" +
                        "  \"password\": \"$password\"\n" +
                        "}}"
            ).`when`()
            .post("/users/login").then()
            .extract().body().jsonPath().getString("user.token")

    @Test
    fun `Add new group`() {
        val token: String = getToken("teacher@teacher.cz", "pass")
        println(token)
        given()
            .contentType("application/json")
            .header("Authorization", "Bearer $token")
            .body(
                "{\n" +
                        "    \"group\": {\n" +
                        "        \"title\": \"4.K\",\n" +
                        "        \"description\": \"ctvrty rocnik cybersec\",\n" +
                        "        \"userIds\": [\n" +
                        "            4,6\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}"
            ).`when`()
            .post("/teacher/group").then()
            .statusCode(201)
            .body("group.title", equalTo("4.K"))
    }

    @Test
    fun `Add new group with student account`() {
        val token = getToken("student@student.cz", "pass")
        println(token)
        given()
            .contentType("application/json")
            .header("Authorization", "Bearer $token")
            .body(
                "{\n" +
                        "    \"group\": {\n" +
                        "        \"title\": \"4.K\",\n" +
                        "        \"description\": \"ctvrty rocnik cybersec\",\n" +
                        "        \"userIds\": [\n" +
                        "            4,6\n" +
                        "        ]\n" +
                        "    }\n" +
                        "}"
            ).`when`()
            .post("/teacher/group").then()
            .statusCode(403)
    }

    @Test
    fun `Create new class`() {
        val token = getToken("teacher@teacher.cz", "pass")
        println(token)
        given()
            .contentType("application/json")
            .header("Authorization", "Bearer $token")
            .body(
                "{\"class\": {\n" +
                        "    \"title\": \"bIoLoGiE\",\n" +
                        "    \"groupId\": 4,\n" +
                        "    \"teacherId\": 3\n" +
                        "}}"
            ).`when`()
            .post("/teacher/class").then()
            .statusCode(201)
            .body("class.title", equalTo("bIoLoGiE"))
    }
}
