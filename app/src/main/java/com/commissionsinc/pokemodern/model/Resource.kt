package com.commissionsinc.pokemodern.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.commissionsinc.pokemodern.BR

// Resource object with observable "favorited" state to be exposed to data binding
class Resource(val name: String, val url: String, favorited: Boolean = false)
    : BaseObservable() {

    @get:Bindable
    var favorited : Boolean = favorited
    set(value) {
        field = value
        notifyPropertyChanged(BR.favorited)
    }
}