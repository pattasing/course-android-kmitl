package kmitl.lab07.pattasing.mylazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab07.pattasing.mylazyinstagram.adapter.PostAdapter;
import kmitl.lab07.pattasing.mylazyinstagram.api.LazyInstagramApi;
import kmitl.lab07.pattasing.mylazyinstagram.api.UserProfile;
import kmitl.lab07.pattasing.mylazyinstagram.model.Posts;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static kmitl.lab07.pattasing.mylazyinstagram.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    private List<Posts> posts = new ArrayList<>();
    private String username;
    public static int numView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        String[] items = getResources().getStringArray(R.array.ID);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, activity_main, items);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectItem = spinner.getSelectedItem().toString();
                username = selectItem;
                getUserProfile(username);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final ImageView imageList = (ImageView) findViewById(R.id.imageList);
        final ImageView imageGrid = (ImageView) findViewById(R.id.imageGrid);
        imageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numView = 1;
                imageList.setVisibility(View.INVISIBLE);
                imageGrid.setVisibility(View.VISIBLE);
                getUserProfile(username);
            }
        });


        imageGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numView = 0;
                imageGrid.setVisibility(View.INVISIBLE);
                imageList.setVisibility(View.VISIBLE);
                getUserProfile(username);
            }
        });
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

                    posts = userProfile.getPosts();
                    PostAdapter postAdapter = new PostAdapter(MainActivity.this);

                    RecyclerView recyclerView = findViewById(R.id.list);

                    if(numView == 1){
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                    else{
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
                    }

                    postAdapter.setPosts(posts);
                    recyclerView.setAdapter(postAdapter);


                    //หลัง with ใส่ Activity เด้อ
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
}
