package stqu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Imad Abu on 30.09.2016.
 */
public class Repeating_activity extends AppCompatActivity {
@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    Intent intent = new Intent(this, HausaufgabenloeschenActivity.class);
    setContentView(R.layout.hausaufgabenlayout);
    }

}
