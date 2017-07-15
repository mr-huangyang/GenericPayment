package com.longhui.generic.payment;

public interface PaymentOrderGenerator<I,R> {

    R generator(I order);

}
