package com.example.gameshield;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.gameshield.databinding.ActivityMainBinding;

import gameshield.Gameshield;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

//    static {
//        System.loadLibrary("gameshield");
//
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        new Thread(){
            @Override
            public void run() {
                //   Gameshield.onSdkInit(); // sdk 初始化
                while(true) {
                    try {
                        String ret = Gameshield.onLibStart();
                        if(ret.length() > 2) {
                            Thread.sleep(100);
                        }
                    } catch (Exception ignored) { }
                }
            }
        }.start();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }


    @Override
    protected void onResume() {
   //  Gameshield.onLibStopListen();
        super.onResume();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}