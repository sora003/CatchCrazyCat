package com.sora.catchcrazycat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Sora on 2015/12/28.
 */
public class Playground extends SurfaceView{


    public Playground(Context context) {
        super(context);
        getHolder().addCallback(callback);
    }

    //绘制界面
    private void redraw(){
        //锁定SurfaceView对象 获取该SurfaceView上的Canvas
        Canvas canvas = getHolder().lockCanvas();
        //设置背景颜色
        canvas.drawColor(Color.CYAN);
        //取消Canvas的锁定 更新界面
        getHolder().unlockCanvasAndPost(canvas);
    }

    SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            redraw();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };
}
