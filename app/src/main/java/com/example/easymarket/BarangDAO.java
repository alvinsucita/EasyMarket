package com.example.easymarket;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BarangDAO {

    @Query("SELECT * FROM BARANG")
    List<_BarangBF> getAllBarang();

    @Insert
    void insertbarang(_BarangBF barang);

    @Update
    void update(_BarangBF barang);

    @Delete
    void delete(_BarangBF barang);

    @Query("DELETE FROM BARANG")
    void deleteAll();

}