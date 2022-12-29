package ru.lachesis.translator.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Translation(
    @field:SerializedName("text")
    @Expose
    val translation: String?
)