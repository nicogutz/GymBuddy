package be.kuleuven.gymbuddy;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import be.kuleuven.gymbuddy.data.local.AppDatabase;
import be.kuleuven.gymbuddy.data.local.access.PublicExerciseDAO;
import be.kuleuven.gymbuddy.data.local.entities.PublicExercise;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private PublicExerciseDAO exerciseDAO;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        exerciseDAO = db.getPublicExerciseDAO();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        PublicExercise publicExercise = TestUtil.createUser(3);
        publicExercise.setName("george");
        exerciseDAO.insert(publicExercise);
        List<User> byName = exerciseDAO.findUsersByName("george");
        assertThat(byName.get(0), equalTo(publicExercise));
    }
}
