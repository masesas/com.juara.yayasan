package com.juara.yayasan.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataAnakDAO {

    @Query("SELECT * FROM DataAnakEntity")
    List<DataAnakEntity> getAll();

    @Query("DELETE FROM DataAnakEntity WHERE ID=:id")
    void deleteById(int id);

    @Query("DELETE FROM DataAnakEntity")
    void deleteAll();

    @Insert
    void insert(DataAnakEntity... DataAnakEntity);

    @Insert
    void insertOne(DataAnakEntity DataAnakEntity);

    @Update
    void UpdateOne(DataAnakEntity DataAnakEntity);

    @Delete
    void delete(DataAnakEntity... DataAnakEntity);

    @Delete
    void deleteOne(DataAnakEntity DataAnakEntity);
}
