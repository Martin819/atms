package cz.polreich.banks;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import cz.polreich.banks.dao.ATMDao;
import cz.polreich.banks.dao.BranchDao;
import cz.polreich.banks.model.UniATM;
import cz.polreich.banks.model.UniAddress;
import cz.polreich.banks.model.UniBranch;
import cz.polreich.banks.model.UniLocation;
import cz.polreich.banks.model.UniOpeningHours;
import cz.polreich.banks.model.airBank.*;

@Database(version = 5, entities = {AirBankAddress.class, AirBankATM.class, AirBankBranch.class, AirBankLocation.class, AirBankOpeningHours.class, AirBankOpeningHoursDay.class, UniAddress.class, UniATM.class, UniBranch.class, UniLocation.class, UniOpeningHours.class})
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "BanksDB";
    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();

    abstract public BranchDao branchDao();
    abstract public ATMDao atmDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
