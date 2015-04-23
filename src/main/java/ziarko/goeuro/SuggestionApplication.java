package ziarko.goeuro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ziarko.goeuro.boundary.ApplicationFailureListener;

@SpringBootApplication
public class SuggestionApplication {

    public static void main(String[] args) {
        getSpringApplication().run(args);
    }

    private static SpringApplication getSpringApplication() {
        SpringApplication springApplication = new SpringApplication(SuggestionApplication.class);
        springApplication.addListeners(new ApplicationFailureListener());
        return springApplication;
    }

}
