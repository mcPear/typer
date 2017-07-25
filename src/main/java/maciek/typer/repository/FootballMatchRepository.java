package maciek.typer.repository;

import maciek.typer.model.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by maciej on 13.07.17.
 */
@Repository
public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long> {
    List<FootballMatch> findByIdData(Long idData);
    List<FootballMatch> findByResultIsNotNull();
    List<FootballMatch> findByResultIsNotNullAndRate1GreaterThanAndRate0GreaterThanAndRate2GreaterThan(BigDecimal rate1, BigDecimal rate0, BigDecimal rate2);

}

