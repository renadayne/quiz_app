package com.example.quizzzzi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

//import com.example.quizzzzi.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.security.PublicKey;

public class QuizMenu extends AppCompatActivity {

//    ActivityMainBinding binding;
    MediaPlayer mainTheme;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_quiz_menu);
//        replaceFragment(new homeFragment());



        mainTheme = MediaPlayer.create(QuizMenu.this,R.raw.boom);
        // music loop
        if (!mainTheme.isPlaying())
        {
            mainTheme.start();
            mainTheme.setLooping(true);
        }


        View play;
        play = findViewById(R.id.play);
        play.setOnClickListener(view -> {
            rePlaceFragment(new homeFragment());
        });

        View history;
        history = findViewById(R.id.history);
        history.setOnClickListener(view -> {
            rePlaceFragment(new levelFragment());
        });



        NavHostFragment navHostFragment =(NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav,navController);


    }


    private void rePlaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint({"QueryPermissionsNeeded", "NonConstantResourceId"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController Navcontroller = Navigation.findNavController(this, R.id.nav_host_fragment);

        switch (item.getItemId()){
            case R.id.toGameinformation: //id item
                //mo infor
                rePlaceFragment(new inforFragment());
//                Navcontroller.navigate(R.id.inforFragment);//id fragment
                return true;

            case R.id.toFeedback:

                Navcontroller.navigate(R.id.feedbackFragment);
                Intent sendmail = new Intent(Intent.ACTION_SEND);
                sendmail.setType("text/plain");
                sendmail.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"Dohieu825@gmail.com", "huytuduelist@gmail.com"});
                sendmail.putExtra(Intent.EXTRA_SUBJECT,"Góp Ý");

                if(sendmail.resolveActivity(getPackageManager()) != null){
                    startActivity(sendmail);
                }else{
                    Toast.makeText(QuizMenu.this, "DOWNLOAD E-MAIL APPS", Toast.LENGTH_SHORT).show();
                }
                return true;

        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onPause() {
        super.onPause();
        mainTheme.release();
    }



    public void selectLevel(View view){
        if(view.getId()==R.id.easy){
            save.setLevel("easy");
            //chuyen sang frament qusstion
            //Toast.makeText(this, save.getTopic()+" "+save.getLevel(), Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new questionFragment(), "fragment")
                    .addToBackStack(null).commit();
        }
        if(view.getId()==R.id.medium){
            save.setLevel("medium");
            //Toast.makeText(this, save.getTopic()+" "+save.getLevel(), Toast.LENGTH_SHORT).show();
            rePlaceFragment(new questionFragment());
        }
        if(view.getId()==R.id.hard){
            save.setLevel("hard");
            //Toast.makeText(this, save.getTopic()+" "+save.getLevel(), Toast.LENGTH_SHORT).show();
            rePlaceFragment(new questionFragment());
        }
    }

    public void selectAnswer(View view) {
        if(view.getId() == R.id.answer1) {
            save.setAnswer("Answer 1");
            //Toast.makeText(this, save.getTopic()+" "+save.getLevel() + " " +save.getAnswer(), Toast.LENGTH_SHORT).show();
        }
        if(view.getId() == R.id.answer2) {
            save.setAnswer("Answer 2");
            //Toast.makeText(this, save.getTopic()+" "+save.getLevel() + " " +save.getAnswer(), Toast.LENGTH_SHORT).show();

        }

    }

    //tro ve home
    public void goHome(View view){
        if(view.getId() == R.id.go_home){
            homeFragment goHomeFragment = new homeFragment();
            rePlaceFragment(goHomeFragment);
        }
        //reset so cau tra loi dung ve 0
        HistoryFragment.AddHistoryList();

        //history result
        HistoryFragment.getCorrectCount();
        HistoryFragment.resetCorrectCount();

    }

    //chia se thanh tich
    public void shareResult(View view) {
        if(view.getId() == R.id.share_result){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String result = "Tôi đã trả lời đúng " + (HistoryFragment.getCorrectCount() + "/5") + " câu hỏi " + "cấp độ " + save.getLevel() + " ở chủ đề " + save.getTopic();
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            shareIntent.putExtra(Intent.EXTRA_TEXT, result);
            startActivity(Intent.createChooser(shareIntent, null));

        }
    }

}