package kmitl.lab07.pattasing.lazyinstagram.adepter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import kmitl.lab07.pattasing.lazyinstagram.MainActivity;
import kmitl.lab07.pattasing.lazyinstagram.R;

class Holder extends RecyclerView.ViewHolder {
    public ImageView image;
    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);

    }
} //การจะทำ Recyeler ต้องมี holder ก่อน

public class PostAdapter extends RecyclerView.Adapter<Holder>{
    String[] data = {
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n1.jpg"
            , "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n2.jpg"
            , "https://raw.githubusercontent.com/iangkub/gitdemo/master/nature/n3.jpg"
    };


    private Context context;
    public PostAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(itemView);
        return holder; //หนึ่งเ host มี commemt มี like เราจะเอามาต้องสร้าง Item ่ก่อน
    } //สร้าง Holder

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(data[position]).into(image);
    }

    @Override
    public int getItemCount() {
        return data.length;
    } //บอกจำนวนว่ามีเท่าไหร่
}
