package com.nitzanwerber.picflow;

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
import com.nitzanwerber.picflow.pojo.FlickerPhotos;
import com.nitzanwerber.picflow.pojo.FlickerPrePhoto;
import com.nitzanwerber.picflow.pojo.FlickrPhotosSearchResponse;

import javax.inject.Inject;
import java.util.ArrayList;


public class PictureFlowFragment extends Fragment {

    private PhotoFlowViewModel viewModel;
    private RecyclerView.LayoutManager viewManager;
    private PictureAdapter viewAdapter;
    @Inject ViewModelFactory viewModelFactory;

    @Override
    public void onAttach(Context context) {
        ((MyApp)context.getApplicationContext()).getAppComponent().inject(this);
        super.onAttach(context);
//        Log.d("myapp", Log.getStackTraceString(new Exception()));

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
        viewModel = ViewModelProviders.of(getActivity(),viewModelFactory).get(PhotoFlowViewModel.class);
        viewModel.init("", "");
        subscribeUi(viewAdapter);


    }

    private void subscribeUi(final PictureAdapter viewAdapter) {
        viewModel.getPhotoResponse().observe(getActivity(), new Observer<FlickrPhotosSearchResponse>() {
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
        viewAdapter = new PictureAdapter(new ArrayList<FlickerPrePhoto>());
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
