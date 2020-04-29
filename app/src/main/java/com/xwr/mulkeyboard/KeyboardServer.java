package com.xwr.mulkeyboard;

import static com.xwr.mulkeyboard.UKeyDevice.usbEpIn;
import static com.xwr.mulkeyboard.UKeyDevice.usbEpOut;

/**
 * Create by xwr on 2020/4/28
 * Describe:
 */
public class KeyboardServer {

  public void key_init() {
    UKeyApi.key_reader_Init(UKeyDevice.mDeviceConnection, usbEpIn, usbEpOut);
  }

  public int key_read_init() {
    long ret;
    ret = UKeyApi.key_usb_init();
    return (int) ret;
  }

  public String read_pwd() {
    byte[] response = new byte[1024];
    int ret, length;
    ret = UKeyApi.key_read_psd(response);
    if (ret > 0) {
      length = Integer.valueOf(response[0]) - 3;
      byte[] data = new byte[length];
      System.arraycopy(response, 3, data, 0, length);
      return new String(data);
    }
    return null;
  }

  public String read_scan() {
    String result = null;
    byte[] response = new byte[1024];
    int ret = UKeyApi.key_scan(response);
    if (ret > 0) {
      int length;
      length = Integer.valueOf(response[0]) - 5;
      if (length > 0) {
        byte[] data = new byte[length];
        System.arraycopy(response, 4, data, 0, length);
        result = HexUtil.base64Decode(new String(data), "");
        return result;
      }
    }
    return result;
  }

  public String read_old_pwd() {
    byte[] response = new byte[1024];
    int ret, length;
    ret = UKeyApi.key_read_old_psd(response);
    if (ret > 0) {
      length = Integer.valueOf(response[0]) - 3;
      byte[] data = new byte[length];
      System.arraycopy(response, 3, data, 0, length);
      return new String(data);
    }
    return null;
  }

  public String read_new_pwd() {
    byte[] response = new byte[1024];
    int ret, length;
    ret = UKeyApi.key_read_new_psd(response);
    if (ret > 0) {
      length = Integer.valueOf(response[0]) - 3;
      byte[] data = new byte[length];
      System.arraycopy(response, 3, data, 0, length);
      return new String(data);
    }
    return null;
  }

  public String read_new_pwd_again() {
    byte[] response = new byte[1024];
    int ret, length;
    ret = UKeyApi.key_read_new_psd_again(response);
    if (ret > 0) {
      length = Integer.valueOf(response[0]) - 3;
      byte[] data = new byte[length];
      System.arraycopy(response, 3, data, 0, length);
      return new String(data);
    }
    return null;
  }

  public int read_clear() {
    return UKeyApi.key_clear();
  }

  public String read_evaluation() {
    byte[] response = new byte[1024];
    int ret, length;
    ret = UKeyApi.key_evaluation(response);
    System.out.println(HexUtil.bytesToHexString(response, ret));
    if (ret > 0) {
      length = Integer.valueOf(response[0]) - 3;
      byte[] data = new byte[length];
      System.arraycopy(response, 3, data, 0, length);
      return new String(data);
    }
    return null;
  }

  public String read_serialnumber() {
    byte[] response = new byte[1024];
    int ret, length;
    ret = UKeyApi.key_read_serialnumber(response);
    if (ret > 0) {
      length = Integer.valueOf(response[0]) - 3;
      byte[] data = new byte[length];
      System.arraycopy(response, 3, data, 0, length);
      return new String(data);
    }
    return null;
  }
}
