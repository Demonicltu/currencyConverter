package currency.exchanger.singleton;

import com.opencsv.bean.CsvToBeanBuilder;
import currency.exchanger.entity.Currency;
import currency.exchanger.exception.CurrencyNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CurrenciesHolder {

    private static CurrenciesHolder single_instance = null;

    private final List<Currency> currencies;

    private CurrenciesHolder() throws IOException {
        currencies = loadCurrencies();
    }

    public static CurrenciesHolder getInstance() throws IOException {
        if (single_instance == null)
            single_instance = new CurrenciesHolder();

        return single_instance;
    }

    private static List<Currency> loadCurrencies() throws IOException {
        try (
                InputStream inputStream = CurrenciesHolder.class.getResourceAsStream("/currenciesCourse.csv");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream)
        ) {
            return new CsvToBeanBuilder(inputStreamReader)
                    .withType(Currency.class)
                    .build()
                    .parse();
        }
    }

    public Currency getCurrencyByName(String name) throws CurrencyNotFoundException {
        return currencies.stream()
                .filter(currency -> currency.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(CurrencyNotFoundException::new);
    }

}
