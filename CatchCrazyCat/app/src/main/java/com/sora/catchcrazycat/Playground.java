package com.sora.catchcrazycat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by Sora on 2015/12/28.
 */
public class Playground extends SurfaceView{

    private static final int ROW = 10;
    private static final int COL = 10;
    //默认添加的路障数量
    private static final int BLOCKS = 10;
    private static final int initX = 4;
    private static final int initY = 5;

    private Dot matrix[][];
    private Dot cat;

    public Playground(Context context) {
        super(context);
        getHolder().addCallback(callback);
        matrix= new Dot[ROW][COL];
        for (int i=0;i<ROW;i++){
            for (int j=0;j<COL;j++){
                matrix[i][j] = new Dot(j,i);
            }
        }
        //初始化游戏元素
        initGame();
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

    //初始化游戏元素
    private void initGame(){
        //坐标初始化
        for (int i=0;i<ROW;i++){
            for (int j=0;j<COL;j++){
                matrix[i][j].setStatus(Dot.STATUS_OFF);
            }
        }
        //设置猫的位置
        cat = new Dot(initX,initY);
        getDot(initX,initY).setStatus(Dot.STATUS_IN);
        //设置路障位置
        for(int i=0;i<BLOCKS;){
            //随机获取路障坐标
            int x = (int) ((Math.random()*1000)%COL);
            int y = (int) ((Math.random()*1000)%ROW);
            //确保路障的设置不重复
            if (getDot(x,y).getStatus() == Dot.STATUS_OFF){
                getDot(x,y).setStatus(Dot.STATUS_ON);
                i++;
//                System.out.println(x+"      "+y);
            }
        }
    }

    //根据坐标获取Dot对象
    private Dot getDot(int x,int  y){
        return matrix[y][x];
    }
}
