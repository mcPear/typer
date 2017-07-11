package maciek.typer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by maciej on 11.07.17.
 */
@Component
public class URLDownloader {

    public void dowload(){




        try {
            URL website = new URL("https://www.efortuna.pl/pl/strona_glowna/2017-07-11?showActions=1&limit=100&tickLeft=0&selectDates=1&tickRight=0");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("information.html");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        catch(MalformedURLException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
    }

    public void downloadSource(){
        try {

            URL website = new URL("https://www.efortuna.pl/pl/strona_glowna/2017-07-11?showActions=1&limit=100&tickLeft=0&selectDates=1&tickRight=0");
            URLConnection conn = website.openConnection();
            conn.setRequestProperty("agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20300316 Firefox/3.6.2");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("filename.txt"), "utf-8")) ;
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                writer.write(inputLine+'\n');
            in.close();

        }
        catch(IOException e){e.printStackTrace();}
    }

    public void dowloadHtml(){
        try {
            Document document = Jsoup.connect("https://www.efortuna.pl/pl/strona_glowna/2017-07-11?showActions=1&limit=100&tickLeft=0&selectDates=1&tickRight=0").get();
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("filename2.txt"), "utf-8")) ;
            writer.write(document.outerHtml());
            System.out.println(document.text());
        }
        catch(IOException e){e.printStackTrace();}

    }

}
