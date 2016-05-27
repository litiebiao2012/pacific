package com.pacific.common.utils;

import java.math.BigDecimal;

/**
 * Created by zhanghua on 2016/3/16.
 */
public class BigDecimalUtil {
    /**
     * 相除时，保留长度
     */
    private static int scale=2;
    public static void main(String[] args) {
        BigDecimal b=new BigDecimal(100);
        System.out.println(add(b,null).negate());
    }

    public static BigDecimal strValueBigDecimal(Number number){
        if(number==null){
            return zeroBigDecimal();
        }
        return new BigDecimal(String.valueOf(number));
    }
    public static BigDecimal negation(BigDecimal bigDecimal){
        if(bigDecimal==null){
            return zeroBigDecimal();
        }
        return bigDecimal.negate();
    }

    /**
     * big decimal 增加
     * @param bigDecimal
     * @param bigDecimal2
     * @return
     */
    public static BigDecimal add(BigDecimal bigDecimal,BigDecimal bigDecimal2){
        if(bigDecimal==null&&bigDecimal2==null){
            return zeroBigDecimal();
        }
        if(bigDecimal==null){
            return bigDecimal2;
        }
        if(bigDecimal2==null){
            return bigDecimal;
        }
        return bigDecimal.add(bigDecimal2);
    }

    /**
     * big decimal 相乘
     * @param bigDecimal
     * @param bigDecimal2
     * @return
     */
    public static BigDecimal multiply(BigDecimal bigDecimal,BigDecimal bigDecimal2){
        if(bigDecimal==null&&bigDecimal2==null){
            return zeroBigDecimal();
        }
        if(bigDecimal==null){
            return bigDecimal2;
        }
        if(bigDecimal2==null){
            return bigDecimal;
        }
        return bigDecimal.multiply(bigDecimal2);
    }

    /**
     * devide decimal 相除
     * @param dividend 被除数
     * @param divisor  除数
     * @return
     */
    public static BigDecimal devide(BigDecimal dividend, BigDecimal divisor){
        if(dividend ==null&& divisor ==null){
            return zeroBigDecimal();
        }
        if(dividend ==null){
            return divisor;
        }
        if(divisor ==null){
            return dividend;
        }
        if(divisor.intValue()==0){
            return dividend;
        }
        return dividend.divide(divisor,scale,BigDecimal.ROUND_CEILING);
    }

    /**
     * big decimal 相减
     * @param bigDecimal
     * @param bigDecimal2
     * @return
     */
    public static BigDecimal subtract(BigDecimal bigDecimal,BigDecimal bigDecimal2){
        if(bigDecimal==null&&bigDecimal2==null){
            return zeroBigDecimal();
        }
        if(bigDecimal==null){
            return bigDecimal2;
        }
        if(bigDecimal2==null){
            return bigDecimal;
        }
        return bigDecimal.subtract(bigDecimal2);
    }
    public static BigDecimal zeroBigDecimal(){
        return new BigDecimal("0.00");
    }


    public static BigDecimal newBigDecimal(Object value) {
        if(value==null ){
            return zeroBigDecimal();
        }
        return new BigDecimal(value.toString());
    }
}
