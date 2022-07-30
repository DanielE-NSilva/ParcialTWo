package co.com.poli.bookingsservice.persistence.entity;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false, unique = true, nullable = false)
    private Long id;

    @NotNull(message = "Can not be null")
    @Column(name = "userId")
    private Long userId;

    @NotNull(message = "Can not be null")
    @Column(name = "showTimeId")
    private Long showTimeId;

    @ElementCollection
    @Column(name = "movies")
    private List<Long> movies;

}