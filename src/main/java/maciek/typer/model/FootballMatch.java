package maciek.typer.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by maciej on 11.07.17.
 */
@Data
@Entity
@Table(name="footballMatch")
public class FootballMatch {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long idData;

    @Column
    private String league;

    @Column
    private String teams;

    @Column
    private BigDecimal rate1;

    @Column
    private BigDecimal rate0;

    @Column
    private BigDecimal rate2;

    @Column
    private BigDecimal rate10;

    @Column
    private BigDecimal rate02;

    @Column
    private BigDecimal rate12;

    @Column
    private String result;

    @Column
    private String dateMatch;

    public boolean equalsWithoutId(Object o) {
        if (this == o) return true;
        if (!(o instanceof FootballMatch)) return false;

        FootballMatch that = (FootballMatch) o;

        if (idData != null ? !idData.equals(that.idData) : that.idData != null) return false;
        if (league != null ? !league.equals(that.league) : that.league != null) return false;
        if (teams != null ? !teams.equals(that.teams) : that.teams != null) return false;
        if (rate1 != null ? !rate1.equals(that.rate1) : that.rate1 != null) return false;
        if (rate0 != null ? !rate0.equals(that.rate0) : that.rate0 != null) return false;
        if (rate2 != null ? !rate2.equals(that.rate2) : that.rate2 != null) return false;
        if (rate10 != null ? !rate10.equals(that.rate10) : that.rate10 != null) return false;
        if (rate02 != null ? !rate02.equals(that.rate02) : that.rate02 != null) return false;
        if (rate12 != null ? !rate12.equals(that.rate12) : that.rate12 != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;
        return dateMatch != null ? dateMatch.equals(that.dateMatch) : that.dateMatch == null;
    }
}
