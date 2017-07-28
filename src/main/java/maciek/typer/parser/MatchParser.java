package maciek.typer.parser;

import maciek.typer.model.FootballMatch;
import maciek.typer.repository.FootballMatchRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static javafx.beans.binding.Bindings.select;

/**
 * Created by maciej on 15.07.17.
 */
//@Component
public class MatchParser implements CommandLineRunner{

    @Autowired
    FootballMatchRepository repo;

    @Override
    public void run(String... strings) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //String dateStr = dateFormat.format(date);
        String dateStr = "2017-08-02";

        Document doc = Jsoup.parse(new File("pagesources/pagesource"+dateStr+".txt"), "UTF-8");
        Elements matches = doc.select("tr");

        matches = matches.stream().filter(m -> m.hasAttr("id")
                && (m.className().equals("toggable_row odd") || m.className().equals("toggable_row"))
                && "mecz".equals(m.attr("data-gtm-enhanced-ecommerce-variant"))).
                collect(Collectors.toCollection(Elements::new));
        for(Element match : matches){
            repo.save(fulfillMatchModel(match, dateStr));
        }
    }



    private FootballMatch fulfillMatchModel(Element match, String dateStr){
        FootballMatch footballMatch = new FootballMatch();

        Long dataId = Long.parseLong(match.attr("data-id"));
        System.out.println("Data ID: "+dataId);
        footballMatch.setIdData(dataId);

        String league = match.attr("data-gtm-enhanced-ecommerce-league");
        System.out.println("Liga: "+league);
        footballMatch.setLeague(league);

        String teams = match.attr("data-gtm-enhanced-ecommerce-match");
        System.out.println("Drużyny: "+teams);
        footballMatch.setTeams(teams);

        Elements tds = match.select("td").stream().
                filter(td -> Pattern.matches("col_bet.*",td.className())).
                collect(Collectors.toCollection(Elements::new));

        Element td;

        td = tds.get(0);
        if(td.className().equals("col_bet  col_bet_empty"))
            footballMatch.setRate1(new BigDecimal("0.0"));
            else
            footballMatch.setRate1(new BigDecimal(td.select("a").first().attr("data-rate")));

        td = tds.get(1);
        if(td.className().equals("col_bet  col_bet_empty"))
            footballMatch.setRate0(new BigDecimal("0.0"));
        else
            footballMatch.setRate0(new BigDecimal(td.select("a").first().attr("data-rate")));

        td = tds.get(2);
        if(td.className().equals("col_bet  col_bet_empty"))
            footballMatch.setRate2(new BigDecimal("0.0"));
        else
            footballMatch.setRate2(new BigDecimal(td.select("a").first().attr("data-rate")));

        td = tds.get(3);
        if(td.className().equals("col_bet  col_bet_empty"))
            footballMatch.setRate10(new BigDecimal("0.0"));
        else
            footballMatch.setRate10(new BigDecimal(td.select("a").first().attr("data-rate")));

        td = tds.get(4);
        if(td.className().equals("col_bet  col_bet_empty"))
            footballMatch.setRate02(new BigDecimal("0.0"));
        else
            footballMatch.setRate02(new BigDecimal(td.select("a").first().attr("data-rate")));

        td = tds.get(5);
        if(td.className().equals("col_bet  col_bet_empty"))
            footballMatch.setRate12(new BigDecimal("0.0"));
        else
            footballMatch.setRate12(new BigDecimal(td.select("a").first().attr("data-rate")));

        footballMatch.setDateMatch(dateStr);

        System.out.println(footballMatch);

        return footballMatch;
    }


    
    private boolean pullMatchToDb(FootballMatch model){
        return true;
    }

}
