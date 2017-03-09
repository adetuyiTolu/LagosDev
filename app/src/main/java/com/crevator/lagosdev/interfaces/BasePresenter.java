package com.crevator.lagosdev.interfaces;

public interface BasePresenter<T> {
    T getView();
    void onStart();
}
