import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ResponseHeaders {

    public static final String BASE_ENDPOINT = "https://api.github.com";
    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setup(){client = HttpClientBuilder.create().build();}

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }


    @Test
    public void contentTypeIsJson() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT );

        response = client.execute(get);

        Header contentType = response.getEntity().getContentType();
        assertEquals(contentType.getValue(), "application/json; charset=utf-8");

        ContentType ct = ContentType.getOrDefault(response.getEntity());
        assertEquals(ct.getMimeType(), "application/json");
    }

    @Test
    public void serverIsGithub() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);

        response = client.execute(get);

        String headerValue = ResponseUtils.getHeader(response, "Server");

        assertEquals(headerValue, "GitHub.com");


    }

    @Test
    public void xRateLimitIsSixty() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);

        response = client.execute(get);

        String limitVal = ResponseUtils.getHeaderJava8Way(response, "X-RateLimit-Limit"); //X tells us this is not standard header, created by API dev
        assertEquals(limitVal, "60");
    }

    @Test
    public void eTagIsPresent() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT);

        response = client.execute(get);

        boolean tagIsPresent = ResponseUtils.headerIsPresent(response, "ETag");

        assertTrue(tagIsPresent);
    }

//    private String getHeader(CloseableHttpResponse response, String headerName) {
//
//        // Get All headers
//        Header[] headers = response.getAllHeaders();
//        List<Header> httpHeaders = Arrays.asList(headers);
//        String returnHeader = "";
//
//        // Loop over the headers list
//        for(Header header : httpHeaders) {
//            if(headerName.equalsIgnoreCase(header.getName())) {
//                returnHeader = header.getValue();
//            }
//        }
//
//        // If no header found - throw an exception
//
//        if(returnHeader.isEmpty()){
//            throw new RuntimeException("Didn't find the header: " + headerName);
//        }
//        // Return the header
//        return returnHeader;
//    }

}
