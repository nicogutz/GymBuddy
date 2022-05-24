package be.kuleuven.gymbuddy.data.local.access;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/***
 * This class is in charge of converting the values from the databases to variables that SQLite
 * can understand, Room disallows object references between entity classes. The actual
 * serialization is handled by Gson. An awesome library from Google.
 */
public class Converters {

    @TypeConverter
    public static ArrayList<String[]> mapFromString(String value) {
        Type listType = new TypeToken<ArrayList<String[]>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromMap(ArrayList<String[]> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
