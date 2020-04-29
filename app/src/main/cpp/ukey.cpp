//
// Created by xwr on 2020/4/28.
//

#include <jni.h>
#include "usbrw.h"
#include<android/log.h>

#define TAG "xwr"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型


extern "C"
JNIEXPORT jint JNICALL
Java_com_xwr_mulkeyboard_UKeyApi_key_1read_1psd(JNIEnv *env, jclass type, jbyteArray response_) {
    long ret;
    jbyte *response = env->GetByteArrayElements(response_, NULL);
    unsigned char cmd1[] = {0x01, 0xa3};
    unsigned char cmd2[] = {0x01, 0xa1};
    usb_sent(env, 2, cmd1);
    usb_sent(env, 2, cmd2);
    usleep(1000);
    ret = usb_read(env, (unsigned char *) (response));
    LOGD("init send ret:%d", ret);
    env->ReleaseByteArrayElements(response_, response, 0);
    return ret;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_xwr_mulkeyboard_UKeyApi_key_1scan(JNIEnv *env, jclass type, jbyteArray response_) {
    jbyte *response = env->GetByteArrayElements(response_, NULL);
    long ret;
    unsigned char cmd1[] = {0x01, 0xb0};
    usb_sent(env, 2, cmd1);
    ret = usb_read(env, (unsigned char *) response);
    env->ReleaseByteArrayElements(response_, response, 0);
    return ret;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_xwr_mulkeyboard_UKeyApi_key_1usb_1init(JNIEnv *env, jclass type) {

    long ret;
    unsigned char response[64] = {0x00};
    unsigned char cmd[] = {0x01, 0xa0};
    char resp[] = {0x0a, 0xfe, 0x02, 0x73, 0x6f, 0x6c, 0x69, 0x63, 0x33, 0x03};
    usb_sent(env, 2, cmd);
    usleep(1000);
    usb_read(env, response);
    char test[10];
    memcpy(test, response, 10);
    for (int i = 0; i < 10; i++) {
        if (test[i] != resp[i]) {
            return -1;
        }
    }
    return 0;

}

extern "C"
JNIEXPORT jint JNICALL
Java_com_xwr_mulkeyboard_UKeyApi_key_1read_1new_1psd(JNIEnv *env, jclass type,
                                                     jbyteArray response_) {
    jbyte *response = env->GetByteArrayElements(response_, NULL);
    long ret;
    unsigned char cmd1[] = {0x01, 0xa3};
    unsigned char cmd2[] = {0x01, 0xa2};
    usb_sent(env, 2, cmd1);
    usb_sent(env, 2, cmd2);
    usleep(1000);
    ret = usb_read(env, (unsigned char *) (response));
    env->ReleaseByteArrayElements(response_, response, 0);
    return ret;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_xwr_mulkeyboard_UKeyApi_key_1read_1new_1psd_1again(JNIEnv *env, jclass type,
                                                            jbyteArray response_) {
    jbyte *response = env->GetByteArrayElements(response_, NULL);
    long ret;
    unsigned char cmd1[] = {0x01, 0xa3};
    unsigned char cmd2[] = {0x01, 0xa4};
    usb_sent(env, 2, cmd1);
    usb_sent(env, 2, cmd2);
    usleep(1000);
    ret = usb_read(env, (unsigned char *) (response));
    LOGD("init send ret:%d", ret);
    env->ReleaseByteArrayElements(response_, response, 0);
    return ret;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_xwr_mulkeyboard_UKeyApi_key_1clear(JNIEnv *env, jclass type) {
    long ret;
    unsigned char cmd[] = {0x01, 0xa3};
    ret = usb_sent(env, 2, cmd);
    return ret;

}

extern "C"
JNIEXPORT jint JNICALL
Java_com_xwr_mulkeyboard_UKeyApi_key_1read_1serialnumber(JNIEnv *env, jclass type,
                                                         jbyteArray response_) {
    long ret;
    jbyte *response = env->GetByteArrayElements(response_, NULL);
    unsigned char cmd[] = {0x01, 0xc1};
    ret = usb_sent(env, 2, cmd);
    env->ReleaseByteArrayElements(response_, response, 0);
    return ret;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_xwr_mulkeyboard_UKeyApi_key_1read_1old_1psd(JNIEnv *env, jclass type,
                                                     jbyteArray response_) {
    jbyte *response = env->GetByteArrayElements(response_, NULL);
    long ret;
    unsigned char cmd1[] = {0x01, 0xa3};
    unsigned char cmd2[] = {0x01, 0xa5};
    usb_sent(env, 2, cmd1);
    usb_sent(env, 2, cmd2);
    usleep(1000);
    ret = usb_read(env, (unsigned char *) (response));
    env->ReleaseByteArrayElements(response_, response, 0);
    return ret;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_xwr_mulkeyboard_UKeyApi_key_1evaluation(JNIEnv *env, jclass type, jbyteArray response_) {
    jbyte *response = env->GetByteArrayElements(response_, NULL);
    long ret;
    unsigned char cmd1[] = {0x01, 0xa3};
    unsigned char cmd2[] = {0x01, 0xaa};
    ret = usb_sent(env, 2, cmd1);
    LOGD("evaluation send ret:%d", ret);
    ret = usb_sent(env, 2, cmd2);
    LOGD("evaluation send ret:%d", ret);
    ret = usb_read(env, (unsigned char *) (response));
    LOGD("evaluation response ret:%d", ret);
    env->ReleaseByteArrayElements(response_, response, 0);
    return ret;
}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_xwr_mulkeyboard_UKeyApi_key_1reader_1Init(JNIEnv *env, jclass type,
                                                   jobject deviceConnection, jobject usbEpIn,
                                                   jobject usbEPOut) {

    Reader_Init(env, deviceConnection, usbEpIn, usbEPOut);
    return 0;

}