package cz.polreich.banks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cz.polreich.banks.Converters;
import cz.polreich.banks.model.airBank.AirBankATM;

@Dao
public interface ATMDao {

    Converters converters = new Converters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertATMs(List<AirBankATM> atms);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertATMsArray(AirBankATM... atms);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertATM(AirBankATM atm);

    @Update
    public void updateATMs(List<AirBankATM> atms);

    @Update
    public void updateATMsArray(AirBankATM... atms);

    @Update
    public void updateATM(AirBankATM atm);

    @Delete
    public void deleteATMs(List<AirBankATM> atms);

    @Delete
    public void deleteATMsArray(AirBankATM... atms);

    @Delete
    public void deleteATM(AirBankATM atm);

    @Query("SELECT * FROM AirBankATM")
    public List<AirBankATM> getAllATMs();

    @Query("SELECT * FROM AirBankATM WHERE id = :id")
    public AirBankATM getATMById(String id);

}
