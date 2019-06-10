
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class BackEndCallTest {

    @Test
    public void testBackendCall() throws IOException {
        //http call to
        final String uri = "http://localhost:8080/best/year?year=2014";

        final String bodyAsString = callRumosApi(uri);
        System.out.println(bodyAsString);
        Assert.assertNotNull(bodyAsString);
    }

    private String callRumosApi(final String uri) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(new HttpGet(uri));
        String bodyAsString = EntityUtils.toString(response.getEntity());
        return bodyAsString;
    }
}
