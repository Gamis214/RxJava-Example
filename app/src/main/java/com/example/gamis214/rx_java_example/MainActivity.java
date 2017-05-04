package com.example.gamis214.rx_java_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textview;
    Button button;
    EditText editText;

    Observable<String> Observable;
    Observer<String> Observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.ediText);

        button.setOnClickListener(this);

        Observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(editText.getText().toString());
                subscriber.onCompleted();
            }
        });

        Observer = new Observer<String>() {

            @Override
            public void onCompleted() {
                Toast.makeText(getApplicationContext(),"COMPLETED",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                textview.setText(s);
            }
        };

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                Observable.subscribe(Observer);
                break;
        }
    }
}
