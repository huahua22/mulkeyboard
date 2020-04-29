//
// Created by xwr on 2020/4/28.
//

#ifndef MULKEYBOARD_USBRW_H
#define MULKEYBOARD_USBRW_H
#include <jni.h>
#include <string>
#include <unistd.h>

long usb_sent(JNIEnv *env, long len, unsigned char *WriteBuf);
long usb_read(JNIEnv *env, unsigned char ReadBuf[]);
long Reader_Init(JNIEnv *env, jobject deviceConnection,
                 jobject usbEpIn,
                 jobject usbEPOut);
#endif //MULKEYBOARD_USBRW_H
