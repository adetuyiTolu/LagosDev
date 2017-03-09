package com.crevator.lagosdev.interfaces;

public interface BaseView<T> {
    void showLoading(boolean show);
    void showMessage(String message);
    void setPresenter(T presenter);
}
