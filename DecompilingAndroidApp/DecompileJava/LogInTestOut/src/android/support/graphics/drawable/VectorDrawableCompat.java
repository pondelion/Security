// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.graphics.drawable:
//            VectorDrawableCommon, TypedArrayUtils, AndroidResources, PathParser

public class VectorDrawableCompat extends VectorDrawableCommon
{
    private static class VClipPath extends VPath
    {

        private void updateStateFromTypedArray(TypedArray typedarray)
        {
            String s = typedarray.getString(0);
            if(s != null)
                mPathName = s;
            typedarray = typedarray.getString(1);
            if(typedarray != null)
                mNodes = PathParser.createNodesFromPathData(typedarray);
        }

        public void inflate(Resources resources, AttributeSet attributeset, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser)
        {
            if(!TypedArrayUtils.hasAttribute(xmlpullparser, "pathData"))
            {
                return;
            } else
            {
                resources = VectorDrawableCommon.obtainAttributes(resources, theme, attributeset, AndroidResources.styleable_VectorDrawableClipPath);
                updateStateFromTypedArray(resources);
                resources.recycle();
                return;
            }
        }

        public boolean isClipPath()
        {
            return true;
        }

        public VClipPath()
        {
        }

        public VClipPath(VClipPath vclippath)
        {
            super(vclippath);
        }
    }

    private static class VFullPath extends VPath
    {

        private android.graphics.Paint.Cap getStrokeLineCap(int i, android.graphics.Paint.Cap cap)
        {
            switch(i)
            {
            default:
                return cap;

            case 0: // '\0'
                return android.graphics.Paint.Cap.BUTT;

            case 1: // '\001'
                return android.graphics.Paint.Cap.ROUND;

            case 2: // '\002'
                return android.graphics.Paint.Cap.SQUARE;
            }
        }

        private android.graphics.Paint.Join getStrokeLineJoin(int i, android.graphics.Paint.Join join)
        {
            switch(i)
            {
            default:
                return join;

            case 0: // '\0'
                return android.graphics.Paint.Join.MITER;

            case 1: // '\001'
                return android.graphics.Paint.Join.ROUND;

            case 2: // '\002'
                return android.graphics.Paint.Join.BEVEL;
            }
        }

