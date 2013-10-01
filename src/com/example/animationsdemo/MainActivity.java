package com.example.animationsdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {

	ImageSwitcher imageSwitcher;
	int val=1;
	int imageids[]={R.drawable.and1,R.drawable.and2,R.drawable.and3,R.drawable.and4,R.drawable.and5};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageSwitcher=(ImageSwitcher) findViewById(R.id.imageSwitcher1);
				imageSwitcher.setFactory(new ViewFactory() {
					
					@Override
					public View makeView() {
						// TODO Auto-generated method stub
						
						ImageView imageView=new ImageView(getApplicationContext());
						
						imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
						imageView.setScaleType(ScaleType.CENTER_CROP);
						imageView.setImageResource(R.drawable.and1);
						return imageView;
					}
				});
			//	imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in_from_left));
				imageSwitcher.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {

					if(val>=5)
						val=1;
					imageSwitcher.setImageResource(imageids[val]);
					val++;
						
					}
				});

				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
