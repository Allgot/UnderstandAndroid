package dev_allgot.understand.doitmission_23;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class PaintBoard extends View {
    public boolean changed = false;

    Paint mPaint;

    Canvas cacheCanvas;
    Bitmap cacheBitmap;

    float oldXCoord = -1;
    float oldYCoord = -1;

    Path mPath = new Path();

    float mCurveEndX;
    float mCurveEndY;

    public PaintBoard(Context context) {
        super(context);
        init(context);
    }

    public PaintBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStrokeWidth(3.0F);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(cacheBitmap != null) canvas.drawBitmap(cacheBitmap, 0, 0, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Bitmap cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cacheBitmap);
        cacheCanvas.drawColor(Color.WHITE);

        this.cacheBitmap = cacheBitmap;
        this.cacheCanvas = cacheCanvas;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_UP:
                changed = true;

                Rect rect = touchUp(event, false);
                if(rect != null) invalidate(rect);

                mPath.rewind();

                return true;

            case MotionEvent.ACTION_MOVE:
                rect = touchMove(event);
                if(rect != null) invalidate(rect);

                return true;

            case MotionEvent.ACTION_DOWN:
                rect = touchDown(event);
                if(rect != null) invalidate(rect);

                return true;
        }

        return false;
    }

    private Rect touchUp(MotionEvent event, boolean cancel) {
        Rect rect = processMove(event);

        return rect;
    }

    private Rect touchMove(MotionEvent event) {
        Rect rect = processMove(event);

        return rect;
    }

    private Rect touchDown(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        oldXCoord = x;
        oldYCoord = y;

        Rect mInvalidRect = new Rect();
        mPath.moveTo(x, y);

        mInvalidRect.set((int) x - 10, (int) y - 10, (int) x + 10, (int) y + 10);
        mCurveEndX = x;
        mCurveEndY = y;

        cacheCanvas.drawPath(mPath, mPaint);

        return mInvalidRect;
    }

    private Rect processMove(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        final float dx = Math.abs(x - oldXCoord);
        final float dy = Math.abs(y - oldYCoord);

        Rect mInvalidRect = new Rect();

        mInvalidRect.set((int) mCurveEndX - 10, (int) mCurveEndY - 10, (int) mCurveEndX + 10, (int) mCurveEndY + 10);

        float cX = mCurveEndX = (x + oldXCoord) / 2;
        float cY = mCurveEndY = (y + oldYCoord) / 2;

        mPath.quadTo(oldXCoord, oldYCoord, cX, cY);

        mInvalidRect.union((int) oldXCoord - 10, (int) oldYCoord - 10, (int) oldXCoord + 10, (int) oldYCoord);

        mInvalidRect.union((int) cX - 10, (int) cY - 10, (int) cX + 10, (int) cY + 10);

        oldXCoord = x;
        oldYCoord = y;

        cacheCanvas.drawPath(mPath, mPaint);

        return mInvalidRect;
    }

    public void setCap(String style) {
        if(style.equals("BUTT")) mPaint.setStrokeCap(Paint.Cap.BUTT);
        else if(style.equals("ROUND")) mPaint.setStrokeCap(Paint.Cap.ROUND);
        else if(style.equals("SQUARE")) mPaint.setStrokeCap(Paint.Cap.SQUARE);
    }
}
