package currency.exchanger.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberFormatting {

    private static final int currencyExchangeDecimalPlaces = 18;

    public static double truncateDouble(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(currencyExchangeDecimalPlaces, RoundingMode.FLOOR);

        return bd.doubleValue();
    }

}
