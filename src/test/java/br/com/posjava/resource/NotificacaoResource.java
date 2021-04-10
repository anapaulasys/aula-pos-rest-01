package br.com.posjava.resource;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.vertx.core.json.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.Locale;

@QuarkusTest
public class NotificacaoResource {

    @Test
    public void testStatusCodeGet() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/notificacao")
                .then()
                .statusCode(200);

    }

    @Test
    public void testStatusCodePost() {
        JsonObject body = new JsonObject();
        body.put("paciente", "Jorge de Abreu");
        body.put("unidadeSaude", "UPA");
        body.put("positivo", true);
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post("/api/notificacao")
                .then()
                .statusCode(201);
    }


    @Test
    public void testMuitosRegistros() {

        Faker faker = new Faker(new Locale("pt-br"));

        for (int i = 0; i < 1000; i++) {

            JsonObject body = new JsonObject();
            body.put("paciente", faker.name().fullName());
            body.put("unidadeSaude", "UPA");
            body.put("positivo", i%2==0);

            RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(body.toString())
                    .when()
                    .post("/api/notificacao")
                    .then()
                    .statusCode(201);
        }
    }
}
