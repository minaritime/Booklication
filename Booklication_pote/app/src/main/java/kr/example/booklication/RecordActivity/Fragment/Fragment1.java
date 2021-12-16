package kr.example.booklication.RecordActivity.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import kr.example.booklication.BookInfoActivity.BookInfoMain;
import kr.example.booklication.Define;
import kr.example.booklication.R;
import kr.example.booklication.RecordActivity.Fragment1ListView.Fragment1ListView;

public class Fragment1 extends Fragment {

    Fragment aFragment;
    AppCompatActivity aFragment1;
    Fragment1ListView cFragment1ListView;

    public Fragment1(AppCompatActivity appCompatActivity) {
        aFragment1 = appCompatActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        cFragment1ListView = new Fragment1ListView(aFragment, aFragment1, view);
        cFragment1ListView.setFragment1ListView();
        return view;
    }
}