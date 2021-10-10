package com.squorpikkor.app.wheresmyshoes;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.squorpikkor.app.wheresmyshoes.data.Database;
import com.squorpikkor.app.wheresmyshoes.data.ShoesBox;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<ShoesBox>> deviceList;
    private static Database database;
    public static final String TAG = "tag";


    public MainViewModel(@NonNull Application application) {
        super(application);
        database = Database.getInstance(getApplication());
        deviceList = database.deviceDAO().getAllDevices();
    }

    public LiveData<List<ShoesBox>> getDeviceList() {
        return deviceList;
    }

}
