#include <jni.h>
#include <string>
#include "native_counter.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_jnisample_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

NativeCounter* getVariableManagerInstance(JNIEnv* env, jobject thisj) {
    static jclass classj = env->GetObjectClass(thisj);
    static jfieldID fieldId = env->GetFieldID(classj, "pointer", "J");
    long pointer = env->GetLongField(thisj, fieldId);
    if (!pointer) {
        pointer = (jlong)(new NativeCounter());
        env->SetLongField(thisj, fieldId, (jlong)pointer);
    }
    return (NativeCounter *)pointer;
}

extern "C" JNIEXPORT jint JNICALL
Java_com_example_jnisample_NativeCounter_getValue(JNIEnv* env, jobject thisj) {
    return getVariableManagerInstance(env, thisj)->getValue();
}

extern "C" JNIEXPORT void JNICALL
Java_com_example_jnisample_NativeCounter_setValue(JNIEnv* env, jobject thisj, jint value) {
    getVariableManagerInstance(env, thisj)->setValue(value);
}

extern "C" JNIEXPORT jlong JNICALL
Java_com_example_jnisample_NativeCounter_getValuePtr(JNIEnv* env, jobject thisj) {
    return getVariableManagerInstance(env, thisj)->getValuePtr();
}