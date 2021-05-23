package com.example.c2foconnect.helper;

import android.content.Context;
import android.widget.Toast;


public class BToastHelper {
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
