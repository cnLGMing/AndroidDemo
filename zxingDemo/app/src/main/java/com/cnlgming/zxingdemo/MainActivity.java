package com.cnlgming.zxingdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.decode.CaptureActivity;
import com.google.zxing.client.android.encode.QRCodeEncoder;
import com.google.zxing.common.HybridBinarizer;

import java.util.EnumMap;
import java.util.Map;


/**
 * @创建者 cnLGMing
 * @博客 www.liuguangmingcn.com
 * @创建时间 16/7/22 00:57
 * @描述 zxing的简单使用:扫描二维码/解析二维码/生成二维码
 */
public class MainActivity extends AppCompatActivity {

    private static final int                    REQUEST_CODE = 0;
    private static final Map<DecodeHintType, ?> HINTS        = new EnumMap<>(DecodeHintType.class);

    private Button    mBtnScan;
    private TextView  mTvScanResult;
    private Button    mBtnCreate;
    private ImageView mIvCreateBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        initListener();
    }

    /**
     * 初始化控件
     */
    private void assignViews() {
        mBtnScan = (Button) findViewById(R.id.btn_scan);
        mTvScanResult = (TextView) findViewById(R.id.tv_scan_result);
        mBtnCreate = (Button) findViewById(R.id.btn_create);
        mIvCreateBarcode = (ImageView) findViewById(R.id.iv_create_barcode);
    }

    /**
     * 初始化监听事件
     */
    private void initListener() {
        /* 扫描二维码按钮 */
        mBtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        /* 生成二维码按钮 */
        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    /**
                     * 第一个参数:内容
                     * 第二个参数:二维码图片的长宽(其实是正方形)
                     */
                    Bitmap bitmap = QRCodeEncoder.encodeAsBitmap("http://www.liuguangmingcn.com", 500);
                    mIvCreateBarcode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        /* 长按二维码时,解析二维码数据 */
        mIvCreateBarcode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // 开启cache
                mIvCreateBarcode.setDrawingCacheEnabled(true);
                // 通过cache机制获取缓存图片
                Bitmap bitmap = Bitmap.createBitmap(mIvCreateBarcode.getDrawingCache());
                // 关闭cache.获取cache通常会占用一定的内存，所以通常不需要的时候有必要对其进行清理.
                // 通过destroyDrawingCache或setDrawingCacheEnabled(false)实现。
                mIvCreateBarcode.setDrawingCacheEnabled(false);
                decodeQRCode(bitmap);
                return true;
            }
        });
    }

        /**
         * 解析二维码.因为解析可能会耗时,所以使用了AsyncTask进行处理
         *
         * @param bitmap 带解析的二维码图片
         */
        private void decodeQRCode(final Bitmap bitmap) {
            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    try {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        int[] pixels = new int[width * height];
                        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
                        // 将 ARGB 像素阵列转换为 RGB
                        RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
                        // 进行解码
                        Result result = new MultiFormatReader().decode(
                                new BinaryBitmap(new HybridBinarizer(source)), HINTS);
                        return result.getText();
                    } catch (Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "解析出错.", Toast.LENGTH_SHORT).show();
                            }
                        });
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(String result) {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                }
            }.execute();
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // CaptureActivity中使用RESULT_OK作为结果返回码
        if (resultCode == RESULT_OK) {  // RESULT_OK == -1
            Bundle bundle = data.getExtras();
            // CaptureActivity中使用"result"作为 key
            String scanResult = bundle.getString("result");
            mTvScanResult.setText("扫描结果: " + scanResult);
        }
    }
}
