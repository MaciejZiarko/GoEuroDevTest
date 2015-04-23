package ziarko.goeuro.domain;

import ziarko.goeuro.domain.model.Suggestion;

import java.util.List;

public interface SuggestionsConsumer {

    void consumeSuggestions(List<Suggestion> suggestions);

}
