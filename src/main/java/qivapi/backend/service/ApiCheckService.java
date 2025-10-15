package qivapi.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import qivapi.backend.endpoint.rest.model.CreateOrUpdateApi;
import qivapi.backend.model.Api;
import qivapi.backend.model.ApiHealth;
import qivapi.backend.model.SecurityInfo;
import qivapi.backend.model.User;
import qivapi.backend.repository.ApiHealthRepository;
import qivapi.backend.repository.ApiRepository;
import qivapi.backend.repository.SecurityInfoRepository;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ApiCheckService {


    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private ApiHealthRepository apiHealthRepository;

    @Autowired
    private SecurityInfoRepository securityInfoRepository;


    public ResponseEntity<ApiHealth> checkAPI(CreateOrUpdateApi apiToCreate) {
        var user = new User(apiToCreate.userId(), null, null, null);

        var apiCheck = new Api(UUID.randomUUID().toString(), apiToCreate.url(), apiToCreate.name(), apiToCreate.description(), LocalDateTime.now(), LocalDateTime.now(), List.of(), user);
        apiRepository.save(apiCheck);
        try {
            var apiHealth = checkSecurity(apiCheck);
            apiHealthRepository.save(apiHealth);
            return ResponseEntity.ok(apiHealth);
        } catch (HttpClientErrorException e) {
            log.warn("Api response {}", e.getMessage());
            var apiHealth = checkSecurity(apiCheck);
            apiHealthRepository.save(apiHealth);
            return ResponseEntity.ok(apiHealth);
        }
    }

    public ApiHealth checkSecurity(Api apiCheck) {
        try {

            Boolean ssl = apiCheck.apiUrl().startsWith("https://");
            var apiResponse = apiCheck.checkIsActive();

            Boolean sts = apiResponse.getHeaders().containsKey("Strict-Transport-Security");
            Boolean nosniff = "nosniff".equalsIgnoreCase(apiResponse.getHeaders().getFirst("X-Content-Type-Options"));
            SecurityInfo securityInfo = new SecurityInfo(UUID.randomUUID().toString(), ssl, sts, nosniff);
            if (apiResponse.getHeaders().getContentType().toString().contains("text/html")) {
                var Health = new ApiHealth(UUID.randomUUID().toString(), apiCheck, true, null, false, LocalDateTime.now());
                log.warn("not an api , probably an website... {} {}", apiCheck.apiUrl(), apiResponse);
                return Health;
            }
            var apiHealth = new ApiHealth(UUID.randomUUID().toString(), apiCheck, true, securityInfo, true, LocalDateTime.now());
            if (apiResponse.getHeaders().getContentType().toString().contains("application/json")) {
                return apiHealth;
            }
            return apiHealth;
        } catch (UnknownHostException unresolvedAddressException) {
            log.warn("Api address not exist response {}", unresolvedAddressException.getMessage());
            var apiHealth = new ApiHealth(UUID.randomUUID().toString(), apiCheck, false, null, false, LocalDateTime.now());
            return apiHealth;
        } catch (HttpClientErrorException e) {
                Boolean ssl = apiCheck.apiUrl().startsWith("https://");

                SecurityInfo securityInfo = new SecurityInfo(UUID.randomUUID().toString(), ssl, false, false);
                var apiHealth = new ApiHealth(UUID.randomUUID().toString(), apiCheck, true, securityInfo, true, LocalDateTime.now());
                return apiHealth;

        }

    }


    public ResponseEntity<List<Api>> findAllByUserId(String userId) {
        return ResponseEntity.ok(apiRepository.findByUserId(userId));
    }
}