package com.example.speed2.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPref {
    Context context;

    public UserPref(Context context) {
        this.context = context;
    }

    public void saveLoginDetails(String id, String nama, String tanggal_lahir, String alamat, String no_hp) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID", id);
        editor.putString("Nama", nama);
        editor.putString("TglLhr", tanggal_lahir);
        editor.putString("Alamat", alamat);
        editor.putString("NoHP", no_hp);
        editor.commit();
    }

    public void setNoAlert(String no_alert) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NoAlert", no_alert);
        editor.commit();
    }

    public String getId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("ID", "");
    }
    public String getNama() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Nama", "");
    }
    public String getTgl() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("TglLhr", "");
    }
    public String getAlamat() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Alamat", "");
    }
    public String getNoHP() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("NoHP", "");
    }

    public String getNoAlert() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("NoAlert", "");
    }

    public boolean isUserLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isIdEmpty = sharedPreferences.getString("ID", "").isEmpty();
        return isIdEmpty;
    }

    public void logout(){
        SharedPreferences settings = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }
}
