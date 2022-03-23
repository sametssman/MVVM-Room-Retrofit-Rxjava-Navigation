package com.sametsisman.kotlincountries.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(

    @ColumnInfo(name = "countryName")
    @SerializedName("name")
    val countryName : String?,

    @ColumnInfo(name = "countryRegion")
    @SerializedName("region")
    val countryRegion : String?,

    @ColumnInfo(name = "countryCapital")
    @SerializedName("capital")
    val countryCapital: String?,

    @ColumnInfo(name = "countryCurrency")
    @SerializedName("currency")
    val countryCurrency: String?,

    @ColumnInfo(name = "countryLanguage")
    @SerializedName("language")
    val countryLanguage: String?,

    @ColumnInfo(name = "countryImage")
    @SerializedName("flag")
    val imageurl : String?) {

    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0

}

