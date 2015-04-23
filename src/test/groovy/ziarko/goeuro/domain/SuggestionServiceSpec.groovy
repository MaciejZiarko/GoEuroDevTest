package ziarko.goeuro.domain

import spock.lang.Specification
import spock.lang.Subject
import ziarko.goeuro.domain.model.Suggestion


class SuggestionServiceSpec extends Specification {

    final SuggestionRepository suggestionRepository = Mock(SuggestionRepository)
    final SuggestionsConsumer suggestionsConsumer = Mock(SuggestionsConsumer)
    final List<Suggestion> fixtureSuggestions = Stub(List)

    @Subject
    SuggestionService suggestionService = new SuggestionService(suggestionRepository, suggestionsConsumer)

    def '''SuggestionService should find Suggestions matching provided query using SuggestionRepository
           and delegate processing to SuggestionsConsumer'''() {
        when:
            suggestionService.sendSuggestionsMatchingQueryToDefaultConsumer('QUERY')
        then:
            1 * suggestionRepository.getSuggestionsForQuery(_) >> fixtureSuggestions
            1 * suggestionsConsumer.consumeSuggestions(fixtureSuggestions)
    }
}
