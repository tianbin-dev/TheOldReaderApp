package com.tianbin.theoldreaderapp.data.module;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tianbin on 16/10/29.
 */
public class TokenInfo implements Parcelable {

    /**
     * SID : none
     * LSID : none
     * Auth : fV6z7DmpnsqLKdmNrwTb
     */

    @SerializedName("SID")
    private String mSID;
    @SerializedName("LSID")
    private String mLSID;
    @SerializedName("Auth")
    private String mAuth;

    protected TokenInfo(Parcel in) {
        mSID = in.readString();
        mLSID = in.readString();
        mAuth = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSID);
        dest.writeString(mLSID);
        dest.writeString(mAuth);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TokenInfo> CREATOR = new Creator<TokenInfo>() {
        @Override
        public TokenInfo createFromParcel(Parcel in) {
            return new TokenInfo(in);
        }

        @Override
        public TokenInfo[] newArray(int size) {
            return new TokenInfo[size];
        }
    };

    public String getSID() {
        return mSID;
    }

    public void setSID(String sID) {
        mSID = sID;
    }

    public String getLSID() {
        return mLSID;
    }

    public void setLSID(String lSID) {
        mLSID = lSID;
    }

    public String getAuth() {
        return mAuth;
    }

    public void setAuth(String auth) {
        mAuth = auth;
    }
}
