package kr.example.booklication.BookInfoActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.example.booklication.BookInfoActivity.Bottomsheetdialog.CustomBottomSheetDialog;
import kr.example.booklication.BookInfoActivity.Dialog.OverlapDialog;
import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.FileIOStream.FileIOStreamWrite;
import kr.example.booklication.R;

public class BookInfoMain extends AppCompatActivity {

    Toolbar toolbar;
    Button save;
    TextView BookDescription;
    TextView Publisher;
    TextView isbn;
    TextView BookTitle;
    TextView Author;
    ImageView BookImage;
    FileIOStreamWrite cFileIOStreamWrite;
    FileIOStreamRead cFileIOStreamRead;
    long now;       // 현재 시간을 담는 변수
    Date date;      // 정해진 형식으로 변경한 시간을 담는 변수
    OverlapDialog cdialog;
    CustomBottomSheetDialog bottomSheetDialog;
    String[] split_bookname1;
    String[] split_bookname2;
    String[] split_bookname3;

    /**
     * -상세 정보
     * 책의 이름, 책의 저자, 출판사, 책의 소개, 책의 고유 번호, 표지 등을 한눈에 볼 수 있는 화면이며 글자가 너무 긴 것은 잘라서 출력했습니다.
     *
     * -저장 버튼
     * 저장 버튼을 누르면 저장되어있지 않은 책이라면 싱글톤에 필요한 값을 저장하고 바텀시트다이얼로그가 팝업되며 읽은 책, 읽고 있는 책, 읽고 싶은 책 중 하나를 택하여 항목마다 다른 책의 정보를 기입할 수 있습니다.
     * 해당 바텀시트다이얼로그에선 읽은 책이라면 시작일과 종료일을, 읽고 있는 책이라면 총 페이지 수, 읽은 페이지 수, 시작일을, 읽고 싶은 책이라면 기대평 등을 기입할 수 있습니다.
     * 정보를 기입하고 하단에 있는 저장하기 버튼을 누르게 되면 싱글톤에 값을 저장하고 해당 싱글톤을 파일에 저장합니다.
     * 만약 저장되어있는 책이라면 일반 다이얼로그를 띄워 다시 저장하는지 묻는 다이얼로그가 팝업됩니다. 확인 버튼을 누르면 바텀시트다이얼로그를, 취소 버튼을 누르면 다이얼로그를 종료합니다.
     *
     * -뒤로가기
     * 툴바의 뒤로가기 기능을 활용했으며 검색 화면으로 되돌아가게 됩니다.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info_activity);

        cFileIOStreamWrite = new FileIOStreamWrite(this);
        cFileIOStreamRead = new FileIOStreamRead(this);
        cdialog = new OverlapDialog(this);
        bottomSheetDialog = new CustomBottomSheetDialog(this);

        // 현재(오늘) 날짜 가져오기
        now = System.currentTimeMillis();
        date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");  // 시간 형식
        System.out.println("오늘 날짜 : " + sdf.format(date));

        //툴바 설정
        toolbar = findViewById(R.id.main_toolbar);

        //툴바 타이틀 제거
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // id 연결
        save = (Button) findViewById(R.id.savebutton);
        BookDescription = (TextView) findViewById(R.id.idTV_BookDescription);
        Publisher = (TextView) findViewById(R.id.idTV_Publisher);
        isbn = (TextView) findViewById(R.id.idTV_isbn);
        BookTitle = (TextView) findViewById(R.id.idTV_BookTitle);
        Author = (TextView) findViewById(R.id.idTV_Author);
        BookImage = (ImageView) findViewById(R.id.idIV_BookImage);

//        System.out.println("intent 값 받음 : " + getIntent().getStringExtra("book_name"));
//        System.out.println("intent 값 받음 : " + getIntent().getStringExtra("book_des"));
//        System.out.println("intent 값 받음 : " + getIntent().getStringExtra("book_pub"));
//        System.out.println("intent 값 받음 : " + getIntent().getStringExtra("book_isb"));
//        System.out.println("intent 값 받음 : " + getIntent().getStringExtra("book_ima"));
//        System.out.println("intent 값 받음 : " + getIntent().getStringExtra("book_aut"));

        // 받은 정보들을 띄워줌
        BookDescription.setText(getIntent().getStringExtra("book_des"));
        Publisher.setText(getIntent().getStringExtra("book_pub"));
        isbn.setText(getIntent().getStringExtra("book_isb"));
        BookTitle.setText(getIntent().getStringExtra("book_name"));
        Author.setText(getIntent().getStringExtra("book_aut"));
        Glide.with(this).load(getIntent().getStringExtra("book_ima")).into(BookImage);

        save.setClipToOutline(true);    // 이미지를 배경에 맞게 자름
        // 책 (서재에)저장하기
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int comfirm = 0;        // 중복확인을 위한 변수
                // 서재에 저장하기 위해 싱글톤에 값 저장
                Define.ins().BookName = getIntent().getStringExtra("book_name") + "#";
                Define.ins().BookAuthor = getIntent().getStringExtra("book_aut") + "#";
                Define.ins().BookImageURL = getIntent().getStringExtra("book_ima") + "#";
                split_bookname1 = cFileIOStreamRead.readData("book_name_1").split("#");
                split_bookname2 = cFileIOStreamRead.readData("book_name_2").split("#");
                split_bookname3 = cFileIOStreamRead.readData("book_name_3").split("#");
                // 이미 서재에 저장되어 있다면 CustomDialog를 띄워줘야 함
                for(int i = 0; i < split_bookname1.length ; i++){
                    // 현재 띄운 책의 이름이 읽은 책이라면
                    if(split_bookname1[i].equals(getIntent().getStringExtra("book_name"))){
                        cdialog.OnClickHandler();
                        break;
                    }else if(i == split_bookname1.length - 1){
                        comfirm++;
                    }
                }
                for(int i = 0; i < split_bookname2.length ; i++){
                    // 현재 띄운 책의 이름이 읽고 있는 책이라면
                    if(split_bookname2[i].equals(getIntent().getStringExtra("book_name"))){
                        cdialog.OnClickHandler();
                        break;
                    }else if(i == split_bookname2.length - 1){
                        comfirm++;
                    }
                }
                for(int i = 0; i < split_bookname3.length ; i++){
                    // 현재 띄운 책의 이름이 읽고 싶은 책이라면
                    if(split_bookname3[i].equals(getIntent().getStringExtra("book_name"))){
                        cdialog.OnClickHandler();
                        break;
                    }else if(i == split_bookname3.length - 1){
                        comfirm++;
                    }
                }
                System.out.println("확인 : " + comfirm);
                if(comfirm == 3) {  // 중복이 아니라면 바텀시트다이얼로그를 띄워줌
                    bottomSheetDialog.callFunction();
                }
            }
        });

    }

    //툴바 뒤로가기
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 액티비티 종료
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
