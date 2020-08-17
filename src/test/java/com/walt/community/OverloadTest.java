package com.walt.community;

import org.hamcrest.core.Is;

/**
 * @author: walt1012
 * @date: 2020/7/28
 */
public class OverloadTest {

    static boolean isOdd(int i, Integer j) {
        System.out.println(i + j);
        return true;
    }

    static boolean isOdd(Integer i, int j) {
        System.out.println(i + j);
        return true;
    }

    public static void main(String[] args) {

    }
}
