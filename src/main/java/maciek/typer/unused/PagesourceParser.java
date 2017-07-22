package maciek.typer.unused;

import maciek.typer.model.FootballMatch;
import maciek.typer.repository.FootballMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by maciej on 12.07.17.
 */
//@Component
public class PagesourceParser implements CommandLineRunner{

    private String TR = "<tr";
    private String ID = "id=\"bet-[0-9]+\"";
    private Scanner sc;
    private FootballMatch match;
    private  Long id;

    @Autowired
    FootballMatchRepository footballMatchRepository;

    @Override
    public void run(String... strings) throws Exception {
        //parseHtmlToDatabase();
    }

    private void parseHtmlToDatabase(){
        try {
            sc = new Scanner(new File("pagesource2017-07-15.txt"));
        } catch(FileNotFoundException e){e.printStackTrace();}

        match = new FootballMatch();

        while (sc.hasNext()) {
            id = seekForMatchGetID();
            if (id!=-1 && !isLive()) {
                System.out.println("NOT LIVE");
                match.setId(null);
                match.setIdData(id);
                match.setTeams(seekForTeams());
                match.setRate1(seekForRate());
                match.setRate0(seekForRate());
                match.setRate2(seekForRate());
                match.setRate10(seekForRate());
                match.setRate02(seekForRate());
                match.setRate12(seekForRate());
                footballMatchRepository.save(match);
            }
        }//while

    }

    private Long getIDfromExpression(String expr){
        return Long.parseLong(expr.replaceAll("[\\D]", ""));
    }

    private String seekForExpression(String regex){

        String expression = null;
        boolean found = false;

        while(sc.hasNext() && !found){
            if(Pattern.matches(regex, expression = sc.next())){
                found = true;
            }
        }
        System.out.println("Found expression: "+expression);
        return  expression;
    }

    private Long seekForMatchGetID(){

        String tr, id=null;
        boolean matchFound = false;

        while (sc.hasNext() && !matchFound) {
            if (Pattern.matches(TR, tr = sc.next()) && Pattern.matches(ID, id = sc.next())) {
                System.out.println(getIDfromExpression(id));
                matchFound = true;
            }
        }//while (sc.hasNext() && !matchFound)

        Long returnID=(id==null?-1:getIDfromExpression(id));
        System.out.println("Found ID: "+returnID);
        return returnID;
    }

    private String seekForTeams(){
        seekForExpression("href=[\\p{Graph}]*");
        String teamStrPart = null;
        StringBuilder teams = new StringBuilder();
        while(sc.hasNext()&&!Pattern.matches("</a>",teamStrPart=sc.next())){
            teams.append(teamStrPart);
        }
        System.out.println(teamStrPart);
        System.out.println("Found teams: "+teams.toString());
        return teams.toString();
    }

    private boolean isLive(){
        seekForExpression("class=[\"_0-9a-zA-Z]+");
        String expr1 = sc.next();
        String expr2 = sc.next();
        System.out.println("isLive: "+expr1+" "+expr2);
        return Pattern.matches("live[\"_0-9a-zA-Z]+",expr1) || Pattern.matches("live[\"_0-9a-zA-Z]+",expr2);

    }

    private BigDecimal seekForRate(){
        String rate = null;
        do {
            rate = seekForExpression("[0-9]{1,3}|[0-9]{1,3}\\.[0-9]{1,2}");
        }
        while(sc.hasNext()&&!sc.next().equals("</a>")&&!sc.next().equals("</td>"));
        System.out.println("Found rate: "+rate);
        return new BigDecimal(rate);
    }


}
