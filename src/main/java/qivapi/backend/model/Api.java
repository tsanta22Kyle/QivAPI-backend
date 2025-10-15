package qivapi.backend.model;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.channels.UnresolvedAddressException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public record Api(String id,
                  String apiUrl,
                  String name,
                  String description,
                  LocalDateTime created,
                  LocalDateTime updated,
                  List<ApiHealth> apiHealthChecks,User user) {
    public ResponseEntity<String> checkIsActive() throws UnknownHostException {
        try {

            RestClient myClient = RestClient.create();
            log.info("api url {} ", apiUrl);
            var host = new java.net.URL(apiUrl).getHost();
            InetAddress.getByName(host);
            var response = myClient.get().uri(this.apiUrl).retrieve().toEntity(String.class);
            log.info("api response {}", response);
            return response;
        } catch (UnknownHostException | MalformedURLException e) {
            throw new UnknownHostException(e.getMessage());
        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(e.getStatusCode());
        }
    }


}
