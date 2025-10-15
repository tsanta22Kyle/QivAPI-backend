package qivapi.backend.repository.jpa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qivapi.backend.model.Api;
import qivapi.backend.model.ApiHealth;
import qivapi.backend.repository.jpa.JApiHealthRepository;
import qivapi.backend.repository.jpa.model.JApi;
import qivapi.backend.repository.jpa.model.JApiHealth;

import java.util.ArrayList;
import java.util.List;

@Component
public class JApiMapper {

    @Autowired
    private JApiHealthRepository apiHealthRepository;
    @Autowired
    private JApiHealthMapper healthMapper;
    public Api toDomain(JApi jApi) {
        List<ApiHealth> apiHealths = apiHealthRepository.findAllByApiId(jApi.getId()).stream().map(healthMapper::toDomain).toList();
        Api api = new Api(jApi.getId(),jApi.getUrl(), jApi.getName(),jApi.getDescription(),jApi.getCreated(),jApi.getUpdated(),apiHealths,null);

        return api;
    }

    public JApi toEntity(Api api) {
        JApi jApi = new JApi();
        jApi.setId(api.id());
        jApi.setUrl(api.apiUrl());
        jApi.setName(api.name());
        jApi.setDescription(api.description());
        jApi.setCreated(api.created());
        jApi.setUpdated(api.updated());

        return jApi;
    }

}
