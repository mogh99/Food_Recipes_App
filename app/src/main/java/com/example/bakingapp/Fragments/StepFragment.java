package com.example.bakingapp.Fragments;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bakingapp.Data.Step;
import com.example.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepFragment extends Fragment {

    private TextView stepTitle;
    private TextView stepDescription;

    private SimpleExoPlayer exoPlayer;
    private SimpleExoPlayerView exoPlayerView;

    private Step step;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_fragment, container, false);

        exoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.exo_player_view);

        step = (Step) getActivity().getIntent().getSerializableExtra(String.valueOf(R.string.stepsKey));

        stepTitle = (TextView) rootView.findViewById(R.id.tv_step_title);
        stepDescription = (TextView) rootView.findViewById(R.id.tv_step_description);

        updateViews();
        if(!(step.getVideoURL().equals(""))){
            initializePlayer(Uri.parse(step.getVideoURL()));
            checkConfigurationChange();
        }
        else
            exoPlayerView.setVisibility(View.INVISIBLE);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(!(step.getVideoURL().equals("")))
            releasesPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(!(step.getVideoURL().equals("")))
            releasesPlayer();
    }

    public void initializePlayer(Uri videoURI){
        if(exoPlayer == null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();

            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            exoPlayerView.setPlayer(exoPlayer);

            String userAgent = Util.getUserAgent(getContext(), "BakingApp");
            DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(), userAgent);
            DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasesPlayer(){
        exoPlayer.stop();
        exoPlayer.release();
    }

    public void checkConfigurationChange() {
        int currentOrientation = getResources().getConfiguration().orientation;

        if(currentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            hideViews();
            openFullScreen();
        }
        else if(currentOrientation == Configuration.ORIENTATION_PORTRAIT){
            closeFullScreen();
            showViews();
        }
    }

    public void openFullScreen(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) exoPlayerView.getLayoutParams();

        params.width = params.MATCH_PARENT;
        params.height = params.MATCH_PARENT;

        exoPlayerView.setLayoutParams(params);
    }

    public void closeFullScreen(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) exoPlayerView.getLayoutParams();

        params.width = params.MATCH_PARENT;
        params.height = (int) ( 200 * getActivity().getApplicationContext().getResources().getDisplayMetrics().density);

        exoPlayerView.setLayoutParams(params);
    }

    public void updateViews(){
        stepTitle.setText(step.getShortDescription());
        stepDescription.setText(step.getDescription());
    }

    public void hideViews(){
        stepTitle.setVisibility(View.GONE);
        stepDescription.setVisibility(View.GONE);
    }

    public void showViews(){
        stepTitle.setVisibility(View.VISIBLE);
        stepDescription.setVisibility(View.VISIBLE);
    }
}
