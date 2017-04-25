package cases;

import com.alibaba.fastjson.JSON;
import com.interfacetest.util.JsonHelper;
import org.testng.annotations.Test;

/**
 * Created by han on 2017/3/3.
 */
public class Case_c {


    @Test
    public void test(){
        String json = "{\"a\":[{\"b\":{\"c\":[{},{\"d\":1}]}}]}";


        System.out.println(JsonHelper.getValue2(json, "a[0].b.c[1].d"));

        String json2 = "{\"total\":150,\"status\":1,\"rows\":[{\"id\":2777,\"coursewareType\":1,\"sort\":1,\"updateTime\":\"2017-04-25 23:46:13\",\"coursewareDiscription\":\"<p>描述<br/><\\/p><br/>\",\"courseStatus\":0,\"coursewareName\":\"name\",\"courseId\":565,\"userAccount\":\"song\",\"domainCode\":\"101000000000000\",\"courseName\":\"批量添加课程1493134527810\"},{\"id\":2776,\"coursewareType\":1,\"sort\":1,\"updateTime\":\"2017-04-25 15:41:00\",\"coursewareDiscription\":\"<p>1<\\/p>\",\"courseStatus\":1,\"coursewareName\":\"042504\",\"courseId\":543,\"userAccount\":\"song\",\"domainCode\":\"101000000000000\",\"courseName\":\"必修课程0425S\"},{\"id\":2775,\"coursewareType\":1,\"sort\":1,\"updateTime\":\"2017-04-25 14:18:07\",\"coursewareDiscription\":\"<p>1<\\/p>\",\"courseStatus\":1,\"coursewareName\":\"052503\",\"courseId\":543,\"userAccount\":\"song\",\"domainCode\":\"101000000000000\",\"courseName\":\"必修课程0425S\"},{\"id\":2774,\"coursewareType\":1,\"sort\":1,\"updateTime\":\"2017-04-25 14:17:57\",\"coursewareDiscription\":\"<p>2<\\/p>\",\"courseStatus\":1,\"coursewareName\":\"052502\",\"courseId\":543,\"userAccount\":\"song\",\"domainCode\":\"101000000000000\",\"courseName\":\"必修课程0425S\"},{\"id\":2773,\"coursewareType\":1,\"sort\":1,\"updateTime\":\"2017-04-25 14:17:44\",\"coursewareDiscription\":\"<p>1<\\/p>\",\"courseStatus\":1,\"coursewareName\":\"052501\",\"courseId\":543,\"userAccount\":\"song\",\"domainCode\":\"101000000000000\",\"courseName\":\"必修课程0425S\"},{\"id\":2768,\"coursewareType\":2,\"sort\":1,\"updateTime\":\"2017-04-25 14:16:15\",\"coursewareDiscription\":\"\",\"courseStatus\":1,\"coursewareName\":\"图片课件0424\",\"courseId\":543,\"userAccount\":\"song\",\"domainCode\":\"101000000000000\",\"courseName\":\"必修课程0425S\"},{\"id\":2767,\"coursewareType\":1,\"sort\":1,\"updateTime\":\"2017-04-25 14:13:54\",\"coursewareDiscription\":\"<p>图文课件描述<\\/p>\",\"courseStatus\":1,\"coursewareName\":\"图文课件0424\",\"courseId\":543,\"userAccount\":\"song\",\"resourceUrl\":\"http://qdtestfile.faxuan.net/study/bf11ab6b8e104729bc6026051d72641b.JPG\",\"domainCode\":\"101000000000000\",\"courseName\":\"必修课程0425S\"},{\"id\":2769,\"coursewareType\":3,\"sort\":1,\"updateTime\":\"2017-04-25 14:13:38\",\"coursewareDiscription\":\"<p>视频课件<\\/p>\",\"courseStatus\":1,\"coursewareName\":\"视频课件0424\",\"courseId\":543,\"userAccount\":\"song\",\"resourceUrl\":\"http://qdtestfile.faxuan.net/study/http://file.faxuan.net/video/lffxz_150827_256.mp4\",\"domainCode\":\"101000000000000\",\"courseName\":\"必修课程0425S\"},{\"id\":2772,\"coursewareType\":1,\"sort\":2,\"updateTime\":\"2017-04-25 10:07:04\",\"coursewareDiscription\":\"反反复复\",\"courseStatus\":1,\"coursewareName\":\"2222\",\"courseId\":539,\"userAccount\":\"yanqianling\",\"domainCode\":\"101000000000000\",\"courseName\":\"添加一个选秀\"},{\"id\":2771,\"coursewareType\":1,\"sort\":1,\"updateTime\":\"2017-04-24 18:04:38\",\"coursewareDiscription\":\"<p>11<\\/p>\",\"courseStatus\":1,\"coursewareName\":\"111\",\"courseId\":536,\"userAccount\":\"song\",\"domainCode\":\"101000000000000\",\"courseName\":\"选修课程042401\"}]}";

        for(int i=0; i< JSON.parseObject(json2).getJSONArray("rows").size(); i++){
            if(JsonHelper.getValue2(json2, "rows["+i+"].courseName").equals("批量添加课程1493134527810")){
                int id = (int) JsonHelper.getValue2(json2, "rows["+i+"].id");
                System.out.println(id);
            }
        }

        int index = 0;
        while (!JsonHelper.getValue2(json2, "rows["+(index++)+"].courseName").equals("批量添加课程1493134527810")
                && index< JSON.parseObject(json2).getJSONArray("rows").size()){}
        int id = (int) JsonHelper.getValue2(json2, "rows["+(index-1)+"].id");
        System.out.println(id);

        System.out.println(JsonHelper.getBByA(json2, "rows.courseName=批量添加课程1493134527810", "id"));


    }
}
