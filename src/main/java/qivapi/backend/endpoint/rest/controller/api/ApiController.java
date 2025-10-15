package qivapi.backend.endpoint.rest.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import qivapi.backend.endpoint.rest.model.CreateOrUpdateApi;
import qivapi.backend.model.Api;
import qivapi.backend.model.ApiHealth;
import qivapi.backend.service.ApiCheckService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ApiController {

    private final ApiCheckService apiCheckService;

    @PostMapping("check")
    public ResponseEntity<ApiHealth> checkAPI(@RequestBody CreateOrUpdateApi api) {
        return apiCheckService.checkAPI(api);
    }

    @GetMapping("apis/{userId}")
    public ResponseEntity<List<Api>> getAllApiByUserId(@PathVariable String userId) {
       return apiCheckService.findAllByUserId(userId);
    }


}
