package cases;

import com.interfacetest.http.Post;
import com.interfacetest.http.Request;
import com.interfacetest.util.Common;
import com.interfacetest.util.JsonHelper;
import com.interfacetest.util.StrHelper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by han on 2017/3/3.
 */
public class Case_a {


//    @DataProvider(name = "user")
//    public Object[][] get(){
//        return new Object[][]{
////                {"13200000039"},
////                {"13200000062"},
////                {"13200000063"},
////                {"13200000068"},
////                {"13200000080"},
////                {"13200000102"},
////                {"13200000103"},
////                {"13200000104"},
////                {"13437173257"},
////                {"13672006677"},
////                {"13820294136"},
////                {"13911683486"},
////                {"15022241295"},
////                {"15194209402"},
////                {"18611123049"},
////                {"18615456446"},
////                {"18638343646"},
////                {"18644556558"},
////                {"18645545455"},
////                {"18645643434"}
//
//                {"18662932297"},
//                {"13929958176"},
//                {"13430367789"},
//                {"13811912878"},
//                {"15069285988"},
//                {"15268880300"},
//                {"13509329426"},
//                {"13480618096"},
//                {"18636198039"},
//                {"13817794046"},
//                {"18507993098"},
//                {"13453696195"},
//                {"13693090280"},
//                {"18512503726"},
//                {"18752226371"},
//                {"13567880793"},
//                {"18122200190"},
//                {"13688847799"},
//                {"15920169507"},
//                {"13838403814"},
//                {"18137157935"},
//                {"13760656608"},
//                {"13828508885"},
//                {"13609769593"},
//                {"15216765302"},
//                {"18636243532"},
//                {"13283136181"},
//                {"18522629856"},
//                {"15302346358"}
//
//        };
//    }

//    @Test(dataProvider="user")
//    public void YHQ(String user){

////        没有优惠券
//        String yhqJson = "[]";
////          有优惠券
////        String yhqJson = "xxx";
//        String[] urls = {
//                "https://h5.koudaitong.com/v2/ump/promocard/fetch?alias=ajmisgfi",
//                "https://h5.koudaitong.com/v2/ump/promocard/fetch?alias=4k2rxqt5",
//                "https://h5.koudaitong.com/v2/ump/promocard/fetch?alias=qkznnu18",
//                "https://h5.koudaitong.com/v2/ump/promocard/fetch?alias=1d4eluv1s",
//                "https://h5.koudaitong.com/v2/ump/promocard/fetch?alias=junowt1m",
//                "https://h5.koudaitong.com/v2/ump/promocard/fetch?alias=1ex9mpoc7",
//                "https://h5.youzan.com/v2/ump/promocard/fetch?alias=1f6qkyjwu"
//        };
//
//
////        String user = "15194209402";
//        String pass = "qqqqqq";
//
//
//        Post post = new Post();
//        Request login = new Post().setUrl("http://beta.exploiter.ivydad.com.cn/jsonapi/api_loginManual")
//                .addParam("phone_no_encryp", Common.encryptByPublicKey(user))
//                .addParam("password",Common.encryptByPublicKey(Common.getMD5(pass)))
//                .send();
//
//        Request yhq = new Post().setUrl("http://beta.exploiter.ivydad.com.cn/jsonapi/api_getCoupon")
//                .addParam("token", JsonHelper.getValue(login.getResult(), "data.token"))
//                .send();
//
//
//        for(int i=0; i<urls.length; i++){
//            Assert.assertEquals(urls[i], JsonHelper.getValue(yhq.getResult(), ("data.unclaimed_coupon_list["+i+"].coupon_url")));
//        }
////        Assert.assertEquals(yhqJson, JsonHelper.getValue(yhq.getResult(), "data.unclaimed_coupon_list".split("\\.")));
//
//    }
}

