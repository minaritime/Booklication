package kr.example.booklication.RecordActivity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import kr.example.booklication.R;
import kr.example.booklication.RecordActivity.Fragment2ListView.Fragment2ListView;

public class Fragment2 extends Fragment {

    Fragment aFragment;
    AppCompatActivity aFragment2;
    Fragment2ListView cFragment2ListView;

    public Fragment2(AppCompatActivity appCompatActivity) {
        aFragment2 = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        cFragment2ListView = new Fragment2ListView(aFragment, aFragment2, view);
        cFragment2ListView.setFragment2ListView();
        return view;
    }
}