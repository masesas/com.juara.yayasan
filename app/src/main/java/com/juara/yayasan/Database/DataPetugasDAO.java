package com.juara.yayasan.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataPetugasDAO {
    @Query("SELECT * FROM DataPetugasEntity")
    List<DataPetugasEntity> getAll();

    @Query("DELETE FROM DataPetugasEntity WHERE ID=:id")
    void deleteById(int id);

    @Query("DELETE FROM DataPetugasEntity")
    void deleteAll();

    @Insert
    void insert(DataPetugasEntity... DataPetugasEntity);

    @Insert
    void insertOne(DataPetugasEntity DataPetugasEntity);

    @Update
    void UpdateOne(DataPetugasEntity DataPetugasEntity);

    @Delete
    void delete(DataPetugasEntity... DataPetugasEntity);

    @Delete
    void deleteOne(DataPetugasEntity DataPetugasEntity);
}
