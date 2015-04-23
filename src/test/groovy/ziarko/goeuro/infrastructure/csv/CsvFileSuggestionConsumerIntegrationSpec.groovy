package ziarko.goeuro.infrastructure.csv

import au.com.bytecode.opencsv.CSVReader
import spock.lang.Specification
import spock.lang.Subject
import ziarko.goeuro.domain.model.Position
import ziarko.goeuro.domain.model.Suggestion
import static org.assertj.core.api.Assertions.*;

import java.nio.file.Files
import java.nio.file.Paths

class CsvFileSuggestionConsumerIntegrationSpec extends Specification {

    final String TEST_FILE_PATH = 'testFile.csv'
    final List<Suggestion> FIXTURE_SUGGESTIONS = [
            //In Groovy real-number literals are of type BigDecimal, yes that's cool :)
            new Suggestion(123L, "Warsaw", "location", new Position(52.22977, 21.01178)),
            new Suggestion(124L, "Warsaw Chopin", "airport", new Position(52.16556, 20.96694))
    ]

    @Subject
    CsvFileSuggestionConsumer tested = new CsvFileSuggestionConsumer(TEST_FILE_PATH, new SuggestionToCsvLineMapper())

    def "Should write valid CSV file to location given in constructor"() {
        when:
            tested.consumeSuggestions(FIXTURE_SUGGESTIONS)
        then:
            verifyProducedCsvFileIsValid()
        cleanup:
            removeTestFile()
    }

    private void verifyProducedCsvFileIsValid() {
        CSVReader csvReader = new CSVReader(new FileReader(TEST_FILE_PATH))
        List<String[]> lines = csvReader.readAll()
        assertThat(lines).hasSize(2)
        assertThat(lines[0]).isEqualTo(['123', 'Warsaw', 'location', '52.22977', '21.01178'] as String[])
        assertThat(lines[1]).isEqualTo(['124', 'Warsaw Chopin', 'airport', '52.16556', '20.96694'] as String[])
    }

    private void removeTestFile() {
        Files.delete(Paths.get(TEST_FILE_PATH))
    }
}
