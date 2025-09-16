package qivapi.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import qivapi.backend.model.ApiCheck;
import qivapi.backend.model.ApiHealthResponse;
import qivapi.backend.model.SecurityInfo;

import java.net.UnknownHostException;

@Service
@Slf4j
public class ApiCheckService {
    public ResponseEntity<ApiHealthResponse> checkAPI(String apiUrl) {
        var apiCheck = new ApiCheck( apiUrl);
        try {
            return ResponseEntity.ok(checkSecurity(apiCheck));
        } catch (HttpClientErrorException e) {
            log.warn("Api response {}", e.getMessage());
            return ResponseEntity.ok(checkSecurity(apiCheck));
        }
    }

    public ApiHealthResponse checkSecurity(ApiCheck apiCheck) {
        try {

            Boolean ssl = apiCheck.apiUrl().startsWith("https://");
            var apiResponse = apiCheck.checkIsActive();
            if (apiResponse.getHeaders().getContentType().toString().contains("text/html")) {
                var Health = new ApiHealthResponse(apiCheck.apiUrl(), true, true, null, false);
                log.warn("not an api , probably an website... {} {}", apiCheck.apiUrl(), apiResponse);
                return Health;
            }
            Boolean sts = apiResponse.getHeaders().containsKey("Strict-Transport-Security");
            Boolean nosniff = "nosniff".equalsIgnoreCase(apiResponse.getHeaders().getFirst("X-Content-Type-Options"));
            SecurityInfo securityInfo = new SecurityInfo(ssl, sts, nosniff);
            var apiHealth = new ApiHealthResponse(apiCheck.apiUrl(), true, true, securityInfo, true);
            if (apiResponse.getHeaders().getContentType().toString().contains("application/json")) {
                return apiHealth;
            }
            return apiHealth;
        } catch (UnknownHostException unresolvedAddressException) {
            log.warn("Api address not exist response {}", unresolvedAddressException.getMessage());
            var apiHealth = new ApiHealthResponse(apiCheck.apiUrl(), false, false, null, false);
            return apiHealth;
        } catch (HttpClientErrorException e){
            log.warn("auto check .... {}", e.getMessage());
            var apiHealth = new ApiHealthResponse(apiCheck.apiUrl(), true, true, null, true);
            return apiHealth;
        }
    }
}