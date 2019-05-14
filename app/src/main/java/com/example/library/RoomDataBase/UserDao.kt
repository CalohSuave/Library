package com.example.library.RoomDataBase

import androidx.room.*

/**
 * Data Access Object for the users table.
 */
@Dao
interface UserDao {

    /**
     * Get a user by username and password.
     * @return the user from the table with a specific id.
     */
    @Query("SELECT * FROM user WHERE email = :email AND password = :pswd")
    fun getUserByEmailAndPassword(email: String, pswd: String): User

    @Query("SELECT COUNT(*) FROM user WHERE email = :email AND password = :pswd")
    fun isExistUser(email: String, pswd: String): Int

    /**
     * Insert a user in the database. If the user already exists, replace it.
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
}

