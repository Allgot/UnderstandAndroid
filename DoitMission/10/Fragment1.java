package dev_allgot.understand.navigation.doitmission_10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {
    ViewPager2 viewPager;
    ImagePagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        adapter = new ImagePagerAdapter(getChildFragmentManager(), getLifecycle());

        iFragment iFragment1 = new iFragment(R.drawable.image1);
        adapter.addFragment(iFragment1);

        iFragment iFragment2 = new iFragment(R.drawable.image2);
        adapter.addFragment(iFragment2);

        iFragment iFragment3 = new iFragment(R.drawable.image3);
        adapter.addFragment(iFragment3);

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);

        return view;
    }

    static class ImagePagerAdapter extends FragmentStateAdapter {
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();

        public ImagePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }
    }

    public static class iFragment extends Fragment {
        ImageView imageView;
        int rId;

        public iFragment(int rId) {
            this.rId = rId;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.inside_fragment, container, false);
            imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(rId);

            return view;
        }
    }
}