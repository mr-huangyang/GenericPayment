package my.test;

import com.longhui.generic.payment.AlibabaPaymentGenerator;
import com.longhui.generic.payment.PaymentOrderGenerator;
import com.longhui.generic.payment.config.AlibabaConfig;
import com.longhui.generic.payment.vo.AlibabaRequest;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by huangyang on 17/7/15.
 */
public class AlibabaTest {

    private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ273HrWCIdHUxFBUuibUPS4KXXpCS4fxNNVbMKIFCZ4nj" +
            "HcLX9vY1uChr9DuMlq3FS90XO1566YEFUu+l243klqvsqR44Q6G3az+5OdJ8l79V2QWVl7" +
            "0SYMB+AEfL/25+hGj8C/XpW98eBY5RAC6go57MDsREEaWeSk4D6of3k3AgMBAAECgYAg/WiovFk+Sfm" +
            "is0Hu6oUtGdN6GMpVvklBVi2QJjvoq/OSRBW0AI0MV6ltGXHtlo8MnkLPa6" +
            "1QQ/euiUvODFtZNYf2+Bc7UIIkkqTlN7aEh0dgF5Lg6m8M8L0p0TtUEel5C/1i7C11esv5DpVe6WaX8M/T" +
             "wVs1UUUUc2nNQpSXsQJBAM4A0AY4Cr9GoaO2s1mjB+lGX1n935acl" +
            "J1wFLDvB81/py4d7O8+HkfC+qBPMPiwyL9PoAsW3Rn5uCIPaezLkm8CQQDEBAZ10ICs1reTwx0ZyAZTR0Gd+cBIFG/3rVr17NVnoKPBWP06JY2WsNB+eX4E" +
            "8ooysc++vQnNAuyhuIUp70m5AkEAt46JzcDJ8r3g8ZuuG8TlG7sU438PZ4iF8/MWvMGA" +
            "Vl/Ue4c2w8a5/H+ECqwFmtMLTUyJ8tegk7Crnc3WOKT8CQJAWnOJD6CxYBCNEfB9Y0I8OP2ut9lh82EK8f3a9ZTePT2Rk00fPU/K8HiTHWmuz1WklBQdS2UsOqBY1Z+yQ1Gac" +
            "QJAGaB+eo2bMFDTfFiQbiiDblr0Kbb3kyHGryrVTL+JXjhp6hzw3VYs1fdplAAriMilO8ZNChL04ewKf9i0fBi7RQ==";

    @Test
    public void test(){

        AlibabaConfig config = new AlibabaConfig() {
            @Override
            public String getAppId() {
                return "app_id_123";
            }

            @Override
            public String getPrivateKey() {
                return PRIVATE_KEY;
            }

            @Override
            public String getNotifyUrl() {
                return "http://www.baidu.com";
            }
        };

        PaymentOrderGenerator<AlibabaRequest,String> generator = new AlibabaPaymentGenerator(config);
        AlibabaRequest request = new AlibabaRequest();
        AlibabaRequest.BizContent biz_content = new AlibabaRequest.BizContent();
        biz_content.setBody("交易啦");
        biz_content.setSubject("买东西");
        biz_content.setOut_trade_no(UUID.randomUUID().toString());
        biz_content.setPassback_params("userId","one.yuang");
        biz_content.setPassback_params("product_type","vip");
        request.setBiz_content(biz_content);
        String requestStr = generator.generator(request);
        System.out.println(requestStr);
    }

}
