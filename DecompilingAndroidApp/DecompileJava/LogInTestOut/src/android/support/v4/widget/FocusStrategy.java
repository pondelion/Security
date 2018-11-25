// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.graphics.Rect;
import java.util.*;

class FocusStrategy
{
    public static interface BoundsAdapter
    {

        public abstract void obtainBounds(Object obj, Rect rect);
    }

    public static interface CollectionAdapter
    {

        public abstract Object get(Object obj, int i);

        public abstract int size(Object obj);
    }

    private static class SequentialComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            int i;
            boolean flag;
            Rect rect;
            Rect rect1;
            flag = true;
            i = 1;
            rect = mTemp1;
            rect1 = mTemp2;
            mAdapter.obtainBounds(obj, rect);
            mAdapter.obtainBounds(obj1, rect1);
            if(rect.top >= rect1.top) goto _L2; else goto _L1
_L1:
            return -1;
_L2:
            if(rect.top > rect1.top)
                return 1;
            if(rect.left < rect1.left)
            {
                if(!mIsLayoutRtl)
                    i = -1;
                return i;
            }
            if(rect.left <= rect1.left)
                continue; /* Loop/switch isn't completed */
            if(!mIsLayoutRtl)
                return 1;
            continue; /* Loop/switch isn't completed */
            if(rect.bottom < rect1.bottom) goto _L1; else goto _L3
_L3:
            if(rect.bottom > rect1.bottom)
                return 1;
            if(rect.right < rect1.right)
            {
                int j;
                if(mIsLayoutRtl)
                    j = ((flag) ? 1 : 0);
                else
                    j = -1;
                return j;
            }
            if(rect.right > rect1.right)
            {
                if(!mIsLayoutRtl)
                    return 1;
            } else
            {
                return 0;
            }
            if(true) goto _L1; else goto _L4
_L4:
        }

        private final BoundsAdapter mAdapter;
        private final boolean mIsLayoutRtl;
        private final Rect mTemp1 = new Rect();
        private final Rect mTemp2 = new Rect();

        SequentialComparator(boolean flag, BoundsAdapter boundsadapter)
        {
            mIsLayoutRtl = flag;
            mAdapter = boundsadapter;
        }
    }


    FocusStrategy()
    {
    }

    private static boolean beamBeats(int i, Rect rect, Rect rect1, Rect rect2)
    {
        boolean flag1 = true;
        boolean flag = beamsOverlap(i, rect, rect1);
        if(beamsOverlap(i, rect, rect2) || !flag)
        {
            flag = false;
        } else
        {
            flag = flag1;
            if(isToDirectionOf(i, rect, rect2))
            {
                flag = flag1;
                if(i != 17)
                {
                    flag = flag1;
                    if(i != 66)
                    {
                        flag = flag1;
                        if(majorAxisDistance(i, rect, rect1) >= majorAxisDistanceToFarEdge(i, rect, rect2))
                            return false;
                    }
                }
            }
        }
        return flag;
    }

    private static boolean beamsOverlap(int i, Rect rect, Rect rect1)
    {
        i;
        JVM INSTR lookupswitch 4: default 44
    //                   17: 54
    //                   33: 80
    //                   66: 54
    //                   130: 80;
           goto _L1 _L2 _L3 _L2 _L3
_L1:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L2:
        if(rect1.bottom < rect.top || rect1.top > rect.bottom) goto _L5; else goto _L4
_L4:
        return true;
_L5:
        return false;
_L3:
        if(rect1.right < rect.left || rect1.left > rect.right)
            return false;
        if(true) goto _L4; else goto _L6
_L6:
    }

    public static Object findNextFocusInAbsoluteDirection(Object obj, CollectionAdapter collectionadapter, BoundsAdapter boundsadapter, Object obj1, Rect rect, int i)
    {
        Rect rect1;
        rect1 = new Rect(rect);
        int j;
        int k;
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
            rect1.offset(rect.width() + 1, 0);
            break;

        case 66: // 'B'
            rect1.offset(-(rect.width() + 1), 0);
            continue;

        case 33: // '!'
            rect1.offset(0, rect.height() + 1);
            continue;

        case 130: 
            rect1.offset(0, -(rect.height() + 1));
            continue;
        }
        break;
        do
        {
            Object obj2 = null;
            k = collectionadapter.size(obj);
            Rect rect2 = new Rect();
            j = 0;
            while(j < k) 
            {
                Object obj3 = collectionadapter.get(obj, j);
                if(obj3 != obj1)
                {
                    boundsadapter.obtainBounds(obj3, rect2);
                    if(isBetterCandidate(i, rect, rect2, rect1))
                    {
                        rect1.set(rect2);
                        obj2 = obj3;
                    }
                }
                j++;
            }
            return obj2;
        } while(true);
    }

    public static Object findNextFocusInRelativeDirection(Object obj, CollectionAdapter collectionadapter, BoundsAdapter boundsadapter, Object obj1, int i, boolean flag, boolean flag1)
    {
        int k = collectionadapter.size(obj);
        ArrayList arraylist = new ArrayList(k);
        for(int j = 0; j < k; j++)
            arraylist.add(collectionadapter.get(obj, j));

        Collections.sort(arraylist, new SequentialComparator(flag, boundsadapter));
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");

        case 2: // '\002'
            return getNextFocusable(obj1, arraylist, flag1);

        case 1: // '\001'
            return getPreviousFocusable(obj1, arraylist, flag1);
        }
    }

    private static Object getNextFocusable(Object obj, ArrayList arraylist, boolean flag)
    {
        int j = arraylist.size();
        int i;
        if(obj == null)
            i = -1;
        else
            i = arraylist.lastIndexOf(obj);
        i++;
        if(i < j)
            return arraylist.get(i);
        if(flag && j > 0)
            return arraylist.get(0);
        else
            return null;
    }

    private static Object getPreviousFocusable(Object obj, ArrayList arraylist, boolean flag)
    {
        int j = arraylist.size();
        int i;
        if(obj == null)
            i = j;
        else
            i = arraylist.indexOf(obj);
        i--;
        if(i >= 0)
            return arraylist.get(i);
        if(flag && j > 0)
            return arraylist.get(j - 1);
        else
            return null;
    }

    private static int getWeightedDistanceFor(int i, int j)
    {
        return i * 13 * i + j * j;
    }

    private static boolean isBetterCandidate(int i, Rect rect, Rect rect1, Rect rect2)
    {
        boolean flag1 = true;
        boolean flag;
        if(!isCandidate(rect, rect1, i))
        {
            flag = false;
        } else
        {
            flag = flag1;
            if(isCandidate(rect, rect2, i))
            {
                flag = flag1;
                if(!beamBeats(i, rect, rect1, rect2))
                {
                    if(beamBeats(i, rect, rect2, rect1))
                        return false;
                    flag = flag1;
                    if(getWeightedDistanceFor(majorAxisDistance(i, rect, rect1), minorAxisDistance(i, rect, rect1)) >= getWeightedDistanceFor(majorAxisDistance(i, rect, rect2), minorAxisDistance(i, rect, rect2)))
                        return false;
                }
            }
        }
        return flag;
    }

    private static boolean isCandidate(Rect rect, Rect rect1, int i)
    {
        i;
        JVM INSTR lookupswitch 4: default 44
    //                   17: 54
    //                   33: 126
    //                   66: 91
    //                   130: 161;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L2:
        if(rect.right <= rect1.right && rect.left < rect1.right || rect.left <= rect1.left) goto _L7; else goto _L6
_L6:
        return true;
_L7:
        return false;
_L4:
        if(rect.left >= rect1.left && rect.right > rect1.left || rect.right >= rect1.right)
            return false;
        continue; /* Loop/switch isn't completed */
_L3:
        if(rect.bottom <= rect1.bottom && rect.top < rect1.bottom || rect.top <= rect1.top)
            return false;
        continue; /* Loop/switch isn't completed */
_L5:
        if(rect.top >= rect1.top && rect.bottom > rect1.top || rect.bottom >= rect1.bottom)
            return false;
        if(true) goto _L6; else goto _L8
_L8:
    }

    private static boolean isToDirectionOf(int i, Rect rect, Rect rect1)
    {
        i;
        JVM INSTR lookupswitch 4: default 44
    //                   17: 54
    //                   33: 82
    //                   66: 69
    //                   130: 95;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
_L2:
        if(rect.left < rect1.right) goto _L7; else goto _L6
_L6:
        return true;
_L7:
        return false;
_L4:
        if(rect.right > rect1.left)
            return false;
        continue; /* Loop/switch isn't completed */
_L3:
        if(rect.top < rect1.bottom)
            return false;
        continue; /* Loop/switch isn't completed */
_L5:
        if(rect.bottom > rect1.top)
            return false;
        if(true) goto _L6; else goto _L8
_L8:
    }

    private static int majorAxisDistance(int i, Rect rect, Rect rect1)
    {
        return Math.max(0, majorAxisDistanceRaw(i, rect, rect1));
    }

    private static int majorAxisDistanceRaw(int i, Rect rect, Rect rect1)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
            return rect.left - rect1.right;

        case 66: // 'B'
            return rect1.left - rect.right;

        case 33: // '!'
            return rect.top - rect1.bottom;

        case 130: 
            return rect1.top - rect.bottom;
        }
    }

    private static int majorAxisDistanceToFarEdge(int i, Rect rect, Rect rect1)
    {
        return Math.max(1, majorAxisDistanceToFarEdgeRaw(i, rect, rect1));
    }

    private static int majorAxisDistanceToFarEdgeRaw(int i, Rect rect, Rect rect1)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
            return rect.left - rect1.left;

        case 66: // 'B'
            return rect1.right - rect.right;

        case 33: // '!'
            return rect.top - rect1.top;

        case 130: 
            return rect1.bottom - rect.bottom;
        }
    }

    private static int minorAxisDistance(int i, Rect rect, Rect rect1)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
        case 66: // 'B'
            return Math.abs((rect.top + rect.height() / 2) - (rect1.top + rect1.height() / 2));

        case 33: // '!'
        case 130: 
            return Math.abs((rect.left + rect.width() / 2) - (rect1.left + rect1.width() / 2));
        }
    }
}
