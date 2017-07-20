package maciek.typer;

/**
 * Created by maciej on 11.07.17.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

//@Component
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
        dateStr = "2017-07-22";

        WebDriver driver = new HtmlUnitDriver(true);
        driver.get("http://www.efortuna.pl");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        System.out.println("Page title is: " + driver.getTitle());

        driver.findElement(By.cssSelector("a[href*='zaklady_internetowe']")).click();
        driver.findElement(By.cssSelector("a[href='/pl/strona_glowna/pilka-nozna?limit=100']")).click();
        driver.findElement(By.cssSelector("a[href='/pl/strona_glowna/pilka-nozna/"+dateStr+"?limit=100&tickLeft=0&selectDates=1&tickRight=0']")).click();
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try{Thread.sleep(2000);}
        catch (InterruptedException e){e.printStackTrace();}
        return driver;
    }

    public static void writePageSourceToFile(WebDriver driver){
        String pageSource = driver.getPageSource();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateStr = dateFormat.format(date);

        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("pagesource"+dateStr+".txt"), "utf-8"));
            writer.write(pageSource);
        }
        catch(FileNotFoundException e){e.printStackTrace();}
        catch(UnsupportedEncodingException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}

        System.out.println("Page title is: " + driver.getTitle());

    }

}
