package ru.anit.weightcounting.mvp.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.anit.weightcounting.mvp.views.MainActivityView;


@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    public void showDtailProducrion(String id){
        getViewState().showDetailProduct(id);
    }
}
