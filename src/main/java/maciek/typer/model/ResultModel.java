package maciek.typer.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by maciej on 20.07.17.
 */
@Data
public class ResultModel {

    private Long id;
    private String result;

}
