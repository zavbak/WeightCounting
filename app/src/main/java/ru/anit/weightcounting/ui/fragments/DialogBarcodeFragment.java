package ru.anit.weightcounting.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.anit.weightcounting.R;
import ru.anit.weightcounting.mvp.presenters.DialogBarcodePresenter;
import ru.anit.weightcounting.mvp.views.DialogBarcodeView;
import ru.anit.weightcounting.ui.activityes.DetailProductActivity;

/**
 * Created by Alex on 15.05.2017.
 */

public class DialogBarcodeFragment extends MvpDialogFragment implements DialogBarcodeView {

    @InjectPresenter
    DialogBarcodePresenter mPresenter;

    @BindView(R.id.tv_id)
    TextView tvId;

    @BindView(R.id.tv_initial_barcode)
    TextView tvBarcode;

    private Unbinder unbinder;


    public static DialogBarcodeFragment getIntent(String id, String barcode) {
        DialogBarcodeFragment dlg = new DialogBarcodeFragment();

        Bundle args = new Bundle();
        args.putString("id", id);
        args.putString("Barcode", barcode);
        dlg.setArguments(args);

        return dlg;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);

        mPresenter.setIdandBarcode(getArguments().getString("id"),getArguments().getString("barcode"));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();



        builder.setView(inflater.inflate(R.layout.dialog_barcode, null))
                // Добавляем кнопки
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogBarcodeFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void refresh() {
        tvBarcode.setText(mPresenter.getBarcode());
        tvId.setText(mPresenter.getId());
    }
}
