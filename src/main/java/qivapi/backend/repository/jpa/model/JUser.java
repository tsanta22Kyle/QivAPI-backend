package qivapi.backend.repository.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor
@Table(name = "\"user\"")
public class JUser {
    @Id private String id;
    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
     private List<JApi> apis;

}
