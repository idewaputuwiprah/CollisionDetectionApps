package com.example.speed2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class FragmentEditData extends Fragment {

    private EditText nik, nama, date, add, nomor_user;
    private Button saveBtn;
    private Context context;
    DatePickerDialog datePickerDialog;

    View view;

    public FragmentEditData() {

    }

    public FragmentEditData(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_data, container, false);
        date = view.findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int tahun, int bulan, int hari) {
                        date.setText(hari+"/"+(bulan+1)+"/"+tahun);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        nik = view.findViewById(R.id.NIK);
        nama = view.findViewById(R.id.nama);
        add = view.findViewById(R.id.alamat);
        nomor_user = view.findViewById(R.id.nomor_user);
        saveBtn = view.findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = nik.getText().toString();
                String name = nama.getText().toString();
                String tgl = date.getText().toString();
                String alamat = add.getText().toString();
                String no_user = nomor_user.getText().toString();
                Toast.makeText(context, id + " " + name + " " + tgl + " " + alamat + " " + no_user + "\n", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
