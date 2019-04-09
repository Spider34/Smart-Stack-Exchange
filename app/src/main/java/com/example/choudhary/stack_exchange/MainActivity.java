package com.example.choudhary.stack_exchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void makeReq(View view) {
        GetDataAsync asyncTask= new GetDataAsync(this);
        String tag = ((EditText)findViewById(R.id.search_bar)).getText().toString();
        if (tag.equals("")) return;
        asyncTask.execute("https://api.stackexchange.com/2.2/tags/"+tag+"/faq?site=stackoverflow");
    }

}
