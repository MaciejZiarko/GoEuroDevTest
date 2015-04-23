package ziarko.goeuro.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import retrofit.RestAdapter;
import ziarko.goeuro.domain.SuggestionRepository;

@Configuration
@EnableAutoConfiguration
@PropertySource("classpath:/infra.properties")
class InfraConfig {

    @Bean
    @Autowired
    SuggestionRepository suggestionRepository(@Value("${location.api.url}") String locationApiUrl) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(locationApiUrl).build();
        return restAdapter.create(SuggestionRepository.class);
    }

}
