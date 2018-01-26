package instagram.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by aleksandar on 5.9.16.
 */
@Configuration
public class BCryptConf {

    @Bean
    public BCryptPasswordEncoder getBCryptEncoder(){

        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    return bcryptEncoder;
}
}
