package com.xiaoyi.tencent.data;

import java.util.ArrayList;
import java.util.List;

import com.xiaoyi.tencent.po.ContactInfo;

public class ContactData {

	
	public static List<ContactInfo> getContactData(){
		List<ContactInfo> cd = new ArrayList<ContactInfo>();
		//(String nickName, String headImage, String info, String type, String singOn, String connectionStatus, String time, String remark, String remark2)
		cd.add(new ContactInfo("cappuccino","f1.jpg","[����ͨ��]��ȡ��������ز�","1","Ψ�����羲ˮ������������磡","1","13:46","",""));
		cd.add(new ContactInfo("��Ѷ����","Tencent.png","��Ա�ºճ����������ϳ���","2","","1","9:18","",""));
		cd.add(new ContactInfo("���}�}�}�}�}�}�}�}�}�}��","f4.jpg","[ͼƬ]","1","","2","����","",""));
		cd.add(new ContactInfo("��ʯ��","f16.jpg","����������","1","","4","����","",""));
		cd.add(new ContactInfo("QQ��������","email.png","����ѧԺ��xiaoyi848699���������������ַ","2","","3","������","",""));
		cd.add(new ContactInfo("��Ǯ","f2.jpg","������ĺ��룿","1","","","������","",""));
		cd.add(new ContactInfo("QQ�Ŷ�","qq.png","QQ�°潻���ؼ������㷢��~","2","","","������","",""));
		cd.add(new ContactInfo("ФԶ","f5.jpg","�ǵ�","1","","","2015-01-16","",""));
		cd.add(new ContactInfo("QQ�ռ�","kongjian.png","���}�}�}�}�}�}�}�}�}�}�̣��ر���ġ�������","2","","","2015-01-09","",""));
		cd.add(new ContactInfo("����","f16.jpg","����","1","","","2015-01-06","",""));
		cd.add(new ContactInfo("׿��","f18.jpg","�Եģ��ܺã�","1","","","2015-01-02","",""));
		return cd;
	}
}
