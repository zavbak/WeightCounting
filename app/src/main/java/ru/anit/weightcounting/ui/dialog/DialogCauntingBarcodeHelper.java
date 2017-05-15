package ru.anit.weightcounting.ui.dialog;


import android.app.AlertDialog;
import android.content.Context;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ru.anit.weightcounting.R;

public class DialogCauntingBarcodeHelper {

    public static   AlertDialog.Builder getDialogText(SettingsDialogBarcode settingsDialogBarcode){

        LayoutInflater li = LayoutInflater.from(settingsDialogBarcode.getContext());
        View view = li.inflate(R.layout.dialog_barcode, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(settingsDialogBarcode.getContext());
        alertDialogBuilder.setView(view);

        //final EditText edDilog = (EditText) view
         //       .findViewById(R.id.editTextDialogUserInput);

        //edDilog.setInputType(InputType.TYPE_CLASS_TEXT);

        //edDilog.setText(defaultStr);
        //edDilog.setSelection(edDilog.getText().length());

        TextView tvBarcode = (TextView) view
               .findViewById(R.id.tvBarcode);
        tvBarcode.setText(settingsDialogBarcode.getBarcode());


        alertDialogBuilder
                .setPositiveButton("OK", (dialog, which) -> settingsDialogBarcode.getCallBackChoicePositive().call("554","54524"))
                .setNegativeButton("Cancel", (dialog, which) -> {
                    settingsDialogBarcode.getCallBackChoiceNegative().call();
                    dialog.cancel();
                })
                .setOnKeyListener((dialog, keyCode, event) -> {

                    switch (keyCode) {
                        case KeyEvent.KEYCODE_ENTER:
                            settingsDialogBarcode.getCallBackChoicePositive().call("635456","65456");
                            dialog.dismiss();
                            break;
                        case KeyEvent.KEYCODE_BACK:
                            settingsDialogBarcode.getCallBackChoiceNegative().call();
                            dialog.dismiss();
                            break;

                    }

                    return false;

                })

                .setMessage(settingsDialogBarcode.getMessage())
                .setCancelable(false);

        return alertDialogBuilder;

    }


    public interface CallBackChoicePositive {
        void call(String siets,String weight);
    }

    public interface CallBackChoiceNegative {
        void call();
    }

    /**
     * Настройки для диалога
     */
    public interface SettingsDialogBarcode{
        Context getContext();
        String getMessage();
        CallBackChoicePositive getCallBackChoicePositive();
        CallBackChoiceNegative getCallBackChoiceNegative();
        String getBarcode();
        String getSeats();
        String getWeight();
    }


}
