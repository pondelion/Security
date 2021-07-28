package com.example.jnisample

class NativeCounter {
    private var pointer: Long = 0

    external fun getValue(): Int
    external fun setValue(value: Int)
    external fun getValuePtr(): Long
}