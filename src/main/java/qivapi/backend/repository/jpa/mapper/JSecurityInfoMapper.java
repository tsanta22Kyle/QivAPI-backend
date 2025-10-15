package qivapi.backend.repository.jpa.mapper;

import org.springframework.stereotype.Component;
import qivapi.backend.model.SecurityInfo;
import qivapi.backend.repository.jpa.model.JSecurityInfo;

@Component
public class JSecurityInfoMapper {

    public JSecurityInfo toEntity(SecurityInfo securityInfo) {
        JSecurityInfo jsSecurityInfo = new JSecurityInfo();
        if (securityInfo == null) {
            return jsSecurityInfo;
        }
        jsSecurityInfo = new JSecurityInfo();
        jsSecurityInfo.setId(securityInfo.id());
        jsSecurityInfo.setSsl(securityInfo.ssl());
        jsSecurityInfo.setHsts(securityInfo.hsts());
        jsSecurityInfo.setNoSniff(securityInfo.nosniff());
        return jsSecurityInfo;
    }
    public SecurityInfo toDomain(JSecurityInfo jsSecurityInfo) {
        SecurityInfo securityInfo = new SecurityInfo(jsSecurityInfo.getId(),jsSecurityInfo.isSsl(),jsSecurityInfo.isHsts(),jsSecurityInfo.isNoSniff());
        return securityInfo;
    }

}
