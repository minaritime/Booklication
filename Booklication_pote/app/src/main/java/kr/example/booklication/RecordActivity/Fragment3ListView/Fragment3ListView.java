package kr.example.booklication.RecordActivity.Fragment3ListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.R;
import kr.example.booklication.RecordActivity.Dialog.DeleteDialog3;

public class Fragment3ListView {
    Fragment afragment;
    AppCompatActivity aFragment3ListView;
    View v;
    ListView listView;
    Fragment3ListViewAdapter cFragment3ListViewAdapter;
    FileIOStreamRead cFileIOStreamRead;
    DeleteDialog3 cDeleteDialog;

    public Fragment3ListView(Fragment fragment, AppCompatActivity appCompatActivity, View view) {
        cFragment3ListViewAdapter = new Fragment3ListViewAdapter();
        aFragment3ListView = appCompatActivity;
        v = view;
        afragment = fragment;
        cFileIOStreamRead = new FileIOStreamRead(aFragment3ListView);
        cDeleteDialog = new DeleteDialog3(aFragment3ListView);
    }

    public void setFragment3ListView() {

        listView = (ListView) v.findViewById(R.id.fragment3listview);

        listView.setAdapter(cFragment3ListViewAdapter);

        if (cFileIOStreamRead.readData("image_url_3").length() == 0 |
                cFileIOStreamRead.readData("book_name_3").length() == 0 |
                cFileIOStreamRead.readData("book_exp").length() == 0) {
            cFragment3ListViewAdapter.addItem(null, "저장된 책이 없습니다.", "");
        } else {
            cFragment3ListViewAdapter.lstlistViewItem.clear();
            Define.ins().splitfile_img_url_3 = cFileIOStreamRead.readData("image_url_3").split("#");
            Define.ins().splitfile_book_name_3 = cFileIOStreamRead.readData("book_name_3").split("#");
            Define.ins().splitfile_book_exp = cFileIOStreamRead.readData("book_exp").split("#");
            for (int i = 0; i < Define.ins().splitfile_book_name_3.length; i++) {
                System.out.println("i result : " + i);
                cFragment3ListViewAdapter.addItem(
                        Define.ins().splitfile_img_url_3[i],
                        Define.ins().splitfile_book_name_3[i],
                        Define.ins().splitfile_book_exp[i]);
            }
            cFragment3ListViewAdapter.notifyDataSetChanged();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!cFragment3ListViewAdapter.lstlistViewItem.get(position).getTitle().equals("저장된 책이 없습니다.")) {
                    Define.ins().delete = 3;
                    cDeleteDialog.OnClickHandler(position, cFragment3ListViewAdapter);
                }
            }
        });
    }
}
