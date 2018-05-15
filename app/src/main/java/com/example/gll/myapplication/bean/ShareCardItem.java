package com.example.gll.myapplication.bean;

import java.util.List;

/**
 * @author SoBan
 * @create 2017/4/13 11:20.
 */

public class ShareCardItem {

    private ZSCardItem data;
    private List<LRCardItem> dataList;

    public ZSCardItem getData() {
        return data;
    }

    public void setData(ZSCardItem data) {
        this.data = data;
    }

    public List<LRCardItem> getDataList() {
        return dataList;
    }

    public void setDataList(List<LRCardItem> dataList) {
        this.dataList = dataList;
    }

    public static class ZSCardItem {
        private double todayIndex;
        private int amount;
        private int shopNum;
        private int memberNum;
        private String indexDate;

        public double getTodayIndex() {
            return todayIndex;
        }

        public void setTodayIndex(double todayIndex) {
            this.todayIndex = todayIndex;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getShopNum() {
            return shopNum;
        }

        public void setShopNum(int shopNum) {
            this.shopNum = shopNum;
        }

        public int getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(int memberNum) {
            this.memberNum = memberNum;
        }

        public String getIndexDate() {
            return indexDate;
        }

        public void setIndexDate(String indexDate) {
            this.indexDate = indexDate;
        }
    }

    public static class LRCardItem {

        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
