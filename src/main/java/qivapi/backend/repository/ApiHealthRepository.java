package qivapi.backend.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import qivapi.backend.model.ApiHealth;
import qivapi.backend.repository.jpa.JApiHealthRepository;
import qivapi.backend.repository.jpa.mapper.JApiHealthMapper;

import java.util.List;

@Repository
@Slf4j
public class ApiHealthRepository {
    @Autowired
    private JApiHealthRepository jApiHealthRepository;
    @Autowired
    private JApiHealthMapper healthMapper;
    @Autowired
    private SecurityInfoRepository securityInfoRepository;

    public ApiHealth save(ApiHealth healthResponse) {
        if (healthResponse.securityInfo() != null) {

            securityInfoRepository.save(healthResponse.securityInfo());
        }
        var healthToSave = healthMapper.toEntity(healthResponse);
        var savedHealthCheck = jApiHealthRepository.save(healthToSave);
        log.info("saving api health {}", healthToSave.toString());
        return healthMapper.toDomain(savedHealthCheck);
    }

    public List<ApiHealth> findByApiId(String apiId) {
        return jApiHealthRepository.findAllByApiId(apiId).stream().map(healthMapper::toDomain).toList();
    }

    public ApiHealth findById(String id) {
        return healthMapper.toDomain(jApiHealthRepository.findById(id).orElse(null));
    }


}
