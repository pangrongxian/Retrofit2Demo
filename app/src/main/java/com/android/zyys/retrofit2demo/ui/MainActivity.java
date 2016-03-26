package com.android.zyys.retrofit2demo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.zyys.retrofit2demo.R;
import com.android.zyys.retrofit2demo.bean.PhoneResult;
import com.android.zyys.retrofit2demo.model.PhoneService;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.phone_view)
    EditText phoneView;
    @Bind(R.id.result_view)
    TextView resultView;

    private static final String BASE_URL = "http://apis.baidu.com";
    private static final String API_KEY = "e1bcf0ddab8a52f404987d12fc4d3fef";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    public void query(View view) {
        //1.创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl(BASE_URL)//主机地址
                .build();

        //2.创建访问api请求
        PhoneService service = retrofit.create(PhoneService.class);
        Call<PhoneResult> call = service.getResult(API_KEY,phoneView.getText().toString());

        //3.发送请求
        call.enqueue(new Callback<PhoneResult>() {
            @Override
            public void onResponse(Call<PhoneResult> call, Response<PhoneResult> response) {
                //处理结果
                if (response.isSuccessful()){
                    PhoneResult result = response.body();
                    if (result != null){
                        PhoneResult.RetDataBean retDataBean = result.getRetData();
                        resultView.append("地址：" + retDataBean.getCity());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneResult> call, Throwable t) {

            }
        });

    }
}
