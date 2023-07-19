package com.example.gme;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BackgroundRender {
    public int x = 0; //тут инкапсуляция сильно мешает
    public  int y = 0;
    private Bitmap bg;

    public BackgroundRender (int width, int height, Resources res) {
        bg = BitmapFactory.decodeResource(res, R.drawable.back);
        bg = Bitmap.createScaledBitmap(bg, width, height, false);
    }

    public Bitmap getBg() {
        return bg;
    }

    public void setBg(Bitmap bg) {
        this.bg = bg;
    }
}
