package com.example.quizzzzi;

import androidx.annotation.NonNull;
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
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

//import com.example.quizzzzi.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

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


}