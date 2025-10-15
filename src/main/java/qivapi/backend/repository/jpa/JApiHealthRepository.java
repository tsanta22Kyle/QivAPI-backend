package qivapi.backend.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qivapi.backend.repository.jpa.model.JApiHealth;

import java.util.List;
public interface JApiHealthRepository extends JpaRepository<JApiHealth,String> {
    public List<JApiHealth> findAllByApiId(String apiId);
}
