package com.rbnb.orangepages.login

import android.widget.CheckBox
import androidx.databinding.BindingAdapter

@BindingAdapter("checked_change")
fun bindCheckChange(checkBox: CheckBox, viewModel: LoginViewModel) {
    checkBox.setOnCheckedChangeListener { _, shouldKeepSignedIn ->
        viewModel.onKeepSignedIn(shouldKeepSignedIn)
    }
}