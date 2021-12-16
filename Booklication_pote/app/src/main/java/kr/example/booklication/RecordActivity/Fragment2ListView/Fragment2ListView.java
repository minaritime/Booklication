package kr.example.booklication.RecordActivity.Fragment2ListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.R;
import kr.example.booklication.RecordActivity.Dialog.DeleteDialog2;
import kr.example.booklication.RecordActivity.Percentage.Percentage;

public class Fragment2ListView {
    Fragment afragment;
    AppCompatActivity aFragment2ListView;
    View v;
    ListView listView;
    Fragment2ListViewAdapter cFragment2ListViewAdapter;
    FileIOStreamRead cFileIOStreamRead;
    Percentage percentage;
    DeleteDialog2 cDeleteDialog;

    public Fragment2ListView(Fragment fragment, AppCompatActivity appCompatActivity, View view) {
        cFragment2ListViewAdapter = new Fragment2ListViewAdapter();
        aFragment2ListView = appCompatActivity;
        v = view;
        afragment = fragment;
        percentage = new Percentage(aFragment2ListView);
        cFileIOStreamRead = new FileIOStreamRead(aFragment2ListView);
        cDeleteDialog = new DeleteDialog2(aFragment2ListView);
    }

    public void setFragment2ListView() {

        listView = (ListView) v.findViewById(R.id.fragment2listview);

        listView.setAdapter(cFragment2ListViewAdapter);

        if (cFileIOStreamRead.readData("book_reading").length() == 0 |
                cFileIOStreamRead.readData("book_total").length() == 0 |
                cFileIOStreamRead.readData("image_url_2").length() == 0 |
                cFileIOStreamRead.readData("book_name_2").length() == 0 |
                cFileIOStreamRead.readData("book_aut_2").length() == 0 |
                cFileIOStreamRead.readData("startday_2").length() == 0 |
                cFileIOStreamRead.readData("percentage").length() == 0) {
            cFragment2ListViewAdapter.addItem(null, "저장된 책이 없습니다.", "", "", 0, "0%", "");
        } else {
            cFragment2ListViewAdapter.lstlistViewItem.clear();
            Define.ins().splitfile_book_reading = cFileIOStreamRead.readData("book_reading").split("#");
            Define.ins().splitfile_book_total = cFileIOStreamRead.readData("book_total").split("#");
            Define.ins().splitfile_img_url_2 = cFileIOStreamRead.readData("image_url_2").split("#");
            Define.ins().splitfile_book_name_2 = cFileIOStreamRead.readData("book_name_2").split("#");
            Define.ins().splitfile_book_aut_2 = cFileIOStreamRead.readData("book_aut_2").split("#");
            Define.ins().splitfile_startday_2 = cFileIOStreamRead.readData("startday_2").split("#");
            Define.ins().splitfile_persent = cFileIOStreamRead.readData("percentage").split("#");
            for (int i = 0; i < Define.ins().splitfile_book_name_2.length; i++) {
                System.out.println("i result : " + i);
                cFragment2ListViewAdapter.addItem(
                        Define.ins().splitfile_img_url_2[i],
                        Define.ins().splitfile_book_name_2[i],
                        Define.ins().splitfile_book_aut_2[i],
                        Define.ins().splitfile_startday_2[i],
                        Integer.valueOf(Define.ins().splitfile_persent[i]),
                        Integer.valueOf(Define.ins().splitfile_persent[i]) + "%",
                        Define.ins().splitfile_book_reading[i] + "/" + Define.ins().splitfile_book_total[i]);
            }
            System.out.println("개새 : " + cFragment2ListViewAdapter.lstlistViewItem);
            cFragment2ListViewAdapter.notifyDataSetChanged();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!cFragment2ListViewAdapter.lstlistViewItem.get(position).getTitle().equals("저장된 책이 없습니다.")) {
                    Define.ins().delete = 2;
                    cDeleteDialog.OnClickHandler(position,cFragment2ListViewAdapter);
                }
            }
        });
    }
}

