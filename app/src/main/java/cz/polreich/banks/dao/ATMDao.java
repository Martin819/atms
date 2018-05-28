package cz.polreich.banks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cz.polreich.banks.Converters;
import cz.polreich.banks.model.airBank.ATM;

@Dao
public interface ATMDao {

    Converters converters = new Converters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertATMs(List<ATM> atms);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertATMsArray(ATM... atms);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertATM(ATM atm);

    @Update
    public void updateATMs(List<ATM> atms);

    @Update
    public void updateATMsArray(ATM... atms);

    @Update
    public void updateATM(ATM atm);

    @Delete
    public void deleteATMs(List<ATM> atms);

    @Delete
    public void deleteATMsArray(ATM... atms);

    @Delete
    public void deleteATM(ATM atm);

    @Query("SELECT * FROM ATM")
    public List<ATM> getAllATMs();

    @Query("SELECT * FROM ATM WHERE id = :id")
    public ATM getATMById(String id);

}
