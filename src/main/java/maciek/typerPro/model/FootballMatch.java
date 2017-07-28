package maciek.typerPro.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

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
    String league;

    @Column
    String teams;

    @Column
    BigDecimal rate1;

    @Column
    BigDecimal rate0;

    @Column
    BigDecimal rate2;

    @Column
    BigDecimal rate10;

    @Column
    BigDecimal rate02;

    @Column
    BigDecimal rate12;

    @Column
    String result;

    @Column
    String dateMatch;

}
