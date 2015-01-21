package com.xiaoyi.tencent.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;
import com.xiaoyi.tencent.R;

public class SlidingMenu extends HorizontalScrollView
{
	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;

	private int mMenuWidth;
	// dp
	private int mMenuRightPadding = 50;

	private boolean once;

	public boolean isLeftOpen;
	public boolean isRightOpen;
	

	Context context;

	/**
	 * 未使用自定义属性时，调用
	 * 
	 * @param context
	 * @param attrs
	 */
	public SlidingMenu(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
		this.context=context;
	}

	/**
	 * 当使用了自定义属性时，会调用此构造方法
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);

		this.context=context;
		// 获取我们定义的属性
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyle, 0);

		int n = a.getIndexCount();
		for (int i = 0; i < n; i++)
		{
			int attr = a.getIndex(i);
			switch (attr)
			{
			case R.styleable.SlidingMenu_rightPadding:
				mMenuRightPadding = a.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50, context
										.getResources().getDisplayMetrics()));
				break;
			}
		}
		a.recycle();

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		Log.d("TAG", "slidingMenu");
	}

	public SlidingMenu(Context context)
	{
		this(context, null);
		this.context=context;
	}

	/**
	 * 设置子View的宽和高 设置自己的宽和高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		if (!once)
		{
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);
			mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth
					- mMenuRightPadding;
			mContent.getLayoutParams().width = mScreenWidth;
			once = true;
			mContent.setClickable(true);
			mContent.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Log.d("TAG", "setOnClickListener");
					if (isRightOpen)
					{
						closeRightMenu();
					} else if(isLeftOpen)
					{
						closeMenuLeft();
					}
					
				}
			});
		}
		Log.d("TAG", "onMeasure");
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	/**
	 * 通过设置偏移量，将menu隐藏
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		super.onLayout(changed, l, t, r, b);
		if (changed)
		{
			this.scrollTo(mMenuWidth, 0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		Log.e("TAG", "onTouchEvent  ");
		int action = ev.getAction();
		switch (action)
		{
		case MotionEvent.ACTION_UP:
			// 隐藏在左边的宽度
			int scrollX = getScrollX();
			if (scrollX >= mMenuWidth / 2&&scrollX<=mMenuWidth)
			{
				this.smoothScrollTo(mMenuWidth, 0);
				isLeftOpen = false;
			} else if(scrollX<mMenuWidth / 2){
				this.smoothScrollTo(0, 0);
				isLeftOpen = true;
			}else if(scrollX>=mMenuWidth+30){
				this.smoothScrollTo(mMenuWidth*2, 0);
				isRightOpen = true;
			}else{
				this.smoothScrollTo(mMenuWidth, 0);
				isRightOpen = false;
			}
			return true;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 打开左边菜单
	 */
	public void openMenuLeft()
	{
		if (isLeftOpen)
			return;
		this.smoothScrollTo(0, 0);
		isLeftOpen = true;
	}
	/**
	 * 打开右边菜单
	 */
	public void openRightMenu()
	{
		if (isRightOpen)
			return;
		this.smoothScrollTo(mMenuWidth*2, 0);
		isRightOpen = true;
	}

	public void closeMenuLeft()
	{
		if (!isLeftOpen)
			return;
		this.smoothScrollTo(mMenuWidth, 0);
		isLeftOpen = false;
	}
	/**
	 * 关闭右边菜单
	 */
	public void closeRightMenu()
	{
		if (!isRightOpen)
			return;
		this.smoothScrollTo(mMenuWidth, 0);
		isRightOpen = false;
	}

	/**
	 * 切换菜单左边
	 */
	public void toggleLeft()
	{
		if (isLeftOpen)
		{
			closeMenuLeft();
		} else
		{
			openMenuLeft();
		}
	}
	/**
	 * 切换菜单右边
	 */
	public void toggleRight()
	{
		if (isRightOpen)
		{
			closeRightMenu();
		} else
		{
			openRightMenu();
		}
	}
	/**
	 * 关闭菜单
	 */
	public void closeMune()
	{
		if (isRightOpen)
		{
			Toast.makeText(context, "isRightOpen关闭菜单", Toast.LENGTH_LONG).show();
			closeRightMenu();
		} else if(isLeftOpen)
		{
			Toast.makeText(context, "isLeftOpen关闭菜单", Toast.LENGTH_LONG).show();
			closeMenuLeft();
		}
	}

	/**
	 * 滚动发生时
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		super.onScrollChanged(l, t, oldl, oldt);
		if(l<=mMenuWidth){//显示左边菜单
			float scale = l * 1.0f / mMenuWidth; // 1 ~ 0

			/**
			 * 区别1：内容区域1.0~0.7 缩放的效果 scale : 1.0~0.0 0.7 + 0.3 * scale
			 * 
			 * 区别2：菜单的偏移量需要修改
			 * 
			 * 区别3：菜单的显示时有缩放以及透明度变化 缩放：0.7 ~1.0 1.0 - scale * 0.3 透明度 0.6 ~ 1.0 
			 * 0.6+ 0.4 * (1- scale) ;
			 * 
			 */
			float rightScale = 0.7f + 0.3f * scale;
			float leftScale = 1.0f - scale * 0.3f;
			float leftAlpha = 0.6f + 0.4f * (1 - scale);

			// 调用属性动画，设置TranslationX
			ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.8f);
			
			ViewHelper.setScaleX(mMenu, leftScale);
			ViewHelper.setScaleY(mMenu, leftScale);
			ViewHelper.setAlpha(mMenu, leftAlpha);
			// 设置content的缩放的中心点
			ViewHelper.setPivotX(mContent, 0);
			ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
			ViewHelper.setScaleX(mContent, rightScale);
			ViewHelper.setScaleY(mContent, rightScale);
		}else{//显示右边菜单
			float scale =1- (l * 1.0f- mMenuWidth)/ mMenuWidth; // 1 ~ 0
			float contentScale = 0.7f + 0.3f * scale;
			float rightScale = 1.0f - scale * 0.3f;
			float rightAlpha = 0.6f + 0.4f * (1 - scale);
			// 调用属性动画，设置TranslationX
			ViewHelper.setTranslationX(mMenu, -mMenuWidth * scale * 0.8f);
			
			ViewHelper.setScaleX(mMenu, rightScale);
			ViewHelper.setScaleY(mMenu, rightScale);
			ViewHelper.setAlpha(mMenu, rightAlpha);
			// 设置content的缩放的中心点
			ViewHelper.setPivotX(mContent, mContent.getWidth());
			ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
			ViewHelper.setScaleX(mContent, contentScale);
			ViewHelper.setScaleY(mContent, contentScale);
		}
		

	}

}
