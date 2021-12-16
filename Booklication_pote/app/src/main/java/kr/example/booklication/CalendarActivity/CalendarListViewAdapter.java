package kr.example.booklication.CalendarActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.example.booklication.R;

public class CalendarListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    public ArrayList<CalendarListViewItem> lstlistViewItem = new ArrayList<CalendarListViewItem>();

    @Override
    public int getCount() {
        return lstlistViewItem.size();
    }

    @Override
    public Object getItem(int position) {
        return lstlistViewItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.calendar_listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView tv_image = (ImageView) convertView.findViewById(R.id.civ1);
        TextView tv_title = (TextView) convertView.findViewById(R.id.ctv1);
        TextView tv_writter = (TextView) convertView.findViewById(R.id.ctv2);

        CalendarListViewItem exchangeListViewItem = lstlistViewItem.get(position);

        Glide.with(convertView).load(exchangeListViewItem.getImage()).into(tv_image);
        tv_title.setText(exchangeListViewItem.getTitle());
        tv_writter.setText(exchangeListViewItem.getWritter());

        return convertView;

    }


    // 아이템 데이터 추가를 위한 함수
    public void addItem(String Imageurl, String Title, String Writter) {
        CalendarListViewItem calendarListViewItem = new CalendarListViewItem();

        calendarListViewItem.setImage(Imageurl);
        calendarListViewItem.setTitle(Title);
        calendarListViewItem.setWritter(Writter);

        lstlistViewItem.add(calendarListViewItem);
    }
}

