package com.xwr.mulkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  KeyboardServer mKeyboardServer;
  TextView mInit;
  TextView psd;
  TextView newPsd;
  TextView newPsdAgain;
  TextView oldPsd;
  TextView scan;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
    initData();
  }


  private void initView() {
    mInit = findViewById(R.id.tv_init);
    psd = findViewById(R.id.tv_psd);
    newPsd = findViewById(R.id.tv_new_psd);
    newPsdAgain = findViewById(R.id.tv_new_psd_again);
    oldPsd = findViewById(R.id.tv_old_psd);
    scan = findViewById(R.id.tv_scan);
  }

  private void initData() {
    try {
      UKeyUtil.getInstance(this).initUsbData();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    mKeyboardServer = new KeyboardServer();
    if (UKeyDevice.mDeviceConnection != null) {
      mKeyboardServer.key_init();
    } else {
      Toast.makeText(this, "请获取权限", Toast.LENGTH_LONG).show();
    }
  }

  public void init(View view) {
    int ret = mKeyboardServer.key_read_init();
    mInit.setText("init:" + ret);
  }

  public void inputPsd(View view) {
    String result = mKeyboardServer.read_pwd();
    if (result != null) {
      psd.setText(result);
    }
  }

  public void inputOldPsd(View view) {
    String result = mKeyboardServer.read_old_pwd();
    if (result != null) {
      oldPsd.setText(result);
    }
  }

  public void inputNewPsd(View view) {
    String result = mKeyboardServer.read_new_pwd();
    if (result != null) {
      newPsd.setText(result);
    }
  }

  public void inputNewPsdAgain(View view) {
    String result = mKeyboardServer.read_new_pwd_again();
    if (result != null) {
      newPsdAgain.setText(result);
    }
  }


  public void scan(View view) {
    String result = mKeyboardServer.read_scan();
    if (result != null) {
      scan.setText(result);
    }
  }
}
