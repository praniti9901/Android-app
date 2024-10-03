package my.bookstore.app.adminBookPanel;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import my.bookstore.app.R;

public class AdminProfileFragment extends Fragment {

    Button postBook;
    ConstraintLayout backimg;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_profile,null);
        getActivity().setTitle("Post Book");

        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.bg2, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img2, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img3, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img4, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img5, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img6, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img7, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img8, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.bg3, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img9, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img10, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img11, null), 3000);
        animationDrawable.addFrame(ResourcesCompat.getDrawable(getResources(), R.drawable.img12, null), 3000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        backimg = v.findViewById(R.id.back1);
        backimg.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        postBook =  (Button)v.findViewById(R.id.post_book);

        postBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),admin_postBook.class));
            }
        });

        return v;
    }
}
