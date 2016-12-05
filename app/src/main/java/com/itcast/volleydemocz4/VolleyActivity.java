package com.itcast.volleydemocz4;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolleyActivity extends AppCompatActivity {

    String test = "http://192.168.191.1:8080/apitest/test";
    String image = "http://192.168.191.1:8080/apitest/image";
    String delay = "http://192.168.191.1:8080/apitest/delay";

    @Bind(R.id.btn_request)
    Button btnRequest;
    @Bind(R.id.tv_result)
    TextView tvResult;
    @Bind(R.id.iv_image)
    ImageView ivImage;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //1.创建请求队列,请求队列是用来执行请求的
        requestQueue = Volley.newRequestQueue(this);

        //执行一个延时请求
        delayRequest();


//        VolleyHelper.create(this)
//                    .execRequest(test, User.class, new Response.Listener<User>() {
//                        @Override
//                        public void onResponse(User response) {
//
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    });

//        VolleyHelper.create(this)
//                    .execRequest(test, User.class, new VolleyHelper.VolleyCallbck<User>() {
//                        @Override
//                        public void onSuccess(User user) {
//
//                        }
//                        @Override
//                        public void onFial(Exception error) {
//
//                        }
//                    });
    }

    private void delayRequest() {
        StringRequest stringRequest = new StringRequest(delay, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvResult.setText(response);
            }
        }, errorListener);
        //给stringRequest设置一个标识
        stringRequest.setTag("1");

        //3.执行请求
        requestQueue.add(stringRequest);
    }

    @OnClick(R.id.btn_request)
    public void onClick() {

//        volleyGet();
//        jsonobjRequest();
//        imageRequest();
//        imageLoader();

//        gsonRequest();

        cancelRequest();

    }

    private void cancelRequest() {
        //取消请求的代码一般会写在onDestory中，可以避免内存泄露
        requestQueue.cancelAll("1");
    }

    private void gsonRequest() {
        //2.创建GsonRequest类
        GsonRequest<User> gsonRequest = new GsonRequest<>(test, User.class, new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                tvResult.setText(response.gender);
            }
        }, errorListener);

        //3.执行请求
        requestQueue.add(gsonRequest);
    }

    private void imageLoader() {
        VolleyCache volleyCache = new VolleyCache();
        ImageLoader imageLoader = new ImageLoader(requestQueue, volleyCache);
        imageLoader.get(image,ImageLoader.getImageListener(ivImage,R.mipmap.ic_launcher,
                R.mipmap.ic_launcher));
    }

    private void imageRequest() {
        //2.创建ImageRequest对象
        ImageRequest imageRequest = new ImageRequest(image, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                ivImage.setImageBitmap(response);
            }
        }, 400, 400, Bitmap.Config.RGB_565, errorListener);
        //3.执行请求
        requestQueue.add(imageRequest);
    }

    private void jsonobjRequest() {
        //2.创建JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(test, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    tvResult.setText(response.getString("nickname"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, errorListener);
        //3.执行请求
        requestQueue.add(jsonObjectRequest);
    }

    private void volleyGet() {
        //2.创建请求对象
        StringRequest stringRequest = new StringRequest(test, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvResult.setText(response);
            }
        }, errorListener);

        //3.执行请求
        requestQueue.add(stringRequest);
    }


    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            tvResult.setText(error.getMessage());
        }
    };
}
