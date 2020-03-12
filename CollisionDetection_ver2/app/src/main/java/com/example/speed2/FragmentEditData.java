package com.example.speed2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


import com.example.speed2.database.DatabaseHelper;
import com.example.speed2.database.model.User;
import com.example.speed2.preferences.UserPref;

import java.util.Calendar;

public class FragmentEditData extends Fragment {

    private EditText nik, nama, date, add, nomor_user;
    private Button saveBtn;
    private Context context;
    DatePickerDialog datePickerDialog;
    DatabaseHelper db;

    View view;

    public FragmentEditData() {

    }

    public FragmentEditData(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_data, container, false);
        db= new DatabaseHelper(view.getContext());
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

        if(!new UserPref(view.getContext()).isUserLogedOut())
        {
            nik.setText(new UserPref(view.getContext()).getId());
            nama.setText(new UserPref(view.getContext()).getNama());
            add.setText(new UserPref(view.getContext()).getAlamat());
            date.setText(new UserPref(view.getContext()).getTgl());
            nomor_user.setText(new UserPref(view.getContext()).getNoHP());
        }

        saveBtn = view.findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = nik.getText().toString();
                String name = nama.getText().toString();
                String tgl = date.getText().toString();
                String alamat = add.getText().toString();
                String no_user = nomor_user.getText().toString();
//                db.insertData(id, name, tgl, alamat, no_user);
                UserPref pref = new UserPref(view.getContext());
                pref.saveLoginDetails(id,name,tgl,alamat,no_user);

                Toast.makeText(context, "Data telah disimpan\n", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
