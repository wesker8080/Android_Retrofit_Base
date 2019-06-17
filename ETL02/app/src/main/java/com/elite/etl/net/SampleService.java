package com.elite.etl.net;


import com.elite.etl.model.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author: Wesker
 * @Date: 2019-05-15 14:52
 * @Version: 1.0
 */
public interface SampleService {
    String ADDRESS = "http://v.juhe.cn/";
    @GET("toutiao/index")
    Observable<Result> getNews(
            @Query("type") String type,
            @Query("key") String key
    );
}
