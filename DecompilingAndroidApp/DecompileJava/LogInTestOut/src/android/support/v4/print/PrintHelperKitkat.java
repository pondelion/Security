// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.print;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.*;
import android.net.Uri;
import android.os.*;
import android.print.*;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import java.io.*;

class PrintHelperKitkat
{
    public static interface OnPrintFinishCallback
    {

        public abstract void onFinish();
    }


    PrintHelperKitkat(Context context)
    {
        mDecodeOptions = null;
        mScaleMode = 2;
        mColorMode = 2;
        mPrintActivityRespectsOrientation = true;
        mIsMinMarginsHandlingCorrect = true;
        mContext = context;
    }

    private Bitmap convertBitmapForColorMode(Bitmap bitmap, int i)
    {
        if(i != 1)
        {
            return bitmap;
        } else
        {
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap1);
            Paint paint = new Paint();
            ColorMatrix colormatrix = new ColorMatrix();
            colormatrix.setSaturation(0.0F);
            paint.setColorFilter(new ColorMatrixColorFilter(colormatrix));
            canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint);
            canvas.setBitmap(null);
            return bitmap1;
        }
    }

    private Matrix getMatrix(int i, int j, RectF rectf, int k)
    {
        Matrix matrix = new Matrix();
        float f = rectf.width() / (float)i;
        if(k == 2)
            f = Math.max(f, rectf.height() / (float)j);
        else
            f = Math.min(f, rectf.height() / (float)j);
        matrix.postScale(f, f);
        matrix.postTranslate((rectf.width() - (float)i * f) / 2.0F, (rectf.height() - (float)j * f) / 2.0F);
        return matrix;
    }

    private static boolean isPortrait(Bitmap bitmap)
    {
        return bitmap.getWidth() <= bitmap.getHeight();
    }

    private Bitmap loadBitmap(Uri uri, android.graphics.BitmapFactory.Options options)
        throws FileNotFoundException
    {
        Uri uri1;
        if(uri == null || mContext == null)
            throw new IllegalArgumentException("bad argument to loadBitmap");
        uri1 = null;
        uri = mContext.getContentResolver().openInputStream(uri);
        uri1 = uri;
        options = BitmapFactory.decodeStream(uri, null, options);
        if(uri != null)
            try
            {
                uri.close();
            }
            // Misplaced declaration of an exception variable
            catch(Uri uri)
            {
                Log.w("PrintHelperKitkat", "close fail ", uri);
                return options;
            }
        return options;
        uri;
        if(uri1 != null)
            try
            {
                uri1.close();
            }
            // Misplaced declaration of an exception variable
            catch(android.graphics.BitmapFactory.Options options)
            {
                Log.w("PrintHelperKitkat", "close fail ", options);
            }
        throw uri;
    }

    private Bitmap loadConstrainedBitmap(Uri uri, int i)
        throws FileNotFoundException
    {
        int l;
        int i1;
        if(i <= 0 || uri == null || mContext == null)
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        loadBitmap(uri, options);
        l = options.outWidth;
        i1 = options.outHeight;
        if(l > 0 && i1 > 0) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        int j;
        int k = Math.max(l, i1);
        for(j = 1; k > i; j <<= 1)
            k >>>= 1;

        if(j <= 0 || Math.min(l, i1) / j <= 0) goto _L1; else goto _L3
_L3:
        android.graphics.BitmapFactory.Options options1;
        synchronized(mLock)
        {
            mDecodeOptions = new android.graphics.BitmapFactory.Options();
            mDecodeOptions.inMutable = true;
            mDecodeOptions.inSampleSize = j;
            options1 = mDecodeOptions;
        }
        obj = loadBitmap(uri, options1);
        synchronized(mLock)
        {
            mDecodeOptions = null;
        }
        return ((Bitmap) (obj));
        obj;
        uri;
        JVM INSTR monitorexit ;
        throw obj;
        uri;
        obj;
        JVM INSTR monitorexit ;
        throw uri;
        Exception exception;
        exception;
        synchronized(mLock)
        {
            mDecodeOptions = null;
        }
        throw exception;
        exception1;
        uri;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    private void writeBitmap(final PrintAttributes attributes, final int fittingMode, final Bitmap bitmap, final ParcelFileDescriptor fileDescriptor, final CancellationSignal cancellationSignal, final android.print.PrintDocumentAdapter.WriteResultCallback writeResultCallback)
    {
        final PrintAttributes pdfAttributes;
        if(mIsMinMarginsHandlingCorrect)
            pdfAttributes = attributes;
        else
            pdfAttributes = copyAttributes(attributes).setMinMargins(new android.print.PrintAttributes.Margins(0, 0, 0, 0)).build();
        (new AsyncTask() {

            protected volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Void[])aobj);
            }

            protected transient Throwable doInBackground(Void avoid[])
            {
                Bitmap bitmap1;
                Object obj;
                android.graphics.pdf.PdfDocument.Page page;
                Object obj1;
                if(cancellationSignal.isCanceled())
                    return null;
                boolean flag;
                try
                {
                    obj = new PrintedPdfDocument(mContext, pdfAttributes);
                    bitmap1 = convertBitmapForColorMode(bitmap, pdfAttributes.getColorMode());
                    flag = cancellationSignal.isCanceled();
                }
                // Misplaced declaration of an exception variable
                catch(Void avoid[])
                {
                    return avoid;
                }
                if(flag) goto _L2; else goto _L1
_L1:
                page = ((PrintedPdfDocument) (obj)).startPage(1);
                if(!mIsMinMarginsHandlingCorrect) goto _L4; else goto _L3
_L3:
                avoid = new RectF(page.getInfo().getContentRect());
_L12:
                obj1 = getMatrix(bitmap1.getWidth(), bitmap1.getHeight(), avoid, fittingMode);
                if(!mIsMinMarginsHandlingCorrect) goto _L6; else goto _L5
_L5:
                page.getCanvas().drawBitmap(bitmap1, ((Matrix) (obj1)), null);
                ((PrintedPdfDocument) (obj)).finishPage(page);
                flag = cancellationSignal.isCanceled();
                if(!flag) goto _L8; else goto _L7
_L7:
                ((PrintedPdfDocument) (obj)).close();
                avoid = fileDescriptor;
                if(avoid == null)
                    break MISSING_BLOCK_LABEL_181;
                try
                {
                    fileDescriptor.close();
                }
                // Misplaced declaration of an exception variable
                catch(Void avoid[]) { }
                if(bitmap1 == bitmap) goto _L2; else goto _L9
_L9:
                bitmap1.recycle();
                return null;
_L4:
                obj1 = new PrintedPdfDocument(mContext, attributes);
                android.graphics.pdf.PdfDocument.Page page1 = ((PrintedPdfDocument) (obj1)).startPage(1);
                avoid = new RectF(page1.getInfo().getContentRect());
                ((PrintedPdfDocument) (obj1)).finishPage(page1);
                ((PrintedPdfDocument) (obj1)).close();
                continue; /* Loop/switch isn't completed */
                avoid;
                ((PrintedPdfDocument) (obj)).close();
                obj = fileDescriptor;
                if(obj == null)
                    break MISSING_BLOCK_LABEL_281;
                try
                {
                    fileDescriptor.close();
                }
                catch(IOException ioexception) { }
                if(bitmap1 != bitmap)
                    bitmap1.recycle();
                throw avoid;
_L6:
                ((Matrix) (obj1)).postTranslate(((RectF) (avoid)).left, ((RectF) (avoid)).top);
                page.getCanvas().clipRect(avoid);
                  goto _L5
_L8:
                ((PrintedPdfDocument) (obj)).writeTo(new FileOutputStream(fileDescriptor.getFileDescriptor()));
                ((PrintedPdfDocument) (obj)).close();
                avoid = fileDescriptor;
                if(avoid == null)
                    break MISSING_BLOCK_LABEL_362;
                try
                {
                    fileDescriptor.close();
                }
                // Misplaced declaration of an exception variable
                catch(Void avoid[]) { }
                if(bitmap1 == bitmap) goto _L2; else goto _L10
_L10:
                bitmap1.recycle();
                return null;
_L2:
                return null;
                if(true) goto _L12; else goto _L11
_L11:
            }

            protected volatile void onPostExecute(Object obj)
            {
                onPostExecute((Throwable)obj);
            }

            protected void onPostExecute(Throwable throwable)
            {
                if(cancellationSignal.isCanceled())
                {
                    writeResultCallback.onWriteCancelled();
                    return;
                }
                if(throwable == null)
                {
                    writeResultCallback.onWriteFinished(new PageRange[] {
                        PageRange.ALL_PAGES
                    });
                    return;
                } else
                {
                    Log.e("PrintHelperKitkat", "Error writing printed content", throwable);
                    writeResultCallback.onWriteFailed(null);
                    return;
                }
            }

            final PrintHelperKitkat this$0;
            final PrintAttributes val$attributes;
            final Bitmap val$bitmap;
            final CancellationSignal val$cancellationSignal;
            final ParcelFileDescriptor val$fileDescriptor;
            final int val$fittingMode;
            final PrintAttributes val$pdfAttributes;
            final android.print.PrintDocumentAdapter.WriteResultCallback val$writeResultCallback;

            
            {
                this$0 = PrintHelperKitkat.this;
                cancellationSignal = cancellationsignal;
                pdfAttributes = printattributes;
                bitmap = bitmap1;
                attributes = printattributes1;
                fittingMode = i;
                fileDescriptor = parcelfiledescriptor;
                writeResultCallback = writeresultcallback;
                super();
            }
        }
).execute(new Void[0]);
    }

    protected android.print.PrintAttributes.Builder copyAttributes(PrintAttributes printattributes)
    {
        android.print.PrintAttributes.Builder builder = (new android.print.PrintAttributes.Builder()).setMediaSize(printattributes.getMediaSize()).setResolution(printattributes.getResolution()).setMinMargins(printattributes.getMinMargins());
        if(printattributes.getColorMode() != 0)
            builder.setColorMode(printattributes.getColorMode());
        return builder;
    }

    public int getColorMode()
    {
        return mColorMode;
    }

    public int getOrientation()
    {
        if(mOrientation == 0)
            return 1;
        else
            return mOrientation;
    }

    public int getScaleMode()
    {
        return mScaleMode;
    }

    public void printBitmap(final String jobName, final Bitmap bitmap, final OnPrintFinishCallback callback)
    {
        if(bitmap == null)
            return;
        final int fittingMode = mScaleMode;
        PrintManager printmanager = (PrintManager)mContext.getSystemService("print");
        Object obj;
        if(isPortrait(bitmap))
            obj = android.print.PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
        else
            obj = android.print.PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
        obj = (new android.print.PrintAttributes.Builder()).setMediaSize(((android.print.PrintAttributes.MediaSize) (obj))).setColorMode(mColorMode).build();
        printmanager.print(jobName, new PrintDocumentAdapter() {

            public void onFinish()
            {
                if(callback != null)
                    callback.onFinish();
            }

            public void onLayout(PrintAttributes printattributes, PrintAttributes printattributes1, CancellationSignal cancellationsignal, android.print.PrintDocumentAdapter.LayoutResultCallback layoutresultcallback, Bundle bundle)
            {
                boolean flag = true;
                mAttributes = printattributes1;
                cancellationsignal = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(1).setPageCount(1).build();
                if(printattributes1.equals(printattributes))
                    flag = false;
                layoutresultcallback.onLayoutFinished(cancellationsignal, flag);
            }

            public void onWrite(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, android.print.PrintDocumentAdapter.WriteResultCallback writeresultcallback)
            {
                writeBitmap(mAttributes, fittingMode, bitmap, parcelfiledescriptor, cancellationsignal, writeresultcallback);
            }

            private PrintAttributes mAttributes;
            final PrintHelperKitkat this$0;
            final Bitmap val$bitmap;
            final OnPrintFinishCallback val$callback;
            final int val$fittingMode;
            final String val$jobName;

            
            {
                this$0 = PrintHelperKitkat.this;
                jobName = s;
                fittingMode = i;
                bitmap = bitmap1;
                callback = onprintfinishcallback;
                super();
            }
        }
, ((PrintAttributes) (obj)));
    }

    public void printBitmap(final String jobName, final Uri imageFile, final OnPrintFinishCallback callback)
        throws FileNotFoundException
    {
        android.print.PrintAttributes.Builder builder;
        imageFile = new PrintDocumentAdapter() {

            private void cancelLoad()
            {
                synchronized(mLock)
                {
                    if(mDecodeOptions != null)
                    {
                        mDecodeOptions.requestCancelDecode();
                        mDecodeOptions = null;
                    }
                }
                return;
                exception;
                obj;
                JVM INSTR monitorexit ;
                throw exception;
            }

            public void onFinish()
            {
                super.onFinish();
                cancelLoad();
                if(mLoadBitmap != null)
                    mLoadBitmap.cancel(true);
                if(callback != null)
                    callback.onFinish();
                if(mBitmap != null)
                {
                    mBitmap.recycle();
                    mBitmap = null;
                }
            }

            public void onLayout(final PrintAttributes oldPrintAttributes, final PrintAttributes newPrintAttributes, final CancellationSignal cancellationSignal, android.print.PrintDocumentAdapter.LayoutResultCallback layoutresultcallback, Bundle bundle)
            {
                boolean flag = true;
                this;
                JVM INSTR monitorenter ;
                mAttributes = newPrintAttributes;
                this;
                JVM INSTR monitorexit ;
                if(cancellationSignal.isCanceled())
                {
                    layoutresultcallback.onLayoutCancelled();
                    return;
                }
                break MISSING_BLOCK_LABEL_30;
                oldPrintAttributes;
                this;
                JVM INSTR monitorexit ;
                throw oldPrintAttributes;
                if(mBitmap != null)
                {
                    cancellationSignal = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(1).setPageCount(1).build();
                    if(newPrintAttributes.equals(oldPrintAttributes))
                        flag = false;
                    layoutresultcallback.onLayoutFinished(cancellationSignal, flag);
                    return;
                } else
                {
                    mLoadBitmap = (layoutresultcallback. new AsyncTask() {

                        protected transient Bitmap doInBackground(Uri auri[])
                        {
                            try
                            {
                                auri = loadConstrainedBitmap(imageFile, 3500);
                            }
                            // Misplaced declaration of an exception variable
                            catch(Uri auri[])
                            {
                                return null;
                            }
                            return auri;
                        }

                        protected volatile Object doInBackground(Object aobj[])
                        {
                            return doInBackground((Uri[])aobj);
                        }

                        protected void onCancelled(Bitmap bitmap)
                        {
                            layoutResultCallback.onLayoutCancelled();
                            mLoadBitmap = null;
                        }

                        protected volatile void onCancelled(Object obj)
                        {
                            onCancelled((Bitmap)obj);
                        }

                        protected void onPostExecute(Bitmap bitmap)
                        {
                            Object obj;
                            super.onPostExecute(bitmap);
                            obj = bitmap;
                            if(bitmap == null)
                                break MISSING_BLOCK_LABEL_108;
                            if(mPrintActivityRespectsOrientation)
                            {
                                obj = bitmap;
                                if(mOrientation != 0)
                                    break MISSING_BLOCK_LABEL_108;
                            }
                            this;
                            JVM INSTR monitorenter ;
                            android.print.PrintAttributes.MediaSize mediasize = mAttributes.getMediaSize();
                            this;
                            JVM INSTR monitorexit ;
                            obj = bitmap;
                            if(mediasize != null)
                            {
                                obj = bitmap;
                                if(mediasize.isPortrait() != PrintHelperKitkat.isPortrait(bitmap))
                                {
                                    obj = new Matrix();
                                    ((Matrix) (obj)).postRotate(90F);
                                    obj = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), ((Matrix) (obj)), true);
                                }
                            }
                            mBitmap = ((Bitmap) (obj));
                            if(obj != null)
                            {
                                bitmap = (new android.print.PrintDocumentInfo.Builder(jobName)).setContentType(1).setPageCount(1).build();
                                boolean flag;
                                if(!newPrintAttributes.equals(oldPrintAttributes))
                                    flag = true;
                                else
                                    flag = false;
                                layoutResultCallback.onLayoutFinished(bitmap, flag);
                            } else
                            {
                                layoutResultCallback.onLayoutFailed(null);
                            }
                            mLoadBitmap = null;
                            return;
                            bitmap;
                            this;
                            JVM INSTR monitorexit ;
                            throw bitmap;
                        }

                        protected volatile void onPostExecute(Object obj)
                        {
                            onPostExecute((Bitmap)obj);
                        }

                        protected void onPreExecute()
                        {
                            cancellationSignal.setOnCancelListener(new android.os.CancellationSignal.OnCancelListener() {

                                public void onCancel()
                                {
                                    cancelLoad();
                                    cancel(false);
                                }

                                final _cls1 this$2;

            
            {
                this$2 = _cls1.this;
                super();
            }
                            }
);
                        }

                        final _cls3 this$1;
                        final CancellationSignal val$cancellationSignal;
                        final android.print.PrintDocumentAdapter.LayoutResultCallback val$layoutResultCallback;
                        final PrintAttributes val$newPrintAttributes;
                        final PrintAttributes val$oldPrintAttributes;

            
            {
                this$1 = final__pcls3;
                cancellationSignal = cancellationsignal;
                newPrintAttributes = printattributes;
                oldPrintAttributes = printattributes1;
                layoutResultCallback = android.print.PrintDocumentAdapter.LayoutResultCallback.this;
                super();
            }
                    }
).execute(new Uri[0]);
                    return;
                }
            }

            public void onWrite(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, android.print.PrintDocumentAdapter.WriteResultCallback writeresultcallback)
            {
                writeBitmap(mAttributes, fittingMode, mBitmap, parcelfiledescriptor, cancellationsignal, writeresultcallback);
            }

            private PrintAttributes mAttributes;
            Bitmap mBitmap;
            AsyncTask mLoadBitmap;
            final PrintHelperKitkat this$0;
            final OnPrintFinishCallback val$callback;
            final int val$fittingMode;
            final Uri val$imageFile;
            final String val$jobName;



            
            {
                this$0 = PrintHelperKitkat.this;
                jobName = s;
                imageFile = uri;
                callback = onprintfinishcallback;
                fittingMode = i;
                super();
                mBitmap = null;
            }
        }
;
        callback = (PrintManager)mContext.getSystemService("print");
        builder = new android.print.PrintAttributes.Builder();
        builder.setColorMode(mColorMode);
        if(mOrientation != 1 && mOrientation != 0) goto _L2; else goto _L1
_L1:
        builder.setMediaSize(android.print.PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
_L4:
        callback.print(jobName, imageFile, builder.build());
        return;
_L2:
        if(mOrientation == 2)
            builder.setMediaSize(android.print.PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setColorMode(int i)
    {
        mColorMode = i;
    }

    public void setOrientation(int i)
    {
        mOrientation = i;
    }

    public void setScaleMode(int i)
    {
        mScaleMode = i;
    }

    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    private static final String LOG_TAG = "PrintHelperKitkat";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode;
    final Context mContext;
    android.graphics.BitmapFactory.Options mDecodeOptions;
    protected boolean mIsMinMarginsHandlingCorrect;
    private final Object mLock = new Object();
    int mOrientation;
    protected boolean mPrintActivityRespectsOrientation;
    int mScaleMode;






}
