package in.example.aisle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.rohitarya.picasso.facedetection.transformation.FaceCenterCrop;
import com.rohitarya.picasso.facedetection.transformation.core.PicassoFaceDetector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.example.aisle.MainActivity;
import in.example.aisle.R;
import in.example.aisle.adapter.people_adapter;
import in.example.aisle.model.PhotosItem;
import in.example.aisle.model.ProfileModel;
import in.example.aisle.modellikes.ProfilesItem;

import static androidx.annotation.Dimension.DP;

public class DiscoverFragment extends Fragment {

    private RecyclerView rv2;
    private people_adapter peopleAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
  private   ArrayList<ProfilesItem> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_discover, container, false);

        ImageView profile=(ImageView)root.findViewById(R.id.profile_pic) ;
        TextView name_age=(TextView)root.findViewById(R.id.name_age) ;
        TextView notes=(TextView)root.findViewById(R.id.notes) ;
        LottieAnimationView loader=(LottieAnimationView) root.findViewById(R.id.progress);
        NestedScrollView layout=(NestedScrollView)root.findViewById(R.id.layout) ;




        rv2 = (RecyclerView) root.findViewById(R.id.rv_people);

        setRecyclerView(rv2);
        PicassoFaceDetector.initialize(requireContext());

        MainActivity.homeViewModel.peoplelist.observe(requireActivity(), profilelist -> {

            if (profilelist!=null ) {
                loader.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.VISIBLE);

                for (ProfileModel pos : profilelist)

                {
                    name_age.setText(pos.getGeneral_information().getFirst_name()+", "+pos.getGeneral_information().getAge()

                   );
                    for (PhotosItem photo : pos.getPhotos())
                {
                    Picasso
                            .with(requireContext())
                            .load(photo.getPhoto())
                            .fit()
                            .centerInside()
                            .transform(new FaceCenterCrop(600, 600,DP))
                            .into(profile);
                }
                }
            }
        });
        MainActivity.homeViewModel.likes.observe(requireActivity(), Likeslist -> {

            if (Likeslist!=null ) {
               list.addAll(Likeslist.getProfiles());
                peopleAdapter.notifyDataSetChanged();
            }
        });
        return root;
    }
    private void setRecyclerView(RecyclerView rv2) {
        GridLayoutManager mLayoutManager = new GridLayoutManager(requireContext(), 2);
        rv2.setLayoutManager(mLayoutManager);


         peopleAdapter = new people_adapter(requireContext(), list);
        rv2.setAdapter(peopleAdapter);


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PicassoFaceDetector.releaseDetector();
    }
}