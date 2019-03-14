package com.nitzanwerber.picflow.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.nitzanwerber.picflow.dataModel.dto.FlickrPrePhoto;
import com.nitzanwerber.picflow.db.dao.FlickrPrePhotoDao;

@Database(entities = {FlickrPrePhoto.class}, version = 1)
public abstract class FlickrPrePhotoDatabase extends RoomDatabase {
    public abstract FlickrPrePhotoDao flickrPrePhotoDao();

    // --- SINGLETON ---
    private static volatile FlickrPrePhotoDatabase instance;

}
