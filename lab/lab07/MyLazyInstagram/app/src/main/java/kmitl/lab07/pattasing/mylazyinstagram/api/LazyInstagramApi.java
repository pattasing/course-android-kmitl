package kmitl.lab07.pattasing.mylazyinstagram.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by patta on 18/10/2560.
 */

public interface LazyInstagramApi {
    String BASE_URL = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
//    Call<String> getProfile(@Query("user") String userName); //return เป็น String (Scalar)
    Call<UserProfile> getProfile(@Query("user") String userName);
}
