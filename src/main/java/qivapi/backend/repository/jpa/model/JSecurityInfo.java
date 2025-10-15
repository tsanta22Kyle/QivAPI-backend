package qivapi.backend.repository.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "security_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JSecurityInfo {
    @Id
    private String id;
    private boolean ssl;
    private boolean hsts;
    private boolean noSniff;
}
