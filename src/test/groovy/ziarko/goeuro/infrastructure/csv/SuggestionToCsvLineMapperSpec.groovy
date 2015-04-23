package ziarko.goeuro.infrastructure.csv

import spock.lang.Specification
import spock.lang.Subject
import ziarko.goeuro.domain.model.Position
import ziarko.goeuro.domain.model.Suggestion

class SuggestionToCsvLineMapperSpec extends Specification {

    final Long FIXTURE_ID = 123L
    final String FIXTURE_NAME = 'Sochaczew';
    final String FIXTURE_TYPE = 'location'
    final BigDecimal FIXTURE_LATITUDE = 52.22944
    final BigDecimal FIXTURE_LONGITUDE = 20.23838

    @Subject
    SuggestionToCsvLineMapper tested = new SuggestionToCsvLineMapper()


    def "Suggestion serialized to CSV line has properties in the following order: id, name, type, latitude, longitude"() {
        given:
            Suggestion fixtureSuggestion = getFixtureSuggestion()
        when:
            String[] mappedSuggestion = tested.map(fixtureSuggestion)
        then:
            mappedSuggestion.size() == 5
            mappedSuggestion[0] == FIXTURE_ID.toString()
            mappedSuggestion[1] == FIXTURE_NAME.toString()
            mappedSuggestion[2] == FIXTURE_TYPE.toString()
            mappedSuggestion[3] == FIXTURE_LATITUDE.toString()
            mappedSuggestion[4] == FIXTURE_LONGITUDE.toString()
    }

    private Suggestion getFixtureSuggestion() {
        return new Suggestion(FIXTURE_ID, FIXTURE_NAME, FIXTURE_TYPE, new Position(FIXTURE_LATITUDE, FIXTURE_LONGITUDE))
    }

}
