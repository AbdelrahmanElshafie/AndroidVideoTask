package com.shafie.androidvideotask.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.shafie.androidvideotask.fragmants.SeedItemFragment;
import com.shafie.androidvideotask.models.VideoSeed;

import java.util.List;

public class SeedPagerAdapter extends FragmentStateAdapter {

    private final List<VideoSeed> videoList;

    public SeedPagerAdapter(@NonNull Fragment fragment, List<VideoSeed> videoList) {
        super(fragment);
        this.videoList = videoList;
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return SeedItemFragment.newInstance(videoList.get(position));
    }
}
