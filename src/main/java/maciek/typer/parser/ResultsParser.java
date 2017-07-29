package maciek.typer.parser;

import maciek.typer.model.FootballMatch;
import maciek.typer.model.ResultModel;
import maciek.typer.repository.FootballMatchRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by maciej on 19.07.17.
 */
//@Component
public class ResultsParser implements CommandLineRunner {

    @Autowired
    FootballMatchRepository repo;

    @Override
    public void run(String... strings) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        //String dateStr = dateFormat.format(date);
        String dateStr = "2017-07-28";

        Document doc = Jsoup.parse(new File("results/results"+dateStr+".txt"), "UTF-8");
        Elements matches = doc.select("tr");

        matches = matches.stream().filter(m -> m.hasAttr("id")
                && Pattern.matches("result-[0-9]+",m.attr("id"))).
                collect(Collectors.toCollection(Elements::new));
        for(Element match : matches){
            ResultModel resultModel = fulfillResultModel(match);
            List<FootballMatch> footballMatches = repo.findByIdData(resultModel.getId());
            if(footballMatches.size()>0) {
                FootballMatch footballMatch = footballMatches.get(0);
                footballMatch.setResult(resultModel.getResult());
                repo.save(footballMatch);
                System.out.println(footballMatch);
            }
        }
    }



    private ResultModel fulfillResultModel(Element match){
        ResultModel resultModel = new ResultModel();

        Long id = getIdFromAttribute(match.attr("id"));
        resultModel.setId(id);
        System.out.println(id);

        Elements tds = match.select("td").stream().filter(td -> td.className().equals("col_correctBets")).collect(Collectors.toCollection(Elements::new));
        String result = Pattern.matches("[0-2],[0-2]{2},[0-2]{2}",tds.text().trim())?tds.text().trim().charAt(0)+"":"missing";
        resultModel.setResult(result);
        System.out.println(result);

        return resultModel;
    }

    private Long getIdFromAttribute(String idAttr){
        return Long.parseLong(idAttr.replaceAll("[\\D]", ""));
    }



    private boolean pullMatchToDb(FootballMatch model){
        return true;
    }

}
