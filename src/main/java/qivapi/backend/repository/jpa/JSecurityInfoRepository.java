package qivapi.backend.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qivapi.backend.repository.jpa.model.JSecurityInfo;
public interface JSecurityInfoRepository extends JpaRepository<JSecurityInfo,String> {
}
