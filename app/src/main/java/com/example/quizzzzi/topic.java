package com.example.quizzzzi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class topic implements Serializable {
    String heading;
    int titleImange;
    public topic(String heading, int titleImange) {
        this.heading = heading;
        this.titleImange = titleImange;
}
    public static ArrayList<topic> list = new ArrayList<>(
            Arrays.asList(
                   new topic("Lịch sử", R.drawable.history),
                    new topic("Văn học", R.drawable.literatue),
                    new topic("Khoa học", R.drawable.science),
                    new topic("Toán học", R.drawable.math)
            )
    );

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getTitleImange() {
        return titleImange;
    }

    public void setTitleImange(int titleImange) {
        this.titleImange = titleImange;
    }
}

