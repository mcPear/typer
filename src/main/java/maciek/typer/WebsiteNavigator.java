package maciek.typer;

/**
 * Created by maciej on 11.07.17.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WebsiteNavigator implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        writePageSourceToFile(navigateToDailyFootbal());
    }

    public static WebDriver navigateToDailyFootbal(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        System.out.println(dateStr);

        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://www.efortuna.pl");
        System.out.println("Page title is: " + driver.getTitle());

        driver.findElement(By.cssSelector("a[href*='zaklady_internetowe']")).click();
        driver.findElement(By.cssSelector("a[href='/pl/strona_glowna/pilka-nozna?limit=100']")).click();
        driver.findElement(By.cssSelector("a[href='/pl/strona_glowna/pilka-nozna/"+dateStr+"?limit=100&tickLeft=0&selectDates=1&tickRight=0']")).click();
        return driver;
    }

    public static void writePageSourceToFile(WebDriver driver){
        String pageSource = driver.getPageSource();

        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("pagesource.txt"), "utf-8"));
            writer.write(pageSource);
        }
        catch(FileNotFoundException e){e.printStackTrace();}
        catch(UnsupportedEncodingException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}

        System.out.println("Page title is: " + driver.getTitle());

    }

}
