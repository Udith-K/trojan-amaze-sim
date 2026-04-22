package io.ktor.client.utils;

import io.ktor.events.EventDefinition;

/* JADX INFO: compiled from: ClientEvents.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ClientEventsKt {
    private static final EventDefinition HttpRequestCreated = new EventDefinition();
    private static final EventDefinition HttpRequestIsReadyForSending = new EventDefinition();
    private static final EventDefinition HttpResponseReceived = new EventDefinition();
    private static final EventDefinition HttpResponseReceiveFailed = new EventDefinition();
    private static final EventDefinition HttpResponseCancelled = new EventDefinition();

    public static final EventDefinition getHttpRequestCreated() {
        return HttpRequestCreated;
    }

    public static final EventDefinition getHttpRequestIsReadyForSending() {
        return HttpRequestIsReadyForSending;
    }

    public static final EventDefinition getHttpResponseReceived() {
        return HttpResponseReceived;
    }

    public static final EventDefinition getHttpResponseReceiveFailed() {
        return HttpResponseReceiveFailed;
    }

    public static final EventDefinition getHttpResponseCancelled() {
        return HttpResponseCancelled;
    }
}
