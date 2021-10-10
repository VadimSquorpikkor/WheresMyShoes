package com.squorpikkor.app.wheresmyshoes.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.concurrent.Executors;


@androidx.room.Database(entities = {ShoesBox.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static Database movieDatabase;
    public static final String DB_NAME = "shoes.db";
//    public static final String DEVICES = "shoes";
    public static final Object LOCK = new Object();

    public static Database getInstance(Context context) {
        synchronized (LOCK) {
            if (movieDatabase == null) {
                movieDatabase = Room.databaseBuilder(context, Database.class, DB_NAME)
                        .addCallback(new Callback() {
                                         @Override
                                         public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                             super.onCreate(db);
                                             Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                                 @Override
                                                 public void run() {
                                                     Log.e("TAG", "run: CALLBACK!!!");
                                                     getInstance(context).deviceDAO().insertAll(DataEntity.getAll());
                                                 }
                                             });
                                         }
                                     }
                        )
                        .fallbackToDestructiveMigration()//Это добавлено для того, чтобы при изменении версии на новую, старые данные вместе с таблицами автоматом удалялись (иначе нужно вручную удалять) и новые таблицы создавались
                        .build();
            }
            return movieDatabase;
        }

    }

    public abstract DeviceDAO deviceDAO();

}