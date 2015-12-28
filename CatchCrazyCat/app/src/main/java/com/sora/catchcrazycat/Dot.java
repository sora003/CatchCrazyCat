package com.sora.catchcrazycat;

/**
 * Created by Sora on 2015/12/28.
 */
public class Dot {
    //坐标
    int x,y;
    //状态
    int status;

    //可以通过
    public static final int STATUS_ON = 1;
    //已通过 不可再走
    public static final int STATUS_OFF = 0;
    //当前所在位置
    public static final int STATUS_IN = -1;

    public Dot(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        //创建后处于不可走状态
        status = STATUS_OFF;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }
}
