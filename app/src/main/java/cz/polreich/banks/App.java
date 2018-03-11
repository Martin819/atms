package cz.polreich.banks;

import android.app.Application;
import android.arch.persistence.room.Room;

public class App extends Application {

    public static App INSTANCE;
    private static final String DATABASE_NAME = "BanksDB";
    private static final String PREFERENCES = "Banks.preferences";
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();

        INSTANCE = this;
    }

    public static App getApp() {
        return INSTANCE;
    }

    public AppDatabase getDatabase() {
        return database;
    }

}
