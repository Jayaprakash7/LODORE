package lodore.com.lodore.service;

import lodore.com.lodore.Pojo.BlogResponse;
import lodore.com.lodore.Pojo.BrandDetailsResponse;
import lodore.com.lodore.Pojo.BrandInfo;
import lodore.com.lodore.Pojo.Brandresp;
import lodore.com.lodore.Pojo.HomeFragrancePlantResponse;
import lodore.com.lodore.Pojo.RegResult;
import lodore.com.lodore.Pojo.Registerresp;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by w7 on 05-Apr-17.
 */

public interface Retrofit_rest {

    @POST("/customer")
    Registerresp regUrlEncode(@Body RegResult param);


    @POST("/customer/login")
    Registerresp loginUrlEncode(@Body RegResult param);

    @POST("/customer/update")
    Registerresp updateUrlEncode(@Body RegResult param);

    @POST("/customer/update")
    Registerresp updatepasswordUrlEncode(@Body RegResult param);

    //@Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/category")
    Brandresp getBrandList();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("/category/subcategory")
    BrandDetailsResponse getBrandDeatails(@Body BrandInfo param);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/blog")
    BlogResponse getBlogList();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("/banner")
    HomeFragrancePlantResponse getPlantList();


}
