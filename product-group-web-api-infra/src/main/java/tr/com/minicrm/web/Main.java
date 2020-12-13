package tr.com.minicrm.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MainConfiguration.class)
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}
