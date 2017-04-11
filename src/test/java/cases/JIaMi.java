package cases;

import java.sql.*;

/**
 * Created by jd on 2017/3/22.
 */
public class JIaMi {

    public static void mian(String args[]){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(
                    "rr-2zel5jbtvrx2s8565o.mysql.rds.aliyuncs.com", "ivydad_test", "IVYDAd2015");
            stmt = con.createStatement();
            String sql = "";
            // 一般验证SQL语句写的对不对最好打印出来,然后去相应的数据库工具执行该条语句,执行成功则SQL没错,否则SQL写错
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }

}
