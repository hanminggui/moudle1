package cases;

import com.interfacetest.util.JsonHelper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by han on 2017/3/3.
 */
public class TetsJsonHelper {

    @DataProvider(name = "json")
    public Object[][] get(){
        return new Object[][]{
                {"[{\"require\":\"true\",\"name\":\"phone_no_encryp\"}]", "[0].require", "true"},
                {"{\"a\":\"a\"}","a","a"},
                {"{\"a\":{\"b\":\"b\"}}","a.b","b"},
                {"{\"a\":[{\"b\":1},{\"c\":\"c\"}]}","a[0].b","1"},
                {"{\"a\":[{\"b\":1},{\"c\":\"c\"}]}","a[1].c","c"},
                {"{\"a\":[{\"b\":1},{\"c\":\"c\",\"d\":{\"e\":[{\"f\":\"f\"}]}}]}","a[1].d.e[0].f","f"},
                {"{\"a\":[{\"b\":1},{\"c\":\"c\",\"d\":{\"e\":[{\"f\":\"f\"}]}}]}","a[1].d.e.f","f"},
                {"{\"a\":[{\"b\":1},{\"c\":\"c\",\"d\":{\"e\":[{\"f\":\"f\"},{\"g\":\"g\"}]}}]}","a[1].d.e.g","null"}
        };
    }

    @Test(dataProvider="json")
    public void testGetValue(String json, String key, String value){
        Assert.assertEquals(value, new JsonHelper().getValue(json,key.split("\\.")));
    }

}
