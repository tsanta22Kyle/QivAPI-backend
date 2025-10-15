package qivapi.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import qivapi.backend.model.Api;
import qivapi.backend.repository.jpa.JApiRepository;
import qivapi.backend.repository.jpa.mapper.JApiMapper;
import qivapi.backend.repository.jpa.model.JApi;

import java.util.List;
import java.util.Optional;

@Repository
public class ApiRepository {

    @Autowired
    private JApiRepository jApiRepository;

    @Autowired
    private JApiMapper apiMapper;

    public Api save(Api Api) {
        var apiToSave = apiMapper.toEntity(Api);
        return apiMapper.toDomain(jApiRepository.save(apiToSave));
    }
    public Api findById(String id) {
        return apiMapper.toDomain(jApiRepository.findById(id).orElse(null));
    }
    public List<Api> findAll() {
        return jApiRepository.findAll().stream().map(apiMapper::toDomain).toList();
    }
    public List<Api> findByUserId(String userId) {
        return jApiRepository.findAllByUserId(userId).stream().map(apiMapper::toDomain).toList();
    }

}
