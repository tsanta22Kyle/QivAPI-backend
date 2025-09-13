package qivapi.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import qivapi.backend.model.ApiCheck;

@Service
public class ApiCheckService {

    public ResponseEntity<String> checkAPI(String apiUrl){
        var apiHealth = new ApiCheck(false,apiUrl);
        return (apiHealth.checkIsActive());
    }

}
