package util;

import java.text.DecimalFormat;

public class Utils {
    //format number will add comma's to the numbers
    public static String formatNumber(int value){

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String formatted = formatter.format(value);

        return  formatted;
    }
}
