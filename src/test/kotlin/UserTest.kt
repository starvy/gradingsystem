import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

class UserTest {
    @Test
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
    }

    @Test
    fun `Test user login`() {
        given()
            .contentType("application/json")
            .body(
                "{\"user\": {\n" +
                        "  \"email\": \"soukup@gmail.com\",\n" +
                        "  \"password\": \"pass\"\n" +
                        "}}"
            ).`when`()
            .post("/users/login").then()
            .statusCode(200)
            .body("user.email", equalTo("soukup@gmail.com"))
    }
}
