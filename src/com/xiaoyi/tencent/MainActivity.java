package com.xiaoyi.tencent;

import com.xiaoyi.tencent.MessageInfoFragment.ICallListener;
import com.xiaoyi.tencent.utils.PhotoUtil;
import com.xiaoyi.tencent.view.SlidingMenu;
import com.xiaoyi.tencent.view.SlidingMenu.onChangeListener;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener,ICallListener,onChangeListener
{
	private SlidingMenu mLeftMenu;
	private ImageView head_image;//头像
	private ImageView message_info; 
	private ImageView contact_info; 
	private ImageView circle_info; 
	private ImageView sing_on_iv; 
	private LinearLayout fragment_content;
	
	private FragmentManager fm = null;
	private FragmentTransaction ft = null;
	private MessageInfoFragment msgif = null;
	private ContactInfoFragment conif = null;
	private CircleInfoFragment cirif = null;
	int nowPage=1;
	int historyPage=0;
	int muneIsOpen =0;//0表示关闭菜单  1表示打开左边菜单 2表示打开右边菜单
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	
	private void initView() {
		mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
		head_image = (ImageView) findViewById(R.id.head_image);
		message_info = (ImageView) findViewById(R.id.message_info);
		contact_info = (ImageView) findViewById(R.id.contact_info);
		circle_info = (ImageView) findViewById(R.id.circle_info);
		sing_on_iv = (ImageView) findViewById(R.id.sing_on_iv);
		fragment_content = (LinearLayout) findViewById(R.id.fragment_content);
		message_info.setOnClickListener(this);
		contact_info.setOnClickListener(this);
		circle_info.setOnClickListener(this);
		
		head_image.setImageBitmap(PhotoUtil.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.drawable.heads), 1000));
		sing_on_iv.setImageBitmap(PhotoUtil.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.drawable.qq_setting_me_richstatus_icon_none), 1000));
		mLeftMenu.setmChangeListener(this);
		
		fm = getSupportFragmentManager();
		
		msgif = new MessageInfoFragment();
		ft = fm.beginTransaction();
		ft.replace(R.id.fragment_content, msgif);
		
		ft.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.message_info:
			if(muneIsOpen==1){
				mLeftMenu.toggleLeft();
			}else if(muneIsOpen==2){
				mLeftMenu.toggleRight();
			}else{
				nowPage=1;
				initBottomView();
				message_info.setImageDrawable(getResources().getDrawable(
						R.drawable.skin_tab_icon_conversation_selected));
				setAnimations();
				if(msgif==null){
					msgif = new MessageInfoFragment();
				}
				ft.replace(R.id.fragment_content, msgif);
				historyPage=1;
				ft.commit();
			}
			break;
		case R.id.contact_info:
			nowPage=2;
			initBottomView();
			contact_info.setImageDrawable(getResources().getDrawable(
					R.drawable.skin_tab_icon_contact_selected));
			setAnimations();
			if(conif==null){
				conif = new ContactInfoFragment();
			}
			ft.replace(R.id.fragment_content, conif);
			historyPage=2;
			ft.commit();
			break;
		case R.id.circle_info:
			nowPage=3;
			initBottomView();
			circle_info.setImageDrawable(getResources().getDrawable(
					R.drawable.skin_tab_icon_plugin_selected));
			setAnimations();
			if(cirif==null){
				cirif = new CircleInfoFragment();
			}
			ft.replace(R.id.fragment_content, cirif);
			historyPage=4;
			ft.commit();
			break;

		default:
			break;
		}
		
	}

	/**
	 * 重置底部按钮
	 */
	public void initBottomView(){
		message_info.setImageDrawable(getResources().getDrawable(
				R.drawable.skin_tab_icon_conversation_normal));
		contact_info.setImageDrawable(getResources().getDrawable(
				R.drawable.skin_tab_icon_contact_normal));
		circle_info.setImageDrawable(getResources().getDrawable(
				R.drawable.skin_tab_icon_plugin_normal));
		ft = fm.beginTransaction();
	}
	/**
	 * 设置切换动画
	 */
	public void setAnimations(){
		if(nowPage>historyPage){
			ft.setCustomAnimations(R.anim.in_from_right_200,  
	                R.anim.out_to_left_200); 
		}else if(nowPage<historyPage){
			ft.setCustomAnimations(R.anim.in_from_left_200,  
	                R.anim.out_to_rigth_200); 
		}else{
			ft.setCustomAnimations(0,  
	               0); 
		}
	}

	@Override
	public void OpenMune(int direction) {
		if(direction==1){
			mLeftMenu.toggleLeft();
		}else{
			mLeftMenu.toggleRight();
		}
		
	}

	@Override
	public void openLeft() {
		muneIsOpen = 1;
		if(msgif!=null){
			msgif.muneChange(1, 1);
		}
	}

	@Override
	public void openRight() {
		muneIsOpen = 2;
		if(msgif!=null){
			msgif.muneChange(2, 1);
		}
	}

	@Override
	public void closeLeft() {
		muneIsOpen = 0;
		if(msgif!=null){
			msgif.muneChange(1, 0);
		}
	}

	@Override
	public void closeRight() {
		muneIsOpen = 0;
		if(msgif!=null){
			msgif.muneChange(2, 0);
		}
	}
	
}
