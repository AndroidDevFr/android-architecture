package com.android.architecture.example.ui;

import com.android.architecture.example.core.model.GithubUser;
import com.android.architecture.example.core.repository.GithubRepository;
import com.android.architecture.example.mvp.AbstractPresenter;

import javax.inject.Inject;

public class GithubPresenter extends AbstractPresenter<GithubPresenter.View> {

    private final GithubRepository githubRepository;

    @Inject
    public GithubPresenter(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    @Override
    protected void start() {
        githubRepository.getUserProfile("florent37")
                .compose(super.<GithubUser>composeSingle())
                .doOnSubscribe($ -> onView(view -> view.displayLoading(false)))
                .doAfterTerminate(() -> onView(view -> view.displayLoading(false)))
                .subscribe(user -> {
                    onView(view -> view.displayUser(user));
                }, throwable -> {

                });
    }

    public interface View extends AbstractPresenter.View {
        void displayLoading(boolean display);
        void displayUser(GithubUser user);
    }
}
