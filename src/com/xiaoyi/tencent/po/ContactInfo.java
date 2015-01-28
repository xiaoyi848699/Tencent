package com.xiaoyi.tencent.po;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactInfo implements Parcelable {

	private String nickName;
	private String headImage;
	private String info;
	private String type;
	private String singOn;
	private String connectionStatus;// 0.¿Îœﬂ 1.µÁƒ‘ 2.2G 3.3G 4.4G 5.wifi
	private String time;
	private String remark;
	private String remark2;

	public ContactInfo() {
		// TODO Auto-generated constructor stub
	}

	public ContactInfo(String nickName, String headImage, String info,
			String type, String singOn, String connectionStatus, String time,
			String remark, String remark2) {
		super();
		this.nickName = nickName;
		this.headImage = headImage;
		this.info = info;
		this.type = type;
		this.singOn = singOn;
		this.connectionStatus = connectionStatus;
		this.time = time;
		this.remark = remark;
		this.remark2 = remark2;
	}

	@Override
	public String toString() {
		return "ContactInfo [nickName=" + nickName + ", headImage=" + headImage
				+ ", info=" + info + ", type=" + type + ", singOn=" + singOn
				+ ", connectionStatus=" + connectionStatus + ", time=" + time
				+ ", remark=" + remark + ", remark2=" + remark2 + "]";
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSingOn() {
		return singOn;
	}

	public void setSingOn(String singOn) {
		this.singOn = singOn;
	}

	public String getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nickName);
		dest.writeString(headImage);
		dest.writeString(info);
		dest.writeString(type);
		dest.writeString(singOn);
		dest.writeString(connectionStatus);
		dest.writeString(time);
		dest.writeString(remark);
		dest.writeString(remark2);

	}

	public static final Parcelable.Creator<ContactInfo> CREATOR = new Creator<ContactInfo>() {

		@Override
		public ContactInfo[] newArray(int size) {
			return new ContactInfo[size];
		}

		@Override
		public ContactInfo createFromParcel(Parcel source) {
			ContactInfo parcelableContactInfo = new ContactInfo();
			parcelableContactInfo.nickName = source.readString();
			parcelableContactInfo.headImage = source.readString();
			parcelableContactInfo.info = source.readString();
			parcelableContactInfo.type = source.readString();
			parcelableContactInfo.singOn = source.readString();
			parcelableContactInfo.connectionStatus = source.readString();
			parcelableContactInfo.time = source.readString();
			parcelableContactInfo.remark = source.readString();
			parcelableContactInfo.remark2 = source.readString();
			return parcelableContactInfo;
		}
	};

}
