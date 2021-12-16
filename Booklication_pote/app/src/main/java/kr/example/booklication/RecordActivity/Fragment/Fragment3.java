package kr.example.booklication.RecordActivity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import kr.example.booklication.R;
import kr.example.booklication.RecordActivity.Fragment3ListView.Fragment3ListView;

public class Fragment3 extends Fragment {

    Fragment aFragment;
    AppCompatActivity aFragment3;

    Fragment3ListView cFragment3ListView;

    public Fragment3(AppCompatActivity appCompatActivity) {
        aFragment3 = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        cFragment3ListView = new Fragment3ListView(aFragment, aFragment3, view);
        cFragment3ListView.setFragment3ListView();
        return view;
    }
}