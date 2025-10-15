package qivapi.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qivapi.backend.model.SecurityInfo;
import qivapi.backend.repository.jpa.JSecurityInfoRepository;
import qivapi.backend.repository.jpa.mapper.JSecurityInfoMapper;

@Component
public class SecurityInfoRepository {
    @Autowired
    private JSecurityInfoRepository securityInfoRepository;

    @Autowired
    private JSecurityInfoMapper securityInfoMapper;


    public SecurityInfo save(SecurityInfo securityInfo) {
        var securityInfoToSave = securityInfoMapper.toEntity(securityInfo);
        var savedSecurityInfo = securityInfoMapper.toDomain(securityInfoRepository.save(securityInfoToSave));
        return savedSecurityInfo;
    }
    public SecurityInfo findById(String id) {
        var securityInfo = securityInfoRepository.findById(id).orElse(null);
        return securityInfoMapper.toDomain(securityInfo);
    }
}
