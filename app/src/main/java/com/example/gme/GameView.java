package com.example.gme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private  BackgroundRender bg1, bg2;
    private int width, height;
    private float ratioX, ratioY;
    private Paint paint;


    public GameView(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;

        ratioX = 1920f / width;
        ratioY = 1080f / height;

        bg1 = bg2 = new BackgroundRender(width, height, getResources());
        bg2.x = width;
        paint = new Paint();
    }

    @Override
    public void run() {
        while(isPlaying){
            update();
            draw();
            sleep();
        }
    }

    public void startThread () {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pauseThread () {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException error) {
            //чё? а ничё!
        }
    }

    public void update () {
        bg1.x =- (int)(10 * ratioX);
        bg2.x =- (int)(10 * ratioX);

        if (bg1.x + bg1.getBg().getWidth() <= 0){ //смотрите, как становится весело и легко без этой злоеб@#*й инкапсуляции
            bg1.x = width;
        }
        if (bg2.x + bg2.getBg().getWidth() <= 0){
            bg2.x = width; //вместо того что-бы проверять, исчез-ли фон, легче проверить дошёл ли другой фон до 0
        }
    }
    public void  sleep () {
        try{thread.sleep(16);} catch (InterruptedException error){/* ._. */}
    }
    public void draw () {
        if (getHolder().getSurface().isValid()){ //обьясните мне, как работает это заклинание?
            Canvas can = getHolder().lockCanvas(); //Canvas can? Yes, he can
            can.drawBitmap(bg1.getBg(), bg1.x, bg1.y, paint);
            can.drawBitmap(bg2.getBg(), bg2.x, bg2.y, paint); //тут инкапсуляция тоже слегка мешает
            getHolder().unlockCanvasAndPost(can);

        }
    }
}
