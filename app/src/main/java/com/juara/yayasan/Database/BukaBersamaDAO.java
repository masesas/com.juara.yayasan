package com.juara.yayasan.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BukaBersamaDAO {
    @Query("SELECT * FROM BukaBersamaEntity")
    List<BukaBersamaEntity> getAll();

    @Query("DELETE FROM BukaBersamaEntity WHERE ID=:id")
    void deleteById(int id);

    @Query("DELETE FROM BukaBersamaEntity")
    void deleteAll();

    @Insert
    void insert(BukaBersamaEntity... BukaBersamaEntity);

    @Insert
    void insertOne(BukaBersamaEntity BukaBersamaEntity);

    @Update
    void UpdateOne(BukaBersamaEntity BukaBersamaEntity);

    @Delete
    void delete(BukaBersamaEntity... BukaBersamaEntity);

    @Delete
    void deleteOne(BukaBersamaEntity BukaBersamaEntity);
}
