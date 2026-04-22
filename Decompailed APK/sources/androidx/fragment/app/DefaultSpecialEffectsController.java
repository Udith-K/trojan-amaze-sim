package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DefaultSpecialEffectsController extends SpecialEffectsController {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController(ViewGroup container) {
        super(container);
        Intrinsics.checkNotNullParameter(container, "container");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void executeOperations$lambda$2(List awaitingContainerChanges, SpecialEffectsController.Operation operation, DefaultSpecialEffectsController this$0) {
        Intrinsics.checkNotNullParameter(awaitingContainerChanges, "$awaitingContainerChanges");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (awaitingContainerChanges.contains(operation)) {
            awaitingContainerChanges.remove(operation);
            this$0.applyContainerChanges(operation);
        }
    }

    private final void syncAnimations(List list) {
        Fragment fragment = ((SpecialEffectsController.Operation) CollectionsKt.last(list)).getFragment();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SpecialEffectsController.Operation operation = (SpecialEffectsController.Operation) it.next();
            operation.getFragment().mAnimationInfo.mEnterAnim = fragment.mAnimationInfo.mEnterAnim;
            operation.getFragment().mAnimationInfo.mExitAnim = fragment.mAnimationInfo.mExitAnim;
            operation.getFragment().mAnimationInfo.mPopEnterAnim = fragment.mAnimationInfo.mPopEnterAnim;
            operation.getFragment().mAnimationInfo.mPopExitAnim = fragment.mAnimationInfo.mPopExitAnim;
        }
    }

    private final void startAnimations(List list, List list2, boolean z, Map map) {
        Context context = getContainer().getContext();
        ArrayList<AnimationInfo> arrayList = new ArrayList();
        Iterator it = list.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            final AnimationInfo animationInfo = (AnimationInfo) it.next();
            if (animationInfo.isVisibilityUnchanged()) {
                animationInfo.completeSpecialEffect();
            } else {
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
                if (animation == null) {
                    animationInfo.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(animationInfo);
                    } else {
                        final SpecialEffectsController.Operation operation = animationInfo.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Intrinsics.areEqual(map.get(operation), Boolean.TRUE)) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.completeSpecialEffect();
                        } else {
                            final boolean z3 = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            if (z3) {
                                list2.remove(operation);
                            }
                            final View view = fragment.mView;
                            getContainer().startViewTransition(view);
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.startAnimations.1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator anim) {
                                    Intrinsics.checkNotNullParameter(anim, "anim");
                                    DefaultSpecialEffectsController.this.getContainer().endViewTransition(view);
                                    if (z3) {
                                        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
                                        View viewToAnimate = view;
                                        Intrinsics.checkNotNullExpressionValue(viewToAnimate, "viewToAnimate");
                                        finalState.applyState(viewToAnimate);
                                    }
                                    animationInfo.completeSpecialEffect();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + operation + " has ended.");
                                    }
                                }
                            });
                            animator.setTarget(view);
                            animator.start();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animator from operation " + operation + " has started.");
                            }
                            animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda1
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public final void onCancel() {
                                    DefaultSpecialEffectsController.startAnimations$lambda$3(animator, operation);
                                }
                            });
                            z2 = true;
                        }
                    }
                }
            }
        }
        for (final AnimationInfo animationInfo2 : arrayList) {
            final SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                final View view2 = fragment2.mView;
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation2 = animationInfo2.getAnimation(context);
                if (animation2 == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                Animation animation3 = animation2.animation;
                if (animation3 == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                if (operation2.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view2.startAnimation(animation3);
                    animationInfo2.completeSpecialEffect();
                } else {
                    getContainer().startViewTransition(view2);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation3, getContainer(), view2);
                    endViewTransitionAnimation.setAnimationListener(new AnonymousClass3(operation2, this, view2, animationInfo2));
                    view2.startAnimation(endViewTransitionAnimation);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Animation from operation " + operation2 + " has started.");
                    }
                }
                animationInfo2.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda2
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public final void onCancel() {
                        DefaultSpecialEffectsController.startAnimations$lambda$4(view2, this, animationInfo2, operation2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startAnimations$lambda$3(Animator animator, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(operation, "$operation");
        animator.end();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animator from operation " + operation + " has been canceled.");
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.DefaultSpecialEffectsController$startAnimations$3, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
    public static final class AnonymousClass3 implements Animation.AnimationListener {
        final /* synthetic */ AnimationInfo $animationInfo;
        final /* synthetic */ SpecialEffectsController.Operation $operation;
        final /* synthetic */ View $viewToAnimate;
        final /* synthetic */ DefaultSpecialEffectsController this$0;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
        }

        AnonymousClass3(SpecialEffectsController.Operation operation, DefaultSpecialEffectsController defaultSpecialEffectsController, View view, AnimationInfo animationInfo) {
            this.$operation = operation;
            this.this$0 = defaultSpecialEffectsController;
            this.$viewToAnimate = view;
            this.$animationInfo = animationInfo;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Animation from operation " + this.$operation + " has reached onAnimationStart.");
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
            ViewGroup container = this.this$0.getContainer();
            final DefaultSpecialEffectsController defaultSpecialEffectsController = this.this$0;
            final View view = this.$viewToAnimate;
            final AnimationInfo animationInfo = this.$animationInfo;
            container.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$startAnimations$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultSpecialEffectsController.AnonymousClass3.onAnimationEnd$lambda$0(defaultSpecialEffectsController, view, animationInfo);
                }
            });
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Animation from operation " + this.$operation + " has ended.");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onAnimationEnd$lambda$0(DefaultSpecialEffectsController this$0, View view, AnimationInfo animationInfo) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(animationInfo, "$animationInfo");
            this$0.getContainer().endViewTransition(view);
            animationInfo.completeSpecialEffect();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00cd  */
    @Override // androidx.fragment.app.SpecialEffectsController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void executeOperations(java.util.List r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.executeOperations(java.util.List, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startAnimations$lambda$4(View view, DefaultSpecialEffectsController this$0, AnimationInfo animationInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animationInfo, "$animationInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        view.clearAnimation();
        this$0.getContainer().endViewTransition(view);
        animationInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Animation from operation " + operation + " has been cancelled.");
        }
    }

    private final Map startTransitions(List list, List list2, boolean z, final SpecialEffectsController.Operation operation, final SpecialEffectsController.Operation operation2) {
        String str;
        String str2;
        View view;
        Object obj;
        String str3;
        boolean z2;
        Object obj2;
        Object obj3;
        View view2;
        final ArrayList arrayList;
        LinkedHashMap linkedHashMap;
        Object obj4;
        SpecialEffectsController.Operation operation3;
        View view3;
        Rect rect;
        Pair pair;
        View view4;
        final View view5;
        DefaultSpecialEffectsController defaultSpecialEffectsController = this;
        final boolean z3 = z;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj5 : list) {
            if (!((TransitionInfo) obj5).isVisibilityUnchanged()) {
                arrayList2.add(obj5);
            }
        }
        ArrayList<TransitionInfo> arrayList3 = new ArrayList();
        for (Object obj6 : arrayList2) {
            if (((TransitionInfo) obj6).getHandlingImpl() != null) {
                arrayList3.add(obj6);
            }
        }
        final FragmentTransitionImpl fragmentTransitionImpl = null;
        for (TransitionInfo transitionInfo : arrayList3) {
            FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
            if (fragmentTransitionImpl != null && handlingImpl != fragmentTransitionImpl) {
                throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition type than other Fragments.").toString());
            }
            fragmentTransitionImpl = handlingImpl;
        }
        if (fragmentTransitionImpl == null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                TransitionInfo transitionInfo2 = (TransitionInfo) it.next();
                linkedHashMap2.put(transitionInfo2.getOperation(), Boolean.FALSE);
                transitionInfo2.completeSpecialEffect();
            }
            return linkedHashMap2;
        }
        View view6 = new View(getContainer().getContext());
        final Rect rect2 = new Rect();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayMap arrayMap = new ArrayMap();
        Iterator it2 = list.iterator();
        View view7 = null;
        Object obj7 = null;
        boolean z4 = false;
        while (true) {
            str = "FragmentManager";
            if (!it2.hasNext()) {
                break;
            }
            TransitionInfo transitionInfo3 = (TransitionInfo) it2.next();
            if (!transitionInfo3.hasSharedElementTransition() || operation == null || operation2 == null) {
                rect = rect2;
                view6 = view6;
                arrayList4 = arrayList4;
                linkedHashMap2 = linkedHashMap2;
                arrayMap = arrayMap;
                view7 = view7;
                arrayList5 = arrayList5;
                rect2 = rect;
                z3 = z;
            } else {
                Object objWrapTransitionInSet = fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(transitionInfo3.getSharedElementTransition()));
                ArrayList<String> sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                Intrinsics.checkNotNullExpressionValue(sharedElementSourceNames, "lastIn.fragment.sharedElementSourceNames");
                ArrayList<String> sharedElementSourceNames2 = operation.getFragment().getSharedElementSourceNames();
                View view8 = view7;
                Intrinsics.checkNotNullExpressionValue(sharedElementSourceNames2, "firstOut.fragment.sharedElementSourceNames");
                ArrayList<String> sharedElementTargetNames = operation.getFragment().getSharedElementTargetNames();
                LinkedHashMap linkedHashMap3 = linkedHashMap2;
                Intrinsics.checkNotNullExpressionValue(sharedElementTargetNames, "firstOut.fragment.sharedElementTargetNames");
                int size = sharedElementTargetNames.size();
                View view9 = view6;
                int i = 0;
                while (i < size) {
                    int i2 = size;
                    int iIndexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i));
                    ArrayList<String> arrayList6 = sharedElementTargetNames;
                    if (iIndexOf != -1) {
                        sharedElementSourceNames.set(iIndexOf, sharedElementSourceNames2.get(i));
                    }
                    i++;
                    size = i2;
                    sharedElementTargetNames = arrayList6;
                }
                ArrayList<String> sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                Intrinsics.checkNotNullExpressionValue(sharedElementTargetNames2, "lastIn.fragment.sharedElementTargetNames");
                if (z3) {
                    operation.getFragment().getEnterTransitionCallback();
                    operation2.getFragment().getExitTransitionCallback();
                    pair = TuplesKt.to(null, null);
                } else {
                    operation.getFragment().getExitTransitionCallback();
                    operation2.getFragment().getEnterTransitionCallback();
                    pair = TuplesKt.to(null, null);
                }
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(pair.component1());
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(pair.component2());
                int i3 = 0;
                for (int size2 = sharedElementSourceNames.size(); i3 < size2; size2 = size2) {
                    arrayMap.put(sharedElementSourceNames.get(i3), sharedElementTargetNames2.get(i3));
                    i3++;
                }
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", ">>> entering view names <<<");
                    for (Iterator<String> it3 = sharedElementTargetNames2.iterator(); it3.hasNext(); it3 = it3) {
                        Log.v("FragmentManager", "Name: " + it3.next());
                    }
                    Log.v("FragmentManager", ">>> exiting view names <<<");
                    for (Iterator<String> it4 = sharedElementSourceNames.iterator(); it4.hasNext(); it4 = it4) {
                        Log.v("FragmentManager", "Name: " + it4.next());
                    }
                }
                ArrayMap arrayMap2 = new ArrayMap();
                View view10 = operation.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view10, "firstOut.fragment.mView");
                defaultSpecialEffectsController.findNamedViews(arrayMap2, view10);
                arrayMap2.retainAll(sharedElementSourceNames);
                arrayMap.retainAll(arrayMap2.keySet());
                final ArrayMap arrayMap3 = new ArrayMap();
                View view11 = operation2.getFragment().mView;
                Intrinsics.checkNotNullExpressionValue(view11, "lastIn.fragment.mView");
                defaultSpecialEffectsController.findNamedViews(arrayMap3, view11);
                arrayMap3.retainAll(sharedElementTargetNames2);
                arrayMap3.retainAll(arrayMap.values());
                FragmentTransition.retainValues(arrayMap, arrayMap3);
                Set setKeySet = arrayMap.keySet();
                Intrinsics.checkNotNullExpressionValue(setKeySet, "sharedElementNameMapping.keys");
                defaultSpecialEffectsController.retainMatchingViews(arrayMap2, setKeySet);
                Collection collectionValues = arrayMap.values();
                Intrinsics.checkNotNullExpressionValue(collectionValues, "sharedElementNameMapping.values");
                defaultSpecialEffectsController.retainMatchingViews(arrayMap3, collectionValues);
                if (arrayMap.isEmpty()) {
                    arrayList4.clear();
                    arrayList5.clear();
                    view7 = view8;
                    linkedHashMap2 = linkedHashMap3;
                    view6 = view9;
                    obj7 = null;
                } else {
                    ArrayMap arrayMap4 = arrayMap;
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z3, arrayMap2, true);
                    OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.startTransitions$lambda$9(operation2, operation, z3, arrayMap3);
                        }
                    });
                    arrayList4.addAll(arrayMap2.values());
                    if (sharedElementSourceNames.isEmpty()) {
                        view4 = view8;
                    } else {
                        view4 = (View) arrayMap2.get(sharedElementSourceNames.get(0));
                        fragmentTransitionImpl.setEpicenter(objWrapTransitionInSet, view4);
                    }
                    arrayList5.addAll(arrayMap3.values());
                    if (!sharedElementTargetNames2.isEmpty() && (view5 = (View) arrayMap3.get(sharedElementTargetNames2.get(0))) != null) {
                        OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda4
                            @Override // java.lang.Runnable
                            public final void run() {
                                DefaultSpecialEffectsController.startTransitions$lambda$10(fragmentTransitionImpl, view5, rect2);
                            }
                        });
                        z4 = true;
                    }
                    fragmentTransitionImpl.setSharedElementTargets(objWrapTransitionInSet, view9, arrayList4);
                    ArrayList arrayList7 = arrayList5;
                    rect = rect2;
                    fragmentTransitionImpl.scheduleRemoveTargets(objWrapTransitionInSet, null, null, null, null, objWrapTransitionInSet, arrayList7);
                    Boolean bool = Boolean.TRUE;
                    linkedHashMap3.put(operation, bool);
                    linkedHashMap3.put(operation2, bool);
                    view7 = view4;
                    arrayList4 = arrayList4;
                    arrayMap = arrayMap4;
                    obj7 = objWrapTransitionInSet;
                    arrayList5 = arrayList7;
                    view6 = view9;
                    linkedHashMap2 = linkedHashMap3;
                    rect2 = rect;
                    z3 = z;
                }
            }
        }
        View view12 = view7;
        ArrayMap arrayMap5 = arrayMap;
        ArrayList arrayList8 = arrayList5;
        ArrayList arrayList9 = arrayList4;
        Rect rect3 = rect2;
        boolean z5 = true;
        LinkedHashMap linkedHashMap4 = linkedHashMap2;
        View view13 = view6;
        ArrayList arrayList10 = new ArrayList();
        Iterator it5 = list.iterator();
        Object objMergeTransitionsTogether = null;
        Object objMergeTransitionsTogether2 = null;
        while (it5.hasNext()) {
            TransitionInfo transitionInfo4 = (TransitionInfo) it5.next();
            if (transitionInfo4.isVisibilityUnchanged()) {
                linkedHashMap4.put(transitionInfo4.getOperation(), Boolean.FALSE);
                transitionInfo4.completeSpecialEffect();
            } else {
                Object objCloneTransition = fragmentTransitionImpl.cloneTransition(transitionInfo4.getTransition());
                SpecialEffectsController.Operation operation4 = transitionInfo4.getOperation();
                boolean z6 = (obj7 == null || !(operation4 == operation || operation4 == operation2)) ? false : z5;
                if (objCloneTransition != null) {
                    LinkedHashMap linkedHashMap5 = linkedHashMap4;
                    ArrayList arrayList11 = new ArrayList();
                    View view14 = operation4.getFragment().mView;
                    Object obj8 = obj7;
                    Intrinsics.checkNotNullExpressionValue(view14, "operation.fragment.mView");
                    defaultSpecialEffectsController.captureTransitioningViews(arrayList11, view14);
                    if (z6) {
                        if (operation4 == operation) {
                            arrayList11.removeAll(CollectionsKt.toSet(arrayList9));
                        } else {
                            arrayList11.removeAll(CollectionsKt.toSet(arrayList8));
                        }
                    }
                    if (arrayList11.isEmpty()) {
                        fragmentTransitionImpl.addTarget(objCloneTransition, view13);
                        view2 = view13;
                        obj4 = objCloneTransition;
                        str3 = str;
                        obj2 = objMergeTransitionsTogether;
                        obj3 = objMergeTransitionsTogether2;
                        arrayList = arrayList11;
                        view = view12;
                        linkedHashMap = linkedHashMap5;
                        obj = obj8;
                        z2 = true;
                        operation3 = operation4;
                    } else {
                        fragmentTransitionImpl.addTargets(objCloneTransition, arrayList11);
                        view = view12;
                        obj = obj8;
                        str3 = str;
                        z2 = true;
                        obj2 = objMergeTransitionsTogether;
                        obj3 = objMergeTransitionsTogether2;
                        view2 = view13;
                        arrayList = arrayList11;
                        linkedHashMap = linkedHashMap5;
                        fragmentTransitionImpl.scheduleRemoveTargets(objCloneTransition, objCloneTransition, arrayList11, null, null, null, null);
                        if (operation4.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation3 = operation4;
                            list2.remove(operation3);
                            ArrayList arrayList12 = new ArrayList(arrayList);
                            arrayList12.remove(operation3.getFragment().mView);
                            obj4 = objCloneTransition;
                            fragmentTransitionImpl.scheduleHideFragmentView(obj4, operation3.getFragment().mView, arrayList12);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    DefaultSpecialEffectsController.startTransitions$lambda$11(arrayList);
                                }
                            });
                        } else {
                            obj4 = objCloneTransition;
                            operation3 = operation4;
                        }
                    }
                    if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList10.addAll(arrayList);
                        if (z4) {
                            fragmentTransitionImpl.setEpicenter(obj4, rect3);
                        }
                        view3 = view;
                    } else {
                        view3 = view;
                        fragmentTransitionImpl.setEpicenter(obj4, view3);
                    }
                    linkedHashMap.put(operation3, Boolean.TRUE);
                    if (transitionInfo4.isOverlapAllowed()) {
                        objMergeTransitionsTogether2 = fragmentTransitionImpl.mergeTransitionsTogether(obj3, obj4, null);
                        linkedHashMap4 = linkedHashMap;
                        view12 = view3;
                        view13 = view2;
                        z5 = z2;
                        obj7 = obj;
                        str = str3;
                        objMergeTransitionsTogether = obj2;
                    } else {
                        objMergeTransitionsTogether2 = obj3;
                        objMergeTransitionsTogether = fragmentTransitionImpl.mergeTransitionsTogether(obj2, obj4, null);
                        linkedHashMap4 = linkedHashMap;
                        view12 = view3;
                        view13 = view2;
                        z5 = z2;
                        obj7 = obj;
                        str = str3;
                    }
                    defaultSpecialEffectsController = this;
                } else if (!z6) {
                    linkedHashMap4.put(operation4, Boolean.FALSE);
                    transitionInfo4.completeSpecialEffect();
                }
            }
        }
        String str4 = str;
        boolean z7 = z5;
        LinkedHashMap linkedHashMap6 = linkedHashMap4;
        Object obj9 = obj7;
        Object objMergeTransitionsInSequence = fragmentTransitionImpl.mergeTransitionsInSequence(objMergeTransitionsTogether2, objMergeTransitionsTogether, obj9);
        if (objMergeTransitionsInSequence == null) {
            return linkedHashMap6;
        }
        ArrayList<TransitionInfo> arrayList13 = new ArrayList();
        for (Object obj10 : list) {
            if (!((TransitionInfo) obj10).isVisibilityUnchanged()) {
                arrayList13.add(obj10);
            }
        }
        for (final TransitionInfo transitionInfo5 : arrayList13) {
            Object transition = transitionInfo5.getTransition();
            final SpecialEffectsController.Operation operation5 = transitionInfo5.getOperation();
            boolean z8 = (obj9 == null || !(operation5 == operation || operation5 == operation2)) ? false : z7;
            if (transition == null && !z8) {
                str2 = str4;
            } else if (ViewCompat.isLaidOut(getContainer())) {
                str2 = str4;
                fragmentTransitionImpl.setListenerForTransitionEnd(transitionInfo5.getOperation().getFragment(), objMergeTransitionsInSequence, transitionInfo5.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultSpecialEffectsController.startTransitions$lambda$14$lambda$13(transitionInfo5, operation5);
                    }
                });
            } else {
                if (FragmentManager.isLoggingEnabled(2)) {
                    str2 = str4;
                    Log.v(str2, "SpecialEffectsController: Container " + getContainer() + " has not been laid out. Completing operation " + operation5);
                } else {
                    str2 = str4;
                }
                transitionInfo5.completeSpecialEffect();
            }
            str4 = str2;
        }
        String str5 = str4;
        if (!ViewCompat.isLaidOut(getContainer())) {
            return linkedHashMap6;
        }
        FragmentTransition.setViewVisibility(arrayList10, 4);
        ArrayList arrayListPrepareSetNameOverridesReordered = fragmentTransitionImpl.prepareSetNameOverridesReordered(arrayList8);
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(str5, ">>>>> Beginning transition <<<<<");
            Log.v(str5, ">>>>> SharedElementFirstOutViews <<<<<");
            for (Object sharedElementFirstOutViews : arrayList9) {
                Intrinsics.checkNotNullExpressionValue(sharedElementFirstOutViews, "sharedElementFirstOutViews");
                View view15 = (View) sharedElementFirstOutViews;
                Log.v(str5, "View: " + view15 + " Name: " + ViewCompat.getTransitionName(view15));
            }
            Log.v(str5, ">>>>> SharedElementLastInViews <<<<<");
            for (Object sharedElementLastInViews : arrayList8) {
                Intrinsics.checkNotNullExpressionValue(sharedElementLastInViews, "sharedElementLastInViews");
                View view16 = (View) sharedElementLastInViews;
                Log.v(str5, "View: " + view16 + " Name: " + ViewCompat.getTransitionName(view16));
            }
        }
        fragmentTransitionImpl.beginDelayedTransition(getContainer(), objMergeTransitionsInSequence);
        fragmentTransitionImpl.setNameOverridesReordered(getContainer(), arrayList9, arrayList8, arrayListPrepareSetNameOverridesReordered, arrayMap5);
        FragmentTransition.setViewVisibility(arrayList10, 0);
        fragmentTransitionImpl.swapSharedElementTargets(obj9, arrayList9, arrayList8);
        return linkedHashMap6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$9(SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, boolean z, ArrayMap lastInViews) {
        Intrinsics.checkNotNullParameter(lastInViews, "$lastInViews");
        FragmentTransition.callSharedElementStartEnd(operation.getFragment(), operation2.getFragment(), z, lastInViews, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$10(FragmentTransitionImpl impl, View view, Rect lastInEpicenterRect) {
        Intrinsics.checkNotNullParameter(impl, "$impl");
        Intrinsics.checkNotNullParameter(lastInEpicenterRect, "$lastInEpicenterRect");
        impl.getBoundsOnScreen(view, lastInEpicenterRect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$11(ArrayList transitioningViews) {
        Intrinsics.checkNotNullParameter(transitioningViews, "$transitioningViews");
        FragmentTransition.setViewVisibility(transitioningViews, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startTransitions$lambda$14$lambda$13(TransitionInfo transitionInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(transitionInfo, "$transitionInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        transitionInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Transition for operation " + operation + " has completed");
        }
    }

    private final void retainMatchingViews(ArrayMap arrayMap, final Collection collection) {
        Set entries = arrayMap.entrySet();
        Intrinsics.checkNotNullExpressionValue(entries, "entries");
        CollectionsKt.retainAll(entries, new Function1() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.retainMatchingViews.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Map.Entry entry) {
                Intrinsics.checkNotNullParameter(entry, "entry");
                return Boolean.valueOf(CollectionsKt.contains(collection, ViewCompat.getTransitionName((View) entry.getValue())));
            }
        });
    }

    private final void captureTransitioningViews(ArrayList arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                if (arrayList.contains(view)) {
                    return;
                }
                arrayList.add(view);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = viewGroup.getChildAt(i);
                if (child.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(child, "child");
                    captureTransitioningViews(arrayList, child);
                }
            }
            return;
        }
        if (arrayList.contains(view)) {
            return;
        }
        arrayList.add(view);
    }

    private final void findNamedViews(Map map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = viewGroup.getChildAt(i);
                if (child.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(child, "child");
                    findNamedViews(map, child);
                }
            }
        }
    }

    private final void applyContainerChanges(SpecialEffectsController.Operation operation) {
        View view = operation.getFragment().mView;
        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
        Intrinsics.checkNotNullExpressionValue(view, "view");
        finalState.applyState(view);
    }

    /* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation operation;
        private final CancellationSignal signal;

        public SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal signal) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            this.operation = operation;
            this.signal = signal;
        }

        public final SpecialEffectsController.Operation getOperation() {
            return this.operation;
        }

        public final CancellationSignal getSignal() {
            return this.signal;
        }

        public final boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State state;
            SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.Companion;
            View view = this.operation.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
            SpecialEffectsController.Operation.State stateAsOperationState = companion.asOperationState(view);
            SpecialEffectsController.Operation.State finalState = this.operation.getFinalState();
            return stateAsOperationState == finalState || !(stateAsOperationState == (state = SpecialEffectsController.Operation.State.VISIBLE) || finalState == state);
        }

        public final void completeSpecialEffect() {
            this.operation.completeSpecialEffect(this.signal);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
    static final class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator animation;
        private boolean isAnimLoaded;
        private final boolean isPop;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean z) {
            super(operation, signal);
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            this.isPop = z;
        }

        public final FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this.isAnimLoaded) {
                return this.animation;
            }
            FragmentAnim.AnimationOrAnimator animationOrAnimatorLoadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.isPop);
            this.animation = animationOrAnimatorLoadAnimation;
            this.isAnimLoaded = true;
            return animationOrAnimatorLoadAnimation;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: DefaultSpecialEffectsController.kt */
    static final class TransitionInfo extends SpecialEffectsInfo {
        private final boolean isOverlapAllowed;
        private final Object sharedElementTransition;
        private final Object transition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal signal, boolean z, boolean z2) {
            Object returnTransition;
            boolean allowEnterTransitionOverlap;
            Object sharedElementEnterTransition;
            super(operation, signal);
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(signal, "signal");
            SpecialEffectsController.Operation.State finalState = operation.getFinalState();
            SpecialEffectsController.Operation.State state = SpecialEffectsController.Operation.State.VISIBLE;
            if (finalState == state) {
                Fragment fragment = operation.getFragment();
                returnTransition = z ? fragment.getReenterTransition() : fragment.getEnterTransition();
            } else {
                Fragment fragment2 = operation.getFragment();
                returnTransition = z ? fragment2.getReturnTransition() : fragment2.getExitTransition();
            }
            this.transition = returnTransition;
            if (operation.getFinalState() != state) {
                allowEnterTransitionOverlap = true;
            } else if (z) {
                allowEnterTransitionOverlap = operation.getFragment().getAllowReturnTransitionOverlap();
            } else {
                allowEnterTransitionOverlap = operation.getFragment().getAllowEnterTransitionOverlap();
            }
            this.isOverlapAllowed = allowEnterTransitionOverlap;
            if (!z2) {
                sharedElementEnterTransition = null;
            } else if (z) {
                sharedElementEnterTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                sharedElementEnterTransition = operation.getFragment().getSharedElementEnterTransition();
            }
            this.sharedElementTransition = sharedElementEnterTransition;
        }

        public final Object getTransition() {
            return this.transition;
        }

        public final boolean isOverlapAllowed() {
            return this.isOverlapAllowed;
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final boolean hasSharedElementTransition() {
            return this.sharedElementTransition != null;
        }

        public final FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.transition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.sharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl == null ? handlingImpl2 : handlingImpl;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.transition + " which uses a different Transition  type than its shared element transition " + this.sharedElementTransition).toString());
        }

        private final FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = FragmentTransition.PLATFORM_IMPL;
            if (fragmentTransitionImpl != null && fragmentTransitionImpl.canHandle(obj)) {
                return fragmentTransitionImpl;
            }
            FragmentTransitionImpl fragmentTransitionImpl2 = FragmentTransition.SUPPORT_IMPL;
            if (fragmentTransitionImpl2 != null && fragmentTransitionImpl2.canHandle(obj)) {
                return fragmentTransitionImpl2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
