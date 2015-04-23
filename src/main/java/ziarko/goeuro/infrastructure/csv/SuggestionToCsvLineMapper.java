package ziarko.goeuro.infrastructure.csv;

import org.springframework.stereotype.Component;
import ziarko.goeuro.domain.model.Suggestion;

@Component
class SuggestionToCsvLineMapper {

    String[] map(Suggestion suggestion) {
        return new String[] {
                suggestion.getId().toString(),
                suggestion.getName(),
                suggestion.getType(),
                suggestion.getPosition().getLatitude().toString(),
                suggestion.getPosition().getLongitude().toString(),
        };
    }

}
