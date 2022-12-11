package com.example.quizzzzi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzzzi.databinding.FragmentQuestionBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class questionFragment extends Fragment {
    FragmentQuestionBinding binding;

    List<Question> questionItems;
    int currentQuestionCount = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQuestionBinding.inflate(inflater, container, false);

        // get questions
        try {
            loadQuestion(save.getTopic(), save.getLevel());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // random dao dap an
        Collections.shuffle(questionItems);
        // nhan cau hoi dau tien
        DisplayQuestion(currentQuestionCount);
        // Chuyen den cau hoi tiep theo
        binding.answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // kiem tra dap an dung
                if(questionItems.get(currentQuestionCount).getAnswer1().equals(questionItems.get(currentQuestionCount).getQuestioAnswer()))
                {
                    HistoryFragment.increaseCorrectCount();
                }
                //load next question neu co
                if(currentQuestionCount < 4){  // 4: chi chon 5 cau hoi
                    currentQuestionCount++;
                    DisplayQuestion(currentQuestionCount);
                }else{
                    //go to result Fragment
//                    ResultFragment resultFragment = new ResultFragment();
//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, resultFragment).addToBackStack(null).commit();
                }
            }
        });

        binding.answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check correct answer
                if(questionItems.get(currentQuestionCount).getAnswer2().equals(questionItems.get(currentQuestionCount).getQuestioAnswer())){
                    HistoryFragment.increaseCorrectCount();
                }

                //load next question neu co
                if(currentQuestionCount < 4){
                    currentQuestionCount++;
                    DisplayQuestion(currentQuestionCount);
                }else{
                    //go to result Fragment
//                    ResultFragment resultFragment = new ResultFragment();
//                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, resultFragment).addToBackStack(null).commit();
                }
            }
        });
        return binding.getRoot();

    }

    public  String loadJSONFromAsset()
            throws JSONException {
        String json = "";
        try{
            InputStream is = getActivity().getAssets().open("questionbank.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }

    //make list with all question
    public  void loadQuestion(String topic, String difficulty) throws JSONException {
        questionItems = new ArrayList<>();
        //load all question to string jsonStr
        String jsonStr = loadJSONFromAsset();

        //load all data into the list
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String to = jsonObj.getString("math");
            JSONArray all = jsonObj.getJSONObject(topic).getJSONArray(difficulty);
            for(int i = 0; i < all.length(); i++){
                JSONObject question = all.getJSONObject(i);

                String questionString = question.getString("question");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String correctString = question.getString("correct");

                questionItems.add(new Question(
                        questionString,
                        answer1String,
                        answer2String,
                        correctString
                ));

            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    private void DisplayQuestion(int number)
    {
        binding.tvQuestion.setText(questionItems.get(number).getQuestionText());
    }

}