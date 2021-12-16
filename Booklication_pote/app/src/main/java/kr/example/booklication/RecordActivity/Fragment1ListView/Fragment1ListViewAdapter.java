package kr.example.booklication.RecordActivity.Fragment1ListView;

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

public class Fragment1ListViewAdapter extends BaseAdapter {
    public ArrayList<Fragment1ListViewItem> lstlistViewItem = new ArrayList<Fragment1ListViewItem>();

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
            convertView = inflater.inflate(R.layout.fragment1_item, parent, false);
        }

        ImageView iv_image = (ImageView)convertView.findViewById(R.id.iv_image);
        TextView tv_title = (TextView)convertView.findViewById(R.id.tv_title);
        TextView tv_author = (TextView)convertView.findViewById(R.id.tv_author);
        TextView tv_begindate = (TextView)convertView.findViewById(R.id.tv_begindate);
        TextView tv_closedate = (TextView)convertView.findViewById(R.id.tv_closedate);

        Fragment1ListViewItem fragment1ListViewItem = lstlistViewItem.get(position);


        Glide.with(convertView).load(fragment1ListViewItem.getImage()).into(iv_image);
        tv_title.setText(fragment1ListViewItem.getTitle());
        tv_author.setText(fragment1ListViewItem.getAuthor());
        tv_begindate.setText(fragment1ListViewItem.getBeginDate());
        tv_closedate.setText(fragment1ListViewItem.getCloseDate());

        return convertView;
    }

    public void addItem(String Imageurl, String Title, String Author, String BeginDate, String CloseDate) {
        Fragment1ListViewItem fragment1ListViewItem = new Fragment1ListViewItem();

        fragment1ListViewItem.setImage(Imageurl);
        fragment1ListViewItem.setTitle(Title);
        fragment1ListViewItem.setAuthor(Author);
        fragment1ListViewItem.setBeginDate(BeginDate);
        fragment1ListViewItem.setCloseDate(CloseDate);

        lstlistViewItem.add(fragment1ListViewItem);

    }
}
