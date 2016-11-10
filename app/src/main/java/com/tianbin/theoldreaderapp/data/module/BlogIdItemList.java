package com.tianbin.theoldreaderapp.data.module;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * content item list
 * Created by tianbin on 16/11/10.
 */
public class BlogIdItemList {

    @SerializedName("itemRefs")
    private List<BlogIdItem> mItemRefs;

    public List<BlogIdItem> getItemRefs() {
        return mItemRefs;
    }

    public void setItemRefs(List<BlogIdItem> itemRefs) {
        mItemRefs = itemRefs;
    }

    public static class BlogIdItem {
        @SerializedName("id")
        private String mId;
        @SerializedName("timestampUsec")
        private String mTimestampUsec;
        @SerializedName("directStreamIds")
        private List<?> mDirectStreamIds;

        public String getId() {
            return mId;
        }

        public void setId(String id) {
            mId = id;
        }

        public String getTimestampUsec() {
            return mTimestampUsec;
        }

        public void setTimestampUsec(String timestampUsec) {
            mTimestampUsec = timestampUsec;
        }

        public List<?> getDirectStreamIds() {
            return mDirectStreamIds;
        }

        public void setDirectStreamIds(List<?> directStreamIds) {
            mDirectStreamIds = directStreamIds;
        }
    }
}
