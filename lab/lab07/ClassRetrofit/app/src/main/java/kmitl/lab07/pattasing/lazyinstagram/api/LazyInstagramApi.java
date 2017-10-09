package kmitl.lab07.pattasing.lazyinstagram.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LazyInstagramApi {

    String BASE_URL = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
//    Call<String> getProfile(@Query("user") String userName); //return เป็น String (Scalar)
    Call<UserProfile> getProfile(@Query("user") String userName);
}
