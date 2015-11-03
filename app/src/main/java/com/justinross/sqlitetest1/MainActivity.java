package com.justinross.sqlitetest1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test1.db", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("DROP TABLE contacts");
        sqLiteDatabase.execSQL("CREATE TABLE contacts(name TEXT, phone INTEGER, email TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('justin',9046352750,'ross@boldcityapps/io');");
        sqLiteDatabase.execSQL("INSERT INTO contacts VALUES('amy',9045553450,'amy@boldcityapps/io');");
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM contacts", null);
        if(query.moveToFirst()) {
            do {
                //cycle through our records
                String name = query.getString(0);
                int phone = query.getInt(1);
                String email = query.getString(2);
                Toast.makeText(getBaseContext(),"Name = " + name + " phone = " + phone + " email = " + email, Toast.LENGTH_LONG).show(); }
            while(query.moveToNext());

        }
        else {
            Toast.makeText(getBaseContext(), "Error, blah blah", Toast.LENGTH_LONG);
        }
        sqLiteDatabase.close();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
