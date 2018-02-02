package com.android.architecture.example.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.architecture.example.R;
import com.android.architecture.example.SampleApplication;
import com.android.architecture.example.core.model.GithubUser;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubFragment extends Fragment implements GithubPresenter.View {

    @Inject
    GithubPresenter presenter;

    @BindView(R.id.progressBar)
    View progressBar;

    @BindView(R.id.userName)
    TextView userName;

    @BindView(R.id.userAvatar)
    ImageView userAvatar;

    public static GithubFragment newInstance() {
        final Bundle args = new Bundle();
        final GithubFragment fragment = new GithubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        SampleApplication.component(getContext()).inject(this);
        presenter.bind(this, this);
    }

    @Override
    public void displayLoading(boolean display) {
        progressBar.setVisibility(display ? View.VISIBLE : View.GONE);
    }

    @Override
    public void displayUser(GithubUser user) {
        userName.setVisibility(View.VISIBLE);
        userName.setText(user.getName());

        userAvatar.setVisibility(View.VISIBLE);
        Glide.with(getContext()).asBitmap().load(user.getAvatarUrl()).into(userAvatar);
    }
}
