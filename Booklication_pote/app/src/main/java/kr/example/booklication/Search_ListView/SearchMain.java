package kr.example.booklication.Search_ListView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import kr.example.booklication.API.APISet;
import kr.example.booklication.BookInfoActivity.BookInfoMain;
import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamCheckDir;
import kr.example.booklication.FileIOStream.FileIOStreamCheckFile;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.FileIOStream.FileIOStreamWrite;
import kr.example.booklication.Loading.Loading_dialog;
import kr.example.booklication.R;

public class SearchMain extends Fragment {

    AppCompatActivity aSearchMain;
    FileIOStreamCheckDir cFileIOStreamCheckDir;
    FileIOStreamCheckFile cFileIOStreamCheckFile;
    FileIOStreamWrite cFileIOStreamWrite;
    FileIOStreamRead cFileIOStreamRead;
    ImageButton searchbtn;
    EditText editTextFilter;
    ListView listView;
    APISet cApiSet;
    SearchListViewAdapter adapter;

    /**
     * -검색
     * 앱 최초 실행시 보여지는 화면이며 원하는 책을 검색할 수 있습니다.
     *
     * -하단메뉴
     * 검색화면에서 기록, 서재 화면으로 이동할 수 있는 메뉴버튼입니다.
     *
     * -검색
     * edittext에 검색어를 입력하면 해당 검색어로 API에서 최대 10개의 책을 불러와 리스트를 초기화하고 추가합니다.
     * 책의 제목과 책의 저자, 표지를 한눈에 볼 수 있게 출력하였습니다.
     *
     * -로딩
     * 검색을 했을때 API를 불러오는 시간이 약간 걸리기때문에 간단한 로딩화면을 추가했습니다.
     *
     * -책의 상세 정보
     * 검색을 한후 리스트에 있는 책중 하나를 선택하면 책의 상세한 정보가 있는 화면으로 이동합니다.
     *
     * @param appCompatActivity
     */

    public SearchMain(AppCompatActivity appCompatActivity) {
        aSearchMain = appCompatActivity;
        cFileIOStreamCheckDir = new FileIOStreamCheckDir(aSearchMain);
        cFileIOStreamCheckFile = new FileIOStreamCheckFile(aSearchMain);
        cFileIOStreamRead = new FileIOStreamRead(aSearchMain);
        cFileIOStreamWrite = new FileIOStreamWrite(aSearchMain);
        cApiSet = new APISet(aSearchMain);
        adapter = new SearchListViewAdapter();
    }

