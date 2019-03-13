package com.nitzanwerber.picflow.views;

import android.content.Context;
import android.location.Location;
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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nitzanwerber.picflow.*;
import com.nitzanwerber.picflow.dataModel.dto.FlickerPrePhoto;
import com.nitzanwerber.picflow.dataModel.dto.FlickrPhotosSearchResponse;
import com.nitzanwerber.picflow.viewModel.PhotoFlowViewModel;
import com.nitzanwerber.picflow.viewModel.ViewModelFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import java.util.ArrayList;


public class PictureFlowFragment extends Fragment {

    private PhotoFlowViewModel viewModel;
    private PhotoAdapter viewAdapter;
    private RecyclerView recycleView;
    @Inject
    public ViewModelFactory viewModelFactory;
    @Inject
    public Picasso picasso;

    @Override
    public void onAttach(Context context) {
        ((MyApp) context.getApplicationContext()).getAppComponent().inject(this);
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
        viewModel = ViewModelProviders.of(this.getActivity(), viewModelFactory).get(PhotoFlowViewModel.class);
        viewModel.init();
        subscribeUi(viewAdapter);


    }

    private void subscribeUi(final PhotoAdapter viewAdapter) {
        viewModel.getPhotoResponse().observe(this.getActivity(), new Observer<FlickrPhotosSearchResponse>() {
            @Override
            public void onChanged(FlickrPhotosSearchResponse response) {
                if (response != null && viewModel.requestingLocationUpdates()) {
                    viewAdapter.updateData(response);
                }
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager viewManager = new LinearLayoutManager(view.getContext());
        viewAdapter = new PhotoAdapter(new ArrayList<FlickerPrePhoto>(), picasso);
        if(savedInstanceState != null) {
            ArrayList<FlickerPrePhoto> items = savedInstanceState.getParcelableArrayList("viewAdapter");
            viewAdapter.setDataset(items); // Load saved data if any.
        }
        recycleView = view.findViewById(R.id.location_list_view);
        recycleView.setLayoutManager(viewManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setHasFixedSize(true);
        recycleView.setAdapter(viewAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pic_flow, container, false);
    }


    @Override
    public void onSaveInstanceState(Bundle state) {
        // Save activity state

        super.onSaveInstanceState(state);

        state.putParcelableArrayList("viewAdapter", viewAdapter.getDataSet());

    }
}