package kr.example.booklication.CalendarActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import kr.example.booklication.CalendarViewDecorator.EventDecorator;
import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.R;

public class CalendarMain extends Fragment implements OnDateSelectedListener {

    FileIOStreamRead cFileIOStreamRead;
    AppCompatActivity aCalendarMain;

    MaterialCalendarView widget;
    CalendarListViewSet calendarListViewSet;
    DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

    ArrayList<String> dotarray = new ArrayList<String>();       // 책이 저장된 날짜들을 담는 리스트
    ArrayList<String> splitdate = new ArrayList<String>();           // 저장된 날짜들을 년, 월, 일로 나눠 담는 리스트
    String[] splitText;

    int year = 0;
    int month = 0;
    int day = 0;
    int num = 0;

    /**
     * -기록
     * 하단메뉴에서 기록버튼을 누를 시 보여지는 화면이며 지금까지 저장한 책들을 날짜별로 볼 수 있습니다.
     *
     * -달력
     * 해당 화면을 들어오면 파일에 저장되어있는 책들을 Arraylist에 저장한 후 싱글톤에 추가합니다.
     * 싱글톤에 추가된 값중 날짜를 판별하여 해당 날짜에 점을 찍습니다.
     * 점이 찍힌 날짜를 선택하면 해당 날짜에 서재에 저장된 책의 제목, 책의 저자, 표지를 볼 수 있습니다.
     *
     *
     * @param appCompatActivity
     */

