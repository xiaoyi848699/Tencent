package com.xiaoyi.tencent;

import com.xiaoyi.tencent.utils.PhotoUtil;
import com.xiaoyi.tencent.view.SlidingMenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity
{

	private SlidingMenu mLeftMenu ; 
	private LinearLayout mWapper;
	private ViewGroup mContent;
	private ViewGroup mright;
	
	private int mScreenWidth;
	private boolean isleftOpen;
	
	private ImageView iv_bg1;
	private ImageView iv_bg2;
	private ImageView head_image;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	private void initView() {
		DisplayMetrics outMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
		iv_bg1 = (ImageView) findViewById(R.id.iv_bg1);
		iv_bg2 = (ImageView) findViewById(R.id.iv_bg2);
		head_image = (ImageView) findViewById(R.id.head_image);
		mWapper = (LinearLayout) mLeftMenu.getChildAt(0);
		mContent = (ViewGroup) mWapper.getChildAt(1);
		mright = (ViewGroup) mWapper.getChildAt(2);
		
		head_image.setImageBitmap(PhotoUtil.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.drawable.heads), 1000));
		
	}

	public void toggleMenu(View view)
	{
		if(view.getId()==R.id.btn1){
			mLeftMenu.toggleLeft();
		}else{
			mLeftMenu.toggleRight();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
