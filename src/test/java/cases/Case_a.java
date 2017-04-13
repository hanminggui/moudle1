package cases;

import com.interfacetest.http.Interface;
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
    }
}

