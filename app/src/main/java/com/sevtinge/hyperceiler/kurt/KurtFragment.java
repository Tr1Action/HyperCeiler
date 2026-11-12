package com.sevtinge.hyperceiler.kurt;

import com.sevtinge.hyperceiler.R;
import com.sevtinge.hyperceiler.common.base.BasePreferenceFragment;

/**
 * Kurt Lock ana sayfası: Good Lock tarzı kategori kartları.
 * Her kart mevcut HyperCeiler ayar sayfalarından birine gider.
 */
public class KurtFragment extends BasePreferenceFragment {

    @Override
    public int getPreferenceScreenResId() {
        return R.xml.prefs_kurt;
    }
}
