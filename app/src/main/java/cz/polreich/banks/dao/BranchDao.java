package cz.polreich.banks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cz.polreich.banks.Converters;
import cz.polreich.banks.model.airBank.AirBankBranch;

@Dao
public interface BranchDao {

    Converters converters = new Converters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBranches(List<AirBankBranch> branches);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBranchesArray(AirBankBranch... branches);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBranch(AirBankBranch branch);

    @Update
    public void updateBranches(List<AirBankBranch> branches);

    @Update
    public void updateBranchesArray(AirBankBranch... branches);

    @Update
    public void updateBranch(AirBankBranch branch);

    @Delete
    public void deleteBranches(List<AirBankBranch> branches);

    @Delete
    public void deleteBranchesArray(AirBankBranch... branches);

    @Delete
    public void deleteBranch(AirBankBranch branch);

    @Query("SELECT * FROM AirBankBranch")
    public List<AirBankBranch> getAllBranches();

    @Query("SELECT * FROM AirBankBranch WHERE id = :id")
    public AirBankBranch getBranchById(String id);
}
