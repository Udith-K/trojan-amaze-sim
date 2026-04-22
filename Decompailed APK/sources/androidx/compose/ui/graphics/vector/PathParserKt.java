package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.vector.PathNode;
import java.util.List;

/* JADX INFO: compiled from: PathParser.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PathParserKt {
    private static final float[] EmptyArray = new float[0];

    public static final Path toPath(List list, Path path) {
        PathNode pathNode;
        float f;
        float f2;
        float x1;
        float y1;
        float x2;
        float y2;
        float f3;
        float f4;
        float dy2;
        float dy;
        int iMo1240getFillTypeRgk1Os = path.mo1240getFillTypeRgk1Os();
        path.rewind();
        path.mo1242setFillTypeoQ8Xj4U(iMo1240getFillTypeRgk1Os);
        PathNode pathNode2 = list.isEmpty() ? PathNode.Close.INSTANCE : (PathNode) list.get(0);
        int size = list.size();
        float f5 = 0.0f;
        int i = 0;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float x = 0.0f;
        float y = 0.0f;
        float dx2 = 0.0f;
        float y3 = 0.0f;
        while (i < size) {
            PathNode pathNode3 = (PathNode) list.get(i);
            if (pathNode3 instanceof PathNode.Close) {
                path.close();
                pathNode = pathNode3;
                f6 = x;
                dx2 = f6;
                f7 = y;
                y3 = f7;
            } else if (pathNode3 instanceof PathNode.MoveTo) {
                PathNode.MoveTo moveTo = (PathNode.MoveTo) pathNode3;
                x = moveTo.getX();
                y = moveTo.getY();
                path.moveTo(moveTo.getX(), moveTo.getY());
                pathNode = pathNode3;
                dx2 = x;
                y3 = y;
            } else {
                if (pathNode3 instanceof PathNode.RelativeLineTo) {
                    PathNode.RelativeLineTo relativeLineTo = (PathNode.RelativeLineTo) pathNode3;
                    path.relativeLineTo(relativeLineTo.getDx(), relativeLineTo.getDy());
                    dx2 += relativeLineTo.getDx();
                    dy = relativeLineTo.getDy();
                } else {
                    if (pathNode3 instanceof PathNode.LineTo) {
                        PathNode.LineTo lineTo = (PathNode.LineTo) pathNode3;
                        path.lineTo(lineTo.getX(), lineTo.getY());
                        float x3 = lineTo.getX();
                        y3 = lineTo.getY();
                        dx2 = x3;
                    } else if (pathNode3 instanceof PathNode.RelativeHorizontalTo) {
                        PathNode.RelativeHorizontalTo relativeHorizontalTo = (PathNode.RelativeHorizontalTo) pathNode3;
                        path.relativeLineTo(relativeHorizontalTo.getDx(), f5);
                        dx2 += relativeHorizontalTo.getDx();
                    } else if (pathNode3 instanceof PathNode.HorizontalTo) {
                        PathNode.HorizontalTo horizontalTo = (PathNode.HorizontalTo) pathNode3;
                        path.lineTo(horizontalTo.getX(), y3);
                        dx2 = horizontalTo.getX();
                    } else if (pathNode3 instanceof PathNode.RelativeVerticalTo) {
                        PathNode.RelativeVerticalTo relativeVerticalTo = (PathNode.RelativeVerticalTo) pathNode3;
                        path.relativeLineTo(f5, relativeVerticalTo.getDy());
                        dy = relativeVerticalTo.getDy();
                    } else if (pathNode3 instanceof PathNode.VerticalTo) {
                        PathNode.VerticalTo verticalTo = (PathNode.VerticalTo) pathNode3;
                        path.lineTo(dx2, verticalTo.getY());
                        y3 = verticalTo.getY();
                    } else {
                        if (pathNode3 instanceof PathNode.RelativeCurveTo) {
                            PathNode.RelativeCurveTo relativeCurveTo = (PathNode.RelativeCurveTo) pathNode3;
                            pathNode = pathNode3;
                            path.relativeCubicTo(relativeCurveTo.getDx1(), relativeCurveTo.getDy1(), relativeCurveTo.getDx2(), relativeCurveTo.getDy2(), relativeCurveTo.getDx3(), relativeCurveTo.getDy3());
                            x1 = relativeCurveTo.getDx2() + dx2;
                            y1 = relativeCurveTo.getDy2() + y3;
                            dx2 += relativeCurveTo.getDx3();
                            dy2 = relativeCurveTo.getDy3();
                        } else {
                            pathNode = pathNode3;
                            if (pathNode instanceof PathNode.CurveTo) {
                                PathNode.CurveTo curveTo = (PathNode.CurveTo) pathNode;
                                path.cubicTo(curveTo.getX1(), curveTo.getY1(), curveTo.getX2(), curveTo.getY2(), curveTo.getX3(), curveTo.getY3());
                                x1 = curveTo.getX2();
                                y1 = curveTo.getY2();
                                x2 = curveTo.getX3();
                                y2 = curveTo.getY3();
                            } else if (pathNode instanceof PathNode.RelativeReflectiveCurveTo) {
                                if (pathNode2.isCurve()) {
                                    f4 = y3 - f7;
                                    f3 = dx2 - f6;
                                } else {
                                    f3 = 0.0f;
                                    f4 = 0.0f;
                                }
                                PathNode.RelativeReflectiveCurveTo relativeReflectiveCurveTo = (PathNode.RelativeReflectiveCurveTo) pathNode;
                                path.relativeCubicTo(f3, f4, relativeReflectiveCurveTo.getDx1(), relativeReflectiveCurveTo.getDy1(), relativeReflectiveCurveTo.getDx2(), relativeReflectiveCurveTo.getDy2());
                                x1 = relativeReflectiveCurveTo.getDx1() + dx2;
                                y1 = relativeReflectiveCurveTo.getDy1() + y3;
                                dx2 += relativeReflectiveCurveTo.getDx2();
                                dy2 = relativeReflectiveCurveTo.getDy2();
                            } else if (pathNode instanceof PathNode.ReflectiveCurveTo) {
                                if (pathNode2.isCurve()) {
                                    float f8 = 2;
                                    f2 = (f8 * y3) - f7;
                                    f = (dx2 * f8) - f6;
                                } else {
                                    f = dx2;
                                    f2 = y3;
                                }
                                PathNode.ReflectiveCurveTo reflectiveCurveTo = (PathNode.ReflectiveCurveTo) pathNode;
                                path.cubicTo(f, f2, reflectiveCurveTo.getX1(), reflectiveCurveTo.getY1(), reflectiveCurveTo.getX2(), reflectiveCurveTo.getY2());
                                x1 = reflectiveCurveTo.getX1();
                                y1 = reflectiveCurveTo.getY1();
                                x2 = reflectiveCurveTo.getX2();
                                y2 = reflectiveCurveTo.getY2();
                            }
                            dx2 = x2;
                            y3 = y2;
                            f7 = y1;
                            f6 = x1;
                        }
                        y3 += dy2;
                        f7 = y1;
                        f6 = x1;
                    }
                    pathNode = pathNode3;
                }
                y3 += dy;
                pathNode = pathNode3;
            }
            i++;
            pathNode2 = pathNode;
            f5 = 0.0f;
        }
        return path;
    }
}
