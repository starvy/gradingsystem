import io.restassured.RestAssured.given
import io.restassured.RestAssured.`when`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `Test user registration, login and get`() {
        given()
            .contentType("application/json")
            .body("{\"user\": {\n" +
                    "  \"username\": \"testuserr\",\n" +
                    "  \"email\": \"testt@gmail.com\",\n" +
                    "  \"password\": \"testuser\"\n" +
                    "}}").
        `when`()
            .post("/users").
        then()
            .statusCode(201)
            .body("user.username", equalTo("testuserr"))

    }
}