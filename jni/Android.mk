LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := algorithms
LOCAL_SRC_FILES := algorithms.c

include $(BUILD_SHARED_LIBRARY)
