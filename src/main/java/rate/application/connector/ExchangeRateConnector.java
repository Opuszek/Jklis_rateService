package rate.application.connector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ExchangeRateConnector {

    @Value("${exchangerate.token}")
    private String token;

    @Value("${exchangerate.url}")
    private String url;

    @Value("${exchange.baseCurrency}")
    private String baseCurrency;

    @Value("${exchange.symbols}")
    private String symbols;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final String urlParameters = "%s?access_key=%s&base=%s&symbols=%s";

    public String loadRateExchange(LocalDate date) {
        String formattedDate = formatter.format(date);
        String getRateExchangeUrl = url + String.format(urlParameters, formattedDate, token, baseCurrency, symbols);
        String result = null;
        try {
            result = get(getRateExchangeUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String get(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
