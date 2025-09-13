package qivapi.backend.endpoint.rest.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qivapi.backend.service.ApiCheckService;

import java.util.Map;

@RestController
@AllArgsConstructor
public class ApiCheckController {

    private final ApiCheckService apiCheckService;

    @PostMapping("check")
    public ResponseEntity<String> checkAPI(@RequestBody String apiUrl){
        return apiCheckService.checkAPI(apiUrl);
    }
    
}
