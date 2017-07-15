package com.longhui.generic.payment;

import com.longhui.generic.payment.config.AlibabaConfig;
import com.longhui.generic.payment.vo.AlibabaRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AlibabaPaymentGenerator implements PaymentOrderGenerator<AlibabaRequest,String> {

    private AlibabaConfig config;

    public AlibabaPaymentGenerator(AlibabaConfig config){
        this.config = config;
        //验证
    }

    @Override
    public String generator(AlibabaRequest request) {
        request.setApp_id(config.getAppId());
        request.setNotify_url(config.getNotifyUrl());
        String sign = request.sign(config.getPrivateKey());
        try {
            URLEncoder.encode(sign,request.getCharset());
        } catch (UnsupportedEncodingException e) {
            throw  new RuntimeException(e);
        }
        return sign;
    }
}
