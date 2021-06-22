package currency.exchanger.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    private static final int currencyExchangeDecimalPlaces = 18;

    public static double roundNumber(double value) {
        BigDecimal decimal = new BigDecimal(Double.toString(value));
        decimal = decimal.setScale(currencyExchangeDecimalPlaces, RoundingMode.FLOOR);

        return decimal.doubleValue();
    }

}
