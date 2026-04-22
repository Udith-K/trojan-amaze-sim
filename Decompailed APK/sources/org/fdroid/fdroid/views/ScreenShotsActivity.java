package org.fdroid.fdroid.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.RequestBuilder;
import java.util.List;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.App;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenShotsActivity extends AppCompatActivity {
    private static final String EXTRA_REPO_ID = "EXTRA_REPO_ID";
    private static final String EXTRA_SCREENSHOT_LIST = "EXTRA_SCREENSHOT_LIST";
    private static final String EXTRA_START_POSITION = "EXTRA_START_POSITION";
    private static boolean allowDownload = true;

    static Intent getStartIntent(Context context, long j, List<FileV2> list, int i) {
        Intent intent = new Intent(context, (Class<?>) ScreenShotsActivity.class);
        intent.putExtra(EXTRA_REPO_ID, j);
        intent.putStringArrayListExtra(EXTRA_SCREENSHOT_LIST, Utils.toString(list));
        intent.putExtra(EXTRA_START_POSITION, i);
        return intent;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        FDroidApp fDroidApp = (FDroidApp) getApplication();
        fDroidApp.setSecureWindow(this);
        fDroidApp.applyPureBlackBackgroundInDarkTheme(this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_screenshots);
        long longExtra = getIntent().getLongExtra(EXTRA_REPO_ID, 1L);
        List<FileV2> listFileV2FromStrings = Utils.fileV2FromStrings(getIntent().getStringArrayListExtra(EXTRA_SCREENSHOT_LIST));
        int intExtra = getIntent().getIntExtra(EXTRA_START_POSITION, 0);
        ViewPager viewPager = (ViewPager) findViewById(R.id.screenshot_view_pager);
        viewPager.setAdapter(new ScreenShotPagerAdapter(getSupportFragmentManager(), longExtra, listFileV2FromStrings));
        viewPager.setCurrentItem(intExtra);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        allowDownload = Preferences.get().isOnDemandDownloadAllowed();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        allowDownload = Preferences.get().isBackgroundDownloadAllowed();
    }

    private static class ScreenShotPagerAdapter extends FragmentStatePagerAdapter {
        private final long repoId;
        private final List<FileV2> screenshots;

        ScreenShotPagerAdapter(FragmentManager fragmentManager, long j, List<FileV2> list) {
            super(fragmentManager);
            this.repoId = j;
            this.screenshots = list;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int i) {
            return ScreenShotPageFragment.newInstance(this.repoId, this.screenshots.get(i));
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.screenshots.size();
        }
    }

    public static class ScreenShotPageFragment extends Fragment {
        private static final String ARG_REPO_ID = "ARG_REPO_ID";
        private static final String ARG_SCREENSHOT_URL = "ARG_SCREENSHOT_URL";
        private long repoId;
        private FileV2 screenshot;

        static ScreenShotPageFragment newInstance(long j, FileV2 fileV2) {
            ScreenShotPageFragment screenShotPageFragment = new ScreenShotPageFragment();
            Bundle bundle = new Bundle();
            bundle.putLong(ARG_REPO_ID, j);
            bundle.putString(ARG_SCREENSHOT_URL, fileV2.serialize());
            screenShotPageFragment.setArguments(bundle);
            return screenShotPageFragment;
        }

        @Override // androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.repoId = requireArguments().getLong(ARG_REPO_ID);
            this.screenshot = FileV2.deserialize(requireArguments().getString(ARG_SCREENSHOT_URL));
        }

        @Override // androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View viewInflate = layoutInflater.inflate(R.layout.activity_screenshots_page, viewGroup, false);
            ((RequestBuilder) ((RequestBuilder) ((RequestBuilder) App.loadWithGlide(requireContext(), this.repoId, this.screenshot).onlyRetrieveFromCache(!ScreenShotsActivity.allowDownload)).error(R.drawable.screenshot_placeholder)).fallback(R.drawable.screenshot_placeholder)).into((ImageView) viewInflate.findViewById(R.id.screenshot));
            return viewInflate;
        }
    }

    public static class DepthPageTransformer implements ViewPager.PageTransformer {
        @Override // androidx.viewpager.widget.ViewPager.PageTransformer
        public void transformPage(View view, float f) {
            int width = view.getWidth();
            if (f <= 0.0f) {
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
            } else if (f <= 1.0f) {
                view.setAlpha(1.0f - f);
                view.setTranslationX((width * (-f)) / 2.0f);
            } else {
                view.setAlpha(0.0f);
            }
        }
    }
}
