package maciek.typer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.math.RoundingMode;

@ComponentScan
@SpringBootApplication
public class TyperApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TyperApplication.class);
		builder.headless(false).run(args);
	}
}
