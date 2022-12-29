package ru.lachesis.translator.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meanings(
    @field:SerializedName("translation")
    @Expose
    val translation: Translation?,
    @field:SerializedName("imageUrl")
    @Expose
    val imageUrl: String?
)
