package cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.interfacetest.http.HttpUtil;
import com.interfacetest.http.bean.Interface;
import com.interfacetest.http.bean.Request;
import com.interfacetest.http.bean.RequestResponseArgs;
import com.interfacetest.http.enums.ContentType;
import com.interfacetest.http.enums.RequestMethod;
import com.interfacetest.util.JdbcUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        JSONObject joo = jsonParam(reqArgs);

        System.out.println(joo);

        HttpUtil hu = new HttpUtil();
        Request req = new Request();
        req.setUrl(checkPhone.getUrl());
        req.setRequestMethod(RequestMethod.valueOf(checkPhone.getRequestMethod()));
        req.setContentType(ContentType.valueOf(checkPhone.getContentType()));
        req.setParam("a=a");
        hu.send(req);

        req = new Request();
        req.setUrl("http://www.baidu.com");
        req.setRequestMethod(RequestMethod.GET);
        hu.send(req);
    }


    /**
     *根据参数的规范 自动补全所有参数
     *
     * @param reqArgs
     * @return
     */
    public static JSONObject jsonParam(List<RequestResponseArgs> reqArgs){
        JSONObject jo = new JSONObject();

        for (RequestResponseArgs rra : reqArgs){
            if(rra.getChildren().size()>0){
                jo.put(rra.getName(), jsonParam(rra.getChildren()));
            }else{
                Object value;
                switch (rra.getType()){
                    case "string":
                        value = rra.getDefaultValue();
                        break;

                    case "number":
//                        value = Integer.parseInt(rra.getDefaultValue());
                        break;

                    case "boolean":
//                        value = Boolean.valueOf(rra.getDefaultValue());
                        break;

                    case "array":

                        break;
                }
                jo.put(rra.getName(), "aaa");
            }
        }



        return jo;
    }


    public static List<JSONObject> allJsonParam(List<RequestResponseArgs> reqArgs){
        List<JSONObject> params = new ArrayList<>();

        for (RequestResponseArgs rra : reqArgs){
            JSONObject jo = new JSONObject();




        }




        return null;
    }


    public static void x(RequestResponseArgs reqArg){
        Map<Object, Boolean> pa = new HashMap<>();
        //空 必填是反例 非必填是正例
        pa.put(null, !Boolean.valueOf(reqArg.getRequire()));

        switch (reqArg.getType()){
            case "string":
                pa.put(reqArg.getDefaultValue(), true);
                pa.put(123456, true);
                break;

            case "number":
                String number = reqArg.getDefaultValue();
                if(null == number){
                    number = "1";
                }
//                pa.put()
//                value = Integer.parseInt(rra.getDefaultValue());
                break;

            case "boolean":
//                value = Boolean.valueOf(rra.getDefaultValue());
                break;

            case "array":

                break;
        }
        //数据类型正确

        //数据类型不正确

    }


}

