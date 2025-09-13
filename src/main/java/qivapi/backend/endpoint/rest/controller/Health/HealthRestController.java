package qivapi.backend.endpoint.rest.controller.Health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthRestController {
    @GetMapping("ping")
    public String health() {
        return "pong";
    }
}
