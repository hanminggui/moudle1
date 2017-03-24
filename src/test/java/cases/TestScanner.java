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


        String s = Common.getMD5("123456");
        System.out.println(s);
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgSxoiHRZ7P/OhBhxRDhs/cs6C\n" +
                "w67/NJum28LtKByzEVexcnYiwlWdDXk6zWau+9Ig8NJbxxjjj4sUjnXUKoJb41mE\n" +
                "aXQvCTdorDS75G05PrAjE24hYrlwxHyvNFMGRbHb6/vDdfnU2+zGf+QN7nnkRWzy\n" +
                "w2LT+zH28r0bKML6MwIDAQAB";
        s = Common.encryptByPublicKey("15600212730");
        System.out.println(s);
        s = Common.encryptByPublicKey("asdfghjkl");
        System.out.println(s);


    }
}
