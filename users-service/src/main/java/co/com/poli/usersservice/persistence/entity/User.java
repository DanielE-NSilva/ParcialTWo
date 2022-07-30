package co.com.poli.usersservice.persistence.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false, unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "Can not be blank")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Can not be blank")
    @Column(name = "lastname")
    private String lastname;

}