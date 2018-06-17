package cz.polreich.banks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cz.polreich.banks.Converters;
import cz.polreich.banks.model.UniBranch;
import cz.polreich.banks.model.airBank.AirBankBranch;

@Dao
public interface BranchDao {

    Converters converters = new Converters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBranches(List<UniBranch> branches);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBranchesArray(UniBranch... branches);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBranch(UniBranch branch);

    @Update
    public void updateBranches(List<UniBranch> branches);

    @Update
    public void updateBranchesArray(UniBranch... branches);

    @Update
    public void updateBranch(UniBranch branch);

    @Delete
    public void deleteBranches(List<UniBranch> branches);

    @Delete
    public void deleteBranchesArray(UniBranch... branches);

    @Delete
    public void deleteBranch(UniBranch branch);

    @Query("SELECT * FROM UniBranch")
    public List<UniBranch> getAllBranches();

    @Query("SELECT * FROM UniBranch ORDER BY distance ASC")
    public List<UniBranch> getAllBranchesByDistance();

    @Query("SELECT * FROM UniBranch WHERE id = :id")
    public UniBranch getBranchById(String id);

    @Query("SELECT * FROM UniBranch WHERE name = :name")
    public UniBranch getBranchByName(String name);
}
