package maciek.typer.repository;

import maciek.typer.model.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

/**
 * Created by maciej on 13.07.17.
 */
@Repository
public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long> {}

