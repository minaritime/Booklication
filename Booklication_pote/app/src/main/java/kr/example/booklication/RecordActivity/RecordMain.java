package kr.example.booklication.RecordActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.R;
import kr.example.booklication.RecordActivity.Fragment.Fragment1;
import kr.example.booklication.RecordActivity.Fragment.Fragment2;
import kr.example.booklication.RecordActivity.Fragment.Fragment3;
import kr.example.booklication.RecordActivity.ViewPager.ViewPagerAdapter;

public class RecordMain extends Fragment {

    AppCompatActivity aRecordMain;

    NestedScrollView scrollView;
    TabLayout tabLayout;
    ViewPager viewPager;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    ViewPagerAdapter viewPagerAdapter;

    FileIOStreamRead cFileIOStreamRead;

    /**
     * -서재
     * 하단메뉴에서 서재버튼을 누를 시 보여지는 화면이며 저장한 책들을 종류별로 볼 수 있습니다.
     *
     * -종류별 리스트
     * 리스트를 쉽게 구분하기 위해 탭레이아웃을 이용해 종류별로 리스트를 띄워 쉽게 볼 수 있습니다.
     * 만약 해당 종류에 저장된 책이 없다면 저장된 책이 없는 것을 사용자에게 알려줍니다.
     *
     * -읽은 책
     * 읽은 책에 저장된 책들을 리스트로 띄워 표지, 책의 제목, 책의 저자, 시작일, 종료일을 한눈에 볼 수 있습니다.
     *
     * -읽고 있는 책
     * 읽고 있는 책에 저장된 책들을 리스트로 띄워 표지, 책의 제목, 책의 저자, 시작일, 총 페이지, 읽은 페이지를 볼 수 있으며
     * 읽은 만큼을 퍼센트와 프로그레스바로 표현해 얼만큼 읽었는지를 한번에 알 수 있습니다.
     *
     * -읽고 싶은 책
     * 읽고 싶은 책에 저장된 책들을 리스트로 띄워 표지, 책의 제목, 기대평을 볼 수 있습니다.
     *
     * -삭제
     * 리스트 아이템을 클릭하게 되면 다이얼로그가 팝업되며 삭제할것인지 사용자에게 묻습니다.
     * 확인 버튼을 누르면 싱글톤을 덮어써서 삭제 시킨후 파일에 다시 저장하고 리스트를 갱신합니다.
     *
     * -툴바
     * 최대한 많은 정보를 사용자가 볼 수 있게 아래로 스크롤을 하게 되면 툴바가 위로 숨겨집니다.
     *
     * @param appCompatActivity
     */
    public RecordMain(AppCompatActivity appCompatActivity){
        aRecordMain = appCompatActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.record_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragment1 = new Fragment1(aRecordMain);
        fragment2 = new Fragment2(aRecordMain);
        fragment3 = new Fragment3(aRecordMain);
        viewPagerAdapter = new ViewPagerAdapter(aRecordMain.getSupportFragmentManager(), 0);
        cFileIOStreamRead = new FileIOStreamRead(aRecordMain);

        scrollView = (NestedScrollView) view.findViewById (R.id.nest_scrollview);
        scrollView.setFillViewport (true);

        // ViewPager 설정
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3); //페이지 유지 개수

        // tabLayout에 ViewPager 연결
        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // ViewPagerAdapter를 이용하여 Fragment 연결
        viewPagerAdapter.addFragment(fragment1, "First");
        viewPagerAdapter.addFragment(fragment2, "Second");
        viewPagerAdapter.addFragment(fragment3, "Third");
        viewPager.setAdapter(viewPagerAdapter);

        // tabLayout에 아이콘 설정 부분
        tabLayout.getTabAt(0).setText("읽은 책");
        tabLayout.getTabAt(1).setText("읽고 있는 책");
        tabLayout.getTabAt(2).setText("읽고 싶은 책");
    }
}
