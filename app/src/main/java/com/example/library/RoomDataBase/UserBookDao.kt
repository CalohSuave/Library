package com.example.library.RoomDataBase

import androidx.room.*

/**
 * Data Access Object for the users table.
 */
@Dao
interface UserBookDao {

    /**
     * Get a book by id.
     * @return the book from the table with a specific id.
     */
    @Query("SELECT * FROM userbook WHERE bookid = :bookid AND userid = :userid")
    fun getFavoriteBookByBookIdAndUserId(bookid: String, userid: String): Book

    /**
     * Insert a favorite book in the database. If the user already exists, replace it.
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteBook(userbook: UserBook)

    /**
     * Remove book of favorite.
     */
    @Query("DELETE FROM userbook WHERE bookid = :bookid AND userid = :userid")
    fun removeFavorite(bookid: String, userid: String)
}