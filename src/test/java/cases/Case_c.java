package cases;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by han on 2017/3/3.
 */
public class Case_c {


    @Test
    public void test(){
        Map<Object,Object> param = new HashMap<Object, Object>();
        param.put("pageSize",25);
        param.put("areaCode","880000");

    }
}
