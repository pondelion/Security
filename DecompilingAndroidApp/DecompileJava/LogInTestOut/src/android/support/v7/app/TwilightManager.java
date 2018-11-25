// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import java.util.Calendar;

// Referenced classes of package android.support.v7.app:
//            TwilightCalculator

class TwilightManager
{
    private static class TwilightState
    {

        boolean isNight;
        long nextUpdate;
        long todaySunrise;
        long todaySunset;
        long tomorrowSunrise;
        long yesterdaySunset;

        TwilightState()
        {
        }
    }


    TwilightManager(Context context, LocationManager locationmanager)
    {
        mContext = context;
        mLocationManager = locationmanager;
    }

    static TwilightManager getInstance(Context context)
    {
        if(sInstance == null)
        {
            context = context.getApplicationContext();
            sInstance = new TwilightManager(context, (LocationManager)context.getSystemService("location"));
        }
        return sInstance;
    }

    private Location getLastKnownLocation()
    {
        Location location;
        Location location1;
        location = null;
        location1 = null;
        if(PermissionChecker.checkSelfPermission(mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0)
            location = getLastKnownLocationForProvider("network");
        if(PermissionChecker.checkSelfPermission(mContext, "android.permission.ACCESS_FINE_LOCATION") == 0)
            location1 = getLastKnownLocationForProvider("gps");
        if(location1 == null || location == null) goto _L2; else goto _L1
_L1:
        if(location1.getTime() <= location.getTime()) goto _L4; else goto _L3
_L3:
        return location1;
_L4:
        return location;
_L2:
        if(location1 == null)
            return location;
        if(true) goto _L3; else goto _L5
_L5:
    }

    private Location getLastKnownLocationForProvider(String s)
    {
        if(mLocationManager == null)
            break MISSING_BLOCK_LABEL_39;
        if(!mLocationManager.isProviderEnabled(s))
            break MISSING_BLOCK_LABEL_39;
        s = mLocationManager.getLastKnownLocation(s);
        return s;
        s;
        Log.d("TwilightManager", "Failed to get last known location", s);
        return null;
    }

    private boolean isStateValid()
    {
        return mTwilightState != null && mTwilightState.nextUpdate > System.currentTimeMillis();
    }

    static void setInstance(TwilightManager twilightmanager)
    {
        sInstance = twilightmanager;
    }

    private void updateState(Location location)
    {
        long l;
        long l2;
        long l3;
        long l4;
        TwilightState twilightstate = mTwilightState;
        l = System.currentTimeMillis();
        TwilightCalculator twilightcalculator = TwilightCalculator.getInstance();
        twilightcalculator.calculateTwilight(l - 0x5265c00L, location.getLatitude(), location.getLongitude());
        long l1 = twilightcalculator.sunset;
        twilightcalculator.calculateTwilight(l, location.getLatitude(), location.getLongitude());
        boolean flag;
        if(twilightcalculator.state == 1)
            flag = true;
        else
            flag = false;
        l2 = twilightcalculator.sunrise;
        l3 = twilightcalculator.sunset;
        twilightcalculator.calculateTwilight(0x5265c00L + l, location.getLatitude(), location.getLongitude());
        l4 = twilightcalculator.sunrise;
        if(l2 != -1L && l3 != -1L) goto _L2; else goto _L1
_L1:
        l += 0x2932e00L;
_L4:
        twilightstate.isNight = flag;
        twilightstate.yesterdaySunset = l1;
        twilightstate.todaySunrise = l2;
        twilightstate.todaySunset = l3;
        twilightstate.tomorrowSunrise = l4;
        twilightstate.nextUpdate = l;
        return;
_L2:
        if(l <= l3)
            break; /* Loop/switch isn't completed */
        l = 0L + l4;
_L5:
        l += 60000L;
        if(true) goto _L4; else goto _L3
_L3:
        if(l > l2)
            l = 0L + l3;
        else
            l = 0L + l2;
          goto _L5
        if(true) goto _L4; else goto _L6
_L6:
    }

    boolean isNight()
    {
        TwilightState twilightstate = mTwilightState;
        if(isStateValid())
            return twilightstate.isNight;
        Location location = getLastKnownLocation();
        if(location != null)
        {
            updateState(location);
            return twilightstate.isNight;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    private static final int SUNRISE = 6;
    private static final int SUNSET = 22;
    private static final String TAG = "TwilightManager";
    private static TwilightManager sInstance;
    private final Context mContext;
    private final LocationManager mLocationManager;
    private final TwilightState mTwilightState = new TwilightState();
}
