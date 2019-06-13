
import com.mario.backend.movies.MovieByIdService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;


public class BackEndCallTest {

    @Test
    @Ignore
    public void testBackendCall() throws IOException {
        //http call to
        final String uri = "http://localhost:8080/best/year?year=2014";

        final String bodyAsString = callRumosApi(uri);
        System.out.println(bodyAsString);
        Assert.assertNotNull(bodyAsString);
    }

    @Test
    public void testMovieDetailsCallViaId() throws IOException {

        final String BASE_URL = "https://api.themoviedb.org/3/movie/";
        final String API_KEY = "db10e7a8660d7d089fb952a7a4fe4d13";
        final long id = 157336;

        String uri = BASE_URL + id +  "?api_key="+API_KEY;

        System.out.println(callRumosApi(uri));
    }

    private String callRumosApi(final String uri) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(new HttpGet(uri));
        String bodyAsString = EntityUtils.toString(response.getEntity());
        return bodyAsString;
    }
}
