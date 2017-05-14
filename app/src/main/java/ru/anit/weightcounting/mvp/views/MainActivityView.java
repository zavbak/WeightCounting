package ru.anit.weightcounting.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface MainActivityView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showDetailProduct(String id);
}
