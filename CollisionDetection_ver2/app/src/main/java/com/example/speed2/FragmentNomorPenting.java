package com.example.speed2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentNomorPenting extends Fragment {

    private Context context;
    View view;
    EditText nomor_penting;
    Button saveBtn;

    public FragmentNomorPenting() {

    }

    public FragmentNomorPenting(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nomor_penting,container,false);
        nomor_penting = view.findViewById(R.id.nomor_penting);
        saveBtn = view.findViewById(R.id.save_nomor);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no_darurat = nomor_penting.getText().toString();
                Toast.makeText(context, no_darurat, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
