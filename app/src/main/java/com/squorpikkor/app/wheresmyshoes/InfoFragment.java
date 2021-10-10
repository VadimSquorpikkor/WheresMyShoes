package com.squorpikkor.app.wheresmyshoes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class InfoFragment extends Fragment {

    MainViewModel mViewModel;

    public static Fragment newInstance() {
        return new InfoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        int position = mViewModel.getPosition();
        int resId = mViewModel.getDeviceList().getValue().get(position).getImageResId();
        int num = mViewModel.getDeviceList().getValue().get(position).getBoxNumber();
        ImageView img = view.findViewById(R.id.img);
        TextView numText = view.findViewById(R.id.num);
        numText.setText("Номер коробки: "+num);
        img.setImageResource(resId);
        return view;
    }
}
