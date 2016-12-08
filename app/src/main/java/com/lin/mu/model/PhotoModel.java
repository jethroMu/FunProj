package com.lin.mu.model;

import java.util.List;

/**
 * Created by lin on 2016/8/2.
 */
public class PhotoModel {

    public PageBean pagebean;
    public String ret_code;

    public class PageBean {
        public String allNum;
        public String allPages;
        public String currentPage;
        public String maxResult;
        public List<PictureBody> contentlist;
    }

    public class PictureBody {
        public String ct;// 2016-03-10 04;//12;//06.606,
        public String itemId;// 39889571,
        public String title;// 清纯美女头像壁纸 葵花丛中的女孩,
        public String type;// 4001,
        public String typeName;// 清纯
        public List<PictureImage> list;//图片数组
    }

    public class PictureImage {
        public String big;// http;////image.tianjimedia.com/uploadImages/2014/308/28/968836971LMM.JPEG,
        public String middle;// http;////image.tianjimedia.com/uploadImages/2014/308/28/968836971LMM_680x500.JPEG,
        public String small;// http;////image.tianjimedia.com/uploadImages/2014/308/28/968836971LMM_113.JPEG
    }
}
