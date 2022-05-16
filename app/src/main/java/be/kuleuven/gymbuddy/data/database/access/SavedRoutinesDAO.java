package be.kuleuven.gymbuddy.data.database.access;

import androidx.room.*;

import java.util.List;

import be.kuleuven.gymbuddy.data.database.entities.User;

@Dao
public interface SavedRoutinesDAO {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
