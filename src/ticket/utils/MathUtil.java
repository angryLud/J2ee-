package ticket.utils;

import java.math.BigDecimal;

/**
 * Created by Jiayiwu on 17/2/5.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class MathUtil {
    public static double roundNum(double num,int count){
        BigDecimal   tem   =   new BigDecimal(num);
        return tem.setScale(count,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static int roundIntNum(double num){
        BigDecimal   tem   =   new BigDecimal(num);
        return tem.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
    }
}
