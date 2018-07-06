package com.example.gll.myapplication.bean;

import java.util.List;

public class CrowdList {

    /**
     * success : true
     * code : 0
     * msg : OK
     * data : [{"id":1,"title":"斗破苍穹","inputtime":1527264000,"thumb":["http://yingshi.oyaoyin.com/uploads/20180702/69dcd7eea51359b3e306a1772ace842c.jpg"]},{"id":2,"title":"捉妖记3","inputtime":0,"thumb":["http://yingshi.oyaoyin.com/uploads/20180628/05bdd0f2b61c3ef601b91c11dcc0d5f1.gif","https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/whfpf%3D180%2C140%2C50/sign=5fde723ea3c379317d3dd5698df9867d/960a304e251f95caf6e6228ec5177f3e670952f2.jpg"]},{"id":3,"title":"侏罗纪世界3","inputtime":1530583096,"thumb":["http://yingshi.oyaoyin.com/uploads/20180703/0bdcb6167ea0cc0ac56e47037f7448f1.jpg","http://yingshi.oyaoyin.com/uploads/20180703/0bdcb6167ea0cc0ac56e47037f7448f1.jpg","http://yingshi.oyaoyin.com/uploads/20180703/0bdcb6167ea0cc0ac56e47037f7448f1.jpg"]}]
     */

    private boolean success;
    private int code;
    private String msg;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 斗破苍穹
         * inputtime : 1527264000
         * thumb : ["http://yingshi.oyaoyin.com/uploads/20180702/69dcd7eea51359b3e306a1772ace842c.jpg"]
         */

        private int id;
        private String title;
        private int inputtime;
        private List<String> thumb;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getInputtime() {
            return inputtime;
        }

        public void setInputtime(int inputtime) {
            this.inputtime = inputtime;
        }

        public List<String> getThumb() {
            return thumb;
        }

        public void setThumb(List<String> thumb) {
            this.thumb = thumb;
        }
    }
}
