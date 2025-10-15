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
@Table(name = "\"api\"")
@AllArgsConstructor
@NoArgsConstructor
public class JApi {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = true)
    private JUser user;
    private String name;
    private String description;
    private String url;
    private LocalDateTime created;
    private LocalDateTime updated;
}
