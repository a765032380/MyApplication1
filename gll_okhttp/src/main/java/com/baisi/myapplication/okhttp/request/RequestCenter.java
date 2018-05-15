package com.baisi.myapplication.okhttp.request;

import com.baisi.myapplication.okhttp.CommonOkHttpClient;
import com.baisi.myapplication.okhttp.listener.DisposeDataHandle;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.listener.DisposeDownloadListener;

import java.util.Map;

public class RequestCenter {
    /**
     *  带请求头的请求
     * @param url                   请求地址
     * @param params                请求内容
     * @param headers               请求头
     * @param disposeDataListener   请求回调
     * @param clazz                 请求返回数据的实体类
     */
    public static void postRequestAddHeaders(String url, RequestParams params, RequestParams headers,
                                    DisposeDataListener disposeDataListener, Class<?> clazz){
        CommonOkHttpClient.post(CommonRequest.createPostRequest1(url,params,headers)
                ,new DisposeDataHandle(disposeDataListener,clazz));
    }
    /**
     *  不带请求头的请求
     * @param url                   请求地址
     * @param params                请求内容
     * @param disposeDataListener   请求回调
     * @param clazz                 请求返回数据的实体类
     */
    public static void postRequest(String url, Class<?> clazz, RequestParams params,
                                    DisposeDataListener disposeDataListener){
        CommonOkHttpClient.post(CommonRequest.createPostRequest1(url,params,null)
                ,new DisposeDataHandle(disposeDataListener,clazz));
    }

    public static void getRequest1(String url, RequestParams params,RequestParams headers,
                                   DisposeDataListener disposeDataListener, Class<?> clazz){
        CommonOkHttpClient.get(CommonRequest.createGetRequest(url,params,headers)
                ,new DisposeDataHandle(disposeDataListener,clazz));
    }

    /**
     * 上传图片
     * @param url       请求地址
     * @param clazz     请求返回数据的实体类
     * @param map       上传的图片组
     * @param listener  请求回调
     */
    public static void uploadPicturesMap(String url,Class<?> clazz, Map<String, Object> map, DisposeDataListener listener){
        CommonOkHttpClient.uploadImgAndParameter(map,url,new DisposeDataHandle(listener,clazz));
    }

    /**
     *
     * @param url       请求地址
     * @param clazz     请求返回数据的实体类
     * @param params    上传的图片组
     * @param listener  请求回调
     */
    public static void uploadPictures(String url,Class<?> clazz,RequestParams params,DisposeDataListener listener){
        CommonOkHttpClient.uploadPictures2(
                CommonRequest.createMultiPostRequest(url,params),new DisposeDataHandle(listener,clazz));
    }

    /**
     *
     * @param url       请求地址
     * @param map       上传的图片组
     * @param listener  请求回调
     */

    public static void uploadPicturesList(String url,Class mClass, Map<String, Object> map,DisposeDataListener listener){
        CommonOkHttpClient.uploadImgAndParameterList(map,url,new DisposeDataHandle(listener,mClass));
    }
    /**
     * 断开链接
     */
    public static void cancel(){
        CommonOkHttpClient.breakLink();
    }
    /**
     * 应用版本号请求
     *
     * @param listener
     */
    public static void checkVersion(String url,DisposeDataListener listener,Class mClass) {
        RequestCenter.postRequest(url, mClass,
                null, listener);
    }

    /**
     * 下载文件的方法
     * @param url       下载地址
     * @param path      保存文件的路径
     * @param listener  下载的回调
     */
    public static void downloadFile(String url, String path, DisposeDownloadListener listener) {
        CommonOkHttpClient.downloadFile(CommonRequest.createGetRequest(url, null),
                new DisposeDataHandle(listener, path));
    }
























}
