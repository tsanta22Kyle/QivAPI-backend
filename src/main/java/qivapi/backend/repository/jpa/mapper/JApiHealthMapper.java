package qivapi.backend.repository.jpa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qivapi.backend.model.Api;
import qivapi.backend.model.ApiHealth;
import qivapi.backend.repository.jpa.model.JApi;
import qivapi.backend.repository.jpa.model.JApiHealth;

@Component
public class JApiHealthMapper  {



    @Autowired
    private JSecurityInfoMapper securityInfoMapper;

    public JApiHealth toEntity(ApiHealth apiHealth) {
        JApiHealth jApiHealth = new JApiHealth();
        jApiHealth.setId(apiHealth.id());
        jApiHealth.setSecurityInfo(securityInfoMapper.toEntity(apiHealth.securityInfo()));
        jApiHealth.setActive(apiHealth.isActive());
        jApiHealth.setAnApi(apiHealth.isApi());
        jApiHealth.setCheckingDatetime(apiHealth.checkingDatetime());
        return jApiHealth;
    }

    public ApiHealth toDomain(JApiHealth jApiHealth) {
        JApi jApi = jApiHealth.getApi();
        if(jApi == null) {
            ApiHealth apiHealth = new ApiHealth(jApiHealth.getId(), null,jApiHealth.isActive(),securityInfoMapper.toDomain(jApiHealth.getSecurityInfo()),jApiHealth.isAnApi(),jApiHealth.getCheckingDatetime());
            return apiHealth;
        }
        Api api = new Api(jApi.getId(),jApi.getUrl(), jApi.getName(),jApi.getDescription(),jApi.getCreated(),jApi.getUpdated(),null,null);

        ApiHealth apiHealth = new ApiHealth(jApiHealth.getId(), api,jApiHealth.isActive(),securityInfoMapper.toDomain(jApiHealth.getSecurityInfo()),jApiHealth.isAnApi(),jApiHealth.getCheckingDatetime());
        return apiHealth;
    }
}
