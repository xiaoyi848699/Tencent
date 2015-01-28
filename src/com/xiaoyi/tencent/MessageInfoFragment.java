package com.xiaoyi.tencent;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.xiaoyi.tencent.data.ContactData;
import com.xiaoyi.tencent.po.ContactInfo;
import com.xiaoyi.tencent.utils.PhotoUtil;
import com.xiaoyi.tencent.utils.PullToRefreshListView;
import com.xiaoyi.tencent.utils.PullToRefreshListView.OnRefreshListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MessageInfoFragment extends Fragment{
	ImageView small_image_head;
	ImageView small_image_head_bg;
	ImageView call_bg_iv;
	Button msg_info;
	Button call_info;
	ImageButton right_mune;
	ICallListener mCallback;
	PullToRefreshListView  msg_listview;
	List<ContactInfo> cd;
	int muneIsOpen =0;//0表示关闭菜单  1表示打开左边菜单 2表示打开右边菜单
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message_info, null);
		return view;
	}
	 @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 这是为了保证Activity容器实现了用以回调的接口。如果没有，它会抛出一个异常。
        try {
            mCallback = (ICallListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		msg_listview = (PullToRefreshListView) getView().findViewById(R.id.msg_listview);
		small_image_head = (ImageView) getView().findViewById(R.id.small_image_head);
		call_bg_iv = (ImageView) getView().findViewById(R.id.call_bg_iv);
		small_image_head_bg = (ImageView) getView().findViewById(R.id.small_image_head_bg);
		msg_info = (Button) getView().findViewById(R.id.msg_info);
		call_info = (Button) getView().findViewById(R.id.call_info);
		right_mune = (ImageButton) getView().findViewById(R.id.right_mune);
		small_image_head.setImageBitmap(PhotoUtil.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.drawable.f4), 1000));
		msg_info.setBackgroundResource(R.drawable.left_circle_down);
		call_info.setTextColor(getResources().getColor(R.color.main_top_dowm));
		
		msg_info.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				msg_info.setBackgroundResource(R.drawable.left_circle_down);
				call_info.setBackgroundResource(R.drawable.right_circle);
				call_info.setTextColor(getResources().getColor(R.color.main_top_dowm));
				msg_info.setTextColor(getResources().getColor(R.color.white));
				
				changeMainPane(1);
			}
		});
		call_info.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				call_info.setBackgroundResource(R.drawable.right_circle_down);
				msg_info.setBackgroundResource(R.drawable.left_circle);
				call_info.setTextColor(getResources().getColor(R.color.white));
				msg_info.setTextColor(getResources().getColor(R.color.main_top_dowm));
				changeMainPane(2);
			}
		});
		small_image_head.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//打开左边菜单
//				Toast.makeText(getActivity(), "打开左边菜单", Toast.LENGTH_LONG).show();
				mCallback.OpenMune(1);
			}
		});
		right_mune.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//打开右边菜单
//				Toast.makeText(getActivity(), "打开右边菜单", Toast.LENGTH_LONG).show();
				mCallback.OpenMune(2);
			}
		});
		msg_listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(muneIsOpen==1){
					mCallback.OpenMune(1);
				}else if(muneIsOpen==2){
					mCallback.OpenMune(2);
				}else{
					//去聊天界面
				}
				
			}
		});
		msg_listview.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
//				adapter.loadData();
				
				msg_listview.postDelayed(new Runnable() {

					
					@Override
					public void run() {
						msg_listview.onRefreshComplete();
					}
				}, 2000);
			}
		});
		initData();
		
		super.onActivityCreated(savedInstanceState);
	}
	
	private void initData() {
		cd = ContactData.getContactData();
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.msg_headview, null);
		msg_listview.addHeaderView(view);
		msg_listview.setAdapter(new MyAdapter());
		
	}
	/**
	 * 改变主界面显示(消息、通话)
	 * @param i
	 */
	private void changeMainPane(int i) {
		if(i==1){
			msg_listview.setVisibility(View.VISIBLE);
			call_bg_iv.setVisibility(View.GONE);
		}else{
			msg_listview.setVisibility(View.GONE);
			call_bg_iv.setVisibility(View.VISIBLE);
		}
		
	}
	
	/**
	 * fragment直接通信
	 * @author xiaoyi
	 *
	 */
	public interface ICallListener {  
	  /**
	   * 打开菜单
	   * @param direction  1左边  2 右边
	   */
		public void OpenMune(int direction);  
	}

	public void muneChange(int direction, int operate) {
		if(direction==1){//左边菜单变化
			if(operate==1){//打开
				small_image_head.setVisibility(View.GONE);
				small_image_head_bg.setVisibility(View.GONE);
				muneIsOpen = 1;
			}else{//关闭
				small_image_head.setVisibility(View.VISIBLE);
				small_image_head_bg.setVisibility(View.VISIBLE);
				muneIsOpen = 0;
			}
		}else{//右边菜单变化
			if(operate==1){//打开
				right_mune.setImageResource(R.drawable.skin_conversation_right_drawer_btn_selected);
				muneIsOpen = 2;
			}else{//关闭
				right_mune.setImageResource(R.drawable.skin_conversation_right_drawer_btn);
				muneIsOpen = 0;
			}
		}
		
	} 
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return cd.size();
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View contView, ViewGroup arg2) {
			if(contView==null){
				contView = LayoutInflater.from(getActivity()).inflate(R.layout.msg_item, null);
			}
			TextView nick_name = (TextView) contView.findViewById(R.id.nick_name);
			TextView msg_info = (TextView) contView.findViewById(R.id.msg_info);
			TextView time = (TextView) contView.findViewById(R.id.time);
			ImageView renzheng_ic = (ImageView) contView.findViewById(R.id.renzheng_ic);
			ImageView image_head = (ImageView) contView.findViewById(R.id.image_head);
			
			nick_name.setText(cd.get(position).getNickName());
			msg_info.setText(cd.get(position).getInfo());
			time.setText(cd.get(position).getTime());
			if(cd.get(position).getType().equals("1")){
				renzheng_ic.setVisibility(View.GONE);
			}else if(cd.get(position).getType().equals("2")){
				renzheng_ic.setVisibility(View.VISIBLE);
			}else{
				//可以设置其他的图标
			}
			InputStream open = null;
			try {
				open = getActivity().getAssets().open("image/"+cd.get(position).getHeadImage());
			} catch (IOException e) {
				e.printStackTrace();
			}  
			if(open!=null){
				Bitmap bitmap = BitmapFactory.decodeStream(open); 
				image_head.setImageBitmap(PhotoUtil.toRoundCorner(bitmap, 1000));
			}else{
				image_head.setImageBitmap(PhotoUtil.toRoundCorner(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), 1000));
				
			}
			return contView;
		}
		
	}
}
