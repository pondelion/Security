// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.*;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.*;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v4.content:
//            ContextCompat

public class FileProvider extends ContentProvider
{
    static interface PathStrategy
    {

        public abstract File getFileForUri(Uri uri);

        public abstract Uri getUriForFile(File file);
    }

    static class SimplePathStrategy
        implements PathStrategy
    {

        public void addRoot(String s, File file)
        {
            if(TextUtils.isEmpty(s))
                throw new IllegalArgumentException("Name must not be empty");
            File file1;
            try
            {
                file1 = file.getCanonicalFile();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to resolve canonical path for ").append(file).toString(), s);
            }
            mRoots.put(s, file1);
        }

        public File getFileForUri(Uri uri)
        {
            Object obj1 = uri.getEncodedPath();
            int i = ((String) (obj1)).indexOf('/', 1);
            Object obj = Uri.decode(((String) (obj1)).substring(1, i));
            obj1 = Uri.decode(((String) (obj1)).substring(i + 1));
            obj = (File)mRoots.get(obj);
            if(obj == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Unable to find configured root for ").append(uri).toString());
            uri = new File(((File) (obj)), ((String) (obj1)));
            try
            {
                obj1 = uri.getCanonicalFile();
            }
            catch(IOException ioexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to resolve canonical path for ").append(uri).toString());
            }
            if(!((File) (obj1)).getPath().startsWith(((File) (obj)).getPath()))
                throw new SecurityException("Resolved path jumped beyond configured root");
            else
                return ((File) (obj1));
        }

        public Uri getUriForFile(File file)
        {
            String s1;
            Iterator iterator;
            try
            {
                s1 = file.getCanonicalPath();
            }
            catch(IOException ioexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to resolve canonical path for ").append(file).toString());
            }
            file = null;
            iterator = mRoots.entrySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                String s2 = ((File)entry.getValue()).getPath();
                if(s1.startsWith(s2) && (file == null || s2.length() > ((File)file.getValue()).getPath().length()))
                    file = entry;
            } while(true);
            if(file == null)
                throw new IllegalArgumentException((new StringBuilder()).append("Failed to find configured root that contains ").append(s1).toString());
            String s = ((File)file.getValue()).getPath();
            if(s.endsWith("/"))
                s = s1.substring(s.length());
            else
                s = s1.substring(s.length() + 1);
            file = (new StringBuilder()).append(Uri.encode((String)file.getKey())).append('/').append(Uri.encode(s, "/")).toString();
            return (new android.net.Uri.Builder()).scheme("content").authority(mAuthority).encodedPath(file).build();
        }

        private final String mAuthority;
        private final HashMap mRoots = new HashMap();

        public SimplePathStrategy(String s)
        {
            mAuthority = s;
        }
    }


    public FileProvider()
    {
    }

    private static transient File buildPath(File file, String as[])
    {
        int j = as.length;
        for(int i = 0; i < j; i++)
        {
            String s = as[i];
            if(s != null)
                file = new File(file, s);
        }

        return file;
    }

