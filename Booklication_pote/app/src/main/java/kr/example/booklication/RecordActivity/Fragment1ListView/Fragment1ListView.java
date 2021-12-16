package kr.example.booklication.RecordActivity.Fragment1ListView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.R;
import kr.example.booklication.RecordActivity.Dialog.DeleteDialog1;

public class Fragment1ListView {
    Fragment afragment;
    AppCompatActivity aFragment1ListView;
    View v;
    ListView listView;
    Fragment1ListViewAdapter cFragment1ListViewAdapter;
    FileIOStreamRead cFileIOStreamRead;
    DeleteDialog1 cDeleteDialog;

    public Fragment1ListView(Fragment fragment, AppCompatActivity appCompatActivity, View view) {
        cFragment1ListViewAdapter = new Fragment1ListViewAdapter();
        aFragment1ListView = appCompatActivity;
        v = view;
        afragment = fragment;
        cFileIOStreamRead = new FileIOStreamRead(aFragment1ListView);
        cDeleteDialog = new DeleteDialog1(aFragment1ListView);
    }

    public void setFragment1ListView() {

        //View v = afragment.getLayoutInflater().inflate(R.layout.fragment1, null);
//        View rootView = inflater.inflate(R.layout.fragment_2, container, false);

        listView = (ListView) v.findViewById(R.id.fragment1listview);

        listView.setAdapter(cFragment1ListViewAdapter);

        System.out.println("setFragment1ListView..");

        // 저장된 파일 내역을 가지고 추가해줄것
        if (cFileIOStreamRead.readData("image_url_1").length() == 0 |
                cFileIOStreamRead.readData("book_name_1").length() == 0 |
                cFileIOStreamRead.readData("book_aut_1").length() == 0 |
                cFileIOStreamRead.readData("startday_1").length() == 0 |
                cFileIOStreamRead.readData("lastday").length() == 0) {
            cFragment1ListViewAdapter.addItem(null, "저장된 책이 없습니다.", "", "", "");
        } else {
            cFragment1ListViewAdapter.lstlistViewItem.clear();
            Define.ins().splitfile_img_url_1 = cFileIOStreamRead.readData("image_url_1").split("#");
            Define.ins().splitfile_book_name_1 = cFileIOStreamRead.readData("book_name_1").split("#");
            Define.ins().splitfile_book_aut_1 = cFileIOStreamRead.readData("book_aut_1").split("#");
            Define.ins().splitfile_startday_1 = cFileIOStreamRead.readData("startday_1").split("#");
            Define.ins().splitfile_lastday = cFileIOStreamRead.readData("lastday").split("#");
            for (int i = 0; i < Define.ins().splitfile_book_name_1.length; i++) {
                System.out.println("i result : " + i);
                //책 이미지, 책 제목, 책의 저자, 시작일, 종료일
                cFragment1ListViewAdapter.addItem(
                        Define.ins().splitfile_img_url_1[i],
                        Define.ins().splitfile_book_name_1[i],
                        Define.ins().splitfile_book_aut_1[i],
                        Define.ins().splitfile_startday_1[i],
                        Define.ins().splitfile_lastday[i]);
            }
            cFragment1ListViewAdapter.notifyDataSetChanged();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!cFragment1ListViewAdapter.lstlistViewItem.get(position).getTitle().equals("저장된 책이 없습니다.")) {
                    Define.ins().delete = 1;
                    cDeleteDialog.OnClickHandler(position, cFragment1ListViewAdapter);
                }
            }
        });
    }
}