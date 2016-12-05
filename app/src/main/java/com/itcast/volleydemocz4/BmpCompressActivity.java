package com.itcast.volleydemocz4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lxj on 2016/11/11.
 */

public class BmpCompressActivity extends AppCompatActivity {
    @Bind(R.id.iv_image)
    ImageView ivImage;
    @Bind(R.id.tv_size)
    TextView tvSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmp_compress);
        ButterKnife.bind(this);

         new Handler().postDelayed(
                 new Runnable() {
                     @Override
                     public void run() {
                         showBmpSize();
                     }
                 }, 200);

    }

    private void showBmpSize() {
        //1.获取当前app暂用的内存大小
        long preMemory = Runtime.getRuntime().totalMemory();

        //2.加载图片
        BitmapFactory.Options opts = new BitmapFactory.Options();

        //只加载图片的宽高参数，并不会讲图片真正加载到内存中
        opts.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(getResources(), R.drawable.fast,opts);
        //获取图片的宽高
        int bmpW = opts.outWidth;
        int bmpH = opts.outHeight;

        //设置图片的采样比例
        opts.inSampleSize = calculateSampleSize(bmpW,bmpH);

        //采用颜色模式进一步压缩bitmap的体积
        opts.inPreferredConfig = Bitmap.Config.RGB_565;//一个像素占用2个字节

        //将改标准置为false
        opts.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fast,opts);
        ivImage.setImageBitmap(bitmap);


        //3.再次计算加载图片后占用的内存大小
        long cost = Runtime.getRuntime().totalMemory() - preMemory;
        tvSize.setText("图片占用内存："+ Formatter.formatFileSize(this,cost));

    }

    /**
     * 计算采样比例
     * @return
     */
    private int calculateSampleSize(int bmpW,int bmpH) {
        int sampleSize = 1;
        float ratioW = bmpW*1f/ivImage.getWidth();//计算宽的比例
        float ratioH = bmpH*1f/ivImage.getHeight();//计算高的比例

        sampleSize = (int) Math.max(ratioH,ratioW);

        if(sampleSize<1){
            sampleSize = 1;
        }

        return sampleSize;
    }
}
