package com.rbnb.orangepages.login

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("checked_change")
fun bindCheckChange(checkBox: CheckBox, viewModel: LoginViewModel) {
    checkBox.setOnCheckedChangeListener { _, shouldKeepSignedIn ->
        viewModel.onKeepSignedIn(shouldKeepSignedIn)
    }
}

@BindingAdapter("error_enabled")
fun bindErrorEnabled(textView: TextView, error_enabled: Boolean) {
    textView.visibility = when (error_enabled) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}