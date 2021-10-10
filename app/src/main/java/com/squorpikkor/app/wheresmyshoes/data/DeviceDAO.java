package com.squorpikkor.app.wheresmyshoes.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

import static com.squorpikkor.app.wheresmyshoes.data.Database.DB_NAME;

@Dao
public interface DeviceDAO {
    @Query("SELECT * FROM shoes")
    LiveData<List<ShoesBox>> getAllDevices();

    @Insert
    void insertAll(ShoesBox... shoesBoxes);
}
