package rs.edu.raf.rma.homework3.repository.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import rs.edu.raf.rma.homework3.repository.db.dao.MovieDao;

public abstract class MovieDatabase extends RoomDatabase {

    private static volatile MovieDatabase DATABASE;

    private static final String DATABASE_NAME = "movie.db";

    public abstract MovieDao getMovieDao();

    public static MovieDatabase getDb(Context context) {
        if (DATABASE == null) {
            synchronized (MovieDatabase.class) {
                if (DATABASE == null) {
                    DATABASE = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return DATABASE;
    }
}
