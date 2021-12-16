package kr.example.booklication.CalendarActivity;

import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import kr.example.booklication.CalendarActivity.CalendarListViewAdapter;
import kr.example.booklication.Define;
import kr.example.booklication.R;

public class CalendarListViewSet {

    AppCompatActivity aListView;
    ListView listView;
    CalendarListViewAdapter cCalendarListViewAdapter;
    ArrayList<ArrayList<String>> finalarray = new ArrayList<ArrayList<String>>();

    public CalendarListViewSet(AppCompatActivity appCompatActivity) {
        aListView = appCompatActivity;
    }

    public void setCalendarListView() {
        //객체 생성
        cCalendarListViewAdapter = new CalendarListViewAdapter();

        //캘린더 리스트뷰 ID가져오기
        listView = (ListView) aListView.findViewById(R.id.listviewcalendar);
        listView.setAdapter(cCalendarListViewAdapter);

        //해당 날짜의 배열 값들 초기화
        finalarray.clear();
        System.out.println("temparray : " + Define.ins().temparray);
        for (int a = 0; a < Define.ins().temparray.size(); a++){
            //만약 저장된 값의 배열 값과 선택한 날짜와 같다면
            if(Define.ins().temparray.get(a).get(3).equals(Define.ins().Date)){
                //finalarray에 배열 값 추가
                finalarray.add(Define.ins().temparray.get(a));
            }
        }
        System.out.println("finalarray : " + finalarray);
        for (int i = 0; i < finalarray.size(); i++) {
            // addItem(책 이미지, 책 제목, 책 저자)
            cCalendarListViewAdapter.addItem(finalarray.get(i).get(0), finalarray.get(i).get(1), finalarray.get(i).get(2));
        }
        //리스트뷰 새로 고침
        cCalendarListViewAdapter.notifyDataSetChanged();
    }
}
