package com.example.animationsdemo;

import java.util.ArrayList;

import com.example.animationsdemo.R.anim;


import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

@SuppressLint("NewApi")
public class MyAnimation extends View implements ValueAnimator.AnimatorUpdateListener,AnimatorListener{ 

	   public final ArrayList<ShapeHolder> balls = new ArrayList<ShapeHolder>();
       AnimatorSet animationset = null;
       private float mDensity;
       Context context;
       public MyAnimation(Context context) {
           super(context);	
           this.context=context;
           mDensity = getContext().getResources().getDisplayMetrics().density;

           addBall(50f, 25f);
           for(int i=1;i<=10;i++){
        	   addBall(50f*i+50f, 25f);
           }
       }

       private void createAnimation(){
    	   
    	   if(animationset==null){
    		   
    		 
    		   ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.
                       loadAnimator(context, R.animator.object_animator);
               anim.addUpdateListener(this);
               anim.setTarget(balls.get(0));

               ValueAnimator fader = (ValueAnimator) AnimatorInflater.
                       loadAnimator(context, R.animator.animator);
               fader.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                   public void onAnimationUpdate(ValueAnimator animation) {
                       balls.get(1).setAlpha((Float) animation.getAnimatedValue());
                   }
               });

               AnimatorSet seq =
                       (AnimatorSet) AnimatorInflater.loadAnimator(context,
                       R.animator.animator_set);
               seq.setTarget(balls.get(2));

               ObjectAnimator colorizer = (ObjectAnimator) AnimatorInflater.
                       loadAnimator(context, R.animator.color_animator);
               colorizer.setTarget(balls.get(3));

               animationset = new AnimatorSet();
               ((AnimatorSet) animationset).playTogether(anim, fader, seq, colorizer);
    	   }
	
       }
       private void createAnimation1() {
    	   
        if(animationset==null){
        	 ObjectAnimator anim1 = ObjectAnimator.ofFloat(balls.get(0), "y",
                     0f, getHeight() - balls.get(0).getHeight()).setDuration(500);
             ObjectAnimator anim2=(ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.object_animator);
             anim2.setTarget(balls.get(1));
             anim1.addUpdateListener(this);			
             ShapeHolder ball2 = balls.get(2);
             ObjectAnimator animDown = ObjectAnimator.ofFloat(ball2, "y",
                     0f, getHeight() - ball2.getHeight()).setDuration(500);
             animDown.setInterpolator(new AccelerateInterpolator());
             ObjectAnimator animUp = ObjectAnimator.ofFloat(ball2, "y",
                     getHeight() - ball2.getHeight(), 0f).setDuration(500);
             animUp.setInterpolator(new DecelerateInterpolator());
             AnimatorSet s1 = new AnimatorSet();
             s1.playSequentially(animDown, animUp);
             animDown.addUpdateListener(this);
             animUp.addUpdateListener(this);
             AnimatorSet s2 = (AnimatorSet) s1.clone();
             s2.setTarget(balls.get(3));
             animationset = new AnimatorSet();
             animationset.playSequentially(anim1, anim2);
             animationset.playSequentially(s1, s2);
        	
        }
       }

       private ShapeHolder addBall(float x, float y) {
           OvalShape circle = new OvalShape();
           circle.resize(50f * mDensity, 50f * mDensity);
           ShapeDrawable drawable = new ShapeDrawable(circle);
           ShapeHolder shapeHolder = new ShapeHolder(drawable);
           shapeHolder.setX(x - 25f);
           shapeHolder.setY(y - 25f);
           int red = (int)(100 + Math.random() * 155);
           int green = (int)(100 + Math.random() * 155);
           int blue = (int)(100 + Math.random() * 155);
           int color = 0xff000000 | red << 16 | green << 8 | blue;
           Paint paint = drawable.getPaint(); //new Paint(Paint.ANTI_ALIAS_FLAG);
           int darkColor = 0xff000000 | red/4 << 16 | green/4 << 8 | blue/4;
           RadialGradient gradient = new RadialGradient(37.5f, 12.5f,
                   50f, color, darkColor, Shader.TileMode.CLAMP);
           paint.setShader(gradient);
           shapeHolder.setPaint(paint);
           balls.add(shapeHolder);
           return shapeHolder;
       }

       @Override
       protected void onDraw(Canvas canvas) {
           for (int i = 0; i < balls.size(); ++i) {
               ShapeHolder shapeHolder = balls.get(i);
               canvas.translate(shapeHolder.getX(), shapeHolder.getY());
               shapeHolder.getShape().draw(canvas);
               canvas.translate(-shapeHolder.getX(), -shapeHolder.getY());
           }
       }

       public void startAnimation() {
          createAnimation();
          animationset.start();
         
       }

       public void onAnimationUpdate(ValueAnimator animation) {
    	   invalidate();
       }

	@Override
	public void onAnimationCancel(Animator animation) {
		// 
		
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		
	}

	@Override
	public void onAnimationRepeat(Animator animation) {
		
		
	}

	@Override
	public void onAnimationStart(Animator animation) {


		showLog("Animantion start");
		
	}

	private void showLog(String string) {
	
		Log.e("Myanimation",string);
		
	}

}
