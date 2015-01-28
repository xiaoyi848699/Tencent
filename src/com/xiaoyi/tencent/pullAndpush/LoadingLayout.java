package com.xiaoyi.tencent.pullAndpush;


import com.xiaoyi.tencent.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingLayout extends FrameLayout {

	static final int DEFAULT_ROTATION_ANIMATION_DURATION = 150;

	private final ImageView mHeaderImage;
	private final ProgressBar mHeaderProgress;
	private final TextView mHeaderText;

	private String mPullLabel;
	private String mCompleteLabel;
	private String mRefreshingLabel;
	private String mReleaseLabel;
	ViewGroup header;
	private final Animation mRotateAnimation, mResetRotateAnimation;

	public LoadingLayout(Context context, final int mode, String releaseLabel, String pullLabel, String refreshingLabel,String completeLabel) {
		super(context);
		header = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_hind, this);
		mHeaderText = (TextView) header.findViewById(R.id.pull_to_refresh_text);
		mHeaderImage = (ImageView) header.findViewById(R.id.pull_to_refresh_image);
		mHeaderProgress = (ProgressBar) header.findViewById(R.id.pull_to_refresh_progress);

		final Interpolator interpolator = new LinearInterpolator();
		mRotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateAnimation.setInterpolator(interpolator);
		mRotateAnimation.setDuration(DEFAULT_ROTATION_ANIMATION_DURATION);
		mRotateAnimation.setFillAfter(true);

		mResetRotateAnimation = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f,
		        Animation.RELATIVE_TO_SELF, 0.5f);
		mResetRotateAnimation.setInterpolator(interpolator);
		mResetRotateAnimation.setDuration(DEFAULT_ROTATION_ANIMATION_DURATION);
		mResetRotateAnimation.setFillAfter(true);

		mReleaseLabel = releaseLabel;
		mPullLabel = pullLabel;
		mCompleteLabel = completeLabel;
		mRefreshingLabel = refreshingLabel;

		switch (mode) {
			case PullToRefreshBase.MODE_PULL_UP_TO_REFRESH:
                mHeaderImage.setImageResource(R.drawable.erg_up);
				break;
			case PullToRefreshBase.MODE_PULL_DOWN_TO_REFRESH:
            default:
                mHeaderImage.setImageResource(R.drawable.erg_down);
				break;
		}
	} 

	public void reset() {
        mHeaderText.setText(mPullLabel);
		mHeaderImage.setVisibility(View.VISIBLE);
		mHeaderProgress.setVisibility(View.GONE);
	}
	/**
	 * 添加刷新完成(本来想设置为刷新完成，然后在隐藏)
	 */
	public void refreshCompleteReset() {
		//mHeaderText.setText(mCompleteLabel);
		Log.d("TAG", "===================================================");
//		mHeaderImage.setVisibility(View.VISIBLE);
//		mHeaderProgress.setVisibility(View.GONE);
		header.setVisibility(View.GONE);
	}

	public void releaseToRefresh() {
        mHeaderText.setText(mReleaseLabel);
        mHeaderImage.clearAnimation();
		mHeaderImage.startAnimation(mRotateAnimation);
	}

	public void setPullLabel(String pullLabel) {
		mPullLabel = pullLabel;
	}

	public void refreshing() {
        mHeaderText.setText(mRefreshingLabel);
		mHeaderImage.clearAnimation();
		mHeaderImage.setVisibility(View.INVISIBLE);
		mHeaderProgress.setVisibility(View.VISIBLE);
	}

	public void setRefreshingLabel(String refreshingLabel) {
		mRefreshingLabel = refreshingLabel;
	}

	public void setReleaseLabel(String releaseLabel) {
	    mReleaseLabel = releaseLabel;
    }

    public void pullToRefresh() {
		mHeaderText.setText(mPullLabel);
		mHeaderImage.clearAnimation();
		mHeaderImage.startAnimation(mResetRotateAnimation);
    }

    public void setTextColor(int color) {
		mHeaderText.setTextColor(color);
	}

}