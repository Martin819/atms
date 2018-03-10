package cz.polreich.banks.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import cz.polreich.banks.Converters;
import cz.polreich.banks.model.airBank.Branch;

@Dao
public interface BranchDao {

    Converters converters = new Converters();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBranches(List<Branch> branches);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBranchesArray(Branch... branches);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertBranch(Branch branch);

    @Update
    public void updateBranches(List<Branch> branches);

    @Update
    public void updateBranchesArray(Branch... branches);

    @Update
    public void updateBranch(Branch branch);

    @Delete
    public void deleteBranches(List<Branch> branches);

    @Delete
    public void deleteBranchesArray(Branch... branches);

    @Delete
    public void deleteBranch(Branch branch);

    @Query("SELECT * FROM branches")
    public List<Branch> getAllBranches();

    @Query("SELECT * FROM branches WHERE id = :id")
    public Branch getBranchById(String id);
}
