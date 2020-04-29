package com.xwr.mulkeyboard;

import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Create by xwr on 2020/2/14
 * Describe:
 */
public class HexUtil {
  public static String bytesToHexString(byte[] src, int length) {
    StringBuilder stringBuilder = new StringBuilder("");
    if (src == null || length <= 0) {
      return null;
    }
    for (int i = 0; i < length; i++) {
      int v = src[i] & 0xFF;
      String hv = Integer.toHexString(v);
      if (hv.length() < 2) {
        stringBuilder.append(0);
      }
      stringBuilder.append(hv + " ");
    }
    return stringBuilder.toString();
  }

  public static String bytesToString2(byte[] bytes) {
    final char[] hexArray = "0123456789ABCDEF".toCharArray();
    char[] hexChars = new char[bytes.length * 2];
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
      int v = bytes[i] & 0xFF;
      hexChars[i * 2] = hexArray[v >>> 4];
      hexChars[i * 2 + 1] = hexArray[v & 0x0F];

      sb.append(hexChars[i * 2]);
      sb.append(hexChars[i * 2 + 1]);
      sb.append(' ');
    }
    return sb.toString();
  }

  public static String bytes2HexString(byte[] b) {
    String ret = "";
    for (int i = 0; i < b.length; i++) {
      String hex = Integer.toHexString(b[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      ret += hex.toUpperCase();
    }
    return ret;
  }


  public static String decode(byte[] b) {
    String ret = "";
    for (int i = 0; i < b.length; i++) {
      String hex = Integer.toHexString(b[i] & 0xFF);
      if (hex.length() == 1) {
        hex = '0' + hex;
      }
      ret += hex.toUpperCase();
    }
    return ret;
  }

  public static String base64Decode(String content, String charsetName) {
    if (TextUtils.isEmpty(charsetName)) {
      charsetName = "UTF-8";
    }
    byte[] contentByte = Base64.decode(content, Base64.DEFAULT);
    try {
      return new String(contentByte, charsetName);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

}
