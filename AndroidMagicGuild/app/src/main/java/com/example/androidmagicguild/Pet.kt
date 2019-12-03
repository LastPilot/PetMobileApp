package com.example.androidmagicguild

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class Pet(
    val name : String? = "",
    val type : String? = "",
    val bio : String? = "",
    val contact : String? = "",
    val zipCode : Int? = 0)