    // setContentView와 같은 역할
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_activity, container, false);
    }

    // onCreateView가 끝난 뒤에 실행됨
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchbtn = (ImageButton) view.findViewById(R.id.search_btn);
        editTextFilter = (EditText) view.findViewById(R.id.findedittext);
        listView = (ListView) view.findViewById(R.id.listviewhome);
        listView.setAdapter(adapter);

        // 싱글톤 초기화
        Define.ins().splitfile_img_url_1 = cFileIOStreamRead.readData("image_url_1").split("#");
        Define.ins().splitfile_book_name_1 = cFileIOStreamRead.readData("book_name_1").split("#");
        Define.ins().splitfile_book_aut_1 = cFileIOStreamRead.readData("book_aut_1").split("#");
        Define.ins().splitfile_today_1 = cFileIOStreamRead.readData("today_1").split("#");
        Define.ins().splitfile_startday_1 = cFileIOStreamRead.readData("startday_1").split("#");
        Define.ins().splitfile_lastday = cFileIOStreamRead.readData("lastday").split("#");

        Define.ins().splitfile_startday_2 = cFileIOStreamRead.readData("startday_2").split("#");
        Define.ins().splitfile_img_url_2 = cFileIOStreamRead.readData("image_url_2").split("#");
        Define.ins().splitfile_book_name_2 = cFileIOStreamRead.readData("book_name_2").split("#");
        Define.ins().splitfile_book_aut_2 = cFileIOStreamRead.readData("book_aut_2").split("#");
        Define.ins().splitfile_today_2 = cFileIOStreamRead.readData("today_2").split("#");
        Define.ins().splitfile_book_total = cFileIOStreamRead.readData("book_total").split("#");
        Define.ins().splitfile_book_reading = cFileIOStreamRead.readData("book_reading").split("#");
        Define.ins().splitfile_persent = cFileIOStreamRead.readData("percentage").split("#");

        Define.ins().splitfile_img_url_3 = cFileIOStreamRead.readData("image_url_3").split("#");
        Define.ins().splitfile_book_name_3 = cFileIOStreamRead.readData("book_name_3").split("#");
        Define.ins().splitfile_book_aut_3 = cFileIOStreamRead.readData("book_aut_3").split("#");
        Define.ins().splitfile_today_3 = cFileIOStreamRead.readData("today_3").split("#");
        Define.ins().splitfile_book_exp = cFileIOStreamRead.readData("book_exp").split("#");

        Define.ins().temp_startday_1 = cFileIOStreamRead.readData("startday_1");
        Define.ins().temp_lastday = cFileIOStreamRead.readData("lastday");
        Define.ins().temp_book_name_1 = cFileIOStreamRead.readData("book_name_1");
        Define.ins().temp_book_aut_1 = cFileIOStreamRead.readData("book_aut_1");
        Define.ins().temp_image_url_1 = cFileIOStreamRead.readData("image_url_1");
        Define.ins().temp_today_1 = cFileIOStreamRead.readData("today_1");

        Define.ins().temp_startday_2 = cFileIOStreamRead.readData("startday_2");
        Define.ins().temp_book_name_2 = cFileIOStreamRead.readData("book_name_2");
        Define.ins().temp_book_aut_2 = cFileIOStreamRead.readData("book_aut_2");
        Define.ins().temp_image_url_2 = cFileIOStreamRead.readData("image_url_2");
        Define.ins().temp_book_total = cFileIOStreamRead.readData("book_total");
        Define.ins().temp_book_reading = cFileIOStreamRead.readData("book_reading");
        Define.ins().temp_today_2 = cFileIOStreamRead.readData("today_2");

        Define.ins().temp_image_url_3 = cFileIOStreamRead.readData("image_url_3");
        Define.ins().temp_book_name_3 = cFileIOStreamRead.readData("book_name_3");
        Define.ins().temp_book_exp = cFileIOStreamRead.readData("book_exp");
        Define.ins().temp_today_3 = cFileIOStreamRead.readData("today_3");
        Define.ins().temp_book_aut_3 = cFileIOStreamRead.readData("book_aut_3");

        Define.ins().percent = cFileIOStreamRead.readData("percentage");

        // 로딩창 객체 생성
        Loading_dialog customProgressDialog = new Loading_dialog(aSearchMain);
        // 로딩창을 투명하게
        customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        // 홈으로 돌아올때 리스트, 검색어 그대로 남기기 위함
//        System.out.println("에딧 초기화함 : " + Define.ins().keyword);
        editTextFilter.setText(Define.ins().keyword);   // 검색어 유지시키기 위함
        adapter.listViewItemList.clear();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // ToDo
                System.out.println("apiArray : " + Define.ins().apiArray);
                for (int i = 0; i < Define.ins().apiArray.size(); i++) {
//                    System.out.println("추가됨? : " + i);
                    adapter.addItem(Define.ins().apiArray.get(i).get(3), Define.ins().apiArray.get(i).get(0), Define.ins().apiArray.get(i).get(2), Define.ins().apiArray.get(i).get(4));
                }
                // 필터된 리스트뷰를 띄워줌
                ((SearchListViewAdapter) listView.getAdapter()).getFilter().filter(editTextFilter.getText().toString());
                adapter.notifyDataSetChanged();     // 리스트뷰 갱신
            }
        }, 500);


        // 검색 버튼을 눌렀을 때 api를 가지고 와서 리스트를 추가함
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    customProgressDialog.show();        // 로딩창(dialog)을 띄워줌
                    searchbtn.setEnabled(false);        // 검색버튼 비활성화
                    // 중복으로 더해질 수 있으므로 리스트를 비워줌
                    Define.ins().apiArray.clear();
                    adapter.listViewItemList.clear();
