package stqu.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by Imad Abu on 05.03.2016.
 */
public class AuswahlmenuActivity extends AppCompatActivity {// extends verbindet die classe mit einer activity*/




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auswahlmenulayout);//verbinden der Klasse mit der ausgew. activity




    }

    public void openstundenplan(View View) {
        Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://stupla.gswn.de/schueler/default.htm"));   /** objekt intent das definiert welche activity gestartet wird*/
        startActivity(browserintent);

    }
    public void openhausaufgmenu(View View) {
        Intent intent = new Intent(this,HausaufgabenActivity.class);   /** objekt intent das definiert welche activity gestartet wird*/
        startActivity(intent);

    }
    public void opennotenmenu(View View) {
        Intent intent = new Intent(this,NotenActivity.class);   /** objekt intent das definiert welche activity gestartet wird*/
        startActivity(intent);

    }
    public void openterminemenu(View View) {
        Intent intent = new Intent(this,TermineActivity.class);   /** objekt intent das definiert welche activity gestartet wird*/
        startActivity(intent);

    }



}