        private void updateStateFromTypedArray(TypedArray typedarray, XmlPullParser xmlpullparser)
        {
            mThemeAttrs = null;
            if(!TypedArrayUtils.hasAttribute(xmlpullparser, "pathData"))
                return;
            String s = typedarray.getString(0);
            if(s != null)
                mPathName = s;
            s = typedarray.getString(2);
            if(s != null)
                mNodes = PathParser.createNodesFromPathData(s);
            mFillColor = TypedArrayUtils.getNamedColor(typedarray, xmlpullparser, "fillColor", 1, mFillColor);
            mFillAlpha = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "fillAlpha", 12, mFillAlpha);
            mStrokeLineCap = getStrokeLineCap(TypedArrayUtils.getNamedInt(typedarray, xmlpullparser, "strokeLineCap", 8, -1), mStrokeLineCap);
            mStrokeLineJoin = getStrokeLineJoin(TypedArrayUtils.getNamedInt(typedarray, xmlpullparser, "strokeLineJoin", 9, -1), mStrokeLineJoin);
            mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "strokeMiterLimit", 10, mStrokeMiterlimit);
            mStrokeColor = TypedArrayUtils.getNamedColor(typedarray, xmlpullparser, "strokeColor", 3, mStrokeColor);
            mStrokeAlpha = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "strokeAlpha", 11, mStrokeAlpha);
            mStrokeWidth = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "strokeWidth", 4, mStrokeWidth);
            mTrimPathEnd = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "trimPathEnd", 6, mTrimPathEnd);
            mTrimPathOffset = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "trimPathOffset", 7, mTrimPathOffset);
            mTrimPathStart = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "trimPathStart", 5, mTrimPathStart);
        }

        public void applyTheme(android.content.res.Resources.Theme theme)
        {
            if(mThemeAttrs != null);
        }

        public boolean canApplyTheme()
        {
            return mThemeAttrs != null;
        }

        float getFillAlpha()
        {
            return mFillAlpha;
        }

        int getFillColor()
        {
            return mFillColor;
        }

        float getStrokeAlpha()
        {
            return mStrokeAlpha;
        }

        int getStrokeColor()
        {
            return mStrokeColor;
        }

        float getStrokeWidth()
        {
            return mStrokeWidth;
        }

        float getTrimPathEnd()
        {
            return mTrimPathEnd;
        }

        float getTrimPathOffset()
        {
            return mTrimPathOffset;
        }

        float getTrimPathStart()
        {
            return mTrimPathStart;
        }

        public void inflate(Resources resources, AttributeSet attributeset, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser)
        {
            resources = VectorDrawableCommon.obtainAttributes(resources, theme, attributeset, AndroidResources.styleable_VectorDrawablePath);
            updateStateFromTypedArray(resources, xmlpullparser);
            resources.recycle();
        }

        void setFillAlpha(float f)
        {
            mFillAlpha = f;
        }

        void setFillColor(int i)
        {
            mFillColor = i;
        }

        void setStrokeAlpha(float f)
        {
            mStrokeAlpha = f;
        }

        void setStrokeColor(int i)
        {
            mStrokeColor = i;
        }

        void setStrokeWidth(float f)
        {
            mStrokeWidth = f;
        }

        void setTrimPathEnd(float f)
        {
            mTrimPathEnd = f;
        }

        void setTrimPathOffset(float f)
        {
            mTrimPathOffset = f;
        }

        void setTrimPathStart(float f)
        {
            mTrimPathStart = f;
        }

        float mFillAlpha;
        int mFillColor;
        int mFillRule;
        float mStrokeAlpha;
        int mStrokeColor;
        android.graphics.Paint.Cap mStrokeLineCap;
        android.graphics.Paint.Join mStrokeLineJoin;
        float mStrokeMiterlimit;
        float mStrokeWidth;
        private int mThemeAttrs[];
        float mTrimPathEnd;
        float mTrimPathOffset;
        float mTrimPathStart;

        public VFullPath()
        {
            mStrokeColor = 0;
            mStrokeWidth = 0.0F;
            mFillColor = 0;
            mStrokeAlpha = 1.0F;
            mFillAlpha = 1.0F;
            mTrimPathStart = 0.0F;
            mTrimPathEnd = 1.0F;
            mTrimPathOffset = 0.0F;
            mStrokeLineCap = android.graphics.Paint.Cap.BUTT;
            mStrokeLineJoin = android.graphics.Paint.Join.MITER;
            mStrokeMiterlimit = 4F;
        }

        public VFullPath(VFullPath vfullpath)
        {
            super(vfullpath);
            mStrokeColor = 0;
            mStrokeWidth = 0.0F;
            mFillColor = 0;
            mStrokeAlpha = 1.0F;
            mFillAlpha = 1.0F;
            mTrimPathStart = 0.0F;
            mTrimPathEnd = 1.0F;
            mTrimPathOffset = 0.0F;
            mStrokeLineCap = android.graphics.Paint.Cap.BUTT;
            mStrokeLineJoin = android.graphics.Paint.Join.MITER;
            mStrokeMiterlimit = 4F;
            mThemeAttrs = vfullpath.mThemeAttrs;
            mStrokeColor = vfullpath.mStrokeColor;
            mStrokeWidth = vfullpath.mStrokeWidth;
            mStrokeAlpha = vfullpath.mStrokeAlpha;
            mFillColor = vfullpath.mFillColor;
            mFillRule = vfullpath.mFillRule;
            mFillAlpha = vfullpath.mFillAlpha;
            mTrimPathStart = vfullpath.mTrimPathStart;
            mTrimPathEnd = vfullpath.mTrimPathEnd;
            mTrimPathOffset = vfullpath.mTrimPathOffset;
            mStrokeLineCap = vfullpath.mStrokeLineCap;
            mStrokeLineJoin = vfullpath.mStrokeLineJoin;
            mStrokeMiterlimit = vfullpath.mStrokeMiterlimit;
        }
    }

    private static class VGroup
    {

        private void updateLocalMatrix()
        {
            mLocalMatrix.reset();
            mLocalMatrix.postTranslate(-mPivotX, -mPivotY);
            mLocalMatrix.postScale(mScaleX, mScaleY);
            mLocalMatrix.postRotate(mRotate, 0.0F, 0.0F);
            mLocalMatrix.postTranslate(mTranslateX + mPivotX, mTranslateY + mPivotY);
        }

        private void updateStateFromTypedArray(TypedArray typedarray, XmlPullParser xmlpullparser)
        {
            mThemeAttrs = null;
            mRotate = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "rotation", 5, mRotate);
            mPivotX = typedarray.getFloat(1, mPivotX);
            mPivotY = typedarray.getFloat(2, mPivotY);
            mScaleX = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "scaleX", 3, mScaleX);
            mScaleY = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "scaleY", 4, mScaleY);
            mTranslateX = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "translateX", 6, mTranslateX);
            mTranslateY = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "translateY", 7, mTranslateY);
            typedarray = typedarray.getString(0);
            if(typedarray != null)
                mGroupName = typedarray;
            updateLocalMatrix();
        }

        public String getGroupName()
        {
            return mGroupName;
        }

        public Matrix getLocalMatrix()
        {
            return mLocalMatrix;
        }

        public float getPivotX()
        {
            return mPivotX;
        }

        public float getPivotY()
        {
            return mPivotY;
        }

        public float getRotation()
        {
            return mRotate;
        }

        public float getScaleX()
        {
            return mScaleX;
        }

        public float getScaleY()
        {
            return mScaleY;
        }

        public float getTranslateX()
        {
            return mTranslateX;
        }

        public float getTranslateY()
        {
            return mTranslateY;
        }

        public void inflate(Resources resources, AttributeSet attributeset, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser)
        {
            resources = VectorDrawableCommon.obtainAttributes(resources, theme, attributeset, AndroidResources.styleable_VectorDrawableGroup);
            updateStateFromTypedArray(resources, xmlpullparser);
            resources.recycle();
        }

        public void setPivotX(float f)
        {
            if(f != mPivotX)
            {
                mPivotX = f;
                updateLocalMatrix();
            }
        }

        public void setPivotY(float f)
        {
            if(f != mPivotY)
            {
                mPivotY = f;
                updateLocalMatrix();
            }
        }

        public void setRotation(float f)
        {
            if(f != mRotate)
            {
                mRotate = f;
                updateLocalMatrix();
            }
        }

        public void setScaleX(float f)
        {
            if(f != mScaleX)
            {
                mScaleX = f;
                updateLocalMatrix();
            }
        }

        public void setScaleY(float f)
        {
            if(f != mScaleY)
            {
                mScaleY = f;
                updateLocalMatrix();
            }
        }

        public void setTranslateX(float f)
        {
            if(f != mTranslateX)
            {
                mTranslateX = f;
                updateLocalMatrix();
            }
        }

        public void setTranslateY(float f)
        {
            if(f != mTranslateY)
            {
                mTranslateY = f;
                updateLocalMatrix();
            }
        }

        int mChangingConfigurations;
        final ArrayList mChildren;
        private String mGroupName;
        private final Matrix mLocalMatrix;
        private float mPivotX;
        private float mPivotY;
        float mRotate;
        private float mScaleX;
        private float mScaleY;
        private final Matrix mStackedMatrix;
        private int mThemeAttrs[];
        private float mTranslateX;
        private float mTranslateY;



        public VGroup()
        {
            mStackedMatrix = new Matrix();
            mChildren = new ArrayList();
            mRotate = 0.0F;
            mPivotX = 0.0F;
            mPivotY = 0.0F;
            mScaleX = 1.0F;
            mScaleY = 1.0F;
            mTranslateX = 0.0F;
            mTranslateY = 0.0F;
            mLocalMatrix = new Matrix();
            mGroupName = null;
        }

        public VGroup(VGroup vgroup, ArrayMap arraymap)
        {
            mStackedMatrix = new Matrix();
            mChildren = new ArrayList();
            mRotate = 0.0F;
            mPivotX = 0.0F;
            mPivotY = 0.0F;
            mScaleX = 1.0F;
            mScaleY = 1.0F;
            mTranslateX = 0.0F;
            mTranslateY = 0.0F;
            mLocalMatrix = new Matrix();
            mGroupName = null;
            mRotate = vgroup.mRotate;
            mPivotX = vgroup.mPivotX;
            mPivotY = vgroup.mPivotY;
            mScaleX = vgroup.mScaleX;
            mScaleY = vgroup.mScaleY;
            mTranslateX = vgroup.mTranslateX;
            mTranslateY = vgroup.mTranslateY;
            mThemeAttrs = vgroup.mThemeAttrs;
            mGroupName = vgroup.mGroupName;
            mChangingConfigurations = vgroup.mChangingConfigurations;
            if(mGroupName != null)
                arraymap.put(mGroupName, this);
            mLocalMatrix.set(vgroup.mLocalMatrix);
            ArrayList arraylist = vgroup.mChildren;
            int i = 0;
            do
            {
                if(i < arraylist.size())
                {
                    vgroup = ((VGroup) (arraylist.get(i)));
                    if(vgroup instanceof VGroup)
                    {
                        vgroup = (VGroup)vgroup;
                        mChildren.add(new VGroup(vgroup, arraymap));
                    } else
                    {
                        if(vgroup instanceof VFullPath)
                            vgroup = new VFullPath((VFullPath)vgroup);
                        else
                        if(vgroup instanceof VClipPath)
                            vgroup = new VClipPath((VClipPath)vgroup);
                        else
                            throw new IllegalStateException("Unknown object in the tree!");
                        mChildren.add(vgroup);
                        if(((VPath) (vgroup)).mPathName != null)
                            arraymap.put(((VPath) (vgroup)).mPathName, vgroup);
                    }
                } else
                {
                    return;
                }
                i++;
            } while(true);
        }
    }

    private static class VPath
    {

        public String NodesToString(PathParser.PathDataNode apathdatanode[])
        {
            String s = " ";
            for(int i = 0; i < apathdatanode.length; i++)
            {
                s = (new StringBuilder()).append(s).append(apathdatanode[i].type).append(":").toString();
                float af[] = apathdatanode[i].params;
                for(int j = 0; j < af.length; j++)
                    s = (new StringBuilder()).append(s).append(af[j]).append(",").toString();

            }

            return s;
        }

        public void applyTheme(android.content.res.Resources.Theme theme)
        {
        }

        public boolean canApplyTheme()
        {
            return false;
        }

        public PathParser.PathDataNode[] getPathData()
        {
            return mNodes;
        }

        public String getPathName()
        {
            return mPathName;
        }

        public boolean isClipPath()
        {
            return false;
        }

        public void printVPath(int i)
        {
            String s = "";
            for(int j = 0; j < i; j++)
                s = (new StringBuilder()).append(s).append("    ").toString();

            Log.v("VectorDrawableCompat", (new StringBuilder()).append(s).append("current path is :").append(mPathName).append(" pathData is ").append(NodesToString(mNodes)).toString());
        }

        public void setPathData(PathParser.PathDataNode apathdatanode[])
        {
            if(!PathParser.canMorph(mNodes, apathdatanode))
            {
                mNodes = PathParser.deepCopyNodes(apathdatanode);
                return;
            } else
            {
                PathParser.updateNodes(mNodes, apathdatanode);
                return;
            }
        }

        public void toPath(Path path)
        {
            path.reset();
            if(mNodes != null)
                PathParser.PathDataNode.nodesToPath(mNodes, path);
        }

        int mChangingConfigurations;
        protected PathParser.PathDataNode mNodes[];
        String mPathName;

        public VPath()
        {
            mNodes = null;
        }

        public VPath(VPath vpath)
        {
            mNodes = null;
            mPathName = vpath.mPathName;
            mChangingConfigurations = vpath.mChangingConfigurations;
            mNodes = PathParser.deepCopyNodes(vpath.mNodes);
        }
    }

    private static class VPathRenderer
    {

        private static float cross(float f, float f1, float f2, float f3)
        {
            return f * f3 - f1 * f2;
        }

        private void drawGroupTree(VGroup vgroup, Matrix matrix, Canvas canvas, int i, int j, ColorFilter colorfilter)
        {
            vgroup.mStackedMatrix.set(matrix);
            vgroup.mStackedMatrix.preConcat(vgroup.mLocalMatrix);
            canvas.save();
            int k = 0;
            while(k < vgroup.mChildren.size()) 
            {
                matrix = ((Matrix) (vgroup.mChildren.get(k)));
                if(matrix instanceof VGroup)
                    drawGroupTree((VGroup)matrix, vgroup.mStackedMatrix, canvas, i, j, colorfilter);
                else
                if(matrix instanceof VPath)
                    drawPath(vgroup, (VPath)matrix, canvas, i, j, colorfilter);
                k++;
            }
            canvas.restore();
        }

        private void drawPath(VGroup vgroup, VPath vpath, Canvas canvas, int i, int j, ColorFilter colorfilter)
        {
            float f1 = (float)i / mViewportWidth;
            float f2 = (float)j / mViewportHeight;
            float f = Math.min(f1, f2);
            vgroup = vgroup.mStackedMatrix;
            mFinalPathMatrix.set(vgroup);
            mFinalPathMatrix.postScale(f1, f2);
            f1 = getMatrixScale(vgroup);
            if(f1 != 0.0F)
            {
                vpath.toPath(mPath);
                Path path = mPath;
                mRenderPath.reset();
                if(vpath.isClipPath())
                {
                    mRenderPath.addPath(path, mFinalPathMatrix);
                    canvas.clipPath(mRenderPath);
                    return;
                }
                vgroup = (VFullPath)vpath;
                if(((VFullPath) (vgroup)).mTrimPathStart != 0.0F || ((VFullPath) (vgroup)).mTrimPathEnd != 1.0F)
                {
                    float f6 = ((VFullPath) (vgroup)).mTrimPathStart;
                    float f7 = ((VFullPath) (vgroup)).mTrimPathOffset;
                    float f4 = ((VFullPath) (vgroup)).mTrimPathEnd;
                    float f5 = ((VFullPath) (vgroup)).mTrimPathOffset;
                    if(mPathMeasure == null)
                        mPathMeasure = new PathMeasure();
                    mPathMeasure.setPath(mPath, false);
                    float f3 = mPathMeasure.getLength();
                    f6 = ((f6 + f7) % 1.0F) * f3;
                    f4 = ((f4 + f5) % 1.0F) * f3;
                    path.reset();
                    if(f6 > f4)
                    {
                        mPathMeasure.getSegment(f6, f3, path, true);
                        mPathMeasure.getSegment(0.0F, f4, path, true);
                    } else
                    {
                        mPathMeasure.getSegment(f6, f4, path, true);
                    }
                    path.rLineTo(0.0F, 0.0F);
                }
                mRenderPath.addPath(path, mFinalPathMatrix);
                if(((VFullPath) (vgroup)).mFillColor != 0)
                {
                    if(mFillPaint == null)
                    {
                        mFillPaint = new Paint();
                        mFillPaint.setStyle(android.graphics.Paint.Style.FILL);
                        mFillPaint.setAntiAlias(true);
                    }
                    vpath = mFillPaint;
                    vpath.setColor(VectorDrawableCompat.applyAlpha(((VFullPath) (vgroup)).mFillColor, ((VFullPath) (vgroup)).mFillAlpha));
                    vpath.setColorFilter(colorfilter);
                    canvas.drawPath(mRenderPath, vpath);
                }
                if(((VFullPath) (vgroup)).mStrokeColor != 0)
                {
                    if(mStrokePaint == null)
                    {
                        mStrokePaint = new Paint();
                        mStrokePaint.setStyle(android.graphics.Paint.Style.STROKE);
                        mStrokePaint.setAntiAlias(true);
                    }
                    vpath = mStrokePaint;
                    if(((VFullPath) (vgroup)).mStrokeLineJoin != null)
                        vpath.setStrokeJoin(((VFullPath) (vgroup)).mStrokeLineJoin);
                    if(((VFullPath) (vgroup)).mStrokeLineCap != null)
                        vpath.setStrokeCap(((VFullPath) (vgroup)).mStrokeLineCap);
                    vpath.setStrokeMiter(((VFullPath) (vgroup)).mStrokeMiterlimit);
                    vpath.setColor(VectorDrawableCompat.applyAlpha(((VFullPath) (vgroup)).mStrokeColor, ((VFullPath) (vgroup)).mStrokeAlpha));
                    vpath.setColorFilter(colorfilter);
                    vpath.setStrokeWidth(((VFullPath) (vgroup)).mStrokeWidth * (f * f1));
                    canvas.drawPath(mRenderPath, vpath);
                    return;
                }
            }
        }

        private float getMatrixScale(Matrix matrix)
        {
            float af[] = new float[4];
            float[] _tmp = af;
            af[0] = 0.0F;
            af[1] = 1.0F;
            af[2] = 1.0F;
            af[3] = 0.0F;
            matrix.mapVectors(af);
            float f = (float)Math.hypot(af[0], af[1]);
            float f2 = (float)Math.hypot(af[2], af[3]);
            float f1 = cross(af[0], af[1], af[2], af[3]);
            f2 = Math.max(f, f2);
            f = 0.0F;
            if(f2 > 0.0F)
                f = Math.abs(f1) / f2;
            return f;
        }

        public void draw(Canvas canvas, int i, int j, ColorFilter colorfilter)
        {
            drawGroupTree(mRootGroup, IDENTITY_MATRIX, canvas, i, j, colorfilter);
        }

        public float getAlpha()
        {
            return (float)getRootAlpha() / 255F;
        }

        public int getRootAlpha()
        {
            return mRootAlpha;
        }

        public void setAlpha(float f)
        {
            setRootAlpha((int)(255F * f));
        }

        public void setRootAlpha(int i)
        {
            mRootAlpha = i;
        }

        private static final Matrix IDENTITY_MATRIX = new Matrix();
        float mBaseHeight;
        float mBaseWidth;
        private int mChangingConfigurations;
        private Paint mFillPaint;
        private final Matrix mFinalPathMatrix;
        private final Path mPath;
        private PathMeasure mPathMeasure;
        private final Path mRenderPath;
        int mRootAlpha;
        final VGroup mRootGroup;
        String mRootName;
        private Paint mStrokePaint;
        final ArrayMap mVGTargetsMap;
        float mViewportHeight;
        float mViewportWidth;




/*
        static Paint access$002(VPathRenderer vpathrenderer, Paint paint)
        {
            vpathrenderer.mFillPaint = paint;
            return paint;
        }

*/



/*
        static Paint access$102(VPathRenderer vpathrenderer, Paint paint)
        {
            vpathrenderer.mStrokePaint = paint;
            return paint;
        }

*/

        public VPathRenderer()
        {
            mFinalPathMatrix = new Matrix();
            mBaseWidth = 0.0F;
            mBaseHeight = 0.0F;
            mViewportWidth = 0.0F;
            mViewportHeight = 0.0F;
            mRootAlpha = 255;
            mRootName = null;
            mVGTargetsMap = new ArrayMap();
            mRootGroup = new VGroup();
            mPath = new Path();
            mRenderPath = new Path();
        }

        public VPathRenderer(VPathRenderer vpathrenderer)
        {
            mFinalPathMatrix = new Matrix();
            mBaseWidth = 0.0F;
            mBaseHeight = 0.0F;
            mViewportWidth = 0.0F;
            mViewportHeight = 0.0F;
            mRootAlpha = 255;
            mRootName = null;
            mVGTargetsMap = new ArrayMap();
            mRootGroup = new VGroup(vpathrenderer.mRootGroup, mVGTargetsMap);
            mPath = new Path(vpathrenderer.mPath);
            mRenderPath = new Path(vpathrenderer.mRenderPath);
            mBaseWidth = vpathrenderer.mBaseWidth;
            mBaseHeight = vpathrenderer.mBaseHeight;
            mViewportWidth = vpathrenderer.mViewportWidth;
            mViewportHeight = vpathrenderer.mViewportHeight;
            mChangingConfigurations = vpathrenderer.mChangingConfigurations;
            mRootAlpha = vpathrenderer.mRootAlpha;
            mRootName = vpathrenderer.mRootName;
            if(vpathrenderer.mRootName != null)
                mVGTargetsMap.put(vpathrenderer.mRootName, this);
        }
    }

    private static class VectorDrawableCompatState extends android.graphics.drawable.Drawable.ConstantState
    {

        public boolean canReuseBitmap(int i, int j)
        {
            return i == mCachedBitmap.getWidth() && j == mCachedBitmap.getHeight();
        }

        public boolean canReuseCache()
        {
            return !mCacheDirty && mCachedTint == mTint && mCachedTintMode == mTintMode && mCachedAutoMirrored == mAutoMirrored && mCachedRootAlpha == mVPathRenderer.getRootAlpha();
        }

        public void createCachedBitmapIfNeeded(int i, int j)
        {
            if(mCachedBitmap == null || !canReuseBitmap(i, j))
            {
                mCachedBitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
                mCacheDirty = true;
            }
        }

        public void drawCachedBitmapWithRootAlpha(Canvas canvas, ColorFilter colorfilter, Rect rect)
        {
            colorfilter = getPaint(colorfilter);
            canvas.drawBitmap(mCachedBitmap, null, rect, colorfilter);
        }

        public int getChangingConfigurations()
        {
            return mChangingConfigurations;
        }

        public Paint getPaint(ColorFilter colorfilter)
        {
            if(!hasTranslucentRoot() && colorfilter == null)
                return null;
            if(mTempPaint == null)
            {
                mTempPaint = new Paint();
                mTempPaint.setFilterBitmap(true);
            }
            mTempPaint.setAlpha(mVPathRenderer.getRootAlpha());
            mTempPaint.setColorFilter(colorfilter);
            return mTempPaint;
        }

        public boolean hasTranslucentRoot()
        {
            return mVPathRenderer.getRootAlpha() < 255;
        }

        public Drawable newDrawable()
        {
            return new VectorDrawableCompat(this);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new VectorDrawableCompat(this);
        }

        public void updateCacheStates()
        {
            mCachedTint = mTint;
            mCachedTintMode = mTintMode;
            mCachedRootAlpha = mVPathRenderer.getRootAlpha();
            mCachedAutoMirrored = mAutoMirrored;
            mCacheDirty = false;
        }

        public void updateCachedBitmap(int i, int j)
        {
            mCachedBitmap.eraseColor(0);
            Canvas canvas = new Canvas(mCachedBitmap);
            mVPathRenderer.draw(canvas, i, j, null);
        }

        boolean mAutoMirrored;
        boolean mCacheDirty;
        boolean mCachedAutoMirrored;
        Bitmap mCachedBitmap;
        int mCachedRootAlpha;
        int mCachedThemeAttrs[];
        ColorStateList mCachedTint;
        android.graphics.PorterDuff.Mode mCachedTintMode;
        int mChangingConfigurations;
        Paint mTempPaint;
        ColorStateList mTint;
        android.graphics.PorterDuff.Mode mTintMode;
        VPathRenderer mVPathRenderer;

        public VectorDrawableCompatState()
        {
            mTint = null;
            mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            mVPathRenderer = new VPathRenderer();
        }

        public VectorDrawableCompatState(VectorDrawableCompatState vectordrawablecompatstate)
        {
            mTint = null;
            mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            if(vectordrawablecompatstate != null)
            {
                mChangingConfigurations = vectordrawablecompatstate.mChangingConfigurations;
                mVPathRenderer = new VPathRenderer(vectordrawablecompatstate.mVPathRenderer);
                if(vectordrawablecompatstate.mVPathRenderer.mFillPaint != null)
                    mVPathRenderer.mFillPaint = new Paint(vectordrawablecompatstate.mVPathRenderer.mFillPaint);
                if(vectordrawablecompatstate.mVPathRenderer.mStrokePaint != null)
                    mVPathRenderer.mStrokePaint = new Paint(vectordrawablecompatstate.mVPathRenderer.mStrokePaint);
                mTint = vectordrawablecompatstate.mTint;
                mTintMode = vectordrawablecompatstate.mTintMode;
                mAutoMirrored = vectordrawablecompatstate.mAutoMirrored;
            }
        }
    }

    private static class VectorDrawableDelegateState extends android.graphics.drawable.Drawable.ConstantState
    {

        public boolean canApplyTheme()
        {
            return mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations()
        {
            return mDelegateState.getChangingConfigurations();
        }

        public Drawable newDrawable()
        {
            VectorDrawableCompat vectordrawablecompat = new VectorDrawableCompat();
            vectordrawablecompat.mDelegateDrawable = (VectorDrawable)mDelegateState.newDrawable();
            return vectordrawablecompat;
        }

        public Drawable newDrawable(Resources resources)
        {
            VectorDrawableCompat vectordrawablecompat = new VectorDrawableCompat();
            vectordrawablecompat.mDelegateDrawable = (VectorDrawable)mDelegateState.newDrawable(resources);
            return vectordrawablecompat;
        }

        public Drawable newDrawable(Resources resources, android.content.res.Resources.Theme theme)
        {
            VectorDrawableCompat vectordrawablecompat = new VectorDrawableCompat();
            vectordrawablecompat.mDelegateDrawable = (VectorDrawable)mDelegateState.newDrawable(resources, theme);
            return vectordrawablecompat;
        }

        private final android.graphics.drawable.Drawable.ConstantState mDelegateState;

        public VectorDrawableDelegateState(android.graphics.drawable.Drawable.ConstantState constantstate)
        {
            mDelegateState = constantstate;
        }
    }


    VectorDrawableCompat()
    {
        mAllowCaching = true;
        mTmpFloats = new float[9];
        mTmpMatrix = new Matrix();
        mTmpBounds = new Rect();
        mVectorState = new VectorDrawableCompatState();
    }

    VectorDrawableCompat(VectorDrawableCompatState vectordrawablecompatstate)
    {
        mAllowCaching = true;
        mTmpFloats = new float[9];
        mTmpMatrix = new Matrix();
        mTmpBounds = new Rect();
        mVectorState = vectordrawablecompatstate;
        mTintFilter = updateTintFilter(mTintFilter, vectordrawablecompatstate.mTint, vectordrawablecompatstate.mTintMode);
    }

    static int applyAlpha(int i, float f)
    {
        return i & 0xffffff | (int)((float)Color.alpha(i) * f) << 24;
    }

    public static VectorDrawableCompat create(Resources resources, int i, android.content.res.Resources.Theme theme)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            VectorDrawableCompat vectordrawablecompat = new VectorDrawableCompat();
            vectordrawablecompat.mDelegateDrawable = ResourcesCompat.getDrawable(resources, i, theme);
            vectordrawablecompat.mCachedConstantStateDelegate = new VectorDrawableDelegateState(vectordrawablecompat.mDelegateDrawable.getConstantState());
            return vectordrawablecompat;
        }
        android.content.res.XmlResourceParser xmlresourceparser;
        AttributeSet attributeset;
        xmlresourceparser = resources.getXml(i);
        attributeset = Xml.asAttributeSet(xmlresourceparser);
        do
            i = xmlresourceparser.next();
        while(i != 2 && i != 1);
        if(i != 2)
        {
            try
            {
                throw new XmlPullParserException("No start tag found");
            }
            // Misplaced declaration of an exception variable
            catch(Resources resources)
            {
                Log.e("VectorDrawableCompat", "parser error", resources);
            }
            // Misplaced declaration of an exception variable
            catch(Resources resources)
            {
                Log.e("VectorDrawableCompat", "parser error", resources);
            }
            return null;
        }
        resources = createFromXmlInner(resources, xmlresourceparser, attributeset, theme);
        return resources;
    }

    public static VectorDrawableCompat createFromXmlInner(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        VectorDrawableCompat vectordrawablecompat = new VectorDrawableCompat();
        vectordrawablecompat.inflate(resources, xmlpullparser, attributeset, theme);
        return vectordrawablecompat;
    }

    private void inflateInternal(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        VectorDrawableCompatState vectordrawablecompatstate = mVectorState;
        VPathRenderer vpathrenderer = vectordrawablecompatstate.mVPathRenderer;
        boolean flag = true;
        Stack stack = new Stack();
        stack.push(vpathrenderer.mRootGroup);
        int i = xmlpullparser.getEventType();
        while(i != 1) 
        {
            boolean flag1;
            if(i == 2)
            {
                Object obj = xmlpullparser.getName();
                VGroup vgroup = (VGroup)stack.peek();
                if("path".equals(obj))
                {
                    obj = new VFullPath();
                    ((VFullPath) (obj)).inflate(resources, attributeset, theme, xmlpullparser);
                    vgroup.mChildren.add(obj);
                    if(((VFullPath) (obj)).getPathName() != null)
                        vpathrenderer.mVGTargetsMap.put(((VFullPath) (obj)).getPathName(), obj);
                    flag1 = false;
                    vectordrawablecompatstate.mChangingConfigurations = vectordrawablecompatstate.mChangingConfigurations | ((VFullPath) (obj)).mChangingConfigurations;
                } else
                if("clip-path".equals(obj))
                {
                    obj = new VClipPath();
                    ((VClipPath) (obj)).inflate(resources, attributeset, theme, xmlpullparser);
                    vgroup.mChildren.add(obj);
                    if(((VClipPath) (obj)).getPathName() != null)
                        vpathrenderer.mVGTargetsMap.put(((VClipPath) (obj)).getPathName(), obj);
                    vectordrawablecompatstate.mChangingConfigurations = vectordrawablecompatstate.mChangingConfigurations | ((VClipPath) (obj)).mChangingConfigurations;
                    flag1 = flag;
                } else
                {
                    flag1 = flag;
                    if("group".equals(obj))
                    {
                        VGroup vgroup1 = new VGroup();
                        vgroup1.inflate(resources, attributeset, theme, xmlpullparser);
                        vgroup.mChildren.add(vgroup1);
                        stack.push(vgroup1);
                        if(vgroup1.getGroupName() != null)
                            vpathrenderer.mVGTargetsMap.put(vgroup1.getGroupName(), vgroup1);
                        vectordrawablecompatstate.mChangingConfigurations = vectordrawablecompatstate.mChangingConfigurations | vgroup1.mChangingConfigurations;
                        flag1 = flag;
                    }
                }
            } else
            {
                flag1 = flag;
                if(i == 3)
                {
                    flag1 = flag;
                    if("group".equals(xmlpullparser.getName()))
                    {
                        stack.pop();
                        flag1 = flag;
                    }
                }
            }
            i = xmlpullparser.next();
            flag = flag1;
        }
        if(flag)
        {
            resources = new StringBuffer();
            if(resources.length() > 0)
                resources.append(" or ");
            resources.append("path");
            throw new XmlPullParserException((new StringBuilder()).append("no ").append(resources).append(" defined").toString());
        } else
        {
            return;
        }
    }

    private boolean needMirroring()
    {
        return false;
    }

    private static android.graphics.PorterDuff.Mode parseTintModeCompat(int i, android.graphics.PorterDuff.Mode mode)
    {
        switch(i)
        {
        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        default:
            return mode;

        case 3: // '\003'
            return android.graphics.PorterDuff.Mode.SRC_OVER;

        case 5: // '\005'
            return android.graphics.PorterDuff.Mode.SRC_IN;

        case 9: // '\t'
            return android.graphics.PorterDuff.Mode.SRC_ATOP;

        case 14: // '\016'
            return android.graphics.PorterDuff.Mode.MULTIPLY;

        case 15: // '\017'
            return android.graphics.PorterDuff.Mode.SCREEN;

        case 16: // '\020'
            return android.graphics.PorterDuff.Mode.ADD;
        }
    }

    private void printGroupTree(VGroup vgroup, int i)
    {
        String s = "";
        for(int j = 0; j < i; j++)
            s = (new StringBuilder()).append(s).append("    ").toString();

        Log.v("VectorDrawableCompat", (new StringBuilder()).append(s).append("current group is :").append(vgroup.getGroupName()).append(" rotation is ").append(vgroup.mRotate).toString());
        Log.v("VectorDrawableCompat", (new StringBuilder()).append(s).append("matrix is :").append(vgroup.getLocalMatrix().toString()).toString());
        int k = 0;
        while(k < vgroup.mChildren.size()) 
        {
            Object obj = vgroup.mChildren.get(k);
            if(obj instanceof VGroup)
                printGroupTree((VGroup)obj, i + 1);
            else
                ((VPath)obj).printVPath(i + 1);
            k++;
        }
    }

    private void updateStateFromTypedArray(TypedArray typedarray, XmlPullParser xmlpullparser)
        throws XmlPullParserException
    {
        VectorDrawableCompatState vectordrawablecompatstate = mVectorState;
        VPathRenderer vpathrenderer = vectordrawablecompatstate.mVPathRenderer;
        vectordrawablecompatstate.mTintMode = parseTintModeCompat(TypedArrayUtils.getNamedInt(typedarray, xmlpullparser, "tintMode", 6, -1), android.graphics.PorterDuff.Mode.SRC_IN);
        ColorStateList colorstatelist = typedarray.getColorStateList(1);
        if(colorstatelist != null)
            vectordrawablecompatstate.mTint = colorstatelist;
        vectordrawablecompatstate.mAutoMirrored = TypedArrayUtils.getNamedBoolean(typedarray, xmlpullparser, "autoMirrored", 5, vectordrawablecompatstate.mAutoMirrored);
        vpathrenderer.mViewportWidth = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "viewportWidth", 7, vpathrenderer.mViewportWidth);
        vpathrenderer.mViewportHeight = TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "viewportHeight", 8, vpathrenderer.mViewportHeight);
        if(vpathrenderer.mViewportWidth <= 0.0F)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires viewportWidth > 0").toString());
        if(vpathrenderer.mViewportHeight <= 0.0F)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires viewportHeight > 0").toString());
        vpathrenderer.mBaseWidth = typedarray.getDimension(3, vpathrenderer.mBaseWidth);
        vpathrenderer.mBaseHeight = typedarray.getDimension(2, vpathrenderer.mBaseHeight);
        if(vpathrenderer.mBaseWidth <= 0.0F)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires width > 0").toString());
        if(vpathrenderer.mBaseHeight <= 0.0F)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<vector> tag requires height > 0").toString());
        vpathrenderer.setAlpha(TypedArrayUtils.getNamedFloat(typedarray, xmlpullparser, "alpha", 4, vpathrenderer.getAlpha()));
        typedarray = typedarray.getString(0);
        if(typedarray != null)
        {
            vpathrenderer.mRootName = typedarray;
            vpathrenderer.mVGTargetsMap.put(typedarray, vpathrenderer);
        }
    }

    public volatile void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
    }

    public boolean canApplyTheme()
    {
        if(mDelegateDrawable != null)
            DrawableCompat.canApplyTheme(mDelegateDrawable);
        return false;
    }

    public volatile void clearColorFilter()
    {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas)
    {
        if(mDelegateDrawable == null) goto _L2; else goto _L1
_L1:
        mDelegateDrawable.draw(canvas);
_L4:
        return;
_L2:
        copyBounds(mTmpBounds);
        if(mTmpBounds.width() <= 0 || mTmpBounds.height() <= 0) goto _L4; else goto _L3
_L3:
        int i;
        int j;
        float f;
        float f1;
        float f2;
        float f3;
        int k;
        Object obj;
        if(mColorFilter == null)
            obj = mTintFilter;
        else
            obj = mColorFilter;
        canvas.getMatrix(mTmpMatrix);
        mTmpMatrix.getValues(mTmpFloats);
        f = Math.abs(mTmpFloats[0]);
        f1 = Math.abs(mTmpFloats[4]);
        f3 = Math.abs(mTmpFloats[1]);
        f2 = Math.abs(mTmpFloats[3]);
        if(f3 != 0.0F || f2 != 0.0F)
        {
            f = 1.0F;
            f1 = 1.0F;
        }
        i = (int)((float)mTmpBounds.width() * f);
        j = (int)((float)mTmpBounds.height() * f1);
        i = Math.min(2048, i);
        j = Math.min(2048, j);
        if(i <= 0 || j <= 0) goto _L4; else goto _L5
_L5:
        k = canvas.save();
        canvas.translate(mTmpBounds.left, mTmpBounds.top);
        if(needMirroring())
        {
            canvas.translate(mTmpBounds.width(), 0.0F);
            canvas.scale(-1F, 1.0F);
        }
        mTmpBounds.offsetTo(0, 0);
        mVectorState.createCachedBitmapIfNeeded(i, j);
        if(mAllowCaching) goto _L7; else goto _L6
_L6:
        mVectorState.updateCachedBitmap(i, j);
_L9:
        mVectorState.drawCachedBitmapWithRootAlpha(canvas, ((ColorFilter) (obj)), mTmpBounds);
        canvas.restoreToCount(k);
        return;
_L7:
        if(!mVectorState.canReuseCache())
        {
            mVectorState.updateCachedBitmap(i, j);
            mVectorState.updateCacheStates();
        }
        if(true) goto _L9; else goto _L8
_L8:
    }

    public int getAlpha()
    {
        if(mDelegateDrawable != null)
            return DrawableCompat.getAlpha(mDelegateDrawable);
        else
            return mVectorState.mVPathRenderer.getRootAlpha();
    }

    public int getChangingConfigurations()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getChangingConfigurations();
        else
            return super.getChangingConfigurations() | mVectorState.getChangingConfigurations();
    }

    public volatile ColorFilter getColorFilter()
    {
        return super.getColorFilter();
    }

    public android.graphics.drawable.Drawable.ConstantState getConstantState()
    {
        if(mDelegateDrawable != null)
        {
            return new VectorDrawableDelegateState(mDelegateDrawable.getConstantState());
        } else
        {
            mVectorState.mChangingConfigurations = getChangingConfigurations();
            return mVectorState;
        }
    }

    public volatile Drawable getCurrent()
    {
        return super.getCurrent();
    }

    public int getIntrinsicHeight()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getIntrinsicHeight();
        else
            return (int)mVectorState.mVPathRenderer.mBaseHeight;
    }

    public int getIntrinsicWidth()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getIntrinsicWidth();
        else
            return (int)mVectorState.mVPathRenderer.mBaseWidth;
    }

    public volatile int getLayoutDirection()
    {
        return super.getLayoutDirection();
    }

    public volatile int getMinimumHeight()
    {
        return super.getMinimumHeight();
    }

    public volatile int getMinimumWidth()
    {
        return super.getMinimumWidth();
    }

    public int getOpacity()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getOpacity();
        else
            return -3;
    }

    public volatile boolean getPadding(Rect rect)
    {
        return super.getPadding(rect);
    }

    public float getPixelSize()
    {
        if(mVectorState == null && mVectorState.mVPathRenderer == null || mVectorState.mVPathRenderer.mBaseWidth == 0.0F || mVectorState.mVPathRenderer.mBaseHeight == 0.0F || mVectorState.mVPathRenderer.mViewportHeight == 0.0F || mVectorState.mVPathRenderer.mViewportWidth == 0.0F)
        {
            return 1.0F;
        } else
        {
            float f = mVectorState.mVPathRenderer.mBaseWidth;
            float f1 = mVectorState.mVPathRenderer.mBaseHeight;
            float f2 = mVectorState.mVPathRenderer.mViewportWidth;
            float f3 = mVectorState.mVPathRenderer.mViewportHeight;
            return Math.min(f2 / f, f3 / f1);
        }
    }

    public volatile int[] getState()
    {
        return super.getState();
    }

    Object getTargetByName(String s)
    {
        return mVectorState.mVPathRenderer.mVGTargetsMap.get(s);
    }

    public volatile Region getTransparentRegion()
    {
        return super.getTransparentRegion();
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.inflate(resources, xmlpullparser, attributeset);
            return;
        } else
        {
            inflate(resources, xmlpullparser, attributeset, null);
            return;
        }
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.inflate(mDelegateDrawable, resources, xmlpullparser, attributeset, theme);
            return;
        } else
        {
            VectorDrawableCompatState vectordrawablecompatstate = mVectorState;
            vectordrawablecompatstate.mVPathRenderer = new VPathRenderer();
            TypedArray typedarray = obtainAttributes(resources, theme, attributeset, AndroidResources.styleable_VectorDrawableTypeArray);
            updateStateFromTypedArray(typedarray, xmlpullparser);
            typedarray.recycle();
            vectordrawablecompatstate.mChangingConfigurations = getChangingConfigurations();
            vectordrawablecompatstate.mCacheDirty = true;
            inflateInternal(resources, xmlpullparser, attributeset, theme);
            mTintFilter = updateTintFilter(mTintFilter, vectordrawablecompatstate.mTint, vectordrawablecompatstate.mTintMode);
            return;
        }
    }

    public void invalidateSelf()
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.invalidateSelf();
            return;
        } else
        {
            super.invalidateSelf();
            return;
        }
    }

    public volatile boolean isAutoMirrored()
    {
        return super.isAutoMirrored();
    }

    public boolean isStateful()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.isStateful();
        return super.isStateful() || mVectorState != null && mVectorState.mTint != null && mVectorState.mTint.isStateful();
    }

    public volatile void jumpToCurrentState()
    {
        super.jumpToCurrentState();
    }

    public Drawable mutate()
    {
        if(mDelegateDrawable != null)
            mDelegateDrawable.mutate();
        else
        if(!mMutated && super.mutate() == this)
        {
            mVectorState = new VectorDrawableCompatState(mVectorState);
            mMutated = true;
            return this;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        if(mDelegateDrawable != null)
            mDelegateDrawable.setBounds(rect);
    }

    protected boolean onStateChange(int ai[])
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.setState(ai);
        ai = mVectorState;
        if(((VectorDrawableCompatState) (ai)).mTint != null && ((VectorDrawableCompatState) (ai)).mTintMode != null)
        {
            mTintFilter = updateTintFilter(mTintFilter, ((VectorDrawableCompatState) (ai)).mTint, ((VectorDrawableCompatState) (ai)).mTintMode);
            invalidateSelf();
            return true;
        } else
        {
            return false;
        }
    }

    public void scheduleSelf(Runnable runnable, long l)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.scheduleSelf(runnable, l);
            return;
        } else
        {
            super.scheduleSelf(runnable, l);
            return;
        }
    }

    void setAllowCaching(boolean flag)
    {
        mAllowCaching = flag;
    }

    public void setAlpha(int i)
    {
        if(mDelegateDrawable != null)
            mDelegateDrawable.setAlpha(i);
        else
        if(mVectorState.mVPathRenderer.getRootAlpha() != i)
        {
            mVectorState.mVPathRenderer.setRootAlpha(i);
            invalidateSelf();
            return;
        }
    }

    public volatile void setAutoMirrored(boolean flag)
    {
        super.setAutoMirrored(flag);
    }

    public volatile void setChangingConfigurations(int i)
    {
        super.setChangingConfigurations(i);
    }

    public volatile void setColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        super.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.setColorFilter(colorfilter);
            return;
        } else
        {
            mColorFilter = colorfilter;
            invalidateSelf();
            return;
        }
    }

    public volatile void setFilterBitmap(boolean flag)
    {
        super.setFilterBitmap(flag);
    }

    public volatile void setHotspot(float f, float f1)
    {
        super.setHotspot(f, f1);
    }

    public volatile void setHotspotBounds(int i, int j, int k, int l)
    {
        super.setHotspotBounds(i, j, k, l);
    }

    public volatile boolean setState(int ai[])
    {
        return super.setState(ai);
    }

    public void setTint(int i)
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.setTint(mDelegateDrawable, i);
            return;
        } else
        {
            setTintList(ColorStateList.valueOf(i));
            return;
        }
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.setTintList(mDelegateDrawable, colorstatelist);
        } else
        {
            VectorDrawableCompatState vectordrawablecompatstate = mVectorState;
            if(vectordrawablecompatstate.mTint != colorstatelist)
            {
                vectordrawablecompatstate.mTint = colorstatelist;
                mTintFilter = updateTintFilter(mTintFilter, colorstatelist, vectordrawablecompatstate.mTintMode);
                invalidateSelf();
                return;
            }
        }
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.setTintMode(mDelegateDrawable, mode);
        } else
        {
            VectorDrawableCompatState vectordrawablecompatstate = mVectorState;
            if(vectordrawablecompatstate.mTintMode != mode)
            {
                vectordrawablecompatstate.mTintMode = mode;
                mTintFilter = updateTintFilter(mTintFilter, vectordrawablecompatstate.mTint, mode);
                invalidateSelf();
                return;
            }
        }
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.setVisible(flag, flag1);
        else
            return super.setVisible(flag, flag1);
    }

    public void unscheduleSelf(Runnable runnable)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.unscheduleSelf(runnable);
            return;
        } else
        {
            super.unscheduleSelf(runnable);
            return;
        }
    }

    PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter porterduffcolorfilter, ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode)
    {
        if(colorstatelist == null || mode == null)
            return null;
        else
            return new PorterDuffColorFilter(colorstatelist.getColorForState(getState(), 0), mode);
    }

    private static final boolean DBG_VECTOR_DRAWABLE = false;
    static final android.graphics.PorterDuff.Mode DEFAULT_TINT_MODE;
    private static final int LINECAP_BUTT = 0;
    private static final int LINECAP_ROUND = 1;
    private static final int LINECAP_SQUARE = 2;
    private static final int LINEJOIN_BEVEL = 2;
    private static final int LINEJOIN_MITER = 0;
    private static final int LINEJOIN_ROUND = 1;
    static final String LOGTAG = "VectorDrawableCompat";
    private static final int MAX_CACHED_BITMAP_SIZE = 2048;
    private static final String SHAPE_CLIP_PATH = "clip-path";
    private static final String SHAPE_GROUP = "group";
    private static final String SHAPE_PATH = "path";
    private static final String SHAPE_VECTOR = "vector";
    private boolean mAllowCaching;
    private android.graphics.drawable.Drawable.ConstantState mCachedConstantStateDelegate;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float mTmpFloats[];
    private final Matrix mTmpMatrix;
    private VectorDrawableCompatState mVectorState;

    static 
    {
        DEFAULT_TINT_MODE = android.graphics.PorterDuff.Mode.SRC_IN;
    }
}
