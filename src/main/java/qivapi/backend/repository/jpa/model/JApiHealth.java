package qivapi.backend.repository.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "api_health")
public class JApiHealth {
    @Id private String id;

    @ManyToOne
    private JApi api;
    private boolean isActive;
    @Column(name = "is_api")
    private boolean isAnApi;
    @OneToOne
    @JoinColumn(name = "security_info_id",nullable = true)
    private JSecurityInfo securityInfo;
    private LocalDateTime checkingDatetime;
}
