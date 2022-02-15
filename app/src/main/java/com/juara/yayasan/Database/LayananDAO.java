package com.juara.yayasan.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LayananDAO {

    @Query("SELECT * FROM LayananEntity")
    List<LayananEntity> getAll();

    @Query("DELETE FROM LayananEntity WHERE ID=:id")
    void deleteById(int id);

    @Query("DELETE FROM LayananEntity")
    void deleteAll();

    @Insert
    void insert(LayananEntity... LayananEntity);

    @Insert
    void insertOne(LayananEntity LayananEntity);

    @Update
    void UpdateOne(LayananEntity LayananEntity);

    @Delete
    void delete(LayananEntity... LayananEntity);

    @Delete
    void deleteOne(LayananEntity LayananEntity);

}
