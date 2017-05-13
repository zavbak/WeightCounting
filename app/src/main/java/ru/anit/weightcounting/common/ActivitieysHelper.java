package ru.anit.weightcounting.common;

/**
 * Created by user on 13.05.2017.
 */

public class ActivitieysHelper {

    public  static  <T> String getString(T resurs,String defaultStr ){
        if(resurs!= null | ((int)resurs) == 0){
           return  (String) resurs;
        }else{
            return (String) defaultStr;
        }
    }
}
