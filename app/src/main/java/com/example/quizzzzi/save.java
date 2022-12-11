package com.example.quizzzzi;

import android.view.View;

// class luu lai trang thai sau khi chon topic vs do kho
public class save {
    public String level ="";
    public String topic ="";
    public String answer = "";
    public int image = 0;
    public save(){
    }

    public static save current = new save();
    public static void setImage(int _image){
        current.image =_image;
    }
    public static void setLevel(String _level){
        current.level = _level;
    }
    public static void setTopic(String _topic){
        current.topic =_topic;
    }
    public static void setAnswer(String _answer){
        current.answer = _answer;
    }
    public static String getLevel(){
        return current.level;
    }
    public static String getTopic(){
        return current.topic;
    }
    public static String getAnswer(){
        return  current.answer;
    }


    public static int getImage(){
        return current.image;
    }

}