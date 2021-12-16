package kr.example.booklication.RecordActivity.Percentage;

import androidx.appcompat.app.AppCompatActivity;

import kr.example.booklication.Define;
import kr.example.booklication.FileIOStream.FileIOStreamRead;
import kr.example.booklication.FileIOStream.FileIOStreamWrite;

public class Percentage {

    AppCompatActivity aPercentage;
    FileIOStreamRead cFileIOStreamRead;
    FileIOStreamWrite cFileIOStreamWrite;

    public Percentage(AppCompatActivity appCompatActivity){
        aPercentage = appCompatActivity;
        cFileIOStreamRead = new FileIOStreamRead(aPercentage);
        cFileIOStreamWrite = new FileIOStreamWrite(aPercentage);
    }

    public void CalculationPercentage() {
        System.out.println("CalculationPercentage...");

        if(!(cFileIOStreamRead.readData("book_reading").length() > 0)){
            Define.ins().percent = "";
        }else {
            Define.ins().splitfile_book_reading = cFileIOStreamRead.readData("book_reading").split("#");
            Define.ins().splitfile_book_total = cFileIOStreamRead.readData("book_total").split("#");
            Define.ins().splitfile_persent = cFileIOStreamRead.readData("percentage").split("#");

            if(Define.ins().splitfile_book_reading.length == 0|
                    Define.ins().splitfile_book_total.length == 0|
                    Define.ins().splitfile_book_reading.equals(" ")|
                    Define.ins().splitfile_book_total.equals(" ")){
                cFileIOStreamWrite.writeData("percentage", "");
            }else {
                for (int i = 0; i < Define.ins().splitfile_persent.length; i++) {
                    Define.ins().percent += (int) (Double.valueOf(Define.ins().splitfile_book_reading[i]) / Double.valueOf(Define.ins().splitfile_book_total[i]) * 100) + "#";
                    cFileIOStreamWrite.writeData("percentage", Define.ins().percent);
                }
            }
        }

    }
}
