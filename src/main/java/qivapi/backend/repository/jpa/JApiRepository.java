package qivapi.backend.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qivapi.backend.repository.jpa.model.JApi;

import java.util.List;
public interface JApiRepository extends JpaRepository<JApi,String> {
    public List<JApi> findAllByUserId(String userId);
}
