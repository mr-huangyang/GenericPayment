package com.longhui.generic.payment.vo;

import lombok.Data;

/**
 * refer:https://docs.open.alipay.com/204/105301/  异步通知页面
 */
@Data
public class AlibabaResponse {
    private String notify_time;
    private String notify_id;
    private String app_id;
    private String charset;
    private String sign_type;
    private String sign;
    private String trade_no;//支付宝交易凭证号
    private String out_trade_no;//商家订单号
    private String out_biz_no;
    private String trade_status;//TRADE_SUCCESS,TRADE_FINISHED
    private Double total_amount;
    private String receipt_amount;
    private String buyer_pay_amount;
    private String passback_params;
    private String gmt_create;
    private String gmt_payment;
    private String gmt_refund;
    private String gmt_close;
}
