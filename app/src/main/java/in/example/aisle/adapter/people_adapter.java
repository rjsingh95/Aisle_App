package in.example.aisle.adapter;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;



import com.rohitarya.picasso.facedetection.transformation.FaceCenterCrop;
import com.rohitarya.picasso.facedetection.transformation.core.PicassoFaceDetector;
import com.squareup.picasso.Picasso;

import java.util.List;

import in.example.aisle.R;
import in.example.aisle.modellikes.ProfilesItem;


import static androidx.annotation.Dimension.DP;


public class people_adapter extends RecyclerView
        .Adapter<people_adapter
        .DataObjectHolder> {

    private List<ProfilesItem> list;
    private Context context;

    private static MyClickListener myClickListener;



    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public people_adapter(Context context, List<ProfilesItem> list) {

        PicassoFaceDetector.initialize(context);
        this.context = context;
        this.list = list;
    }



    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hidden_people_layout, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {



        Picasso
                .with(context)
                .load(list.get(position).getAvatar())
                .fit() // use fit() and centerInside() for making it memory efficient.
                .centerInside()
                .transform(new FaceCenterCrop(600, 600,DP)) //in pixels. You can also use FaceCenterCrop(width, height, unit) to provide width, height in DP.
                .into(holder.image);

        holder.title.setText(list.get(position).getFirst_name());



    }

    public void deleteItem(int index) {
        list.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);



    }



    public static class DataObjectHolder extends RecyclerView.ViewHolder
            {





        ImageView image;
        TextView title;


View mview;
        public DataObjectHolder(View itemView) {
            super(itemView);
mview=itemView;


            image =(ImageView) mview.findViewById(R.id.profile_pic);

            title =(TextView) mview.findViewById(R.id.name);



        }



    }
}