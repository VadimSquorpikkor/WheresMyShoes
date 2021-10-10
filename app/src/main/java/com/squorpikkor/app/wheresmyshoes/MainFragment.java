package com.squorpikkor.app.wheresmyshoes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private AdapterShoesBox adapter;
    MainViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        RecyclerView foundRecyclerView = view.findViewById(R.id.recycler_main);
        adapter = new AdapterShoesBox();
//        foundRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        foundRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        foundRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::openDeviceInfo);
        mViewModel.getDeviceList().observe(getViewLifecycleOwner(), list -> adapter.setList(list));

        return view;
    }

    public void openDeviceInfo(int position) {
        mViewModel.setPosition(position);
        // Create new fragment and transaction
        Fragment newFragment = InfoFragment.newInstance();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }
}
