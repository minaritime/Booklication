package kr.example.booklication.Search_ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kr.example.booklication.R;

public class SearchListViewAdapter extends BaseAdapter implements Filterable {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    public ArrayList<SearchListViewItem> listViewItemList = new ArrayList<SearchListViewItem>() ;
    // 필터링된 결과 데이터를 저장하기 위한 ArrayList. 최초에는 전체 리스트 보유.
    public ArrayList<SearchListViewItem> filteredItemList = listViewItemList ;

    Filter listFilter ;

    // Adapter에 사용되는 데이터의 개수를 리턴
    @Override
    public int getCount() {
        return filteredItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "SearchListViewItem" Layout을 inflate하여 convertView 참조 획득
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.main_listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv1) ;
        TextView textView1 = (TextView) convertView.findViewById(R.id.tv1) ;
        TextView textView2 = (TextView) convertView.findViewById(R.id.tv2) ;

        // Data Set(filteredItemList)에서 position에 위치한 데이터 참조 획득
        SearchListViewItem listViewItem = filteredItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        Glide.with(convertView)
                .load(listViewItem.getUrl())
                .error(R.drawable.image_ready)
                .into(imageView);
        textView1.setText(listViewItem.getTitle());
        textView2.setText(listViewItem.getWriter());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }
        return listFilter ;
    }

    // 필터
    private class ListFilter extends Filter {

        // 필터링을 수행하는 함수
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                // 리스트뷰에 저장된 값들을 가져옴
                results.values = listViewItemList;

                // 리스트뷰의 사이즈
                results.count = listViewItemList.size();
            } else {
                ArrayList<SearchListViewItem> itemList = new ArrayList<SearchListViewItem>();

                for (SearchListViewItem item : listViewItemList) {
                    // listViewItemList에 검색어가 포함 되어있다면 itemList에 추가해줌
                    if (item.getTitle().toUpperCase().contains(constraint.toString().toUpperCase())|item.getWriter().toUpperCase().contains(constraint.toString().toUpperCase())|item.getpublisher().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        itemList.add(item);
                    }
                }

                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // 필터를 거친 리스트뷰로 변경
            filteredItemList = (ArrayList<SearchListViewItem>) results.values ;

            // 갱신
            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수
    public void addItem(String image_url, String title, String desc, String pub) {
        SearchListViewItem item = new SearchListViewItem();

        item.setUrl(image_url);
        item.setTitle(title);
        item.setWriter(desc);
        item.setpublisher(pub);

        listViewItemList.add(item);
    }
}
