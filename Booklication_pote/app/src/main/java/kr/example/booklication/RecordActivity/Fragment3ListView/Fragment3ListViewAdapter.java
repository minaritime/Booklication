package kr.example.booklication.RecordActivity.Fragment3ListView;

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

public class Fragment3ListViewAdapter extends BaseAdapter {
    public ArrayList<Fragment3ListViewItem> lstlistViewItem = new ArrayList<Fragment3ListViewItem>();

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
            convertView = inflater.inflate(R.layout.fragment3_item, parent, false);
        }

        ImageView iv_image = (ImageView)convertView.findViewById(R.id.iv_image);
        TextView tv_title = (TextView)convertView.findViewById(R.id.tv_title);
        TextView tv_expectation = (TextView)convertView.findViewById(R.id.tv_expectation);

        Fragment3ListViewItem fragment3ListViewItem = lstlistViewItem.get(position);

        Glide.with(convertView).load(fragment3ListViewItem.getImage()).into(iv_image);
        tv_title.setText(fragment3ListViewItem.getTitle());
        tv_expectation.setText(fragment3ListViewItem.getExpectation());

        return convertView;

    }

    public void addItem(String Imageurl, String Title, String Expectation) {
        Fragment3ListViewItem fragment3ListViewItem = new Fragment3ListViewItem();

        fragment3ListViewItem.setImage(Imageurl);
        fragment3ListViewItem.setTitle(Title);
        fragment3ListViewItem.setExpectation(Expectation);

        lstlistViewItem.add(fragment3ListViewItem);


    }
}
