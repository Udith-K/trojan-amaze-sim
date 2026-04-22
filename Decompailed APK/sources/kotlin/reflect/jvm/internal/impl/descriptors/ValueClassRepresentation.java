package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;

/* JADX INFO: compiled from: ValueClassRepresentation.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ValueClassRepresentation {
    public /* synthetic */ ValueClassRepresentation(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract boolean containsPropertyWithName(Name name);

    private ValueClassRepresentation() {
    }

    public final ValueClassRepresentation mapUnderlyingType(Function1 transform) {
        Intrinsics.checkNotNullParameter(transform, "transform");
        if (this instanceof InlineClassRepresentation) {
            InlineClassRepresentation inlineClassRepresentation = (InlineClassRepresentation) this;
            return new InlineClassRepresentation(inlineClassRepresentation.getUnderlyingPropertyName(), (SimpleTypeMarker) transform.invoke(inlineClassRepresentation.getUnderlyingType()));
        }
        if (!(this instanceof MultiFieldValueClassRepresentation)) {
            throw new NoWhenBranchMatchedException();
        }
        List<Pair> underlyingPropertyNamesToTypes = ((MultiFieldValueClassRepresentation) this).getUnderlyingPropertyNamesToTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(underlyingPropertyNamesToTypes, 10));
        for (Pair pair : underlyingPropertyNamesToTypes) {
            arrayList.add(TuplesKt.to((Name) pair.component1(), transform.invoke((SimpleTypeMarker) pair.component2())));
        }
        return new MultiFieldValueClassRepresentation(arrayList);
    }
}
