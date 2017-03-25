package com.bp.micro.svc.bo;

import java.math.BigInteger;

public class Runner {
  public static void main(String[] args) throws Exception {
    BigInteger first = new BigInteger("123456789123456789");
    BigInteger second = new BigInteger("10000000000000");

    System.out.println(first.add(second));
    System.out.println(first.multiply(second));
  }

}
