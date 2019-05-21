package com.example.library
import android.os.Parcel
import android.os.Parcelable

/**
 * Book class
 * @param title Title of the book
 * @param cover URL of the cover's image
 * @param description Description of the book
 * @param favourite Book is in a favourite list
 */
class Book(title:String, cover:String, description:String, favourite:Int):Parcelable {
    /** Book Title */
    var title:String = ""

    /** Book Cover */
    var cover:String = ""

    /** Book description */
    var description:String = ""

    /** If the book is in the users favourite list the value si 1 else 0 */
    var favourite:Int = 0

    /**
     * Makes the variables parsable
     * @param parcel Parcel: To take an object and pass it through classes
     */
    constructor(parcel: Parcel) : this(
        title = parcel.readString(),
        cover = parcel.readString(),
        description = parcel.readString(),
        favourite = parcel.readInt() //Esta variable deberia de ser Boolean pero no sabemos como parcearla
    )

    /**
     *Initialize the Book class
     */
    init {
        this.title = title
        this.cover = cover
        this.description = description
        this.favourite = favourite
    }

    /**
     * Takes the object and makes its variables parsable
     * @param parcel The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written
     */
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
        /**
         * Creates an empty book
         * @param title is {@value #""}
         * @param cover is {@value #""}
         * @param description is {@value #""}
         * @param favourite is {@value #0}
         * */
        fun getEmptyLibro(): Book {
            return Book(title = " ",cover = " " ,description = " ",favourite = 0)
        }

        /**
         * Creates a book from the parcel
         * @param parcel is the parcel used to create the book
         * @return the created Book
         * */
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        /**
         * Creates an array of nulls from the size entered
         * @param size the size of the array
         * @return Array of nulls with the specified size
         * */
        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}