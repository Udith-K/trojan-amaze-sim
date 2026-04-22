package org.fdroid.fdroid.views.repos;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.os.LocaleListCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.App;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: loaded from: classes2.dex */
public class RepoAdapter extends RecyclerView.Adapter {
    private final List<Repository> items = new ArrayList();
    private final RepoItemListener repoItemListener;

    public interface RepoItemListener {
        void onClicked(Repository repository);

        void onToggleEnabled(Repository repository);
    }

    RepoAdapter(RepoItemListener repoItemListener) {
        this.repoItemListener = repoItemListener;
    }

    Repository getItem(int i) {
        return this.items.get(i);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    void updateItems(List<Repository> list) {
        this.items.clear();
        ListIterator<Repository> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next().isArchiveRepo()) {
                listIterator.remove();
            }
        }
        this.items.addAll(list);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RepoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RepoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repo_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RepoViewHolder repoViewHolder, int i) {
        repoViewHolder.bind(this.items.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    void updateRepoItem(Repository repository) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getRepoId() == repository.getRepoId()) {
                notifyItemChanged(i);
                return;
            }
        }
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {
        private final TextView addressView;
        private final ImageView imageView;
        private final TextView nameView;
        private final View rootView;
        private final CompoundButton switchView;
        private final View unsignedView;
        private final View unverifiedView;

        RepoViewHolder(View view) {
            super(view);
            this.rootView = view;
            this.imageView = (ImageView) view.findViewById(R.id.repo_icon);
            this.switchView = (CompoundButton) view.findViewById(R.id.repo_switch);
            this.nameView = (TextView) view.findViewById(R.id.repo_name);
            this.addressView = (TextView) view.findViewById(R.id.repo_address);
            this.unsignedView = view.findViewById(R.id.repo_unsigned);
            this.unverifiedView = view.findViewById(R.id.repo_unverified);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void bind(final Repository repository) {
            this.rootView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.repos.RepoAdapter$RepoViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$bind$0(repository, view);
                }
            });
            this.switchView.setOnClickListener(null);
            this.switchView.setChecked(repository.getEnabled());
            this.switchView.setOnClickListener(new View.OnClickListener() { // from class: org.fdroid.fdroid.views.repos.RepoAdapter$RepoViewHolder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$bind$1(repository, view);
                }
            });
            FileV2 icon = repository.getIcon(LocaleListCompat.getDefault());
            if (icon == null) {
                Glide.with(this.imageView.getContext()).clear(this.imageView);
                this.imageView.setImageResource(R.drawable.ic_repo_app_default);
            } else {
                Glide.with(this.imageView.getContext()).m3060load(Utils.getGlideModel(repository, icon)).apply((BaseRequestOptions) Utils.getAlwaysShowIconRequestOptions()).into(this.imageView);
            }
            this.nameView.setText(repository.getName(App.getLocales()));
            this.addressView.setText(repository.getAddress().replace("https://", ""));
            if (repository.getCertificate() != null) {
                this.unsignedView.setVisibility(8);
                this.unverifiedView.setVisibility(8);
            } else if (repository.getCertificate() == null) {
                this.unsignedView.setVisibility(8);
                this.unverifiedView.setVisibility(0);
            } else {
                this.unsignedView.setVisibility(0);
                this.unverifiedView.setVisibility(8);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$0(Repository repository, View view) {
            RepoAdapter.this.repoItemListener.onClicked(repository);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$1(Repository repository, View view) {
            if (RepoAdapter.this.repoItemListener != null) {
                RepoAdapter.this.repoItemListener.onToggleEnabled(repository);
            }
        }
    }
}
