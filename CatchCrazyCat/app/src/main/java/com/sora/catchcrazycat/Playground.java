package com.sora.catchcrazycat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by Sora on 2015/12/28.
 */
public class Playground extends SurfaceView{

    private static int WIDTH = 50;
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
        canvas.drawColor(Color.LTGRAY);
        Paint paint = new Paint();
        //抗锯齿
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        //添加单元格颜色
        for (int i=0;i<ROW;i++){
            //单元格偏移量
            int offset = 0;
            //偶数行右偏移
            if (i%2 == 1){
                offset = WIDTH/2;
            }
            for (int j=0;j<COL;j++){
                Dot one = getDot(j,i);
                switch (one.getStatus()){
                    case Dot.STATUS_OFF:
                        paint.setColor(0xFFEEEEEE);
                        break;
                    case Dot.STATUS_ON:
                        paint.setColor(0xFFFF9900);
                        break;
                    case Dot.STATUS_IN:
                        paint.setColor(0xFFFF0000);
                        break;
                }
                canvas.drawOval(new RectF(one.getX()*WIDTH+offset,one.getY()*WIDTH,
                        (one.getX()+1)*WIDTH+offset,(one.getY()+1)*WIDTH),paint);
            }
        }
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
            //根据屏幕宽度适配单元格大小
            WIDTH = width/(COL+1);
            redraw();
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
                //定义所有坐标处于可走状态
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
