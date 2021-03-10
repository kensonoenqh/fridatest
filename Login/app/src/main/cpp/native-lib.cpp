#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_login_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello login w cpp world";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_login_MainActivity_loginFromJNI(
        JNIEnv* env,
        jobject /* this */,
        jstring user,
        jstring pass) {

    const char* userCString = (*env) . GetStringUTFChars(user, NULL);
    if (NULL == userCString) return (jstring) "";
    char* userBuf = (char*)malloc(4);
    strcpy(userBuf, userCString);

    const char* passCString = (*env) . GetStringUTFChars(pass, NULL);
    if (NULL == passCString) return (jstring) "";
    char* passBuf = (char*)malloc(4);
    strcpy(passBuf, passCString);

    std::string login_message = "failed login";
    if (strcmp(userBuf, "a") == 0) {

        if (strcmp(passBuf, "p") == 0) {
            login_message = "successful login!";
        }

    }


    return env->NewStringUTF(login_message.c_str());
}