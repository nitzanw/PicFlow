package com.nitzanwerber.picflow.Module;

import androidx.lifecycle.ViewModel;
import com.nitzanwerber.picflow.PhotoFlowViewModel;
import com.nitzanwerber.picflow.ViewModelFactory;
import com.nitzanwerber.picflow.dataModel.PhotoRepository;
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
    public ViewModel viewModel1(PhotoRepository photoRepository) {
        return new PhotoFlowViewModel(photoRepository);
    }
}