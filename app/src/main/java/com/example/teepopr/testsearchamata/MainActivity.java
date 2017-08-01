package com.example.teepopr.testsearchamata;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.teepopr.testsearchamata.dao.MapSearch;
import com.example.teepopr.testsearchamata.dao.ResponseDao;
import com.example.teepopr.testsearchamata.databinding.ActivityMainBinding;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.getInstance().getService().getMapSearch(binding.editText.getText().toString(), "amata_admin")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ResponseDao>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("Error", e.toString());
                            }

                            @Override
                            public void onNext(ResponseDao responseDao) {
                                String result = "";
                                for (MapSearch mapSearch : responseDao.getData().getMapSearch())
                                    result = result + mapSearch.getNameE() + "\n";
                                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}
