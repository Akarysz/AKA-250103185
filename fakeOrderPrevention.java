package com.ecommerce;

import java.sql.SQLOutput;

public class fakeOrderPrevention {
    public boolean islegit(double amount){
        if(amount < 0){
            System.out.println("Order is not legit ");
            return false;
        }else{
            System.out.println("everything is legit");
            return true;
        }
    }
}
