#include <jni.h>
#include <string>
#include <jni.h>
#include <vector>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_classification_MainActivity_stringFromJNI(JNIEnv *env, jobject thiz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_com_example_classification_MainActivity_findMaxIndex(JNIEnv *env, jobject thiz, jfloatArray outputFeature0) {
    int len = env->GetArrayLength(outputFeature0);
    float *arr = env->GetFloatArrayElements(outputFeature0, JNI_FALSE);

    jint maxI = 0;
    jfloat maxVal = INT_MIN;

    for (int i = 0; i < len; i++) {
        if (arr[i] > maxVal) {
            maxVal = arr[i];
            maxI = i;
        }
    }

    env->ReleaseFloatArrayElements(outputFeature0, arr, JNI_ABORT);

    return maxI;
}

