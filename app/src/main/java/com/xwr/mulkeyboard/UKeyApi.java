package com.xwr.mulkeyboard;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;

/**
 * Create by xwr on 2020/4/28
 * Describe:
 */
public class UKeyApi {
  static {
    System.loadLibrary("cp");
  }


  /**
   * usb设备初始化
   *
   * @param deviceConnection
   * @param usbEpIn
   * @param usbEPOut
   * @return
   */
  public native static long key_reader_Init(UsbDeviceConnection deviceConnection,
    UsbEndpoint usbEpIn,
    UsbEndpoint usbEPOut);

  /**
   * 检测免驱动usb键盘
   *
   * @return >0成功;-1检测失败
   */
  public native static int key_usb_init();

  /**
   * 输入密码
   *
   * @param response 检测免驱动USB键盘
   * @return 总长度
   */
  public native static int key_read_psd(byte[] response);

  /**
   * 输入原密码
   *
   * @param response
   * @return
   */
  public native static int key_read_old_psd(byte[] response);

  /**
   * 输入新密码
   *
   * @param response
   * @return
   */
  public native static int key_read_new_psd(byte[] response);

  /**
   * 再次输入新密码
   *
   * @param response
   * @return
   */
  public native static int key_read_new_psd_again(byte[] response);

  /**
   * 评价
   *
   * @param response
   * @return
   */
  public native static int key_evaluation(byte[] response);

  /**
   * 扫码
   *
   * @param response
   * @return
   */
  public native static int key_scan(byte[] response);


  /**
   * 清除
   *
   * @return
   */
  public native static int key_clear();

  /**
   * 读序列号
   *
   * @param response
   * @return
   */
  public native static int key_read_serialnumber(byte[] response);

}
