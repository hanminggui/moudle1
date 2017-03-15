package cases;

import com.interfacetest.core.Http;
import org.testng.annotations.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by han on 2017/3/3.
 */
public class Case_x {
    protected Log log = LogFactory.getLog(this.getClass());
    @Test
    public void hipost() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("domainCode", "101057001004001");
        params.put("userAccount", "1239526840001");

        String report = Http.post("http://qdtest.faxuan.net/pss/service/pointService!getPointRanking.do", params);
        //log.info(xml);
    }

}
