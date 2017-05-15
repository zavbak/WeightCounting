package ru.anit.weightcounting.ui.dialog;


import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import ru.anit.weightcounting.R;

public class DialogCauntingBarcodeHelper {

    public static AlertDialog.Builder getDialogText(Context context, String mess, String defaultStr
            , DialogHelper.CallBackChoicePositive callBackChoicePositive, DialogHelper.CallBackChoiceNegative callBackChoiceNegative){

        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.dialog_barcode, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);

        final EditText edDilog = (EditText) view
                .findViewById(R.id.editTextDialogUserInput);

        edDilog.setInputType(InputType.TYPE_CLASS_TEXT);

        edDilog.setText(defaultStr);
        edDilog.setSelection(edDilog.getText().length());


        alertDialogBuilder
                .setPositiveButton("OK", (dialog, which) -> callBackChoicePositive.call(edDilog.getText().toString()))
                .setNegativeButton("Cancel", (dialog, which) -> {
                    callBackChoiceNegative.call();
                    dialog.cancel();
                })
                .setOnKeyListener((dialog, keyCode, event) -> {

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            callBackChoicePositive.call(edDilog.getText().toString());
                            dialog.dismiss();
                            break;
                        case KeyEvent.KEYCODE_BACK:
                            callBackChoiceNegative.call();
                            dialog.dismiss();
                            break;

                    }

                    return false;

                })

                .setMessage(mess)
                .setCancelable(false);

        return alertDialogBuilder;

    }


    public interface CallBackChoicePositive {
        void call(String s);
    }

    public interface CallBackChoiceNegative {
        void call();
    }


}
