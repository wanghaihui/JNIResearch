#include <android/log.h>
#include <stdio.h>
#include <jni.h>

jint NativeAddition(JNIEnv *pEnv, jobject pObj, jint pa, jint pb)
{
    return pa + pb;
}

jint NativeMultiplication(JNIEnv *pEnv, jobject pObj, jint pa, jint pb)
{
    return pa * pb;
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *pVm, void *reserved)
{
    JNIEnv *env;
    if ((*pVm)->GetEnv(pVm, (void **)&env, JNI_VERSION_1_6)) {
        return -1;
    }

    JNINativeMethod nm[2];
    nm[0].name = "NativeAddition";
    nm[0].signature = "(II)I";
    nm[0].fnPtr = NativeAddition;
    nm[1].name = "NativeMultiplication";
    nm[1].signature = "(II)I";
    nm[1].fnPtr = NativeMultiplication;

    jclass cls = (*env)->FindClass(env, "com/xiaobukuaipao/jnitestfirst/JNITestFirst");
    (*env)->RegisterNatives(env, cls, nm, 2);

    return JNI_VERSION_1_6;
}

