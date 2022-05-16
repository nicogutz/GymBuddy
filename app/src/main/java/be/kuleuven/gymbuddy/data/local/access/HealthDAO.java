package be.kuleuven.gymbuddy.data.local.access;

import androidx.room.*;

import java.util.List;

import be.kuleuven.gymbuddy.data.model.Health;

@Dao
public interface HealthDAO {
    @Query("SELECT * FROM Health")
    List<Health> getAll();

    @Insert
    void insertAll(Health... users);

    @Delete
    void delete(Health user);
}
