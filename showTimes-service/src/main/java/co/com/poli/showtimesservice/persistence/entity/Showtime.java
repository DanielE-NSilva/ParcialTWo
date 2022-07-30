package co.com.poli.showtimesservice.persistence.entity;

import co.com.poli.showtimesservice.modelDto.Movie;
import com.netflix.discovery.provider.Serializer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ShowTimes")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false, unique = true, nullable = false)
    private Long id;

    @NotNull(message = "Can not be null")
    @Column(name = "date")
    private LocalDateTime date;

    @ElementCollection
    @Column(name = "movies")
    private List<Long> moviesId;

}
