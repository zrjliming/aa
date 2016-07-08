package com.example.ruijie.myapplication.myimageloader;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.example.ruijie.myapplication.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 *
 MiageLoder 使用 加载一张图片直接调用init方法进行了
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 <uses-permission android:name="android.permission.INTERNET" />
 因为要进行缓存所以要用到storage


 ImageLoaderConfiguration参数（只能配置一次，如多次配置，则默认第一次的配置参数）
 线程池 线程数量 和缓存文件的名字

 图片下载配置参数

 ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);

 File cacheDir = StorageUtils.getCacheDirectory(context);  //缓存文件夹路径
 ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
 .memoryCacheExtraOptions(480, 800) // default = device screen dimensions 内存缓存文件的最大长宽
 .diskCacheExtraOptions(480, 800, null)  // 本地缓存的详细信息(缓存的最大长宽)，最好不要设置这个
 .taskExecutor(...)
 .taskExecutorForCachedImages(...)
 .threadPoolSize(3) // default  线程池内加载的数量
 .threadPriority(Thread.NORM_PRIORITY - 2) // default 设置当前线程的优先级
 .tasksProcessingOrder(QueueProcessingType.FIFO) // default
 .denyCacheImageMultipleSizesInMemory()
 .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
 .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
 .memoryCacheSizePercentage(13) // default
 .diskCache(new UnlimitedDiscCache(cacheDir)) // default 可以自定义缓存路径
 .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
 .diskCacheFileCount(100)  // 可以缓存的文件数量
 // default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
 .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
 .imageDownloader(new BaseImageDownloader(context)) // default
 .imageDecoder(new BaseImageDecoder()) // default
 .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
 .writeDebugLogs() // 打印debug log
 .build(); //开始构建


 图片显示参数配置

 DisplayImageOptions options = new DisplayImageOptions.Builder()

 .showImageOnLoading(R.drawable.ic_stub) // 设置图片下载期间显示的图片
 .showImageForEmptyUri(R.drawable.ic_empty) // 设置图片Uri为空或是错误的时候显示的图片
 .showImageOnFail(R.drawable.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
 .resetViewBeforeLoading(false)  // default 设置图片在加载前是否重置、复位
 .delayBeforeLoading(1000)  // 下载前的延迟时间
 .cacheInMemory(false) // default  设置下载的图片是否缓存在内存中
 .cacheOnDisk(false) // default  设置下载的图片是否缓存在SD卡中
 .preProcessor(...)
 .postProcessor(...)
 .extraForDownloader(...)
 .considerExifParams(false) // default
 .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
 .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型
 .decodingOptions(...)  // 图片的解码设置
 .displayer(new SimpleBitmapDisplayer()) // default  还可以设置圆角图片new RoundedBitmapDisplayer(20)
 .handler(new Handler()) // default
 .build();





 */
public class MiageLoderActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miage_loder);
        imageView = (ImageView) findViewById(R.id.iv);
        makeimage();
    }
    private void makeimage() {
        final String urlPath = "http://www.xiansuan.com/uploads/allimg/160107/wm4xpmuvsuk173074.jpg";

        ImageLoader img_loader = ImageLoader.getInstance();
//        初始化配置信息
        img_loader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.qq)
//                内存缓存
                .cacheInMemory(true)
//                sdk缓存
                .cacheOnDisk(true)
                .build();

        img_loader.displayImage(urlPath, imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });

 /*
//
//      加载图片 一个图片加载的监听
//      初始一张图片
        img_loader.loadImage(urlPath,new ImageLoadingListener() {
//            开始加载
            @Override
            public void onLoadingStarted(String s, View view) {

            }
//失败
            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

//            完成
            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

//            取消
            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }
*/
    }
}
