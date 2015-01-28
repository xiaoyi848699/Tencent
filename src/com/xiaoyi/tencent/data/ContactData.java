package com.xiaoyi.tencent.data;

import java.util.ArrayList;
import java.util.List;

import com.xiaoyi.tencent.po.ContactInfo;

public class ContactData {

	
	public static List<ContactInfo> getContactData(){
		List<ContactInfo> cd = new ArrayList<ContactInfo>();
		//(String nickName, String headImage, String info, String type, String singOn, String connectionStatus, String time, String remark, String remark2)
		cd.add(new ContactInfo("cappuccino","f1.jpg","[语音通话]已取消，点击重拨","1","唯有心如静水！方能气贯如虹！","1","13:46","",""));
		cd.add(new ContactInfo("腾讯新闻","Tencent.png","演员陈赫承认已离婚否认出轨","2","","1","9:18","",""));
		cd.add(new ContactInfo("→▆▆▆▆▆▆▆▆▆▆√","f4.jpg","[图片]","1","","2","昨天","",""));
		cd.add(new ContactInfo("邓石如","f16.jpg","哈哈。。。","1","","4","昨天","",""));
		cd.add(new ContactInfo("QQ邮箱提醒","email.png","极客学院：xiaoyi848699点击激活你的邮箱地址","2","","3","星期三","",""));
		cd.add(new ContactInfo("李钱","f2.jpg","这样真的号码？","1","","","星期三","",""));
		cd.add(new ContactInfo("QQ团队","qq.png","QQ新版交友秘籍，等你发现~","2","","","星期六","",""));
		cd.add(new ContactInfo("肖远","f5.jpg","是的","1","","","2015-01-16","",""));
		cd.add(new ContactInfo("QQ空间","kongjian.png","→▆▆▆▆▆▆▆▆▆▆√（特别关心。。。）","2","","","2015-01-09","",""));
		cd.add(new ContactInfo("贺琴","f16.jpg","恩恩","1","","","2015-01-06","",""));
		cd.add(new ContactInfo("卓工","f18.jpg","对的，很好！","1","","","2015-01-02","",""));
		return cd;
	}
}
