/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.contacts.preference;

import com.android.contacts.R;

import android.content.Context;
import android.preference.ListPreference;
import android.provider.ContactsContract;
import android.util.AttributeSet;

/**
 * Custom preference: view-name-as (first name first or last name first).
 */
public final class DisplayOrderPreference extends ListPreference {

    private ContactsPreferences mPreferences;
    private Context mContext;

    public DisplayOrderPreference(Context context) {
        super(context);
        prepare();
    }

    public DisplayOrderPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        prepare();
    }

    private void prepare() {
        mContext = getContext();
        mPreferences = new ContactsPreferences(mContext);
        setEntries(new String[]{
                mContext.getString(R.string.display_options_view_given_name_first),
                mContext.getString(R.string.display_options_view_family_name_first),
        });
        setEntryValues(new String[]{
                String.valueOf(ContactsContract.Preferences.DISPLAY_ORDER_PRIMARY),
                String.valueOf(ContactsContract.Preferences.DISPLAY_ORDER_ALTERNATIVE),
        });
        setValue(String.valueOf(mPreferences.getDisplayOrder()));
    }

    @Override
    protected boolean shouldPersist() {
        return false;   // This preference takes care of its own storage
    }

    @Override
    public CharSequence getSummary() {
        switch (mPreferences.getDisplayOrder()) {
            case ContactsContract.Preferences.DISPLAY_ORDER_PRIMARY:
                return mContext.getString(R.string.display_options_view_given_name_first);
            case ContactsContract.Preferences.DISPLAY_ORDER_ALTERNATIVE:
                return mContext.getString(R.string.display_options_view_family_name_first);
        }
        return null;
    }

    @Override
    protected boolean persistString(String value) {
        int newValue = Integer.parseInt(value);
        if (newValue != mPreferences.getDisplayOrder()) {
            mPreferences.setDisplayOrder(newValue);
            notifyChanged();
        }
        return true;
    }
}