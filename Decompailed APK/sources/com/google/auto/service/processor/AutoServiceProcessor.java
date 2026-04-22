package com.google.auto.service.processor;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.AbstractProcessor;

/* JADX INFO: loaded from: classes.dex */
public class AutoServiceProcessor extends AbstractProcessor {
    private final List exceptionStacks = Collections.synchronizedList(new ArrayList());
    private final Multimap providers = HashMultimap.create();
}
