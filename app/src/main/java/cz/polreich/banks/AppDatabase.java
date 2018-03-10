package cz.polreich.banks;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import cz.polreich.banks.dao.BranchDao;
import cz.polreich.banks.model.airBank.*;

@Database(version = 1, entities = {Address.class, ATM.class, Branch.class, Location.class, OpeningHours.class, OpeningHoursDay.class})
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();

    abstract public BranchDao branchDao();
}
