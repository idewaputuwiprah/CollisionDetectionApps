package com.example.speed2.database.model;

public class Dataset {
    public static final String TABLE_NAME = "dataset";

    public static final String COLUMN_ID = "data_id";
    public static final String COLUMN_X = "x";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_Z = "z";
    public static final String COLUMN_VELO = "kecepatan";

    private int data_id;
    private double x;
    private double y;
    private double z;
    private double kecepatan;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_X + " DOUBLE,"
                    + COLUMN_Y + " DOUBLE,"
                    + COLUMN_Z + " DOUBLE,"
                    + COLUMN_VELO + " DOUBLE"
                    + ")";

    public Dataset(double x, double y, double z, double kecepatan){
        this.x = x;
        this.y = y;
        this.z = z;
        this.kecepatan = kecepatan;
    }

    public int getData_id(){

        return data_id;
    }

    public void setData_id(int data_id){

        this.data_id = data_id;
    }

    public double getX(){

        return x;
    }

    public void  setColumnX(double x){

        this.x = x;
    }

    public double getY(){

        return y;
    }

    public void setColumnY(double y){

        this.y = y;
    }

    public double getZ(){

        return z;
    }

    public void setColumnZ(double z){

        this.z = z;
    }

    public void setColumnVelo(double kecepatan){

        this.kecepatan = kecepatan;
    }

    public double getKecepatan(){

        return kecepatan;
    }
}
