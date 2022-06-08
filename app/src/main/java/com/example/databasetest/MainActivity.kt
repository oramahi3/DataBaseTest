package com.example.databasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.*
import com.example.databasetest.DBHelper.Companion.NAME_COl
import com.example.databasetest.DBHelper.Companion.AGE_COL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addname : Button = findViewById(R.id.addName)
        val printname : Button = findViewById(R.id.printName)
        val entername : EditText = findViewById(R.id.enterName)
        val enterage : EditText = findViewById(R.id.enterAge)
        val Name : TextView = findViewById(R.id.Name)
        val Age : TextView = findViewById(R.id.Age)
        val name = entername.text.toString()
        val age = enterage.text.toString()
        // below code is to add on click
        // listener to our add name button
        addname.setOnClickListener{

            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            // name to our database
            db.addName(name, age)

            // Toast to message on the screen
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            entername.text.clear()
            enterage.text.clear()
        }

        // below code is to add on  click
        // listener to our print name button
        printname.setOnClickListener{

            // creating a DBHelper class
            // and passing context to it
            val db = DBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NAME_COl)) + "\n")
            Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.AGE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NAME_COl)) + "\n")
                Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
    }
}