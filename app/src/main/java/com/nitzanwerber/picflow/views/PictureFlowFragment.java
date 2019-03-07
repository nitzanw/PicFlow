package com.nitzanwerber.picflow.views;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nitzanwerber.picflow.*;
import com.nitzanwerber.picflow.dataModel.pojo.FlickerPrePhoto;
import com.nitzanwerber.picflow.dataModel.pojo.FlickrPhotosSearchResponse;
import com.nitzanwerber.picflow.viewModel.PhotoFlowViewModel;
import com.nitzanwerber.picflow.viewModel.ViewModelFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import java.util.ArrayList;


public class PictureFlowFragment extends Fragment {

    private PhotoFlowViewModel viewModel;
    private RecyclerView.LayoutManager viewManager;
    private PhotoAdapter viewAdapter;
    @Inject public ViewModelFactory viewModelFactory;
    @Inject public Picasso picasso;

    @Override
    public void onAttach(Context context) {
        ((MyApp)context.getApplicationContext()).getAppComponent().inject(this);
        super.onAttach(context);

    }

    public static PictureFlowFragment getInstance() {
        PictureFlowFragment fragment = new PictureFlowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // create this viewModel in such a way that other frags can access it
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(PhotoFlowViewModel.class);
        viewModel.init("", "");
        subscribeUi(viewAdapter);


    }

    private void subscribeUi(final PhotoAdapter viewAdapter) {
        viewModel.getPhotoResponse().observe(this, new Observer<FlickrPhotosSearchResponse>() {
            @Override
            public void onChanged(FlickrPhotosSearchResponse array) {
                if (array != null) {
                    Log.d("!!!!!!!!!", "data has arrived!");
                    viewAdapter.updateData(array);
                }

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewManager = new LinearLayoutManager(view.getContext());
        viewAdapter = new PhotoAdapter(new ArrayList<FlickerPrePhoto>(),picasso);
        RecyclerView recycleListView = view.findViewById(R.id.location_list_view);
        recycleListView.setHasFixedSize(true);
        recycleListView.setLayoutManager(viewManager);
        recycleListView.setAdapter(viewAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pic_flow, container, false);
    }
}
