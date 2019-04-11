package com.example.library.RoomDataBase

import androidx.room.*

/**
 * Data Access Object for the users table.
 */
@Dao
interface BookDao {

    /**
     * Get a book by id.
     * @return the book from the table with a specific id.
     */
    @Query("SELECT * FROM book WHERE bookid = :bookid")
    fun getBookById(bookid: String): Book

    /**
     * Insert a  in the database. If the user already exists, replace it.
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Book)
}