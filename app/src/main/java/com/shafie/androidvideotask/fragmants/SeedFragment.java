package com.shafie.androidvideotask.fragmants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.shafie.androidvideotask.adapters.SeedPagerAdapter;
import com.shafie.androidvideotask.databinding.FragmentSeedBinding;
import com.shafie.androidvideotask.models.VideoSeed;

import java.util.Arrays;
import java.util.List;

public class SeedFragment extends Fragment {

    private FragmentSeedBinding binding;
    private SeedPagerAdapter videoAdapter;

    private final List<VideoSeed> videoList = Arrays.asList(
            new VideoSeed("1", "https://customer-fudf1nd2cvabmz7t.cloudflarestream.com/cc161004f4ccef8dd0007240c0bddf44/downloads/default.mp4", "User 1", "Video 1"),
            new VideoSeed("2", "https://customer-fudf1nd2cvabmz7t.cloudflarestream.com/3a8741b5011848c420662666ba012dce/downloads/default.mp4", "User 2", "Video 2"),
            new VideoSeed("3", "https://customer-fudf1nd2cvabmz7t.cloudflarestream.com/69316d1a5e98e7e257a60edbdab1e015/manifest/video.m3u8", "User 3", "Video 3"),
            new VideoSeed("5", "https://chefshub-videos.s3.me-central-1.amazonaws.com/chefshub-media/download+(6).mp4", "User 5", "Video 5"),
            new VideoSeed("6", "https://chefshub-videos.s3.me-central-1.amazonaws.com/chefshub-media/makloba.mp4", "User 6", "Video 6"),
            new VideoSeed("7", "https://customer-fudf1nd2cvabmz7t.cloudflarestream.com/772fe3de90a8b26e86361cbf7c00a50b/downloads/default.mp4", "User 7", "Video 7"),
            new VideoSeed("8", "https://customer-fudf1nd2cvabmz7t.cloudflarestream.com/1f167b6807c0a46fcf72efd752dfce53/downloads/default.mp4", "User 8", "Video 8")
    );


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSeedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        videoAdapter = new SeedPagerAdapter(this, videoList);

        binding.viewPagerStories.setAdapter(videoAdapter);
        binding.viewPagerStories.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        binding.viewPagerStories.setOffscreenPageLimit(1);

        binding.viewPagerStories.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                managePlayback(position);
            }
        });
    }

    private void managePlayback(int currentPosition) {
        for (int i = 0; i < videoAdapter.getItemCount(); i++) {
            Fragment fragment = getChildFragmentManager().findFragmentByTag("f" + i);
            if (fragment instanceof SeedItemFragment) {
                SeedItemFragment seedFragment = (SeedItemFragment) fragment;
                if (i == currentPosition) {
                    seedFragment.playVideo();
                } else if (i == currentPosition + 1 || i == currentPosition - 1) {
                    seedFragment.hideVideo();
                } else {
                    seedFragment.pauseVideo();
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}
