package cz.polreich.banks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cz.polreich.banks.Converters;
import cz.polreich.banks.model.UniATM;
import cz.polreich.banks.model.airBank.AirBankATM;

@Dao
public interface ATMDao {

    Converters converters = new Converters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertATMs(List<UniATM> atms);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertATMsArray(UniATM... atms);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertATM(UniATM atm);

    @Update
    public void updateATMs(List<UniATM> atms);

    @Update
    public void updateATMsArray(UniATM... atms);

    @Update
    public void updateATM(UniATM atm);

    @Delete
    public void deleteATMs(List<UniATM> atms);

    @Delete
    public void deleteATMsArray(UniATM... atms);

    @Delete
    public void deleteATM(UniATM atm);

    @Query("SELECT * FROM UniATM")
    public List<UniATM> getAllATMs();

    @Query("SELECT * FROM UniATM ORDER BY distance ASC")
    public List<UniATM> getAllATMsByDistance();

    @Query("SELECT * FROM UniATM WHERE id = :id")
    public UniATM getATMById(String id);

    @Query("SELECT * FROM UniBranch WHERE name = :name")
    public UniATM getATMSByName(String name);

}
