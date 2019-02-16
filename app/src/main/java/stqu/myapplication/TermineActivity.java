package stqu.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import stqu.myapplication.TermineloeschenActivity;


/**
 * Created by Imad Abu on 30.03.2016.
 */
public class TermineActivity extends AppCompatActivity {
    Tabletermine myDb;
    Button btn;
    EditText edittermbez ,editdate;
    Button btnAddterm;
    Button btnanzeige;


    int year_x, day_x, month_x;
    static final int DIALOG_ID = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminelayout);  //verbinden der Klasse mit der ausgew. activity
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH); // aktuelle Tage Monat Jahre werden deklariert

        showdialogOnButtonClick();
        myDb = new Tabletermine(this);

        edittermbez = (EditText) findViewById(R.id.txttermbez);
        editdate = (EditText) findViewById(R.id.txtDatum);
        btnAddterm = (Button) findViewById(R.id.btnSpeichernterm);
        btnanzeige = (Button) findViewById(R.id.btnausgabe);

        AddData();
        viewAll();
    }
    public void AddData() {
        btnAddterm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editdate.getText().toString(), edittermbez.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(TermineActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(TermineActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnanzeige.setOnClickListener(
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
                            buffer.append("Termin :" + res.getString(0) + "\n");
                            buffer.append("Datum :" + res.getString(1) + "\n");
                            buffer.append("Beschreibung :" + res.getString(2) + "\n\n");
                            //hier wird der Spaltenindex festgelegt und jeder datensatz ausgewählt
                        }
                        showMessage("Termine", buffer.toString());

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


    public void showdialogOnButtonClick() {
        btn = (Button) findViewById(R.id.btnTermine);

        btn.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);

                    }
                }

        );

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear+1;
            day_x = dayOfMonth;
            Toast.makeText(TermineActivity.this, year_x + "/" + month_x + "/" + day_x,Toast.LENGTH_LONG).show();


        }
    };


    public void openlösch(View View) {
        Intent intent = new Intent(this,TermineloeschenActivity.class);   /** objekt intent das definiert welche activity gestartet wird*/
        startActivity(intent);
    }



}




