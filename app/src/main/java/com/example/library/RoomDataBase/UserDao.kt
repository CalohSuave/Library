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
    @Query("SELECT * FROM Users WHERE userName = :username AND password = :pswd")
    fun getUserByNameAndPassword(username: String, pswd: String): User

    /**
     * Insert a user in the database. If the user already exists, replace it.
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): /*Completable*/ User

    /**
     * Updated a user in the database.
     * @param user the user to be updated.
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User): User

    /**
     * Delete all users.
     */
    @Query("DELETE FROM Users")
    fun deleteAllUsers()
}