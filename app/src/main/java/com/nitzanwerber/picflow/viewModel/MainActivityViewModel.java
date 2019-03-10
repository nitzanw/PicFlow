package com.nitzanwerber.picflow.viewModel;

import androidx.lifecycle.ViewModel;
import com.nitzanwerber.picflow.dataModel.LocationRepository;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {

    @Inject LocationRepository locationRepository;


}
