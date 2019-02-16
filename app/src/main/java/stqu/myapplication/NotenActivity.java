package stqu.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Imad Abu on 29.03.2016.
 */
public class NotenActivity extends AppCompatActivity {

    TableNoten myDb;
    EditText eingabefach,eingabenote;
    Button btnAddData,btnausgabe,btndelete;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notenlayout);
        myDb = new TableNoten(this);

        eingabenote = (EditText) findViewById(R.id.txteingabenote);
        eingabefach = (EditText) findViewById(R.id.txteingabefach);
        btnAddData = (Button) findViewById(R.id.btnSpeichernnote);
        btnausgabe  = (Button) findViewById(R.id.btnanzeigennote);

        AddData();
        viewAll();
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(eingabefach.getText().toString(), eingabenote.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(NotenActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(NotenActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnausgabe.setOnClickListener(
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
                            buffer.append("NotenNr :" + res.getString(0) + "\n");
                            buffer.append("Fach:" + res.getString(1) + "\n");
                            buffer.append("Note :" + res.getString(2) + "\n");

                            //hier wird der Spaltenindex festgelegt und jeder datensatz ausgewählt
                        }
                        showMessage("Noten", buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title, String Message) {                  // eine Meldung erscheint wenn keine Daten vorhanden sind!
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void openlöschmenu(View View) {
        Intent intent = new Intent(this,NotenloeschenActivity.class);   /** objekt intent das definiert welche activity gestartet wird*/
        startActivity(intent);
    }

}
