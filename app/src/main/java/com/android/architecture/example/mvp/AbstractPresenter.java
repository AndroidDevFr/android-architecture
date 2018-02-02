package com.android.architecture.example.mvp;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import florent37.github.com.rxlifecycle.RxLifecycle;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public abstract class AbstractPresenter<V extends AbstractPresenter.View> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private WeakReference<V> viewReference;

    public AbstractPresenter() {
    }

    private void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Nullable
    private V getView() {
        return viewReference.get();
    }

    public void onView(ViewCallback<V> viewCallback) {
        if (viewCallback != null) {
            if (getView() != null) {
                viewCallback.onView(getView());
            }
        }
    }

    @CallSuper
    public void bind(LifecycleOwner lifecycleOwner, V view) {
        unbind();
        this.viewReference = new WeakReference<V>(view);
        RxLifecycle.with(lifecycleOwner)
                .onDestroy()
                .doOnSubscribe(AbstractPresenter.this::addDisposable)
                .subscribe($ -> AbstractPresenter.this.unbind());

        RxLifecycle.with(lifecycleOwner)
                .onStart()
                .distinct() //once
                .doOnSubscribe(AbstractPresenter.this::addDisposable)
                .subscribe($ -> AbstractPresenter.this.start());
    }

    protected abstract void start();

    @CallSuper
    protected void unbind() {
        compositeDisposable.clear();
        if (viewReference != null) {
            viewReference.clear();
        }
    }

    public <R> SingleTransformer<? super R, ? extends R> composeSingle() {
        return (SingleTransformer<R, R>) upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Timber.e(throwable))
                .doOnSubscribe(AbstractPresenter.this::addDisposable);
    }

    public <R> ObservableTransformer<? super R, ? extends R> composeObservable() {
        return (ObservableTransformer<R, R>) upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Timber.e(throwable))
                .doOnSubscribe(AbstractPresenter.this::addDisposable);
    }

    public interface ViewCallback<V> {
        void onView(V view);
    }

    public interface View {

    }

}
