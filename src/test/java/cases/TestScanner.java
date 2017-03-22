package cases;

import com.interfasetest.util.Common;

/**
 * Created by han on 2017/3/20.
 */
public class TestScanner {

    public static void main(String args[]){


        String s = Common.getMD5("123456");
        System.out.println(s);
        s = Common.encryptByPublicKey("123456", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgSxoiHRZ7P/OhBhxRDhs/cs6C\n" +
                "w67/NJum28LtKByzEVexcnYiwlWdDXk6zWau+9Ig8NJbxxjjj4sUjnXUKoJb41mE\n" +
                "aXQvCTdorDS75G05PrAjE24hYrlwxHyvNFMGRbHb6/vDdfnU2+zGf+QN7nnkRWzy\n" +
                "w2LT+zH28r0bKML6MwIDAQAB");
        System.out.println(s);

    }
}
