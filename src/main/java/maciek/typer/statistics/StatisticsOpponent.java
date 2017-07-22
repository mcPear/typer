package maciek.typer.statistics;

import maciek.typer.statistics.model.PosLOpponent;
import maciek.typer.statistics.model.RateModel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maciej on 22.07.17.
 */
public class StatisticsOpponent {

    private Map<BigDecimal, BigDecimal> getOponnentLPercentMap(List<PosLOpponent> posLOpponents, BigDecimal startOponnentLRateVal, BigDecimal stopOponnentLRateVal){
        Map<BigDecimal, BigDecimal> oponnentLPercenMap = new HashMap<>();

        for(BigDecimal i=startOponnentLRateVal;i.compareTo(stopOponnentLRateVal)!=1;i=i.add(new BigDecimal("0.1"))){
            oponnentLPercenMap.put(i, null);
        }

        //TODO wyświetlić wykres procent wygranej od przeciwnej stawki dla wybranej stawki
    }

}
