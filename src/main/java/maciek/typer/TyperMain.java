package maciek.typer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by maciej on 11.07.17.
 */
@Component
public class TyperMain implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        main(args);
    }

    public static void main(String[] args) {
        System.out.println("i'm alive");


    }

}
