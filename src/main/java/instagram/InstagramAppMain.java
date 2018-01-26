package instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class InstagramAppMain {
    public static void main(String[] args) {
        SpringApplication.run(InstagramAppMain.class, args);
    }
}
