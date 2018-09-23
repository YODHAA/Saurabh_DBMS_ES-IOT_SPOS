#include<jni.h>
#include<stdio.h>
#include"CalcJNI.h"

// Implementation of native method add() of CalcJNI class
JNIEXPORT jint JNICALL Java_CalcJNI_add(JNIEnv *env, jobject thisObj,jint n1,jint n2)
{
jint res;
res = n1+n2;
return res;
}
JNIEXPORT jint JNICALL Java_CalcJNI_sub(JNIEnv *env, jobject thisObj,jint n1,jint n2)
{
jint res;
res = n1-n2;
return res;
}
JNIEXPORT jint JNICALL Java_CalcJNI_mul(JNIEnv *env, jobject thisObj,jint n1,jint n2)
{
jint res;
res = n1*n2;
return res;
}
JNIEXPORT jint JNICALL Java_CalcJNI_div(JNIEnv *env, jobject thisObj,jint n1,jint n2)
{
jint res;
res = n1/n2;
return res;
}
