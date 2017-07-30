package maciek.typerPro.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by maciej on 28.07.17.
 */
@Component
public class BigDecimalComparison {
    public boolean isGreater(BigDecimal bd1, BigDecimal bd2){
        return bd1.compareTo(bd2)==1;
    }

    public boolean isGreaterEqual(BigDecimal bd1, BigDecimal bd2){
        return bd1.compareTo(bd2)>=0;
    }

    public boolean isLess(BigDecimal bd1, BigDecimal bd2){
        return bd1.compareTo(bd2)==-1;
    }

    public boolean isLessEqual(BigDecimal bd1, BigDecimal bd2){
        return bd1.compareTo(bd2)<=0;
    }
}
