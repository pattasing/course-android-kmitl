package kmitl.lab07.pattasing.lazyinstagram;

import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;

import kmitl.lab07.pattasing.lazyinstagram.adepter.PostAdapter;
import kmitl.lab07.pattasing.lazyinstagram.api.LazyInstagramApi;
import kmitl.lab07.pattasing.lazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    public static int width;
    public static int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        getUserProfile("android");
        getUserProfile("nature");

        PostAdapter postAdapter = new PostAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
    }

    private void getUserProfile(final String userName){
        OkHttpClient client = new OkHttpClient.Builder().build();
        //สร้าง client ต้องใช้ตัวนี้ ถ้ามา retrofit v3
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LazyInstagramApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()) //ถ้าจะให้เป็น JSon ใช้อันนี้ อันเดิมเป็น Scalar เด้อ เป็น convertor ที่่แปลงจาก Scalar เป็น Gson ใช้กะ android
                .build();


        //นี่คือ client ยังไม่ได้เชื่อมต่อนะ

        LazyInstagramApi lazyInstagramApi = retrofit.create(LazyInstagramApi.class); //นี่เชดื่อมต่อแล้ว นี่เเรียก factory นะ
//        Call<String> call = lazyInstagramApi.getProfile(userName);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//               if(response.isSuccessful())
//               { String result = response.body();
//                TextView textUser = (TextView) findViewById(R.id.textUser);
//                textUser.setText(result);
//               } //success จริงต้องได้ 200 programe เราเปิดมาแล้วต้องเรียก method นี้
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        }); //new Call แล้วก็ Enter จะได้ method มา เชื่อมต่อแล้วนะ //ของ scalar

        Call<UserProfile> call = lazyInstagramApi.getProfile(userName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()) {
                    UserProfile userProfile = response.body();
                    TextView textUser = (TextView) findViewById(R.id.textUser);
                    textUser.setText("@" + userProfile.getUser());

                    ImageView imageProfile = (ImageView) findViewById(R.id.imageProfile);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);

                    TextView textBio = (TextView) findViewById(R.id.textBio);
                    textBio.setText(userProfile.getBio());

                    TextView textPost = (TextView) findViewById(R.id.textPost);
                    textPost.setText("Post\n" + userProfile.getPost());

                    TextView textFollowing = (TextView) findViewById(R.id.textFollowing);
                    textFollowing.setText("Following\n" + userProfile.getFollowing());

                    TextView textFollower = (TextView) findViewById(R.id.textFollower);
                    textFollower.setText("Follower\n" + userProfile.getFollower());

                   //หลัง with ใส่ Activity เด้อ
               }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
}
