package com.example.animationsdemo;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class AnimationCloning extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_cloning);
		final MyAnimation animview=new MyAnimation(AnimationCloning.this);
		((LinearLayout)findViewById(R.id.linrlyt)).addView(animview);
		((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				animview.startAnimation();
			}
		});
	}

	

}
