package com.bumptech.glide.integration.compose;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function5;

/* JADX INFO: compiled from: Transition.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Transition {

    /* JADX INFO: compiled from: Transition.kt */
    public interface Factory {
        Transition build();
    }

    Function5 getDrawCurrent();

    Function5 getDrawPlaceholder();

    Object stop(Continuation continuation);

    Object transition(Function0 function0, Continuation continuation);
}