//                    System.out.println("검색");
//                    System.out.println("edittext : " + editTextFilter.getText().toString());
                    Define.ins().keyword = editTextFilter.getText().toString();     // 검색어를 싱글톤에 저장
//                    System.out.println("저장됨 : " + Define.ins().keyword);
                    cApiSet.APISearch(editTextFilter.getText().toString());     // API 가져오기

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // ToDo
//                            System.out.println("apiArray : " + Define.ins().apiArray);
                            for (int i = 0; i < Define.ins().apiArray.size(); i++) {
//                                System.out.println("추가됨 " + i + " :\t" + Define.ins().apiArray.get(i));
                                adapter.addItem(Define.ins().apiArray.get(i).get(3), Define.ins().apiArray.get(i).get(0), Define.ins().apiArray.get(i).get(2), Define.ins().apiArray.get(i).get(4));
                            }
                            // 필터된 리스트뷰를 띄워줌
                            ((SearchListViewAdapter) listView.getAdapter()).getFilter().filter(editTextFilter.getText().toString());
                            customProgressDialog.cancel();      // 로딩창 닫기
                            searchbtn.setEnabled(true);         // 검색버튼 활성화
                            adapter.notifyDataSetChanged();     // 리스트뷰 갱신
                        }
                    }, 500);

                } catch (Exception e) {
                    // try에서 에러가 발생할 경우 에러를 모두 로그에 띄워줌
                    e.printStackTrace();
                }
            }
        });

        // 위의 버튼과 같은 역할(키보드 검색 버튼 이벤트)
        editTextFilter.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    try {
                        customProgressDialog.show();        // 로딩창(dialog)을 띄워줌
                        // 중복으로 더해질 수 있으므로 리스트를 비워줌
                        Define.ins().apiArray.clear();
                        adapter.listViewItemList.clear();
//                        System.out.println("검색");
//                        System.out.println("edittext : " + editTextFilter.getText().toString());
                        Define.ins().keyword = editTextFilter.getText().toString();     // 검색어를 싱글톤에 저장
                        cApiSet.APISearch(editTextFilter.getText().toString());         // API 가져오기

//                        System.out.println("apiArray : " + Define.ins().apiArray);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // ToDo
                                for (int i = 0; i < Define.ins().apiArray.size(); i++) {
//                                    System.out.println("추가됨? : " + i);
                                    adapter.addItem(Define.ins().apiArray.get(i).get(3), Define.ins().apiArray.get(i).get(0), Define.ins().apiArray.get(i).get(2), Define.ins().apiArray.get(i).get(4));
                                }
                                // 필터된 리스트뷰를 띄워줌
                                ((SearchListViewAdapter) listView.getAdapter()).getFilter().filter(editTextFilter.getText().toString());
                                customProgressDialog.cancel();
                                adapter.notifyDataSetChanged();
                            }
                        }, 500);

                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        // 메인 리스트뷰 아이템 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // intent -> BookInfoActivity
                Intent intent = new Intent(view.getContext(), BookInfoMain.class);

//                System.out.println("intent 전달 : " + Define.ins().apiArray);
                // 값 전달
                intent.putExtra("book_name", Define.ins().apiArray.get(position).get(0));
                intent.putExtra("book_des", Define.ins().apiArray.get(position).get(5));
                intent.putExtra("book_pub", Define.ins().apiArray.get(position).get(4));
                intent.putExtra("book_isb", Define.ins().apiArray.get(position).get(6));
                intent.putExtra("book_ima", Define.ins().apiArray.get(position).get(3));
                intent.putExtra("book_aut", Define.ins().apiArray.get(position).get(2));

                aSearchMain.startActivity(intent);
            }
        });
    }
}
