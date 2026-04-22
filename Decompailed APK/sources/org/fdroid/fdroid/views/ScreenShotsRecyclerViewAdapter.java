package org.fdroid.fdroid.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.data.App;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: loaded from: classes2.dex */
class ScreenShotsRecyclerViewAdapter extends RecyclerView.Adapter {
    private final RequestOptions displayImageOptions = (RequestOptions) ((RequestOptions) new RequestOptions().fallback(R.drawable.screenshot_placeholder)).error(R.drawable.screenshot_placeholder);
    private final Listener listener;
    private final long repoId;
    private final List<FileV2> screenshots;

    public interface Listener {
        void onScreenshotClick(int i);
    }

    ScreenShotsRecyclerViewAdapter(App app, Listener listener) {
        this.repoId = app.repoId;
        this.listener = listener;
        this.screenshots = app.getAllScreenshots();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ScreenShotViewHolder screenShotViewHolder = (ScreenShotViewHolder) viewHolder;
        App.loadWithGlide(screenShotViewHolder.itemView.getContext(), this.repoId, this.screenshots.get(i)).apply((BaseRequestOptions) this.displayImageOptions).into(screenShotViewHolder.image);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ScreenShotViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.app_details2_screenshot_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.screenshots.size();
    }

    private class ScreenShotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView image;

        ScreenShotViewHolder(View view) {
            super(view);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            this.image = imageView;
            imageView.setOnClickListener(this);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ScreenShotsRecyclerViewAdapter.this.listener.onScreenshotClick(getAdapterPosition());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ViewHolder
        public String toString() {
            return super.toString() + " screenshot";
        }
    }
}
