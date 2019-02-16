package stqu.myapplication;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Imad Abu on 26.03.2016.
 */
public class HausaufgabenActivity extends AppCompatActivity
   {
    Tablehw myDb;  // uni direktionale assoziation wird gebildet mit Klasse Tablehw
    EditText edithw, editsubject;// Buttons und Textfelder  werden deklariert
    Button btnAddData;
    Button btnaktual;
    TextView ausgabehw;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hausaufgabenlayout);
        // die activity dieser Klasse wird ausgewählt das heißt man stellt verbindung zur gui und Steuerung her


        findViewById(R.id.btnnotifi).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 15);
                calendar.set(Calendar.MINUTE,0 );
                calendar.set(Calendar.SECOND, 0);


                Intent intent = new Intent(getApplicationContext(), Notification_Receiver.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                Toast.makeText(HausaufgabenActivity.this, "Erinnerung gesetzt sie werden täglich um 15 Uhr an ihre Hausaufgaben erinnert!", Toast.LENGTH_SHORT).show();

            }
        });


        myDb = new Tablehw(this);

        edithw = (EditText) findViewById(R.id.txteingabehw);                   // Die Buttons und Textfelder erhalten ihre zuweisung zu einer Variable
        editsubject = (EditText) findViewById(R.id.txteingabefach);
        btnAddData = (Button) findViewById(R.id.btnSpeichern);
        btnaktual = (Button) findViewById(R.id.btnaktualisiere);
        ausgabehw = (TextView) findViewById(R.id.txtausgabe);



        AddData();
        viewAll();

    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(edithw.getText().toString(), editsubject.getText().toString()); // Variable die Prüfen soll ob Datensatz Daten enthält
                        if (isInserted = true)
                            Toast.makeText(HausaufgabenActivity.this, "Data Inserted", Toast.LENGTH_LONG).show(); // If anweisung die den Datensatz auf Inhalt üperprüft und dann entsprechende Meldungen ausgibt
                        else
                            Toast.makeText(HausaufgabenActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnaktual.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {                                 // es wird überprüft ob ein Datensatz verfügbar ist! und wenn nicht eine entsprechende Meldung
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Hausaufgabe :" + res.getString(0) + "\n");
                            buffer.append("Beschreibung :" + res.getString(1) + "\n");
                            buffer.append("Fach :" + res.getString(2) + "\n\n");
                            //hier wird der Spaltenindex festgelegt und jeder datensatz ausgewählt
                        }
                        showMessage("Hausaufgaben", buffer.toString()); // ausgabe der Daten wird festgelegt Hausaufgaben ist hier die Überschrift

                    }
                }
        );
    }



    public void showMessage(String title, String Message) {                  // Eine Methode die den MessageBuilder initialisiert
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    public void openlöschmenu(View View) {
        Intent intent = new Intent(this, HausaufgabenloeschenActivity.class);   /** objekt intent das definiert welche activity gestartet wird*/
        startActivity(intent);
    }


}