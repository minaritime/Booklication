package kr.example.booklication;

import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kr.example.booklication.CalendarActivity.CalendarMain;
import kr.example.booklication.FileIOStream.FileIOStreamCheckDir;
import kr.example.booklication.FileIOStream.FileIOStreamCheckFile;
import kr.example.booklication.RecordActivity.RecordMain;
import kr.example.booklication.Search_ListView.SearchMain;

public class Setmain {

    AppCompatActivity aSetmain;
    private BottomNavigationView mBottomNV;
    Menu menu;
    FileIOStreamCheckFile cFileIOStreamCheckFile;
    FileIOStreamCheckDir cFileIOStreamCheckDir;

    public Setmain(AppCompatActivity appCompatActivity) {
        aSetmain = appCompatActivity;
        cFileIOStreamCheckDir = new FileIOStreamCheckDir(aSetmain);
        cFileIOStreamCheckFile = new FileIOStreamCheckFile(aSetmain);
    }

    /**
     * 하단 메뉴 버튼 코드
     * - 클래스 : Setmain
     * - 시작라인 : 110
     * - 마지막라인 : 145
     *
     * 검색 리스트 코드
     * - 클래스 : SearchMain
     * - 시작라인 : 164
     * - 마지막라인 : 201
     *
     * 책 저장 버튼 코드
     * - 클래스 : BookInfoMain
     * - 시작라인 : 113
     * - 마지막라인 : 157
     *
     * 바텀다이얼로그 저장 코드
     * - 클래스 : CustomBottomSheetDialog
     * - 시작라인 : 235
     * - 마지막라인 : 349
     *
     * 기록 리스트설정 코드
     * - 클래스 : CalendarMain
     * - 시작라인 : 102
     * - 마지막라인 : 113
     * +
     * - 시작라인 : 141
     * - 마지막라인 : 180
     *
     * 기록 리스트추가 코드
     * - 클래스 : CalendatListViewSet
     * - 시작라인 : 25
     * - 마지막라인 : 50
     *
     * 서재 리스트추가 코드
     * - 클래스 : Fragment1ListView
     * - 시작라인 : 33
     * - 마지막라인 : 80
     *
     *
     */

    // 하단 메뉴 버튼 설정, 프레그먼트 설정
    public void Setting() {

        // 파일 저장 위치 기본 경로로 설정
        cFileIOStreamCheckDir.checkDir();

        cFileIOStreamCheckFile.checkFile("startday_1","");      // 읽은 책을 읽기 시작한 날 모음 파일
        cFileIOStreamCheckFile.checkFile("startday_2","");      // 읽고 있는 책을 읽기 시작한 날 모음 파일

        cFileIOStreamCheckFile.checkFile("lastday","");         // 읽은 책을 다 읽은 날 모음 파일

        cFileIOStreamCheckFile.checkFile("image_url_1", "");    // 읽은 책 이미지 url 모음 파일
        cFileIOStreamCheckFile.checkFile("image_url_2", "");    // 읽고 있는 책 이미지 url 모음 파일
        cFileIOStreamCheckFile.checkFile("image_url_3", "");    // 읽고 싶은 책 이미지 url 모음 파일

        cFileIOStreamCheckFile.checkFile("book_name_1","");     // 읽은 책 제목 모음 파일
        cFileIOStreamCheckFile.checkFile("book_name_2","");     // 읽고 있는 책 제목 모음 파일
        cFileIOStreamCheckFile.checkFile("book_name_3","");     // 읽고 싶은 책 제목 모음 파일

        cFileIOStreamCheckFile.checkFile("book_aut_1","");      // 읽은 책 저자 모음 파일
        cFileIOStreamCheckFile.checkFile("book_aut_2","");      // 읽고 있는 책 저자 모음 파일
        cFileIOStreamCheckFile.checkFile("book_aut_3","");      // 읽고 싶은 책 저자 모음 파일

        cFileIOStreamCheckFile.checkFile("book_total","");      // 읽고 있는 책 총 페이지수 모음 파일
        cFileIOStreamCheckFile.checkFile("book_reading","");    // 읽고 있는 책 읽은 페이지 수 모음 파일
        cFileIOStreamCheckFile.checkFile("book_exp","");        // 읽고 싶은 책 기대평 모음 파일

        cFileIOStreamCheckFile.checkFile("today_1","");         // 읽은 책을 저장한 날 모음 파일
        cFileIOStreamCheckFile.checkFile("today_2","");         // 읽고 있는 책을 저장한 날 모음 파일
        cFileIOStreamCheckFile.checkFile("today_3","");         // 읽고 싶은 책을 저장한 날 모음 파일

        cFileIOStreamCheckFile.checkFile("percentage", "");     // 읽고 있는 책 퍼센티지 모음 파일

        mBottomNV = aSetmain.findViewById(R.id.nav_view);
        menu = mBottomNV.getMenu();
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { // 바텀내비게이션 아이템 클릭 이벤트
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager fragmentManager = aSetmain.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    // 서재 버튼을 클릭 했을때
                    case R.id.item_1:
                        menuItem.setIcon(R.drawable.bookshelf_down);
                        menu.findItem(R.id.item_2).setIcon(R.drawable.home_up);
                        menu.findItem(R.id.item_3).setIcon(R.drawable.docu_up);
                        // 프레그먼트 변경
                        fragmentTransaction.replace(R.id.content_layout, new RecordMain(aSetmain)).commitAllowingStateLoss();
                        break;
                    // 홈 버튼을 클릭 했을때
                    case R.id.item_2:
                        menuItem.setIcon(R.drawable.home_down);
                        menu.findItem(R.id.item_1).setIcon(R.drawable.bookshelf_up);
                        menu.findItem(R.id.item_3).setIcon(R.drawable.docu_up);
                        // 프레그먼트 변경
                        fragmentTransaction.replace(R.id.content_layout, new SearchMain(aSetmain)).commitAllowingStateLoss();
                        break;
                    // 기록 버튼을 클릭 했을때
                    case R.id.item_3:
                        menuItem.setIcon(R.drawable.docu_down);
                        menu.findItem(R.id.item_2).setIcon(R.drawable.home_up);
                        menu.findItem(R.id.item_1).setIcon(R.drawable.bookshelf_up);
                        // 프레그먼트 변경
                        fragmentTransaction.replace(R.id.content_layout, new CalendarMain(aSetmain)).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
        // 기본화면을 홈 화면으로 설정
        mBottomNV.setSelectedItemId(R.id.item_2);
        
    }
}