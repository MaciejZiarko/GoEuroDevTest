package ziarko.goeuro.domain;

import retrofit.http.GET;
import retrofit.http.Path;
import ziarko.goeuro.domain.model.Suggestion;

import java.util.List;

public interface SuggestionRepository {

    @GET("/position/suggest/en/{query}")
    List<Suggestion> getSuggestionsForQuery(@Path("query") String query);

}
