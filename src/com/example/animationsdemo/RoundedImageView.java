package com.example.animationsdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundedImageView extends ImageView{

	@Override
	protected void onDraw(Canvas canvas) {


		BitmapDrawable drawable=(BitmapDrawable) getDrawable();
		if(drawable==null) return;
		if(getWidth()==0||getHeight()==0) return;
		Bitmap bitmap=drawable.getBitmap();
		bitmap=bitmap.copy(Bitmap.Config.ARGB_8888, true);
		    int w = getWidth(), h = getHeight();


		    Bitmap roundBitmap =  getRoundBitmap(bitmap, w);
		    canvas.drawBitmap(roundBitmap, 0,0, null);
		
	}
	private Bitmap getRoundBitmap(Bitmap src,int radius){
		
		Bitmap sbmp;
		if(src.getWidth()!=radius||src.getHeight()!=radius)
			sbmp=Bitmap.createScaledBitmap(src,radius, radius, false);
		else
			sbmp=src;
		Bitmap output=Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas=new Canvas(output);
		Paint paint=new Paint();
		Rect rect=new Rect(0,0, sbmp.getWidth(), sbmp.getHeight());
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(Color.parseColor("#BAB399"));
		canvas.drawCircle(sbmp.getWidth() / 2+0.7f, sbmp.getHeight() / 2+0.7f,
	            sbmp.getWidth() / 2+0.1f, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(sbmp, rect, rect, paint);
		return sbmp;
	}

	private Context mContext;
	
	public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext=context;
		
	}

	public RoundedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext=context;
	}

	public RoundedImageView(Context context) {
		super(context);
		mContext=context;
	}
	

	
}
