package com.example.smartfridge.business_logic;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class getOut  extends AppCompatDialogFragment {
    private static int temp = 0;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        temp = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Hey friend!")
                .setMessage("Are you sure you want to go out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        temp = 1;
                    }
                });
        return builder.create();
    }
    public static int getTemp()
    {return temp;}
}
