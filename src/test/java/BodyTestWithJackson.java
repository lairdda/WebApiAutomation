import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;
import java.io.IOException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BodyTestWithJackson extends BaseClass {

    @Test
    public void returnsCorrectLogin() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        response = client.execute(get);

        User user = ResponseUtils.unmarshall(response, User.class);

        Assert.assertEquals(user.getLogin(), "andrejss88");
}

    @Test
    public void returnsCorrectId() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        response = client.execute(get);

        User user = unmarshall(response, User.class);

        Assert.assertEquals(user.getLogin(), 11834443);
    }


    }
