package stqu.myapplication;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Imad Abu on 26.09.2016.
 */
public class TermineloeschenActivity extends AppCompatActivity {
    Tabletermine myDb;
    Button btnDelete,btnausgabe;
    EditText txtid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termineloeschen);
        myDb = new Tabletermine(this);

        btnausgabe  = (Button) findViewById(R.id.btnanzeigent);
        btnDelete  = (Button) findViewById(R.id.btnloeschen);
        txtid = (EditText) findViewById(R.id.txtid);

        DeleteData();
        viewAll();
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(txtid.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(TermineloeschenActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(TermineloeschenActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
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
                            buffer.append("TerminNr :" + res.getString(0) + "\n");
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
}
