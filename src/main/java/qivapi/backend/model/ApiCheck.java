package qivapi.backend.model;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

@Slf4j
public record ApiCheck (Boolean isActive , String apiUrl ){
    public ResponseEntity<String> checkIsActive(){
        RestClient myClient = RestClient.create();
        log.info("api url {} ",apiUrl);
        var response = myClient.get().uri(this.apiUrl).retrieve().toEntity(String.class);
        return response;
    }

}
