package com.example.library
import android.os.Parcel
import android.os.Parcelable

class Book(title:String, cover:String, description:String, favourite:Int):Parcelable {
    var title:String = ""
    var cover:String = ""
    var description:String = ""
    var favourite:Int = 0

    constructor(parcel: Parcel) : this(
        title = parcel.readString(),
        cover = parcel.readString(),
        description = parcel.readString(),
        favourite = parcel.readInt() //Esta variable deberia de ser Boolean pero no sabemos como parcearla
    )

    init {
        this.title = title
        this.cover = cover
        this.description = description
        this.favourite = favourite
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(cover)
        parcel.writeString(description)
        parcel.writeInt(favourite)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        fun getEmptyLibro(): Book {
            return Book(title = " ",cover = " " ,description = " ",favourite = 0)
        }
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)

        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}