package lodore.com.lodore.service;

import lodore.com.lodore.Pojo.BlogResponse;
import lodore.com.lodore.Pojo.BrandDetailsResponse;
import lodore.com.lodore.Pojo.BrandInfo;
import lodore.com.lodore.Pojo.Brandresp;
import lodore.com.lodore.Pojo.CartRequest;
import lodore.com.lodore.Pojo.CartResponse;
import lodore.com.lodore.Pojo.FavouriteRequest;
import lodore.com.lodore.Pojo.HomeFragrancePlantResponse;
import lodore.com.lodore.Pojo.PerfumeDetail;
import lodore.com.lodore.Pojo.PerfumeFilterRequest;
import lodore.com.lodore.Pojo.PerfumeRequest;
import lodore.com.lodore.Pojo.PerfumeResponse;
import lodore.com.lodore.Pojo.ProductDetailsResponse;
import lodore.com.lodore.Pojo.RegResult;
import lodore.com.lodore.Pojo.Registerresp;
import lodore.com.lodore.Pojo.SearchRequest;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;


public interface Retrofit_rest {

    @POST("/register")
    Registerresp regUrlEncode(@Body RegResult param);


    @POST("/login")
    Registerresp loginUrlEncode(@Body RegResult param);

    @POST("/update")
    Registerresp updateUrlEncode(@Body RegResult param);

    @POST("/update")
    Registerresp updatepasswordUrlEncode(@Body RegResult param);


    @GET("/brands")
    Brandresp getBrandList();

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/branddetails")
    BrandDetailsResponse getBrandDeatails(@Body BrandInfo param);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/suggestedBrands")
    PerfumeResponse getSuggestPerfume (@Body BrandInfo brandInfo);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/blog")
    BlogResponse getBlogList();

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/priceLowtoHigh")
    PerfumeResponse getPerfumeByPrice();

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/homepage")
    HomeFragrancePlantResponse getPlantList();

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("/perfume")
    PerfumeResponse getPerfumes();

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/productDetails")
    ProductDetailsResponse getProductDetails(@Body PerfumeDetail perfumeDetail);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/cartinsert")
    CartResponse getCartResponse(@Body CartRequest cartRequest);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/cart")
    CartResponse getCart(@Body CartRequest cartRequest);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/updatecart")
    CartResponse updateCart(@Body CartRequest cartRequest);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/deleteCart")
    CartResponse deleteCart(@Body CartRequest cartRequest);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/perfumeFilter")
    PerfumeResponse getFilterPerfume(@Body PerfumeFilterRequest perfumeFilterRequest);

    @POST("/quizFilter")
    PerfumeResponse getQuizFilter(@Body PerfumeFilterRequest perfumeFilterRequest);

    @POST("/searchByPerfume")
    PerfumeResponse getsearchPerfume(@Body SearchRequest searchRequest);

    @POST("/searchByBrand")
    Brandresp getsearchBrand(@Body SearchRequest searchRequest);

    @POST("/favorite")
    CartResponse getFavoriteList(@Body FavouriteRequest favouriteRequest);

    @POST("/insertfavorite")
    CartResponse getFavoriteInsert(@Body FavouriteRequest favouriteRequest);

    @POST("/removefavorite")
    CartResponse removeFavorite(@Body FavouriteRequest favouriteRequest);

    @POST("/Checkfavorite")
    CartResponse checkFavorite(@Body FavouriteRequest favouriteRequest);

}
