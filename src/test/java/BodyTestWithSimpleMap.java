
import java.io.IOException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static entities.User.ID;
import static entities.User.LOGIN;

public class BodyTestWithSimpleMap extends BaseClass {

    @Test
    public void returnsCorrectLogin() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        response = client.execute(get);

        String jsoBody = EntityUtils.toString(response.getEntity());

        JSONObject jsonObject = new JSONObject(jsoBody);

        String loginValue = (String) getValueFor(jsonObject, LOGIN);

        Assert.assertEquals(loginValue, "andrejss88");

    }

    @Test
    public void returnsCorrectId() throws IOException {

        HttpGet get = new HttpGet(BASE_ENDPOINT + "/users/andrejss88");

        response = client.execute(get);

        String jsoBody = EntityUtils.toString(response.getEntity());

        JSONObject jsonObject = new JSONObject(jsoBody);

        Integer loginValue = (Integer) getValueFor(jsonObject, ID);

        Assert.assertEquals(loginValue, Integer.valueOf(11834443));

    }

    private Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }
}
