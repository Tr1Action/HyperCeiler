package com.sevtinge.hyperceiler.kurt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sevtinge.hyperceiler.R;

import fan.appcompat.app.Fragment;

public class KurtPageFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeRes(R.style.NavigatorContentTheme);
    }

    @Override
    public View onInflateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewInflated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewInflated(view, savedInstanceState);
        getActionBar().setTitle(com.sevtinge.hyperceiler.core.R.string.app_name);
        if (getChildFragmentManager().findFragmentById(R.id.prefs_container) == null) {
            getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.prefs_container, new KurtFragment())
                .commit();
        }
    }
}
