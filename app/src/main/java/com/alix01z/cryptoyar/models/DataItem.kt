package com.alix01z.cryptoyar.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DataItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("symbol")
    val symbol: String?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("cmcRank")
    val cmcRank: Int,

    @SerializedName("marketPairCount")
    val marketPairCount: Int,

    @SerializedName("circulatingSupply")
    val circulatingSupply: Double,

    @SerializedName("totalSupply")
    val totalSupply: Double,

    @SerializedName("maxSupply")
    val maxSupply: Double,

    @SerializedName("ath")
    val ath: Double,

    @SerializedName("atl")
    val atl: Double,

    @SerializedName("high24h")
    val high24h: Double,

    @SerializedName("low24h")
    val low24h: Double,

    @SerializedName("isActive")
    val isActive: Int,

    @SerializedName("lastUpdated")
    val lastUpdated: String?,

    @SerializedName("dateAdded")
    val dateAdded: String?,

    @SerializedName("quotes")
    val quotes: List<ListDetails>,

    @SerializedName("isAudited")
    val isAudited: Boolean
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        TODO("quotes"),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(symbol)
        parcel.writeString(slug)
        parcel.writeInt(cmcRank)
        parcel.writeInt(marketPairCount)
        parcel.writeDouble(circulatingSupply)
        parcel.writeDouble(totalSupply)
        parcel.writeDouble(maxSupply)
        parcel.writeDouble(ath)
        parcel.writeDouble(atl)
        parcel.writeDouble(high24h)
        parcel.writeDouble(low24h)
        parcel.writeInt(isActive)
        parcel.writeString(lastUpdated)
        parcel.writeString(dateAdded)
        parcel.writeByte(if (isAudited) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataItem> {
        override fun createFromParcel(parcel: Parcel): DataItem {
            return DataItem(parcel)
        }

        override fun newArray(size: Int): Array<DataItem?> {
            return arrayOfNulls(size)
        }
    }
}
