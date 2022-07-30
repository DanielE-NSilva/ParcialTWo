package co.com.poli.showtimesservice.persistence.repository;

import co.com.poli.showtimesservice.persistence.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    @Modifying
    @Query(value =  "UPDATE Show_Times SET Date=:Date WHERE ID=:Id ", nativeQuery = true)
    void updateById(@Param("Id") Long Id,
                    @Param("Date") LocalDateTime Date);

    @Modifying
    @Query(value ="DELETE FROM  ShowTime_Movies_Id  WHERE ShowTime_ID=:Id",nativeQuery = true)
    void updateByIdDelete(@Param("Id") Long Id);

    @Modifying
    @Query(value = "INSERT INTO ShowTime_Movies_Id (ShowTime_ID,Movies) " +
                   "VALUES (:Id, :Movie) ", nativeQuery = true)
    void updateByIdMovies(@Param("Id") Long Id,
                          @Param("Movie") Long Movie);

    @Transactional(readOnly = true)
    @Query(value = "SELECT DISTINCT Movies FROM ShowTime_Movies_Id", nativeQuery = true)
    List<Long> findAllMovies();

    @Transactional(readOnly = true)
    @Query(value = "SELECT Movies FROM ShowTime_Movies_Id WHERE Movies=:Id ", nativeQuery = true)
    Long findMovieById(@Param("Id") Long Id);

    @Transactional(readOnly = true)
    @Query(value = "SELECT TOP 1  * FROM Show_Times ORDER BY Id DESC", nativeQuery = true)
    Showtime lastRecord();

}