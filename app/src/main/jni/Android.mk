LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := JNITest
LOCAL_SRC_FILES := jnitest.c primitive.c stringtest.c
LOCAL_LDLIBS := -llog
include $(BUILD_SHARED_LIBRARY)