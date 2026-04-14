package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
public class EventResourceTest {

    @Inject
    EventService eventService;
    @Inject
    EventRepository eventRepository;

    @Test
    public void testCreate() {
        given()
                .contentType("application/json")
                .body("{\"type\":\"TEST\",\"payload\":\"demo\"}")
                .when()
                .post("/events")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    public void trashTest () {
        given()
                .contentType("application/json")
                .body("{\"type\":\"TEST\",\"payload\":\"demo\"}")
                .when()
                .post("/events")
                .then()
                .statusCode(200)
                .body("id", notNullValue());

        List<EventEntity> eventEntityList = eventRepository.findLatest(1);
        System.out.println("{");
        System.out.println("sysyem.out  { " + eventEntityList.toString() + " } ");
        System.out.println("}");
    }
}
