package ru.anit.weightcounting.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface DetailProductView extends MvpView {


   @StateStrategyType(AddToEndSingleStrategy.class)
   void refresh();

   @StateStrategyType(SkipStrategy.class)
   void showDialogName(String defaultStr);
   @StateStrategyType(SkipStrategy.class)
   void showDialogStartPosition(String defaultStr);
   @StateStrategyType(SkipStrategy.class)
   void showDialogFinishPosition(String defaultStr);
   @StateStrategyType(SkipStrategy.class)
   void showDialogCoefficient(String defaultStr);
   @StateStrategyType(SkipStrategy.class)
   void showDialogBarcode(String defaultStr);
   @StateStrategyType(SkipStrategy.class)
   void showListBarcode();




   @StateStrategyType(SkipStrategy.class)
   void registerBarcodeReceiver();
   @StateStrategyType(SkipStrategy.class)
   void unregisterReceiver();
   @StateStrategyType(SkipStrategy.class)
   void exit();

}