    public CalendarMain(AppCompatActivity appCompatActivity){
        aCalendarMain = appCompatActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.calendar_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 객체생성
        calendarListViewSet = new CalendarListViewSet(aCalendarMain);
        cFileIOStreamRead = new FileIOStreamRead(aCalendarMain);

        // ID 연결
        widget = (MaterialCalendarView) aCalendarMain.findViewById(R.id.calenderView);

        // 날짜 바뀔때 마다의 이벤트를 선언
        widget.setOnDateChangedListener(this);

        // 리스트뷰 초기화
        Define.ins().temparray.clear();

        // split 값을 갱신
        Define.ins().splitfile_img_url_1 = cFileIOStreamRead.readData("image_url_1").split("#");
        Define.ins().splitfile_book_name_1 = cFileIOStreamRead.readData("book_name_1").split("#");
        Define.ins().splitfile_book_aut_1 = cFileIOStreamRead.readData("book_aut_1").split("#");
        Define.ins().splitfile_today_1 = cFileIOStreamRead.readData("today_1").split("#");

        Define.ins().splitfile_img_url_2 = cFileIOStreamRead.readData("image_url_2").split("#");
        Define.ins().splitfile_book_name_2 = cFileIOStreamRead.readData("book_name_2").split("#");
        Define.ins().splitfile_book_aut_2 = cFileIOStreamRead.readData("book_aut_2").split("#");
        Define.ins().splitfile_today_2 = cFileIOStreamRead.readData("today_2").split("#");

        Define.ins().splitfile_img_url_3 = cFileIOStreamRead.readData("image_url_3").split("#");
        Define.ins().splitfile_book_name_3 = cFileIOStreamRead.readData("book_name_3").split("#");
        Define.ins().splitfile_book_aut_3 = cFileIOStreamRead.readData("book_aut_3").split("#");
        Define.ins().splitfile_today_3 = cFileIOStreamRead.readData("today_3").split("#");

        // 읽은 책의 갯수만큼 반복 (정확히는 책의 제목 갯수만큼)
        for(int i = 0; i < Define.ins().splitfile_book_name_1.length ; i++){
            if(!Define.ins().splitfile_book_name_1[i].equals("")) {     // 만약 읽은 책의 제목이 없지 않다면
                ArrayList<String> fileslist = new ArrayList<>();
                // fileslist에 값을 추가
                fileslist.add(Define.ins().splitfile_img_url_1[i]);
                fileslist.add(Define.ins().splitfile_book_name_1[i]);
                fileslist.add(Define.ins().splitfile_book_aut_1[i]);
                fileslist.add(Define.ins().splitfile_today_1[i]);

                Define.ins().temparray.add(fileslist);      // 추가한 값을 temparray에 저장
            }
        }
        // 읽고 있는 책의 갯수만큼 반복
        for(int i = 0; i < Define.ins().splitfile_book_name_2.length ; i++){
            if(!Define.ins().splitfile_book_name_2[i].equals("")) {     // 만약 읽고 있는 책의 제목이 없지 않다면
                ArrayList<String> fileslist = new ArrayList<>();
                // fileslist에 값을 추가
                fileslist.add(Define.ins().splitfile_img_url_2[i]);
                fileslist.add(Define.ins().splitfile_book_name_2[i]);
                fileslist.add(Define.ins().splitfile_book_aut_2[i]);
                fileslist.add(Define.ins().splitfile_today_2[i]);

                Define.ins().temparray.add(fileslist);      // 추가한 값을 temparray에 저장
            }
        }
        // 읽고 싶은 책의 갯수만큼 반복
        for(int i = 0; i < Define.ins().splitfile_book_name_3.length ; i++){
            if(!Define.ins().splitfile_book_name_3[i].equals("")) {     // 만약 읽고 싶은 책의 제목이 없지 않다면
                ArrayList<String> fileslist = new ArrayList<>();
                // fileslist에 값을 추가
                fileslist.add(Define.ins().splitfile_img_url_3[i]);
                fileslist.add(Define.ins().splitfile_book_name_3[i]);
                fileslist.add(Define.ins().splitfile_book_aut_3[i]);
                fileslist.add(Define.ins().splitfile_today_3[i]);

                Define.ins().temparray.add(fileslist);      // 추가한 값을 temparray에 저장
            }
        }

        // temparray의 size만큼 반복
        for(int i = 0 ; i < Define.ins().temparray.size() ; i++){
            // 맨 처음 날짜는 무조건 중복이 없기 때문에 그냥 점찍어줌
            if(i == 0){
                // 해당 날짜에 리스트뷰가 있으면 캘린더뷰에 점찍기
                dotarray.add(Define.ins().temparray.get(i).get(3));
            } else{
                // 중복 예외처리
                if(!dotarray.get(num).equals(Define.ins().temparray.get(i).get(3))){
                    //dotarray변수에 리스트뷰가 들어있는 날짜 추가
                    dotarray.add(Define.ins().temparray.get(i).get(3));
                    num++;
                }
            }
        }

        // 가져온 날짜를 split으로 년, 달, 일로 분해
        for(int k = 0 ; k < dotarray.size() ; k++){
            String temp = dotarray.get(k);
            splitText = temp.split("\\.");
            splitdate.add(splitText[0]);
            splitdate.add(splitText[1]);
            splitdate.add(splitText[2]);
        }
        int count = 0;
        // year, month, day 변수에 각각 년, 달, 일 의 값들 삽입
        for(int l = 0 ; l < splitdate.size() ; l+=3){
            System.out.println("temparray.get(l) : " + splitdate.get(count));
            year = Integer.parseInt(splitdate.get(count));
            count++;
            System.out.println("temparray.get(l) : " + splitdate.get(count));
            month = Integer.parseInt(splitdate.get(count));
            count++;
            System.out.println("temparray.get(l) : " + splitdate.get(count));
            day = Integer.parseInt(splitdate.get(count));
            count++;

            //값이 들어있는 날짜에 점 찍기
            widget.addDecorator(new EventDecorator(Color.RED, Collections.singleton(CalendarDay.from(year,month-1,day))));
        }
    }

    // 날짜 선택 이벤트
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date,
                               boolean selected) {
        System.out.println("날짜 : " + dateFormat.format(date.getDate()));
        Define.ins().Date = selected ? dateFormat.format(date.getDate()) : "No Selection";
        System.out.println("Define.ins().Date : " + Define.ins().Date);
        calendarListViewSet.setCalendarListView();
    }
}
