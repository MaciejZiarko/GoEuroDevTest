package ziarko.goeuro.infrastructure.csv;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ziarko.goeuro.domain.ConsumerException;
import ziarko.goeuro.domain.SuggestionsConsumer;
import ziarko.goeuro.domain.model.Suggestion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static au.com.bytecode.opencsv.CSVWriter.DEFAULT_SEPARATOR;
import static au.com.bytecode.opencsv.CSVWriter.NO_QUOTE_CHARACTER;

@Component
class CsvFileSuggestionConsumer implements SuggestionsConsumer {

    private final String outputFilePath;
    private final SuggestionToCsvLineMapper suggestionToCsvLineMapper;

    @Autowired
    public CsvFileSuggestionConsumer(@Value("${output.file.path}") String outputFilePath,
                                     SuggestionToCsvLineMapper suggestionToCsvLineMapper) throws IOException {
        this.outputFilePath = outputFilePath;
        this.suggestionToCsvLineMapper = suggestionToCsvLineMapper;
    }

    @Override
    public void consumeSuggestions(List<Suggestion> suggestions) {
        try (CSVWriter csvWriter = getCsvWriter()) {
            writeSuggestions(csvWriter, suggestions);
        } catch (IOException e) {
            throw new ConsumerException(e);
        }
    }

    private void writeSuggestions(CSVWriter csvWriter, List<Suggestion> suggestions) {
        for (Suggestion suggestion : suggestions) {
            String[] entries = suggestionToCsvLineMapper.map(suggestion);
            csvWriter.writeNext(entries);
        }
    }

    private CSVWriter getCsvWriter() throws IOException {
        return new CSVWriter(new FileWriter(outputFilePath), DEFAULT_SEPARATOR, NO_QUOTE_CHARACTER);
    }
}
