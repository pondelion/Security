//
// Created by softd on 2021/07/29.
//

#ifndef JNISAMPLE_NATIVE_COUNTER_H
#define JNISAMPLE_NATIVE_COUNTER_H
#include <stdint.h>
#include <unistd.h>
#include <thread>
#include <android//log.h>

class NativeCounter {
 public:
  NativeCounter(): value_(20) {
    auto cntUpWorker = [this]() {
      while (true) {
        this->countUp();
        sleep(1);
      }
    };
    this->cntUpThread_ = std::thread(cntUpWorker);
    __android_log_print(ANDROID_LOG_DEBUG, "NativeCounter", "%p : %d\n", &value_, value_);
  };
  int getValue() { return this->value_; };
  void setValue(int value) { this->value_ = value; };
  long long getValuePtr() {
    return reinterpret_cast<long>(&this->value_);
  };

 private:
  int value_;
  void countUp() { this->value_++; }
  std::thread cntUpThread_;
};

#endif //JNISAMPLE_NATIVE_COUNTER_H
