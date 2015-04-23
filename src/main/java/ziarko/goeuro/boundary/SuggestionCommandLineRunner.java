package ziarko.goeuro.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import ziarko.goeuro.domain.SuggestionService;

import static com.google.common.base.Preconditions.checkArgument;

@Controller
public class SuggestionCommandLineRunner implements CommandLineRunner {

    private static final int QUERY_ARGS_INDEX = 0;

    private final SuggestionService suggestionService;

    @Autowired
    public SuggestionCommandLineRunner(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @Override
    public void run(String... args) throws Exception {
        validateArgs(args);
        suggestionService.sendSuggestionsMatchingQueryToDefaultConsumer(getQuery(args));
    }

    private void validateArgs(String[] args) {
        checkArgument(args.length == 1, "Wrong number of arguments. One argument (query) required.");
    }

    private String getQuery(String[] args) {
        return args[QUERY_ARGS_INDEX];
    }

}
