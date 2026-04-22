package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSetKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyLayoutItemAnimator {
    private DrawModifierNode displayingNode;
    private int firstVisibleIndex;
    private LazyLayoutKeyIndexMap keyIndexMap;
    private final MutableScatterMap keyToItemInfoMap = ScatterMapKt.mutableScatterMapOf();
    private final MutableScatterSet movingAwayKeys = ScatterSetKt.mutableScatterSetOf();
    private final List movingInFromStartBound = new ArrayList();
    private final List movingInFromEndBound = new ArrayList();
    private final List movingAwayToStartBound = new ArrayList();
    private final List movingAwayToEndBound = new ArrayList();
    private final List disappearingItems = new ArrayList();
    private final Modifier modifier = new DisplayingDisappearingItemsElement(this);

    public final void onMeasured(int i, int i2, int i3, List list, LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap, LazyLayoutMeasuredItemProvider lazyLayoutMeasuredItemProvider, boolean z, boolean z2, int i4, boolean z3, int i5, int i6, CoroutineScope coroutineScope, GraphicsContext graphicsContext) {
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap2;
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap3;
        int[] iArr;
        int i7;
        int i8;
        int i9;
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap4;
        int[] iArr2;
        long[] jArr;
        Object[] objArr;
        long[] jArr2;
        Object[] objArr2;
        int[] iArr3;
        LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr;
        int i10;
        LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr2;
        int i11;
        int i12;
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap5;
        int i13;
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap6;
        int i14;
        long[] jArr3;
        Object[] objArr3;
        int i15;
        long[] jArr4;
        Object[] objArr4;
        List list2 = list;
        int i16 = i4;
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap7 = this.keyIndexMap;
        this.keyIndexMap = lazyLayoutKeyIndexMap;
        int size = list.size();
        int i17 = 0;
        while (true) {
            if (i17 >= size) {
                if (this.keyToItemInfoMap.isEmpty()) {
                    reset();
                    return;
                }
            } else if (getHasAnimations((LazyLayoutMeasuredItem) list2.get(i17))) {
                break;
            } else {
                i17++;
            }
        }
        int i18 = this.firstVisibleIndex;
        LazyLayoutMeasuredItem lazyLayoutMeasuredItem = (LazyLayoutMeasuredItem) CollectionsKt.firstOrNull(list);
        this.firstVisibleIndex = lazyLayoutMeasuredItem != null ? lazyLayoutMeasuredItem.getIndex() : 0;
        long jIntOffset = z ? IntOffsetKt.IntOffset(0, i) : IntOffsetKt.IntOffset(i, 0);
        boolean z4 = z2 || !z3;
        MutableScatterMap mutableScatterMap = this.keyToItemInfoMap;
        Object[] objArr5 = mutableScatterMap.keys;
        long[] jArr5 = mutableScatterMap.metadata;
        int length = jArr5.length - 2;
        boolean z5 = z4;
        if (length >= 0) {
            int i19 = 0;
            while (true) {
                long j = jArr5[i19];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i20 = 8 - ((~(i19 - length)) >>> 31);
                    int i21 = 0;
                    while (i21 < i20) {
                        if ((j & 255) < 128) {
                            jArr4 = jArr5;
                            objArr4 = objArr5;
                            this.movingAwayKeys.add(objArr5[(i19 << 3) + i21]);
                        } else {
                            jArr4 = jArr5;
                            objArr4 = objArr5;
                        }
                        j >>= 8;
                        i21++;
                        objArr5 = objArr4;
                        jArr5 = jArr4;
                    }
                    jArr3 = jArr5;
                    objArr3 = objArr5;
                    i15 = 1;
                    if (i20 != 8) {
                        break;
                    }
                } else {
                    jArr3 = jArr5;
                    objArr3 = objArr5;
                    i15 = 1;
                }
                if (i19 == length) {
                    break;
                }
                i19 += i15;
                objArr5 = objArr3;
                jArr5 = jArr3;
            }
        }
        int size2 = list.size();
        int i22 = 0;
        while (i22 < size2) {
            LazyLayoutMeasuredItem lazyLayoutMeasuredItem2 = (LazyLayoutMeasuredItem) list2.get(i22);
            this.movingAwayKeys.remove(lazyLayoutMeasuredItem2.getKey());
            if (getHasAnimations(lazyLayoutMeasuredItem2)) {
                ItemInfo itemInfo = (ItemInfo) this.keyToItemInfoMap.get(lazyLayoutMeasuredItem2.getKey());
                int index = lazyLayoutKeyIndexMap7 != null ? lazyLayoutKeyIndexMap7.getIndex(lazyLayoutMeasuredItem2.getKey()) : -1;
                boolean z6 = index == -1 && lazyLayoutKeyIndexMap7 != null;
                if (itemInfo == null) {
                    ItemInfo itemInfo2 = new ItemInfo();
                    ItemInfo.updateAnimation$default(itemInfo2, lazyLayoutMeasuredItem2, coroutineScope, graphicsContext, i5, i6, 0, 32, null);
                    this.keyToItemInfoMap.set(lazyLayoutMeasuredItem2.getKey(), itemInfo2);
                    if (lazyLayoutMeasuredItem2.getIndex() == index || index == -1) {
                        long jMo318getOffsetBjo55l4 = lazyLayoutMeasuredItem2.mo318getOffsetBjo55l4(0);
                        initializeAnimation(lazyLayoutMeasuredItem2, lazyLayoutMeasuredItem2.isVertical() ? IntOffset.m2458getYimpl(jMo318getOffsetBjo55l4) : IntOffset.m2457getXimpl(jMo318getOffsetBjo55l4), itemInfo2);
                        if (z6) {
                            LazyLayoutItemAnimation[] animations = itemInfo2.getAnimations();
                            for (LazyLayoutItemAnimation lazyLayoutItemAnimation : animations) {
                                if (lazyLayoutItemAnimation != null) {
                                    lazyLayoutItemAnimation.animateAppearance();
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        }
                    } else if (index < i18) {
                        this.movingInFromStartBound.add(lazyLayoutMeasuredItem2);
                    } else {
                        this.movingInFromEndBound.add(lazyLayoutMeasuredItem2);
                    }
                } else if (z5) {
                    ItemInfo.updateAnimation$default(itemInfo, lazyLayoutMeasuredItem2, coroutineScope, graphicsContext, i5, i6, 0, 32, null);
                    LazyLayoutItemAnimation[] animations2 = itemInfo.getAnimations();
                    int i23 = 0;
                    for (int length2 = animations2.length; i23 < length2; length2 = i14) {
                        int i24 = size2;
                        LazyLayoutItemAnimation lazyLayoutItemAnimation2 = animations2[i23];
                        if (lazyLayoutItemAnimation2 != null) {
                            lazyLayoutKeyIndexMap6 = lazyLayoutKeyIndexMap7;
                            i14 = length2;
                            if (!IntOffset.m2456equalsimpl0(lazyLayoutItemAnimation2.m337getRawOffsetnOccac(), LazyLayoutItemAnimation.Companion.m341getNotInitializednOccac())) {
                                lazyLayoutItemAnimation2.m340setRawOffsetgyyYBs(IntOffset.m2461plusqkQi6aY(lazyLayoutItemAnimation2.m337getRawOffsetnOccac(), jIntOffset));
                            }
                        } else {
                            lazyLayoutKeyIndexMap6 = lazyLayoutKeyIndexMap7;
                            i14 = length2;
                        }
                        i23++;
                        size2 = i24;
                        lazyLayoutKeyIndexMap7 = lazyLayoutKeyIndexMap6;
                    }
                    i12 = size2;
                    lazyLayoutKeyIndexMap5 = lazyLayoutKeyIndexMap7;
                    if (z6) {
                        for (LazyLayoutItemAnimation lazyLayoutItemAnimation3 : itemInfo.getAnimations()) {
                            if (lazyLayoutItemAnimation3 != null) {
                                if (lazyLayoutItemAnimation3.isDisappearanceAnimationInProgress()) {
                                    this.disappearingItems.remove(lazyLayoutItemAnimation3);
                                    DrawModifierNode drawModifierNode = this.displayingNode;
                                    if (drawModifierNode != null) {
                                        DrawModifierNodeKt.invalidateDraw(drawModifierNode);
                                        Unit unit2 = Unit.INSTANCE;
                                    }
                                }
                                lazyLayoutItemAnimation3.animateAppearance();
                            }
                        }
                    }
                    i13 = 1;
                    startPlacementAnimationsIfNeeded$default(this, lazyLayoutMeasuredItem2, false, 2, null);
                }
                i12 = size2;
                lazyLayoutKeyIndexMap5 = lazyLayoutKeyIndexMap7;
                i13 = 1;
            } else {
                i12 = size2;
                lazyLayoutKeyIndexMap5 = lazyLayoutKeyIndexMap7;
                i13 = 1;
                removeInfoForKey(lazyLayoutMeasuredItem2.getKey());
            }
            i22 += i13;
            list2 = list;
            size2 = i12;
            lazyLayoutKeyIndexMap7 = lazyLayoutKeyIndexMap5;
        }
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap8 = lazyLayoutKeyIndexMap7;
        int i25 = 0;
        int[] iArr4 = new int[i16];
        int i26 = 0;
        while (i26 < i16) {
            iArr4[i26] = i25;
            i26++;
            i25 = 0;
        }
        if (!z5 || lazyLayoutKeyIndexMap8 == null) {
            lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap8;
        } else {
            if (this.movingInFromStartBound.isEmpty()) {
                lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap8;
            } else {
                List list3 = this.movingInFromStartBound;
                if (list3.size() > 1) {
                    lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap8;
                    CollectionsKt.sortWith(list3, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortByDescending$1
                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap2.getIndex(((LazyLayoutMeasuredItem) obj2).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap2.getIndex(((LazyLayoutMeasuredItem) obj).getKey())));
                        }
                    });
                } else {
                    lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap8;
                }
                List list4 = this.movingInFromStartBound;
                int size3 = list4.size();
                for (int i27 = 0; i27 < size3; i27++) {
                    LazyLayoutMeasuredItem lazyLayoutMeasuredItem3 = (LazyLayoutMeasuredItem) list4.get(i27);
                    initializeAnimation$default(this, lazyLayoutMeasuredItem3, i5 - updateAndReturnOffsetFor(iArr4, lazyLayoutMeasuredItem3), null, 4, null);
                    startPlacementAnimationsIfNeeded$default(this, lazyLayoutMeasuredItem3, false, 2, null);
                }
                ArraysKt.fill$default(iArr4, 0, 0, 0, 6, (Object) null);
            }
            if (!this.movingInFromEndBound.isEmpty()) {
                List list5 = this.movingInFromEndBound;
                if (list5.size() > 1) {
                    CollectionsKt.sortWith(list5, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap2.getIndex(((LazyLayoutMeasuredItem) obj).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap2.getIndex(((LazyLayoutMeasuredItem) obj2).getKey())));
                        }
                    });
                }
                List list6 = this.movingInFromEndBound;
                int size4 = list6.size();
                for (int i28 = 0; i28 < size4; i28++) {
                    LazyLayoutMeasuredItem lazyLayoutMeasuredItem4 = (LazyLayoutMeasuredItem) list6.get(i28);
                    initializeAnimation$default(this, lazyLayoutMeasuredItem4, (i6 + updateAndReturnOffsetFor(iArr4, lazyLayoutMeasuredItem4)) - lazyLayoutMeasuredItem4.getMainAxisSizeWithSpacings(), null, 4, null);
                    startPlacementAnimationsIfNeeded$default(this, lazyLayoutMeasuredItem4, false, 2, null);
                }
                ArraysKt.fill$default(iArr4, 0, 0, 0, 6, (Object) null);
            }
        }
        MutableScatterSet mutableScatterSet = this.movingAwayKeys;
        Object[] objArr6 = mutableScatterSet.elements;
        long[] jArr6 = mutableScatterSet.metadata;
        int length3 = jArr6.length - 2;
        if (length3 >= 0) {
            int i29 = 0;
            while (true) {
                long j2 = jArr6[i29];
                if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i30 = 8 - ((~(i29 - length3)) >>> 31);
                    int i31 = 0;
                    while (i31 < i30) {
                        if ((j2 & 255) < 128) {
                            Object obj = objArr6[(i29 << 3) + i31];
                            Object obj2 = this.keyToItemInfoMap.get(obj);
                            Intrinsics.checkNotNull(obj2);
                            ItemInfo itemInfo3 = (ItemInfo) obj2;
                            int index2 = lazyLayoutKeyIndexMap.getIndex(obj);
                            jArr2 = jArr6;
                            itemInfo3.setSpan(Math.min(i16, itemInfo3.getSpan()));
                            objArr2 = objArr6;
                            itemInfo3.setLane(Math.min(i16 - itemInfo3.getSpan(), itemInfo3.getLane()));
                            if (index2 == -1) {
                                LazyLayoutItemAnimation[] animations3 = itemInfo3.getAnimations();
                                int length4 = animations3.length;
                                int i32 = 0;
                                int i33 = 0;
                                boolean z7 = false;
                                while (i32 < length4) {
                                    LazyLayoutItemAnimation lazyLayoutItemAnimation4 = animations3[i32];
                                    int i34 = i33 + 1;
                                    if (lazyLayoutItemAnimation4 != null) {
                                        if (lazyLayoutItemAnimation4.isDisappearanceAnimationInProgress()) {
                                            lazyLayoutItemAnimationArr2 = animations3;
                                        } else if (lazyLayoutItemAnimation4.isDisappearanceAnimationFinished()) {
                                            lazyLayoutItemAnimation4.release();
                                            itemInfo3.getAnimations()[i33] = null;
                                            lazyLayoutItemAnimationArr2 = animations3;
                                            this.disappearingItems.remove(lazyLayoutItemAnimation4);
                                            DrawModifierNode drawModifierNode2 = this.displayingNode;
                                            if (drawModifierNode2 != null) {
                                                DrawModifierNodeKt.invalidateDraw(drawModifierNode2);
                                                Unit unit3 = Unit.INSTANCE;
                                            }
                                        } else {
                                            lazyLayoutItemAnimationArr2 = animations3;
                                            if (lazyLayoutItemAnimation4.getLayer() != null) {
                                                lazyLayoutItemAnimation4.animateDisappearance();
                                            }
                                            if (lazyLayoutItemAnimation4.isDisappearanceAnimationInProgress()) {
                                                this.disappearingItems.add(lazyLayoutItemAnimation4);
                                                DrawModifierNode drawModifierNode3 = this.displayingNode;
                                                if (drawModifierNode3 != null) {
                                                    DrawModifierNodeKt.invalidateDraw(drawModifierNode3);
                                                    Unit unit4 = Unit.INSTANCE;
                                                }
                                            } else {
                                                lazyLayoutItemAnimation4.release();
                                                itemInfo3.getAnimations()[i33] = null;
                                                i11 = 1;
                                                i32 += i11;
                                                i33 = i34;
                                                animations3 = lazyLayoutItemAnimationArr2;
                                            }
                                        }
                                        i11 = 1;
                                        z7 = true;
                                        i32 += i11;
                                        i33 = i34;
                                        animations3 = lazyLayoutItemAnimationArr2;
                                    } else {
                                        lazyLayoutItemAnimationArr2 = animations3;
                                    }
                                    i11 = 1;
                                    i32 += i11;
                                    i33 = i34;
                                    animations3 = lazyLayoutItemAnimationArr2;
                                }
                                if (!z7) {
                                    removeInfoForKey(obj);
                                }
                            } else {
                                Constraints constraintsM343getConstraintsDWUhwKw = itemInfo3.m343getConstraintsDWUhwKw();
                                Intrinsics.checkNotNull(constraintsM343getConstraintsDWUhwKw);
                                LazyLayoutMeasuredItem lazyLayoutMeasuredItemMo320getAndMeasurehBUhpc = lazyLayoutMeasuredItemProvider.mo320getAndMeasurehBUhpc(index2, itemInfo3.getLane(), itemInfo3.getSpan(), constraintsM343getConstraintsDWUhwKw.m2395unboximpl());
                                lazyLayoutMeasuredItemMo320getAndMeasurehBUhpc.setNonScrollableItem(true);
                                LazyLayoutItemAnimation[] animations4 = itemInfo3.getAnimations();
                                int length5 = animations4.length;
                                iArr3 = iArr4;
                                int i35 = 0;
                                while (true) {
                                    if (i35 < length5) {
                                        LazyLayoutItemAnimation lazyLayoutItemAnimation5 = animations4[i35];
                                        int i36 = length5;
                                        if (lazyLayoutItemAnimation5 != null) {
                                            boolean zIsPlacementAnimationInProgress = lazyLayoutItemAnimation5.isPlacementAnimationInProgress();
                                            lazyLayoutItemAnimationArr = animations4;
                                            i10 = 1;
                                            if (zIsPlacementAnimationInProgress) {
                                                break;
                                            }
                                        } else {
                                            lazyLayoutItemAnimationArr = animations4;
                                            i10 = 1;
                                        }
                                        i35 += i10;
                                        animations4 = lazyLayoutItemAnimationArr;
                                        length5 = i36;
                                    } else if (lazyLayoutKeyIndexMap2 == null || index2 != lazyLayoutKeyIndexMap2.getIndex(obj)) {
                                        break;
                                    } else {
                                        removeInfoForKey(obj);
                                    }
                                }
                                itemInfo3.updateAnimation(lazyLayoutMeasuredItemMo320getAndMeasurehBUhpc, coroutineScope, graphicsContext, i5, i6, itemInfo3.getCrossAxisOffset());
                                if (index2 < this.firstVisibleIndex) {
                                    this.movingAwayToStartBound.add(lazyLayoutMeasuredItemMo320getAndMeasurehBUhpc);
                                } else {
                                    this.movingAwayToEndBound.add(lazyLayoutMeasuredItemMo320getAndMeasurehBUhpc);
                                }
                                j2 >>= 8;
                                i31++;
                                objArr6 = objArr2;
                                i16 = i4;
                                jArr6 = jArr2;
                                iArr4 = iArr3;
                            }
                        } else {
                            jArr2 = jArr6;
                            objArr2 = objArr6;
                        }
                        iArr3 = iArr4;
                        j2 >>= 8;
                        i31++;
                        objArr6 = objArr2;
                        i16 = i4;
                        jArr6 = jArr2;
                        iArr4 = iArr3;
                    }
                    lazyLayoutKeyIndexMap3 = lazyLayoutKeyIndexMap;
                    jArr = jArr6;
                    objArr = objArr6;
                    iArr = iArr4;
                    i7 = 1;
                    if (i30 != 8) {
                        break;
                    }
                } else {
                    lazyLayoutKeyIndexMap3 = lazyLayoutKeyIndexMap;
                    jArr = jArr6;
                    objArr = objArr6;
                    iArr = iArr4;
                    i7 = 1;
                }
                if (i29 == length3) {
                    break;
                }
                i29 += i7;
                objArr6 = objArr;
                i16 = i4;
                jArr6 = jArr;
                iArr4 = iArr;
            }
        } else {
            lazyLayoutKeyIndexMap3 = lazyLayoutKeyIndexMap;
            iArr = iArr4;
            i7 = 1;
        }
        if (this.movingAwayToStartBound.isEmpty()) {
            i8 = i2;
            i9 = i3;
            lazyLayoutKeyIndexMap4 = lazyLayoutKeyIndexMap3;
            iArr2 = iArr;
        } else {
            List list7 = this.movingAwayToStartBound;
            if (list7.size() > i7) {
                CollectionsKt.sortWith(list7, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortByDescending$2
                    @Override // java.util.Comparator
                    public final int compare(Object obj3, Object obj4) {
                        return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap3.getIndex(((LazyLayoutMeasuredItem) obj4).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap3.getIndex(((LazyLayoutMeasuredItem) obj3).getKey())));
                    }
                });
            }
            List list8 = this.movingAwayToStartBound;
            int size5 = list8.size();
            int i37 = 0;
            while (i37 < size5) {
                LazyLayoutMeasuredItem lazyLayoutMeasuredItem5 = (LazyLayoutMeasuredItem) list8.get(i37);
                Object obj3 = this.keyToItemInfoMap.get(lazyLayoutMeasuredItem5.getKey());
                Intrinsics.checkNotNull(obj3);
                ItemInfo itemInfo4 = (ItemInfo) obj3;
                int[] iArr5 = iArr;
                lazyLayoutMeasuredItem5.position((z2 ? getMainAxisOffset((LazyLayoutMeasuredItem) CollectionsKt.first(list)) : itemInfo4.getLayoutMinOffset()) - updateAndReturnOffsetFor(iArr5, lazyLayoutMeasuredItem5), itemInfo4.getCrossAxisOffset(), i2, i3);
                if (z5) {
                    startPlacementAnimationsIfNeeded(lazyLayoutMeasuredItem5, true);
                }
                i37++;
                iArr = iArr5;
            }
            i8 = i2;
            i9 = i3;
            iArr2 = iArr;
            lazyLayoutKeyIndexMap4 = lazyLayoutKeyIndexMap3;
            ArraysKt.fill$default(iArr2, 0, 0, 0, 6, (Object) null);
        }
        if (!this.movingAwayToEndBound.isEmpty()) {
            List list9 = this.movingAwayToEndBound;
            if (list9.size() > 1) {
                CollectionsKt.sortWith(list9, new Comparator() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$onMeasured$$inlined$sortBy$2
                    @Override // java.util.Comparator
                    public final int compare(Object obj4, Object obj5) {
                        return ComparisonsKt.compareValues(Integer.valueOf(lazyLayoutKeyIndexMap4.getIndex(((LazyLayoutMeasuredItem) obj4).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap4.getIndex(((LazyLayoutMeasuredItem) obj5).getKey())));
                    }
                });
            }
            List list10 = this.movingAwayToEndBound;
            int size6 = list10.size();
            for (int i38 = 0; i38 < size6; i38++) {
                LazyLayoutMeasuredItem lazyLayoutMeasuredItem6 = (LazyLayoutMeasuredItem) list10.get(i38);
                Object obj4 = this.keyToItemInfoMap.get(lazyLayoutMeasuredItem6.getKey());
                Intrinsics.checkNotNull(obj4);
                ItemInfo itemInfo5 = (ItemInfo) obj4;
                lazyLayoutMeasuredItem6.position((z2 ? getMainAxisOffset((LazyLayoutMeasuredItem) CollectionsKt.last(list)) : itemInfo5.getLayoutMaxOffset() - lazyLayoutMeasuredItem6.getMainAxisSizeWithSpacings()) + updateAndReturnOffsetFor(iArr2, lazyLayoutMeasuredItem6), itemInfo5.getCrossAxisOffset(), i8, i9);
                if (z5) {
                    startPlacementAnimationsIfNeeded(lazyLayoutMeasuredItem6, true);
                }
            }
        }
        List list11 = this.movingAwayToStartBound;
        CollectionsKt.reverse(list11);
        Unit unit5 = Unit.INSTANCE;
        list.addAll(0, list11);
        list.addAll(this.movingAwayToEndBound);
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.clear();
    }

    private final void removeInfoForKey(Object obj) {
        LazyLayoutItemAnimation[] animations;
        ItemInfo itemInfo = (ItemInfo) this.keyToItemInfoMap.remove(obj);
        if (itemInfo == null || (animations = itemInfo.getAnimations()) == null) {
            return;
        }
        for (LazyLayoutItemAnimation lazyLayoutItemAnimation : animations) {
            if (lazyLayoutItemAnimation != null) {
                lazyLayoutItemAnimation.release();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void reset() {
        /*
            r14 = this;
            androidx.collection.MutableScatterMap r0 = r14.keyToItemInfoMap
            boolean r0 = r0.isNotEmpty()
            if (r0 == 0) goto L63
            androidx.collection.MutableScatterMap r0 = r14.keyToItemInfoMap
            java.lang.Object[] r1 = r0.values
            long[] r0 = r0.metadata
            int r2 = r0.length
            int r2 = r2 + (-2)
            if (r2 < 0) goto L5e
            r3 = 0
            r4 = r3
        L15:
            r5 = r0[r4]
            long r7 = ~r5
            r9 = 7
            long r7 = r7 << r9
            long r7 = r7 & r5
            r9 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r7 = r7 & r9
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L59
            int r7 = r4 - r2
            int r7 = ~r7
            int r7 = r7 >>> 31
            r8 = 8
            int r7 = 8 - r7
            r9 = r3
        L2f:
            if (r9 >= r7) goto L57
            r10 = 255(0xff, double:1.26E-321)
            long r10 = r10 & r5
            r12 = 128(0x80, double:6.3E-322)
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 >= 0) goto L53
            int r10 = r4 << 3
            int r10 = r10 + r9
            r10 = r1[r10]
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$ItemInfo r10 = (androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.ItemInfo) r10
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation[] r10 = r10.getAnimations()
            int r11 = r10.length
            r12 = r3
        L47:
            if (r12 >= r11) goto L53
            r13 = r10[r12]
            if (r13 == 0) goto L50
            r13.release()
        L50:
            int r12 = r12 + 1
            goto L47
        L53:
            long r5 = r5 >> r8
            int r9 = r9 + 1
            goto L2f
        L57:
            if (r7 != r8) goto L5e
        L59:
            if (r4 == r2) goto L5e
            int r4 = r4 + 1
            goto L15
        L5e:
            androidx.collection.MutableScatterMap r0 = r14.keyToItemInfoMap
            r0.clear()
        L63:
            androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap$Empty r0 = androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap.Empty
            r14.keyIndexMap = r0
            r0 = -1
            r14.firstVisibleIndex = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.reset():void");
    }

    static /* synthetic */ void initializeAnimation$default(LazyLayoutItemAnimator lazyLayoutItemAnimator, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, int i, ItemInfo itemInfo, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            Object obj2 = lazyLayoutItemAnimator.keyToItemInfoMap.get(lazyLayoutMeasuredItem.getKey());
            Intrinsics.checkNotNull(obj2);
            itemInfo = (ItemInfo) obj2;
        }
        lazyLayoutItemAnimator.initializeAnimation(lazyLayoutMeasuredItem, i, itemInfo);
    }

    private final void initializeAnimation(LazyLayoutMeasuredItem lazyLayoutMeasuredItem, int i, ItemInfo itemInfo) {
        long jM2454copyiSbpLlY$default;
        int i2 = 0;
        long jMo318getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo318getOffsetBjo55l4(0);
        if (lazyLayoutMeasuredItem.isVertical()) {
            jM2454copyiSbpLlY$default = IntOffset.m2454copyiSbpLlY$default(jMo318getOffsetBjo55l4, 0, i, 1, null);
        } else {
            jM2454copyiSbpLlY$default = IntOffset.m2454copyiSbpLlY$default(jMo318getOffsetBjo55l4, i, 0, 2, null);
        }
        LazyLayoutItemAnimation[] animations = itemInfo.getAnimations();
        int length = animations.length;
        int i3 = 0;
        while (i2 < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = animations[i2];
            int i4 = i3 + 1;
            if (lazyLayoutItemAnimation != null) {
                lazyLayoutItemAnimation.m340setRawOffsetgyyYBs(IntOffset.m2461plusqkQi6aY(jM2454copyiSbpLlY$default, IntOffset.m2460minusqkQi6aY(lazyLayoutMeasuredItem.mo318getOffsetBjo55l4(i3), jMo318getOffsetBjo55l4)));
            }
            i2++;
            i3 = i4;
        }
    }

    static /* synthetic */ void startPlacementAnimationsIfNeeded$default(LazyLayoutItemAnimator lazyLayoutItemAnimator, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        lazyLayoutItemAnimator.startPlacementAnimationsIfNeeded(lazyLayoutMeasuredItem, z);
    }

    private final void startPlacementAnimationsIfNeeded(LazyLayoutMeasuredItem lazyLayoutMeasuredItem, boolean z) {
        Object obj = this.keyToItemInfoMap.get(lazyLayoutMeasuredItem.getKey());
        Intrinsics.checkNotNull(obj);
        LazyLayoutItemAnimation[] animations = ((ItemInfo) obj).getAnimations();
        int length = animations.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = animations[i];
            int i3 = i2 + 1;
            if (lazyLayoutItemAnimation != null) {
                long jMo318getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo318getOffsetBjo55l4(i2);
                long jM337getRawOffsetnOccac = lazyLayoutItemAnimation.m337getRawOffsetnOccac();
                if (!IntOffset.m2456equalsimpl0(jM337getRawOffsetnOccac, LazyLayoutItemAnimation.Companion.m341getNotInitializednOccac()) && !IntOffset.m2456equalsimpl0(jM337getRawOffsetnOccac, jMo318getOffsetBjo55l4)) {
                    lazyLayoutItemAnimation.m333animatePlacementDeltaar5cAso(IntOffset.m2460minusqkQi6aY(jMo318getOffsetBjo55l4, jM337getRawOffsetnOccac), z);
                }
                lazyLayoutItemAnimation.m340setRawOffsetgyyYBs(jMo318getOffsetBjo55l4);
            }
            i++;
            i2 = i3;
        }
    }

    public final LazyLayoutItemAnimation getAnimation(Object obj, int i) {
        LazyLayoutItemAnimation[] animations;
        ItemInfo itemInfo = (ItemInfo) this.keyToItemInfoMap.get(obj);
        if (itemInfo == null || (animations = itemInfo.getAnimations()) == null) {
            return null;
        }
        return animations[i];
    }

    private final int updateAndReturnOffsetFor(int[] iArr, LazyLayoutMeasuredItem lazyLayoutMeasuredItem) {
        int lane = lazyLayoutMeasuredItem.getLane();
        int span = lazyLayoutMeasuredItem.getSpan() + lane;
        int iMax = 0;
        while (lane < span) {
            int mainAxisSizeWithSpacings = iArr[lane] + lazyLayoutMeasuredItem.getMainAxisSizeWithSpacings();
            iArr[lane] = mainAxisSizeWithSpacings;
            iMax = Math.max(iMax, mainAxisSizeWithSpacings);
            lane++;
        }
        return iMax;
    }

    /* JADX INFO: renamed from: getMinSizeToFitDisappearingItems-YbymL2g, reason: not valid java name */
    public final long m342getMinSizeToFitDisappearingItemsYbymL2g() {
        long jM2480getZeroYbymL2g = IntSize.Companion.m2480getZeroYbymL2g();
        List list = this.disappearingItems;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = (LazyLayoutItemAnimation) list.get(i);
            GraphicsLayer layer = lazyLayoutItemAnimation.getLayer();
            if (layer != null) {
                jM2480getZeroYbymL2g = IntSizeKt.IntSize(Math.max(IntSize.m2476getWidthimpl(jM2480getZeroYbymL2g), IntOffset.m2457getXimpl(lazyLayoutItemAnimation.m337getRawOffsetnOccac()) + IntSize.m2476getWidthimpl(layer.m1524getSizeYbymL2g())), Math.max(IntSize.m2475getHeightimpl(jM2480getZeroYbymL2g), IntOffset.m2458getYimpl(lazyLayoutItemAnimation.m337getRawOffsetnOccac()) + IntSize.m2475getHeightimpl(layer.m1524getSizeYbymL2g())));
            }
        }
        return jM2480getZeroYbymL2g;
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    private final boolean getHasAnimations(LazyLayoutMeasuredItem lazyLayoutMeasuredItem) {
        int placeablesCount = lazyLayoutMeasuredItem.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            if (LazyLayoutItemAnimatorKt.getSpecs(lazyLayoutMeasuredItem.getParentData(i)) != null) {
                return true;
            }
        }
        return false;
    }

    private final int getMainAxisOffset(LazyLayoutMeasuredItem lazyLayoutMeasuredItem) {
        long jMo318getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo318getOffsetBjo55l4(0);
        return lazyLayoutMeasuredItem.isVertical() ? IntOffset.m2458getYimpl(jMo318getOffsetBjo55l4) : IntOffset.m2457getXimpl(jMo318getOffsetBjo55l4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCrossAxisOffset(LazyLayoutMeasuredItem lazyLayoutMeasuredItem) {
        long jMo318getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo318getOffsetBjo55l4(0);
        return !lazyLayoutMeasuredItem.isVertical() ? IntOffset.m2458getYimpl(jMo318getOffsetBjo55l4) : IntOffset.m2457getXimpl(jMo318getOffsetBjo55l4);
    }

    /* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
    private final class ItemInfo {
        private Constraints constraints;
        private int crossAxisOffset;
        private int lane;
        private int layoutMaxOffset;
        private int layoutMinOffset;
        private LazyLayoutItemAnimation[] animations = LazyLayoutItemAnimatorKt.EmptyArray;
        private int span = 1;

        public ItemInfo() {
        }

        public final LazyLayoutItemAnimation[] getAnimations() {
            return this.animations;
        }

        /* JADX INFO: renamed from: getConstraints-DWUhwKw, reason: not valid java name */
        public final Constraints m343getConstraintsDWUhwKw() {
            return this.constraints;
        }

        public final int getCrossAxisOffset() {
            return this.crossAxisOffset;
        }

        public final int getLane() {
            return this.lane;
        }

        public final void setLane(int i) {
            this.lane = i;
        }

        public final int getSpan() {
            return this.span;
        }

        public final void setSpan(int i) {
            this.span = i;
        }

        private final boolean isRunningPlacement() {
            for (LazyLayoutItemAnimation lazyLayoutItemAnimation : this.animations) {
                if (lazyLayoutItemAnimation != null && lazyLayoutItemAnimation.isRunningMovingAwayAnimation()) {
                    return true;
                }
            }
            return false;
        }

        public final int getLayoutMinOffset() {
            return this.layoutMinOffset;
        }

        public final int getLayoutMaxOffset() {
            return this.layoutMaxOffset;
        }

        public static /* synthetic */ void updateAnimation$default(ItemInfo itemInfo, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 32) != 0) {
                i3 = LazyLayoutItemAnimator.this.getCrossAxisOffset(lazyLayoutMeasuredItem);
            }
            itemInfo.updateAnimation(lazyLayoutMeasuredItem, coroutineScope, graphicsContext, i, i2, i3);
        }

        public final void updateAnimation(LazyLayoutMeasuredItem lazyLayoutMeasuredItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int i, int i2, int i3) {
            if (!isRunningPlacement()) {
                this.layoutMinOffset = i;
                this.layoutMaxOffset = i2;
            }
            int length = this.animations.length;
            for (int placeablesCount = lazyLayoutMeasuredItem.getPlaceablesCount(); placeablesCount < length; placeablesCount++) {
                LazyLayoutItemAnimation lazyLayoutItemAnimation = this.animations[placeablesCount];
                if (lazyLayoutItemAnimation != null) {
                    lazyLayoutItemAnimation.release();
                }
            }
            if (this.animations.length != lazyLayoutMeasuredItem.getPlaceablesCount()) {
                Object[] objArrCopyOf = Arrays.copyOf(this.animations, lazyLayoutMeasuredItem.getPlaceablesCount());
                Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
                this.animations = (LazyLayoutItemAnimation[]) objArrCopyOf;
            }
            this.constraints = Constraints.m2378boximpl(lazyLayoutMeasuredItem.mo317getConstraintsmsEJaDk());
            this.crossAxisOffset = i3;
            this.lane = lazyLayoutMeasuredItem.getLane();
            this.span = lazyLayoutMeasuredItem.getSpan();
            int placeablesCount2 = lazyLayoutMeasuredItem.getPlaceablesCount();
            final LazyLayoutItemAnimator lazyLayoutItemAnimator = LazyLayoutItemAnimator.this;
            for (int i4 = 0; i4 < placeablesCount2; i4++) {
                LazyLayoutAnimationSpecsNode specs = LazyLayoutItemAnimatorKt.getSpecs(lazyLayoutMeasuredItem.getParentData(i4));
                if (specs == null) {
                    LazyLayoutItemAnimation lazyLayoutItemAnimation2 = this.animations[i4];
                    if (lazyLayoutItemAnimation2 != null) {
                        lazyLayoutItemAnimation2.release();
                    }
                    this.animations[i4] = null;
                } else {
                    LazyLayoutItemAnimation lazyLayoutItemAnimation3 = this.animations[i4];
                    if (lazyLayoutItemAnimation3 == null) {
                        lazyLayoutItemAnimation3 = new LazyLayoutItemAnimation(coroutineScope, graphicsContext, new Function0() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$ItemInfo$updateAnimation$1$animation$1
                            {
                                super(0);
                            }

                            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                            public final void m344invoke() {
                                DrawModifierNode drawModifierNode = lazyLayoutItemAnimator.displayingNode;
                                if (drawModifierNode != null) {
                                    DrawModifierNodeKt.invalidateDraw(drawModifierNode);
                                }
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Object invoke() {
                                m344invoke();
                                return Unit.INSTANCE;
                            }
                        });
                        this.animations[i4] = lazyLayoutItemAnimation3;
                    }
                    lazyLayoutItemAnimation3.setFadeInSpec(specs.getFadeInSpec());
                    lazyLayoutItemAnimation3.setPlacementSpec(specs.getPlacementSpec());
                    lazyLayoutItemAnimation3.setFadeOutSpec(specs.getFadeOutSpec());
                }
            }
        }
    }

    /* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "animator", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "create", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;", "node", "", "update", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final /* data */ class DisplayingDisappearingItemsElement extends ModifierNodeElement {
        private final LazyLayoutItemAnimator animator;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DisplayingDisappearingItemsElement) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsElement) other).animator);
        }

        public int hashCode() {
            return this.animator.hashCode();
        }

        public String toString() {
            return "DisplayingDisappearingItemsElement(animator=" + this.animator + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public DisplayingDisappearingItemsElement(LazyLayoutItemAnimator lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* JADX INFO: renamed from: create */
        public DisplayingDisappearingItemsNode getNode() {
            return new DisplayingDisappearingItemsNode(this.animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(DisplayingDisappearingItemsNode node) {
            node.setAnimator(this.animator);
        }
    }

    /* JADX INFO: compiled from: LazyLayoutItemAnimator.kt */
    private static final class DisplayingDisappearingItemsNode extends Modifier.Node implements DrawModifierNode {
        private LazyLayoutItemAnimator animator;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DisplayingDisappearingItemsNode) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsNode) obj).animator);
        }

        public int hashCode() {
            return this.animator.hashCode();
        }

        @Override // androidx.compose.ui.node.DrawModifierNode
        public /* synthetic */ void onMeasureResultChanged() {
            DrawModifierNode.CC.$default$onMeasureResultChanged(this);
        }

        public String toString() {
            return "DisplayingDisappearingItemsNode(animator=" + this.animator + CoreConstants.RIGHT_PARENTHESIS_CHAR;
        }

        public DisplayingDisappearingItemsNode(LazyLayoutItemAnimator lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        @Override // androidx.compose.ui.node.DrawModifierNode
        public void draw(ContentDrawScope contentDrawScope) {
            List list = this.animator.disappearingItems;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                LazyLayoutItemAnimation lazyLayoutItemAnimation = (LazyLayoutItemAnimation) list.get(i);
                GraphicsLayer layer = lazyLayoutItemAnimation.getLayer();
                if (layer != null) {
                    float fM2457getXimpl = IntOffset.m2457getXimpl(lazyLayoutItemAnimation.m334getFinalOffsetnOccac());
                    float fM2457getXimpl2 = fM2457getXimpl - IntOffset.m2457getXimpl(layer.m1525getTopLeftnOccac());
                    float fM2458getYimpl = IntOffset.m2458getYimpl(lazyLayoutItemAnimation.m334getFinalOffsetnOccac()) - IntOffset.m2458getYimpl(layer.m1525getTopLeftnOccac());
                    contentDrawScope.getDrawContext().getTransform().translate(fM2457getXimpl2, fM2458getYimpl);
                    try {
                        GraphicsLayerKt.drawLayer(contentDrawScope, layer);
                    } finally {
                        contentDrawScope.getDrawContext().getTransform().translate(-fM2457getXimpl2, -fM2458getYimpl);
                    }
                }
            }
            contentDrawScope.drawContent();
        }

        @Override // androidx.compose.ui.Modifier.Node
        public void onAttach() {
            this.animator.displayingNode = this;
        }

        @Override // androidx.compose.ui.Modifier.Node
        public void onDetach() {
            this.animator.reset();
        }

        public final void setAnimator(LazyLayoutItemAnimator lazyLayoutItemAnimator) {
            if (Intrinsics.areEqual(this.animator, lazyLayoutItemAnimator) || !getNode().isAttached()) {
                return;
            }
            this.animator.reset();
            lazyLayoutItemAnimator.displayingNode = this;
            this.animator = lazyLayoutItemAnimator;
        }
    }
}
