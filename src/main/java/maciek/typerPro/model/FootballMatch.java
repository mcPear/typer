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

}
