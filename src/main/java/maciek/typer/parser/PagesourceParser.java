package maciek.typer.parser;

import org.springframework.boot.CommandLineRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by maciej on 12.07.17.
 */
public class SearchState implements CommandLineRunner{

    private String TR = "<tr";
    private String ID = "id=\"bet-[0-9]+\"";

    @Override
    public void run(String... strings) throws Exception {

        Scanner sc = new Scanner(new File("pagesource.txt"));

    }

    @Override
    public void doAction(Context context, Scanner sc) {

        String tr, id, ;
        boolean matchFound = false;
        boolean matchLive = false;

        while(sc.hasNext()) {

            while (sc.hasNext() && !matchFound) {
                if (Pattern.matches(TR, tr = sc.next()) && Pattern.matches(ID, id = sc.next())) {
                    System.out.println(getIDfromExpression(id));
                    matchFound = true;
                }
            }//while (sc.hasNext() && !matchFound)

            while (sc.hasNext() && !matchLive){

                if()

            }//while (!matchLive)


        }//while(sc.hasNext())

        context.setState(this);
    }

    private int getIDfromExpression(String expr){
        return Integer.parseInt(expr.replaceAll("[\\D]", ""));
    }

    private String seekForExpression(String regex, Scanner sc){

        String expression = null;
        boolean found = false;

        while(sc.hasNext() && !found){
            if(Pattern.matches(regex, expression = sc.next())){
                found = true;
            }
        }
        return  expression;
    }

    private int seekForMatchGetID(List<String> regex, Scanner sc){

        String tr, id=null;
        boolean matchFound = false;

        while (sc.hasNext() && !matchFound) {
            if (Pattern.matches(TR, tr = sc.next()) && Pattern.matches(ID, id = sc.next())) {
                System.out.println(getIDfromExpression(id));
                matchFound = true;
            }
        }//while (sc.hasNext() && !matchFound)

        return  getIDfromExpression(id);
    }



}
