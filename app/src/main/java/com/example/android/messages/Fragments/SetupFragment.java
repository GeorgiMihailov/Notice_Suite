package com.example.android.messages.Fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.messages.Helper.ui.Dialogs;
import com.example.android.messages.MessagesActivity;
import com.example.android.messages.R;
import com.philliphsu.bottomsheetpickers.time.grid.GridTimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by pc on 4/22/2018.
 */

public class SetupFragment extends android.support.v4.app.Fragment implements GridTimePickerDialog.OnTimeSetListener {
    private Unbinder mUnnbinder;
    @BindView(R.id.daily_sync_time_BTN)
    Button mDailySyncBtn;
    @BindView(R.id.daily_sync_TEXT)
    TextView mDailySyncText;
    @BindView(R.id.sync_frequency_BTN)
    Button mSyncFrequency;
    @BindView(R.id.sync_now)
    Button mSyncNowBtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.setup_fragment_layout, null);
        mUnnbinder = ButterKnife.bind(this, view);

        ((MessagesActivity) getActivity()).hideFloatingActionButton();

        mSyncFrequency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.showNumberPickerDialog(getActivity(), "messasage");
            }
        });

        mDailySyncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setmTime();

            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnnbinder.unbind();
        ((MessagesActivity) getActivity()).showFloatingActionButton();
    }

    @Override
    public void onTimeSet(ViewGroup viewGroup, int hourOfDay, int minute) {

        mDailySyncText.setText("Daily sycn time set at : " + hourOfDay + " : " + minute);


    }
    public void setmTime() {
        Calendar now = Calendar.getInstance();

        GridTimePickerDialog grid = new GridTimePickerDialog.Builder(
                this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(getActivity()))
                /* ... Set additional options ... */
                .build();
        grid.show(getFragmentManager(), "");
        grid.setHeaderColor(getResources().getColor(R.color.colorAccent));
        grid.setAccentColor(getResources().getColor(R.color.colorAccent));


    }
}
