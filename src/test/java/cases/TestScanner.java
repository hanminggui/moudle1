package cases;

import com.interfasetest.util.Base64;
import com.interfasetest.util.Common;
import com.interfasetest.util.RSAEncrypt;

import java.io.File;

/**
 * Created by han on 2017/3/20.
 */
public class TestScanner {

    public static void main(String args[]) throws Exception {

        String user = "13200002732";
//        String user = "15600212730";
        String pass = "123456";

        System.out.println("账号明文:" + user);
        System.out.println("账号密文:" + Common.encryptByPublicKey(user));
        System.out.println("密码明文:" + pass);
        System.out.println("密码密文:" + Common.encryptByPublicKey(Common.getMD5(pass)));


//        String s = Common.getMD5("123456");
//        System.out.println(s);
//        s = Common.encryptByPublicKey("15600212730");
//        System.out.println(s);
//        s = Common.encryptByPublicKey("asdfghjkl");
//        System.out.println(s);


    }
}