    private static Object[] copyOf(Object aobj[], int i)
    {
        Object aobj1[] = new Object[i];
        System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, i);
        return aobj1;
    }

    private static String[] copyOf(String as[], int i)
    {
        String as1[] = new String[i];
        System.arraycopy(as, 0, as1, 0, i);
        return as1;
    }

    private static PathStrategy getPathStrategy(Context context, String s)
    {
        HashMap hashmap = sCache;
        hashmap;
        JVM INSTR monitorenter ;
        PathStrategy pathstrategy1 = (PathStrategy)sCache.get(s);
        PathStrategy pathstrategy;
        pathstrategy = pathstrategy1;
        if(pathstrategy1 != null)
            break MISSING_BLOCK_LABEL_40;
        pathstrategy = parsePathStrategy(context, s);
        sCache.put(s, pathstrategy);
        hashmap;
        JVM INSTR monitorexit ;
        return pathstrategy;
        context;
        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", context);
        context;
        hashmap;
        JVM INSTR monitorexit ;
        throw context;
        context;
        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", context);
    }

    public static Uri getUriForFile(Context context, String s, File file)
    {
        return getPathStrategy(context, s).getUriForFile(file);
    }

    private static int modeToMode(String s)
    {
        if("r".equals(s))
            return 0x10000000;
        if("w".equals(s) || "wt".equals(s))
            return 0x2c000000;
        if("wa".equals(s))
            return 0x2a000000;
        if("rw".equals(s))
            return 0x38000000;
        if("rwt".equals(s))
            return 0x3c000000;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid mode: ").append(s).toString());
    }

    private static PathStrategy parsePathStrategy(Context context, String s)
        throws IOException, XmlPullParserException
    {
        SimplePathStrategy simplepathstrategy;
        XmlResourceParser xmlresourceparser;
        simplepathstrategy = new SimplePathStrategy(s);
        xmlresourceparser = context.getPackageManager().resolveContentProvider(s, 128).loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
        if(xmlresourceparser == null)
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
_L7:
        int i = xmlresourceparser.next();
        if(i == 1) goto _L2; else goto _L1
_L1:
        Object obj;
        String s1;
        String s2;
        Object obj1;
        if(i != 2)
            continue; /* Loop/switch isn't completed */
        obj1 = xmlresourceparser.getName();
        s1 = xmlresourceparser.getAttributeValue(null, "name");
        s2 = xmlresourceparser.getAttributeValue(null, "path");
        obj = null;
        if(!"root-path".equals(obj1)) goto _L4; else goto _L3
_L3:
        s = DEVICE_ROOT;
_L5:
        if(s != null)
            simplepathstrategy.addRoot(s1, buildPath(s, new String[] {
                s2
            }));
        continue; /* Loop/switch isn't completed */
_L4:
        if("files-path".equals(obj1))
            s = context.getFilesDir();
        else
        if("cache-path".equals(obj1))
            s = context.getCacheDir();
        else
        if("external-path".equals(obj1))
            s = Environment.getExternalStorageDirectory();
        else
        if("external-files-path".equals(obj1))
        {
            obj1 = ContextCompat.getExternalFilesDirs(context, null);
            s = obj;
            if(obj1.length > 0)
                s = obj1[0];
        } else
        {
            s = obj;
            if("external-cache-path".equals(obj1))
            {
                File afile[] = ContextCompat.getExternalCacheDirs(context);
                s = obj;
                if(afile.length > 0)
                    s = afile[0];
            }
        }
        if(true) goto _L5; else goto _L2
_L2:
        return simplepathstrategy;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void attachInfo(Context context, ProviderInfo providerinfo)
    {
        super.attachInfo(context, providerinfo);
        if(providerinfo.exported)
            throw new SecurityException("Provider must not be exported");
        if(!providerinfo.grantUriPermissions)
        {
            throw new SecurityException("Provider must grant uri permissions");
        } else
        {
            mStrategy = getPathStrategy(context, providerinfo.authority);
            return;
        }
    }

    public int delete(Uri uri, String s, String as[])
    {
        return !mStrategy.getFileForUri(uri).delete() ? 0 : 1;
    }

    public String getType(Uri uri)
    {
        uri = mStrategy.getFileForUri(uri);
        int i = uri.getName().lastIndexOf('.');
        if(i >= 0)
        {
            uri = uri.getName().substring(i + 1);
            uri = MimeTypeMap.getSingleton().getMimeTypeFromExtension(uri);
            if(uri != null)
                return uri;
        }
        return "application/octet-stream";
    }

    public Uri insert(Uri uri, ContentValues contentvalues)
    {
        throw new UnsupportedOperationException("No external inserts");
    }

    public boolean onCreate()
    {
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String s)
        throws FileNotFoundException
    {
        return ParcelFileDescriptor.open(mStrategy.getFileForUri(uri), modeToMode(s));
    }

    public Cursor query(Uri uri, String as[], String s, String as1[], String s1)
    {
        s = mStrategy.getFileForUri(uri);
        uri = as;
        if(as == null)
            uri = COLUMNS;
        as1 = new String[uri.length];
        as = ((String []) (new Object[uri.length]));
        int i1 = uri.length;
        int j = 0;
        int i = 0;
        while(j < i1) 
        {
            s1 = uri[j];
            if("_display_name".equals(s1))
            {
                as1[i] = "_display_name";
                int k = i + 1;
                as[i] = s.getName();
                i = k;
            } else
            if("_size".equals(s1))
            {
                as1[i] = "_size";
                int l = i + 1;
                as[i] = Long.valueOf(s.length());
                i = l;
            }
            j++;
        }
        uri = copyOf(as1, i);
        as = ((String []) (copyOf(as, i)));
        uri = new MatrixCursor(uri, 1);
        uri.addRow(as);
        return uri;
    }

    public int update(Uri uri, ContentValues contentvalues, String s, String as[])
    {
        throw new UnsupportedOperationException("No external updates");
    }

    private static final String ATTR_NAME = "name";
    private static final String ATTR_PATH = "path";
    private static final String COLUMNS[] = {
        "_display_name", "_size"
    };
    private static final File DEVICE_ROOT = new File("/");
    private static final String META_DATA_FILE_PROVIDER_PATHS = "android.support.FILE_PROVIDER_PATHS";
    private static final String TAG_CACHE_PATH = "cache-path";
    private static final String TAG_EXTERNAL = "external-path";
    private static final String TAG_EXTERNAL_CACHE = "external-cache-path";
    private static final String TAG_EXTERNAL_FILES = "external-files-path";
    private static final String TAG_FILES_PATH = "files-path";
    private static final String TAG_ROOT_PATH = "root-path";
    private static HashMap sCache = new HashMap();
    private PathStrategy mStrategy;

}
