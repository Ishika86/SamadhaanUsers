package com.cricmads.samadhaanusers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ApplicationAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public ApplicationAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new PetitionerFragment();
        } else if (i == 1) {
            return new RespondentFragment();
        } else {
            return new UploadsFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.petitioner);
        } else if (position == 1) {
            return mContext.getString(R.string.respondent);
        } else {
            return mContext.getString(R.string.uploads);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
