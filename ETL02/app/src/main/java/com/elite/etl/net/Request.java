package com.elite.etl.net;

import com.elite.etl.model.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * 这个类维护了所有的HTTP请求
 *
 * @Author: Wesker
 * @Date: 2019-05-22 10:19
 * @Version: 1.0
 */
public interface Request {
    String HOST = "https://v.juhe.cn/";
    @GET("toutiao/index")
    Observable<Response<Result>> getNews(
            @Query("type") String type,
            @Query("key") String key
    );
}
