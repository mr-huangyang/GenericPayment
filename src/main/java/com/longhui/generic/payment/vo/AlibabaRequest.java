package com.longhui.generic.payment.vo;


import com.alibaba.fastjson.JSON;
import com.longhui.generic.payment.alibaba.AlipaySignature;
import com.longhui.generic.payment.sign.Sign;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.SortedSet;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * refer : https://docs.open.alipay.com/204/105465/
 */
@Data
public class AlibabaRequest implements Sign {

    private String app_id;
    private String method = "alipay.trade.app.pay";
    private String format = "JSON";
    private String charset = "utf-8";
    private String sign_type = "RSA";
    private String timestamp;
    private String version = "1.0";
    private String notify_url;
    private BizContent biz_content;

    @Override
    public String toString() {
        BeanMap beanMap = new BeanMap(this);

        //对属性排序
        SortedSet keys = new TreeSet(beanMap.keySet());
        StringJoiner sb = new StringJoiner("&");
        keys.forEach(k -> {
            if(! "class".equals(k)){
                Object o = beanMap.get(k);
                if(o!=null && StringUtils.isNotEmpty(o.toString())){
                    sb.add(String.format("%s=%s", k, o.toString()));
                }
            }
        });
        return sb.toString();

    }

    @Override
    public String sign(String privateKey) {
        return this.toString() + "&sign=" + AlipaySignature.rsaSign(this.toString(),privateKey,charset);
    }


    @Data
    public static class BizContent {
        private String body;
        private String subject;
        private String out_trade_no;
        private String timeout_express;
        private String total_amount;
        private String seller_id;
        private String product_code;
        private String goods_type;

        @Setter(AccessLevel.NONE)
        private String passback_params;
        private String extend_params;
        private String enable_pay_channels;
        private String disable_pay_channels;
        private String store_id;

        public void setPassback_params(String k, String v) {
            if (passback_params == null) {
                passback_params = "";
            }
            passback_params.concat(k + "=" + v + "&");

        }

        public String getPassback_params() {
            if (StringUtils.isEmpty(passback_params))
                return null;
            try {
                int length = passback_params.length();
                return URLDecoder.decode(passback_params.substring(0, length - 1), StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }


}
