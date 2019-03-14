package com.nitzanwerber.picflow.module;

import androidx.lifecycle.ViewModel;
import com.nitzanwerber.picflow.repository.LocationRepository;
import com.nitzanwerber.picflow.repository.PhotoRepository;
import com.nitzanwerber.picflow.viewModel.PhotoFlowViewModel;
import com.nitzanwerber.picflow.viewModel.ViewModelFactory;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import kotlin.jvm.JvmSuppressWildcards;

import javax.inject.Provider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

//*This class enables the reuse of the same viewModel in different fragments
@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @JvmSuppressWildcards
    @Provides
    public ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(PhotoFlowViewModel.class)
    public ViewModel viewModel1(PhotoRepository photoRepository, LocationRepository locationRepository) {
        return new PhotoFlowViewModel(photoRepository, locationRepository);
    }

}