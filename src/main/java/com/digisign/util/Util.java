/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digisign.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 *
 * @author newbi
 */
public class Util {

    public Util() {
    }

    public static String setSHA256(String input)
            throws UnsupportedEncodingException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(input.getBytes("UTF-8"));
            byte[] messageDigest = md.digest();
            BigInteger bigInt = new BigInteger(1, messageDigest);
            return bigInt.toString(16);

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e);
        }
    }

    public static String setMD5(String input)
            throws UnsupportedEncodingException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(input.getBytes("UTF-8"));
            byte[] messageDigest = md.digest();
            BigInteger bigInt = new BigInteger(1, messageDigest);
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String upperCaseFirst(String input) {
        char[] array = input.toCharArray();

        array[0] = Character.toUpperCase(array[0]);

        return new String(array);
    }

    public static String cekEmail(String input) throws UnsupportedEncodingException {
        String domain = "csasolution.com";
        String[] tokens = input.split("@");
        String kata1 = null;
        String kata2 = null;
        String isiEmail = null;

//        for (String token : tokens) {
//            if (token.equals(domain)) {
//                kata1 = token;
//            } else {
//                kata2 = token;
//            }
//        }
        for (int j = 0; j < tokens.length; j++) {
            if (tokens[j].equals(domain)) {
                kata1 = tokens[j];

            } else {
                kata2 = tokens[j];
            }
        }

        return isiEmail;
    }

    public static String getTokenString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static String getPasswordString() {
//        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static String setMonth(String input) {
        switch (input) {
            case "1":

            case "01":
                input = "I";
                break;
            case "2":

            case "02":
                input = "II";
                break;
            case "3":

            case "03":
                input = "III";
                break;
            case "4":

            case "04":
                input = "IV";
                break;
            case "5":

            case "05":
                input = "V";
                break;
            case "6":

            case "06":
                input = "VI";
                break;
            case "7":

            case "07":
                input = "VII";
                break;
            case "8":

            case "08":
                input = "VIII";
                break;
            case "9":

            case "09":
                input = "IX";
                break;
            case "10":
                input = "X";
                break;
            case "11":
                input = "XI";
                break;
            case "12":
                input = "XII";
                break;
            default:
                input = "";
        }
        return input;
    }

    public static String setNumber(String param) {
        String isiParam = null;
        if (param.length() == 1) {
            isiParam = "000" + param;
        }
        if (param.length() == 2) {
            isiParam = "00" + param;
        }
        if (param.length() == 3) {
            isiParam = "0" + param;
        }
        if (param.length() == 4) {
            isiParam = param;
        }
        return isiParam;

    }
}

