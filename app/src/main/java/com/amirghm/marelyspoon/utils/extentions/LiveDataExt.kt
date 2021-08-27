package com.amirghm.grocery.utils.extentions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


/**
 * This Extension is used to observe a livedata for only once.
 */
fun <T> LiveData<T>.observeOnce(observer: (T) -> Unit) {
    observeForever(object: Observer<T> {
        override fun onChanged(value: T) {
            removeObserver(this)
            observer(value)
        }
    })
}