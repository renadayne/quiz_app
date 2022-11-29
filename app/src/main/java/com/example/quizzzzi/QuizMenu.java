package com.example.quizzzzi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

//import com.example.quizzzzi.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class QuizMenu extends AppCompatActivity {

//    ActivityMainBinding binding;
    MediaPlayer mainTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_quiz_menu);
        mainTheme = MediaPlayer.create(QuizMenu.this,R.raw.boom);
        if (!mainTheme.isPlaying())
        {
            mainTheme.start();
            mainTheme.setLooping(true);
        }


        NavHostFragment navHostFragment =(NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottomNav,navController);
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
                Navcontroller.navigate(R.id.inforFragment);//id fragment
                return true;

            case R.id.toFeedback:

                Navcontroller.navigate(R.id.feedbackFragment);
                Intent sendmail = new Intent(Intent.ACTION_SEND);
                sendmail.setType("text/plain");
                sendmail.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"Dohieu825@gmail.com", "huytuduelist@gmail.com"});
                sendmail.putExtra(Intent.EXTRA_SUBJECT,"Can gop y");

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
}