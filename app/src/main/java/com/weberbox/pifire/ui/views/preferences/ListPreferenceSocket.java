package com.weberbox.pifire.ui.views.preferences;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.ListPreference;

import com.weberbox.pifire.R;
import com.weberbox.pifire.application.PiFireApplication;
import com.weberbox.pifire.utils.AlertUtils;

import io.socket.client.Socket;

@SuppressWarnings("unused")
public class ListPreferenceSocket extends ListPreference {

    private Socket mSocket;
    private Context mContext;

    public ListPreferenceSocket(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public ListPreferenceSocket(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ListPreferenceSocket(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ListPreferenceSocket(Context context) {
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
            AlertUtils.createErrorAlert((Activity) mContext, R.string.settings_error_offline, false);
        }
    }
}