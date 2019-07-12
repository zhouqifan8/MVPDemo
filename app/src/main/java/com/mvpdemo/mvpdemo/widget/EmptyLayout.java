package com.mvpdemo.mvpdemo.widget;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.mvpdemo.mvpdemo.R;
import com.mvpdemo.mvpdemo.widget.loadingview.LoadingView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 加载 空视图
 */
public class EmptyLayout extends FrameLayout {
    public static final int STATUS_NET_ERROR = 1; //无网络错误
    public static final int STATUS_SERVER_ERROR = 2; //服务器错误
    public static final int STATUS_HIDE = 0; //隐藏
    public static final int STATUS_SHOWLOADING = 3;
    private int mEmptyStatus;
    private OnRetryListener onRetryListener;
    private Context mContext;

    @BindView(R.id.netError_layout)
    FrameLayout netError_layout;
    @BindView(R.id.serverError_layout)
    FrameLayout serverError_layout;
    @BindView(R.id.loadingView)
    LoadingView loadingView;


    public EmptyLayout(@NonNull Context context) {
        this(context, null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    /**
     * 初始化
     */
    private void init(AttributeSet attrs) {
        View.inflate(mContext, R.layout.layout_empty, this);
        ButterKnife.bind(this);
    }

    public interface OnRetryListener {
        void onRetry();
    }


    /**
     * 隐藏视图
     */
    public void hide() {
        mEmptyStatus = STATUS_HIDE;
        _switchEmptyView();
    }

    /**
     * 根据状态设置视图
     */
    public void setEmptyStatus(@EmptyStatus int emptyStatus) {
        mEmptyStatus = emptyStatus;
        _switchEmptyView();
    }

    private void _switchEmptyView() {
        switch (mEmptyStatus) {
            case STATUS_SHOWLOADING:
                setVisibility(VISIBLE);
                netError_layout.setVisibility(GONE);
                serverError_layout.setVisibility(GONE);
                break;
            case STATUS_HIDE:
                setVisibility(GONE);
                loadingView.setVisibility(GONE);
                break;
            case STATUS_NET_ERROR:
                setVisibility(VISIBLE);
                loadingView.setVisibility(GONE);
                serverError_layout.setVisibility(GONE);
                break;
            case STATUS_SERVER_ERROR:
                setVisibility(VISIBLE);
                loadingView.setVisibility(GONE);
                netError_layout.setVisibility(GONE);
                break;
        }
    }

    //设置重试监听
    public void setRetryListener(OnRetryListener onRetryListener) {
        this.onRetryListener = onRetryListener;
    }

    @OnClick({R.id.bt_serverRetry, R.id.bt_netRetry})
    public void OnClick() {
        if (onRetryListener != null) {
            onRetryListener.onRetry();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_HIDE, STATUS_NET_ERROR, STATUS_SERVER_ERROR, STATUS_SHOWLOADING})
    public @interface EmptyStatus {
    }

}
