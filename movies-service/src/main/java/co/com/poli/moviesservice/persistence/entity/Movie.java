package co.com.poli.moviesservice.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false, unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "Can not be blank")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Can not be blank")
    @Column(name = "director")
    private String director;

    @Min(value = 1, message = "value less than 1 not allowed")
    @Max(value = 5, message = "value greater than 1 not allowed")
    @Column(name = "rating")
    private int rating;

}
