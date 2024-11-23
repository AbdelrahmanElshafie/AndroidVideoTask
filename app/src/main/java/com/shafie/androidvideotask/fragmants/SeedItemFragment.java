package com.shafie.androidvideotask.fragmants;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.shafie.androidvideotask.databinding.FragmentSeedItemBinding;
import com.shafie.androidvideotask.models.VideoSeed;

public class SeedItemFragment extends Fragment {

    private static final String ARG_VIDEO_URL = "video_url";

    private FragmentSeedItemBinding binding;
    private ExoPlayer player;
    private String videoUrl;

    public static SeedItemFragment newInstance(VideoSeed videoSeed) {
        SeedItemFragment fragment = new SeedItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_VIDEO_URL, videoSeed.getVideoUrl());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSeedItemBinding.inflate(inflater, container, false);
        videoUrl = getArguments() != null ? getArguments().getString(ARG_VIDEO_URL) : null;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializePlayer();
    }

    private void initializePlayer() {
        if (player == null) {
            player = new ExoPlayer.Builder(requireContext()).build();
            binding.videoView.setPlayer(player);
            player.setRepeatMode(ExoPlayer.REPEAT_MODE_ONE);
            player.setPlayWhenReady(false);

            player.addListener(new Player.Listener() {
                @Override
                public void onPlaybackStateChanged(int playbackState) {
                    if (playbackState == ExoPlayer.STATE_READY) {
                        binding.videoView.setVisibility(View.VISIBLE);
                    }
                }
            });

            prepareMedia();
        }
    }

    private void prepareMedia() {
        if (videoUrl != null) {
            MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoUrl));
            player.setMediaItem(mediaItem);
            player.prepare();
            binding.videoView.setVisibility(View.GONE);
        }
    }

    public void playVideo() {
        if (player != null) {
            binding.videoView.setVisibility(View.VISIBLE);
            player.setPlayWhenReady(true);
        }
    }

    public void pauseVideo() {
        if (player != null) {
            player.setPlayWhenReady(false);
        }
    }

    public void hideVideo() {
        binding.videoView.setVisibility(View.GONE);
        pauseVideo();
    }

    @Override
    public void onPause() {
        pauseVideo();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        if (player != null) {
            player.release();
            player = null;
        }
        binding = null;
        super.onDestroyView();
    }
}
