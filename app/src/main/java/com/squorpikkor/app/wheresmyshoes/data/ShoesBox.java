package com.squorpikkor.app.wheresmyshoes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.squorpikkor.app.wheresmyshoes.data.Database.DB_NAME;

@Entity(tableName = "shoes")
public
class ShoesBox {
    @PrimaryKey(autoGenerate = true)
    int id;
    int imageResId;
    int boxNumber;

    public ShoesBox(int imageResId, int boxNumber) {
        this.imageResId = imageResId;
        this.boxNumber = boxNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }
}
