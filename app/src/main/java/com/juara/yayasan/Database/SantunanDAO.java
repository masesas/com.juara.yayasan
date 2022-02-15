package com.juara.yayasan.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SantunanDAO {
    @Query("SELECT * FROM SantunanEntity")
    List<SantunanEntity> getAll();

    @Query("DELETE FROM SantunanEntity WHERE ID=:id")
    void deleteById(int id);

    @Query("DELETE FROM SantunanEntity")
    void deleteAll();

    @Insert
    void insert(SantunanEntity... SantunanEntity);

    @Insert
    void insertOne(SantunanEntity SantunanEntity);

    @Update
    void UpdateOne(SantunanEntity SantunanEntity);

    @Delete
    void delete(SantunanEntity... SantunanEntity);

    @Delete
    void deleteOne(SantunanEntity SantunanEntity);
}
