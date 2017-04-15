package cases;

import com.alibaba.fastjson.JSON;
import com.interfacetest.http.HttpUtil;
import com.interfacetest.http.bean.Interface;
import com.interfacetest.http.bean.Request;
import com.interfacetest.http.bean.RequestResponseArgs;
import com.interfacetest.http.enums.ContentType;
import com.interfacetest.http.enums.RequestMethod;
import com.interfacetest.util.JdbcUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by han on 2017/3/3.
 */
public class Case_a {

    public static void main(String[] args) throws Exception {
        String sql = "select * from interface where name = ?";
        List<Object> param = new ArrayList<Object>();
        param.add("验证手机号是否注册过");
        JdbcUtils mysql = new JdbcUtils();
        mysql.getConnection();
        Interface checkPhone = mysql.findSimpleRefResult(sql, param, Interface.class);
        System.out.println(checkPhone);

        List<RequestResponseArgs> reqArgs =  JSON.parseArray(checkPhone.getRequestArgs()).toJavaList(RequestResponseArgs.class);
        for (RequestResponseArgs rra : reqArgs){
            System.out.println(JSON.toJSONString(rra));
            rra.get
        }

        HttpUtil hu = new HttpUtil();
        Request req = new Request();
        req.setUrl(checkPhone.getUrl());
        req.setRequestMethod(RequestMethod.valueOf(checkPhone.getRequestMethod()));
        req.setContentType(ContentType.valueOf(checkPhone.getContentType()));
        req.setParam("");
        hu.send(req);

        req = new Request();
        req.setUrl("http://www.baidu.com");
        req.setRequestMethod(RequestMethod.GET);
        hu.send(req);
    }


}

