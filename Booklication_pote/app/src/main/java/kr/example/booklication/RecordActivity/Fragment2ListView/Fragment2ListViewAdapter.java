package kr.example.booklication.RecordActivity.Fragment2ListView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.example.booklication.R;

public class Fragment2ListViewAdapter extends BaseAdapter {
    public ArrayList<Fragment2ListViewItem> lstlistViewItem = new ArrayList<Fragment2ListViewItem>();

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
            convertView = inflater.inflate(R.layout.fragment2_item, parent, false);
        }

        ImageView iv_image = (ImageView)convertView.findViewById(R.id.iv_image);
        TextView tv_title = (TextView)convertView.findViewById(R.id.tv_title);
        TextView tv_author = (TextView)convertView.findViewById(R.id.tv_author);
        TextView tv_begindate = (TextView)convertView.findViewById(R.id.tv_begindate);
        ProgressBar pb_progressbar = (ProgressBar)convertView.findViewById(R.id.pb_progressbar);
        TextView tv_percentage = (TextView)convertView.findViewById(R.id.tv_percentage);
        TextView tv_page = (TextView)convertView.findViewById(R.id.tv_page);

        Fragment2ListViewItem fragment2ListViewItem = lstlistViewItem.get(position);

        Glide.with(convertView).load(fragment2ListViewItem.getImage()).into(iv_image);
        tv_title.setText(fragment2ListViewItem.getTitle());
        tv_author.setText(fragment2ListViewItem.getAuthor());
        tv_begindate.setText(fragment2ListViewItem.getBeginDate());
        pb_progressbar.setProgress(fragment2ListViewItem.getProgressBar());
        tv_percentage.setText(fragment2ListViewItem.getPercentage());
        tv_page.setText(fragment2ListViewItem.getPage());

        return convertView;
    }

    public void addItem(String Imageurl, String Title, String Author, String BeginDate, int progressbar, String Percentage, String Page) {
        Fragment2ListViewItem fragment2ListViewItem = new Fragment2ListViewItem();

        fragment2ListViewItem.setImage(Imageurl);
        fragment2ListViewItem.setTitle(Title);
        fragment2ListViewItem.setAuthor(Author);
        fragment2ListViewItem.setBeginDate(BeginDate);
        fragment2ListViewItem.setProgressBar(progressbar);
        fragment2ListViewItem.setPercentage(Percentage);
        fragment2ListViewItem.setPage(Page);

        lstlistViewItem.add(fragment2ListViewItem);


    }
}
