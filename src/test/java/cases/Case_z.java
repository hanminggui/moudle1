package cases;

import com.interfacetest.http.Request;
import com.interfacetest.http.Get;
import com.interfacetest.http.Post;

/**
 * Created by han on 2017/3/3.
 */
public class Case_z {

    public static void main(String[] args){
//        Request re1 = new Http().setUrl("http://www.baidu.com").get();
//        Request re2 = new Http().setUrl("http://www.webservicex.net/whois.asmx/GetWhoIS").setParams("HostName=www.google.com").post();

        Request re3 = new Post()
                .setParams("method=POST&uuid=39351c992cba4a748b631bdced15ef1a&sign=88334aa81037a8f21145ec2e455d7855&userInfo={nick_name:张大,sex:1, baby_sex :1, baby_name:“张三”，baby_birthday:0000-00-00,\"avatar_url\":\"http://aaa\"} ")
                .setHeaders("a=a")
                .setPath("api/ivy_lgrg")
                .send();

        Request re2 = new Get()
                .setUrl("http://apps.faxuan.net/appbss/service/getappbasenewslist?pageSize=1&areaCode=880000")
                .setHeaders("a=b")
                .send();




    }

//    @DataProvider
//    @Test
//    public void testxx(String url, int code, String hasJson){
//        Request req = new Http(url).doGet();//发get请求
//        Assert.assertEquals(req.getCode(), code ,"验证code");
//        Assert.assertEquals(new JsonHelper().isHasJson(req.getResult(),hasJson),true ,"验证json结果是否包含"+hasJson);
//    }
//
//    @DataProvider
//    @Test
//    public void testxz(String url, String param, int code, String hasJson){
//        Request req = new Http(url,param).doPost();//发post请求
//        Assert.assertEquals(req.getCode(), code ,"验证code");
//        Assert.assertEquals(new JsonHelper().isHasJson(req.getResult(),hasJson),true ,"验证json结果是否包含"+hasJson);
//    }

//    @DataProvider
//    @Test
//    public void testxc(String url, String param, String headers, int code, String hasJson){
//        Request req = new Http(url,param,headers).doPost();//发需要设置请求头的post请求
//        Assert.assertEquals(req.getCode(), code ,"验证code");
//        Assert.assertEquals(new JsonHelper().isHasJson(req.getResult(),hasJson),true ,"验证json结果是否包含"+hasJson);
//    }

}
