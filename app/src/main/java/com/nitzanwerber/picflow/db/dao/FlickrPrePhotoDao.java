package com.nitzanwerber.picflow.db.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.nitzanwerber.picflow.dataModel.dto.FlickrPrePhoto;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface FlickrPrePhotoDao {
    @Insert(onConflict = IGNORE)
    void save(FlickrPrePhoto prePhoto);
    //Load all photos
    @Query("SELECT * FROM flickrprephoto ORDER BY insertDate DESC")
    LiveData<List<FlickrPrePhoto>> loadAll();
}
