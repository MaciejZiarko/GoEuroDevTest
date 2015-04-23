package ziarko.goeuro.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ziarko.goeuro.domain.model.Suggestion;

import java.util.List;

@Service
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;
    private final SuggestionsConsumer suggestionsConsumer;

    @Autowired
    public SuggestionService(SuggestionRepository suggestionRepository,
                             SuggestionsConsumer suggestionsConsumer) {
        this.suggestionRepository = suggestionRepository;
        this.suggestionsConsumer = suggestionsConsumer;
    }

    public void sendSuggestionsMatchingQueryToDefaultConsumer(String query) {
        List<Suggestion> suggestions = suggestionRepository.getSuggestionsForQuery(query);
        suggestionsConsumer.consumeSuggestions(suggestions);
    }

}
