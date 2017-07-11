package maciek.typer;

import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by maciej on 11.07.17.
 */
@Component
public class URLDownloader {

    public static void dowload(){

        try {
            URL website = new URL("https://www.efortuna.pl/pl/strona_glowna/2017-07-11?showActions=1&limit=100&tickLeft=0&selectDates=1&tickRight=0");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("information.html");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        catch(MalformedURLException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}
    }


}
