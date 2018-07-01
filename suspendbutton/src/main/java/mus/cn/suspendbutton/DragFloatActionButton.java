package mus.cn.suspendbutton;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/**
 * 悬浮拖拽按钮
 * 功能：
 * 1：屏幕中随意拖拽，并且会自动靠左（右）贴壁。
 * 2：添加图片
 * 3：图形可随意调整大小，但结合图片只有两种，一种是大，一中是袖珍。
 * Suspend the drag button
 Function:
 1: drag and drop randomly on the screen, and it will automatically stick to the left (right) wall.
 2: add pictures
 3: the figure can be adjusted at will, but there are only two kinds of combined pictures. One is big and the other is small.
 *@author liuzhitong
 * */
public class DragFloatActionButton extends FloatingActionButton {

    private int parentHeight;
    private int parentWidth;
    private int lastX;
    private int lastY;          //移动后的Y坐标
    private boolean isDrag;     //是否被拖拽

    public DragFloatActionButton(Context context) {
        super(context);
    }

    public DragFloatActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragFloatActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                setPressed(true);
                isDrag=false;
                getParent().requestDisallowInterceptTouchEvent(true);
                lastX=rawX;
                lastY=rawY;
                ViewGroup parent;
                if(getParent()!=null){
                    parent= (ViewGroup) getParent();
                    parentHeight=parent.getHeight();
                    parentWidth=parent.getWidth();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(parentHeight<=0||parentWidth==0){
                    isDrag=false;
                    break;
                }else {
                    isDrag=true;
                }
                int dx=rawX-lastX;
                int dy=rawY-lastY;
                int distance= (int) Math.sqrt(dx*dx+dy*dy);
                if(distance==0){
                    isDrag=false;
                    break;
                }
                float x=getX()+dx;
                float y=getY()+dy;
                x=x<0?0:x>parentWidth-getWidth()?parentWidth-getWidth():x;
                y=getY()<0?0:getY()+getHeight()>parentHeight?parentHeight-getHeight():y;
                setX(x);
                setY(y);
                lastX=rawX;
                lastY=rawY;
                Log.i("aa","isDrag="+isDrag+"getX="+getX()+";getY="+getY()+";parentWidth="+parentWidth);
                break;
            case MotionEvent.ACTION_UP:
                if(!isNotDrag()){
                    setPressed(false);
                    if(rawX>=parentWidth/2){
                        animate().setInterpolator(new DecelerateInterpolator())
                                .setDuration(500)
                                .xBy(parentWidth-getWidth()-getX())
                                .start();
                    }else {
                        ObjectAnimator oa=ObjectAnimator.ofFloat(this,"x",getX(),0);
                        oa.setInterpolator(new DecelerateInterpolator());
                        oa.setDuration(500);
                        oa.start();
                    }
                }
                break;
        }
        return !isNotDrag() || super.onTouchEvent(event);
    }
    private boolean isNotDrag(){
        return !isDrag&&(getX()==0
                ||(getX()==parentWidth-getWidth()));
    }
}