package maciek.typer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by maciej on 11.07.17.
 */
@Component
public class TyperMain implements CommandLineRunner{

    @Autowired
    URLDownloader urlDownloader;

    @Override
    public void run(String... args) throws Exception {
        urlDownloader.dowload();
        urlDownloader.downloadSource();
        urlDownloader.dowloadHtml();
        main(args);
    }

    public static void main(String[] args) {
        System.out.println("i'm alive");


    }

}
