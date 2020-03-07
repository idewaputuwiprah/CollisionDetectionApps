package com.example.speed2;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public WriteFile() {

    }

    public void writeToFile(String data) throws IOException {
        File dir= new File(Environment.getExternalStorageDirectory(), "dataAcc");
        dir.mkdirs();
        File output=new File(dir, "data" +".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(output, true));
        writer.append(data);

        writer.close();
    }


}
