package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoinsController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/getCoinInfo")
    public String getCoinInfo(@RequestParam(value="Coin", defaultValue="BTC") String DigCoinName , @RequestParam(value="Rate", defaultValue="USD") String RealCoinRate ) throws IOException {
        return requestApi("exchangerate/" + DigCoinName +"/"+  RealCoinRate ) ;
    }
    @RequestMapping("/getAllCoins")
    public String getAllCoins(@RequestParam(value="all", defaultValue="all") String name) throws IOException {
        return  requestApi("assets");
    }


    public String requestApi( String req ) throws IOException {

                        URL url = new URL("https://rest.coinapi.io/v1/" + req );
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.addRequestProperty("X-CoinAPI-Key" , "6C6E5CB7-E984-4A45-8BCD-7FEDFF8B15B6" );
                        int status = con.getResponseCode();

                        BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer content = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                        }

                        in.close();
                        con.disconnect();
                        return  content.toString() ;

    }
}
