package com.example.animationsdemo;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class AnimatorEvents extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_cloning);
		final MyAnimation animview=new MyAnimation(AnimatorEvents.this);
		animview.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT));
		((LinearLayout)findViewById(R.id.linrlyt)).addView(animview);
		((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				animview.startAnimation();
			}
		});
	}

	
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	private class MyAnimation extends View implements AnimatorUpdateListener,AnimatorListener{

		
		private ShapeHolder ball;
		private ValueAnimator  ballAnim;
		private static final float BALLSIZE = 100f;
		private Context mContext ;
		private ShapeHolder createBall(float x, float y) {
			Display display=getWindowManager().getDefaultDisplay();
			Point size=new Point();
			display.getSize(size);
			

			OvalShape shape=new OvalShape();
			shape.resize(BALLSIZE, BALLSIZE);
			ShapeDrawable shaped=new ShapeDrawable(shape);
			ShapeHolder shapeHolder=new ShapeHolder(shaped);
			shapeHolder.setX(200f*getResources().getDisplayMetrics().density);
			shapeHolder.setY(200f);
			  
	            int red = (int)(Math.random() * 255);
	            int green = (int)(Math.random() * 255);
	            int blue = (int)(Math.random() * 255);
	            int color = 0xff000000 | red << 16 | green << 8 | blue;
	            Paint paint = shaped.getPaint(); //new Paint(Paint.ANTI_ALIAS_FLAG);
	            int darkColor = 0xff000000 | red/4 << 16 | green/4 << 8 | blue/4;
	            RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
	                    50f, color, darkColor, Shader.TileMode.CLAMP);
	            paint.setShader(gradient);
	            shapeHolder.setPaint(paint);
	            return shapeHolder;
			
			
		}
		public MyAnimation(Context context) {
			super(context);
			mContext=context;
			ball=createBall(50f,50f);
			
		}
		
		private void createAnimation() {
			
			ballAnim=ObjectAnimator.ofFloat(ball,"Y",ball.getY(),ball.getY()+200f).setDuration(2000);
			ballAnim.setInterpolator(new AccelerateInterpolator());
			ballAnim.addUpdateListener(this);
			
		}
		public void startAnimation() {
			
			createAnimation();
			ballAnim.start();
		}
		
		@SuppressLint("DrawAllocation")
		@Override
		protected void onDraw(Canvas canvas) {
			canvas.save();
			canvas.translate(ball.getX(), ball.getY());
			ball.getShape().draw(canvas);
			canvas.restore();
			
		}
		@Override
		public void onAnimationCancel(Animator animation) {
			
			
			
		}
		@Override
		public void onAnimationEnd(Animator animation) {
			
			animation.setTarget(ball);
			animation.start();
		}
		@Override
		public void onAnimationRepeat(Animator animation) {
			
		}
		@Override
		public void onAnimationStart(Animator animation) {
			
			
		}
		@Override
		public void onAnimationUpdate(ValueAnimator animation) {
			invalidate();
		}
		
		
		
		
		
	}
}
