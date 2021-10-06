package com.weberbox.pifire.ui.preferences;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;

import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceViewHolder;

import com.google.android.material.snackbar.Snackbar;
import com.weberbox.pifire.R;
import com.weberbox.pifire.application.PiFireApplication;

import io.socket.client.Socket;

public class EditTextPreferenceSocket extends EditTextPreference {

    private Socket mSocket;
    private Context mContext;
    private View mView;

    @SuppressWarnings("unused")
    public EditTextPreferenceSocket(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @SuppressWarnings("unused")
    public EditTextPreferenceSocket(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @SuppressWarnings("unused")
    public EditTextPreferenceSocket(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @SuppressWarnings("unused")
    public EditTextPreferenceSocket(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        PiFireApplication app = (PiFireApplication) context.getApplicationContext();
        mSocket = app.getSocket();
        mContext = context;
    }

    @Override
    protected void onClick() {
        if (mSocket != null && mSocket.connected()) {
            super.onClick();
        } else {
            if (mView != null) {
                showSnackBarMessage(mContext, mView);
            }
        }
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        mView = holder.itemView.getRootView();
    }

    private void showSnackBarMessage(Context context, View view) {
        Snackbar offlineSnack = Snackbar.make(view, R.string.prefs_not_connected, Snackbar.LENGTH_LONG);
        offlineSnack.setBackgroundTintList(ColorStateList.valueOf(context.getColor(
                R.color.colorAccentRed)));
        offlineSnack.show();
    }
}