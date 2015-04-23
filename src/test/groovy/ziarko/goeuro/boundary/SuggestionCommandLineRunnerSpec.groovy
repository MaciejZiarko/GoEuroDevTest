package ziarko.goeuro.boundary

import spock.lang.Specification
import spock.lang.Subject
import ziarko.goeuro.domain.SuggestionService


class SuggestionCommandLineRunnerSpec extends Specification {

    SuggestionService suggestionService = Mock(SuggestionService)

    @Subject
    SuggestionCommandLineRunner tested = new SuggestionCommandLineRunner(suggestionService)

    def "Should delegate processing to SuggestionService when number of arguments is equal to 1"() {
        given:
            String singleArg = 'Sample'
        when:
            tested.run(singleArg)
        then:
            1 * suggestionService.sendSuggestionsMatchingQueryToDefaultConsumer(singleArg)
    }

    def "Should throw IllegalArgumentException when number of command line arguments is different than 1"() {
        when:
            tested.run()
        then:
            thrown IllegalArgumentException
        where:
            args << [[], ['one', 'two']]
    }

}
