package org.techtown.graphics.custom.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintBoard extends View {

    Canvas mCanvas;
    Bitmap mBitmap;
    Paint mPaint;

    int lastX;
    int lastY;

    public PaintBoard(Context context) {
        super(context);

        init(context);
    }

    public PaintBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.BLACK);

        this.lastX = -1;
        this.lastY = -1;
    }

    // 화면 사이즈가 변하거나 초기 액티비티가 생성될 때 호출
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        Bitmap img = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);
        canvas.drawColor(Color.WHITE);

        mBitmap = img;
        mCanvas = canvas;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mBitmap != null){
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        // 누르거나 눌린 상태로 이동할 때 뷰 위에 선을 그리는 쉬운 방법은 이전 터치시 좌표와 현재 터치 좌표를 잇는것이다.
        // 터치 이벤트는 계속 전달되기 때문에 선을 그려나가면 손가락이 움직이는대로 그려지게 된다.
        int action = event.getAction();

        int X = (int) event.getX();
        int Y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_UP:
                lastX = -1;
                lastY = -1;

                break;
            case MotionEvent.ACTION_DOWN:
                // 손가락으로 누른 상태에서 좌표 값 저장
                if (lastX != -1) {
                    if (X != lastX || Y != lastY) {
                        mCanvas.drawLine(lastX, lastY, X, Y, mPaint);
                    }
                }

                lastX = X;
                lastY = Y;

                break;
            case MotionEvent.ACTION_MOVE:
                // 이전의 좌표값과 현재의 좌표값을 연결해 선을 잇는다.
                if (lastX != -1) {
                    mCanvas.drawLine(lastX, lastY, X, Y, mPaint);
                }

                lastX = X;
                lastY = Y;

                break;
            }
        invalidate();

        return true;
    }
}
