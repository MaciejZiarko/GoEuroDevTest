package ziarko.goeuro.infrastructure

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll
import ziarko.goeuro.domain.SuggestionRepository

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = InfraConfig)
class RetrofitSuggestionRepositoryIntegrationSpec extends Specification {

    @Autowired
    SuggestionRepository suggestionRepository

    @Unroll
    def "Should access GoEuro remote service and correctly deserialize non-empty response for #query"() {
        expect:
            !suggestionRepository.getSuggestionsForQuery(query).isEmpty()
        where:
            query << ['Moscow', 'New York', 'Auckland', 'Munich']

    }

    @Unroll
    def "Should access GoEuro remote service and correctly deserialize empty list response"() {
        expect:
            suggestionRepository.getSuggestionsForQuery('MustBeEmpty').isEmpty()
    }

}
