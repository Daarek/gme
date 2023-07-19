package com.example.gme;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    private GameView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //я по-эльфийски не понимаю
        Point point = new Point(); //вроде есть способ и полегче, не?
        getWindowManager().getDefaultDisplay().getSize(point);
        gv = new GameView(this, point.x, point.y);
        setContentView(gv);
    }
}