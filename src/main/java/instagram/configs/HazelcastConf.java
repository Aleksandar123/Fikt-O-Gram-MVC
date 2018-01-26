package instagram.configs;

import com.hazelcast.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aleksandar on 27.9.16.
 */
@Configuration
public class HazelcastConf {

    @Bean
    public Config config(){
        return new Config();
    }

}
