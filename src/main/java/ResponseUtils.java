import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.Arrays;
import java.util.List;

public class ResponseUtils {

    public static String getHeader(CloseableHttpResponse response, String headerName) {

        // Get All headers
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";

        // Loop over the headers list
        for(Header header : httpHeaders) {
            if(headerName.equalsIgnoreCase(header.getName())) {
                returnHeader = header.getValue();
            }
        }

        // If no header found - throw an exception

        if(returnHeader.isEmpty()){
            throw   new RuntimeException("Didn't find the header: " + headerName);
        }
        // Return the header
        return returnHeader;
    }

    public static String getHeaderJava8Way(CloseableHttpResponse response, String headerName){

        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());

        Header matchedHeader = httpHeaders.stream()   //take a list of headers and make them flow in a stream 1 by 1
                .filter(header -> headerName.equalsIgnoreCase(header.getName())) //as they flow past, we then filter by header we are seek
                .findFirst().orElseThrow(() -> new RuntimeException("Didn't find the header")); //we then find and return first header that matches criteria

        return matchedHeader.getValue(); // return string value of header
    }

    public static boolean headerIsPresent(CloseableHttpResponse response, String headerName) {
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());

        return httpHeaders.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
    }
}
