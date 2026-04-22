package io.reactivex.rxjava3.disposables;

/* JADX INFO: loaded from: classes.dex */
public interface DisposableContainer {
    boolean add(Disposable disposable);

    boolean delete(Disposable disposable);

    boolean remove(Disposable disposable);
}
