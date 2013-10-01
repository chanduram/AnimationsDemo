package com.example.animationsdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;

public class TopHeaderGradient extends View{

	
	Context mContext;
	private float mDensity;
	@SuppressLint({ "DrawAllocation", "NewApi" })
	@Override
	protected void onDraw(Canvas canvas) {
		
		Paint p = new Paint();
		canvas.drawCircle(getWidth(), 0, getHeight(), p);
		int radius =getHeight();
		RectF rectf=new RectF();
		rectf.set(getWidth()-100, -100, getWidth()+100, getHeight()+100);
		canvas.drawArc(rectf, -90, 90, true, p);
		
	    
	}
	@SuppressLint("NewApi")
	public TopHeaderGradient(Context context, AttributeSet attrs, int defStyle) {
	
		super(context, attrs, defStyle);
		mContext=context;
		mDensity=getResources().getDisplayMetrics().density;
		ShapeDrawable shapeDrawable=new ShapeDrawable();
		showLog("height in 3rd con "+getHeight());
		
		shapeDrawable.getPaint().setShader(new LinearGradient (0, 0, 0,100, new int[] {  0x55FFFFFF, 0x22FFFFFF}, null, TileMode.CLAMP));
		setBackground(shapeDrawable);
	}
	@SuppressLint("NewApi")
	public TopHeaderGradient(Context context) {
		
		super(context);
		mContext=context;
		mDensity=getResources().getDisplayMetrics().density;
		ShapeDrawable shapeDrawable=new ShapeDrawable();
		
		setBackground(new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,  new int[] {  0x55FFFFFF, 0x22FFFFFF}));
		
	}
	@SuppressLint("NewApi")
	public TopHeaderGradient(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext=context;
		mDensity=getResources().getDisplayMetrics().density;
		ShapeDrawable shapeDrawable=new ShapeDrawable();
		showLog("height in 3rd con "+getHeight());
		shapeDrawable.getPaint().setShader(new LinearGradient (0, 0, 0,100, new int[] {  0x55FFFFFF, 0x22FFFFFF}, null, TileMode.CLAMP));
		setBackground(shapeDrawable);
	}
	private void showLog(String string) {
		Log.e("Topheader", string);
		
	}

	
	
	

}
