package kmitl.lab07.pattasing.mylazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab07.pattasing.mylazyinstagram.MainActivity;
import kmitl.lab07.pattasing.mylazyinstagram.R;
import kmitl.lab07.pattasing.mylazyinstagram.model.Posts;

/**
 * Created by patta on 18/10/2560.
 */

class Holder extends RecyclerView.ViewHolder {
    public ImageView image;
    public ImageView image2;
    public TextView textComment;
    public TextView textLike;

    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        image2 = itemView.findViewById(R.id.image2);

        textComment = itemView.findViewById(R.id.textComment);
        textLike = itemView.findViewById(R.id.textLike);

    }
} //การจะทำ Recyeler ต้องมี holder ก่อน

public class PostAdapter extends RecyclerView.Adapter<Holder> {

    private List<Posts> posts;
    private Context context;

    public PostAdapter(Context context) {
        this.context = context;
        posts = new ArrayList<>();
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if (MainActivity.numView == 1) {
            View itemView = layoutInflater.inflate(R.layout.post_item_list, null, false);
            Holder holder = new Holder(itemView);
            return holder;
        } else {
            View itemView = layoutInflater.inflate(R.layout.post_item, null, false);
            Holder holder = new Holder(itemView);
            return holder;
        }
        //หนึ่งเ host มี commemt มี like เราจะเอามาต้องสร้าง Item ่ก่อน
    } //สร้าง Holder

    @Override
    public void onBindViewHolder(Holder holder, int position) {


        if (MainActivity.numView == 1) {
            ImageView image = holder.image2;
            String imageUrl = posts.get(position).getUrl();
            Glide.with(context).load(imageUrl).into(image);
        } else {
            ImageView image = holder.image;
            String imageUrl = posts.get(position).getUrl();
            Glide.with(context).load(imageUrl).into(image);
        }

        TextView textComment = holder.textComment;
        textComment.setText(posts.get(position).getComment() + "");

        TextView textLike = holder.textLike;
        textLike.setText(posts.get(position).getLike() + "");
    }

    @Override
    public int getItemCount() {
        return posts.size();
    } //บอกจำนวนว่ามีเท่าไหร่
}
