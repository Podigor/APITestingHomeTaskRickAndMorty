import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.rickAndMortyEpisode.Episode;
import objects.rickAndMortyEpisode.Result;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import rest.ApiRequest;



public class TestRickAndMortyEpisode {

    public static final String URL = "https://rickandmortyapi.com/api";
    static Episode episode;
    static Episode episodeNextPage;
    static ObjectMapper om = new ObjectMapper();

    @BeforeClass
    public static void bb() throws JsonProcessingException {
        episode = om.readValue(
                ApiRequest.getRest(
                        new JSONObject(
                                ApiRequest.getRest(URL)).getString("episodes")), Episode.class);
        episodeNextPage=om.readValue(ApiRequest.getRest(episode.info.next),Episode.class);
    }

    @Test
    public void test1() throws JsonProcessingException {
        for (Result result : episode.results) {
            System.out.println("name = " + result.name);
            System.out.println("air_date = " + result.air_date);
            System.out.println("id = " + result.id);
            System.out.println("_____________________________");
        }
        while(true) {
            if (episodeNextPage.info.next != null) {
                for (Result result : episodeNextPage.results) {
                    System.out.println("name = " + result.name);
                    System.out.println("air_date = " + result.air_date);
                    System.out.println("id = " + result.id);
                    System.out.println("_____________________________");
                }
                episodeNextPage = om.readValue(ApiRequest.getRest(episodeNextPage.info.next), Episode.class);
            } else {
                for (Result result : episodeNextPage.results) {
                    System.out.println("name = " + result.name);
                    System.out.println("air_date = " + result.air_date);
                    System.out.println("id = " + result.id);
                    System.out.println("_____________________________");
                }
                break;
            }
        }
    }
}
