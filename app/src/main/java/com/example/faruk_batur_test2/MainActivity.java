package com.example.faruk_batur_test2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText question, answer1, answer2, answer3, answer4;
    Button insert, update, delete, view;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.question);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        insert = findViewById(R.id.btninsert);
        update = findViewById(R.id.btnupdate);
        delete = findViewById(R.id.btndelete);
        view = findViewById(R.id.btnview);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionTXT = question.getText().toString();
                String answer1TXT = answer1.getText().toString();
                String answer2TXT = answer2.getText().toString();
                String answer3TXT = answer3.getText().toString();
                String answer4TXT = answer4.getText().toString();

                Boolean checkinsertdata = DB.insertquestiondata(questionTXT, answer1TXT, answer2TXT, answer3TXT, answer4TXT);
                if (checkinsertdata == true){
                    Toast.makeText(MainActivity.this, "New Question Inserted!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "New Question Not Inserted!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionTXT = question.getText().toString();
                String answer1TXT = answer1.getText().toString();
                String answer2TXT = answer2.getText().toString();
                String answer3TXT = answer3.getText().toString();
                String answer4TXT = answer4.getText().toString();

                Boolean checkupdatedata = DB.updatequestiondata(questionTXT, answer1TXT, answer2TXT, answer3TXT, answer4TXT);
                if (checkupdatedata == true){
                    Toast.makeText(MainActivity.this, "Question Updated!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Question Not Updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String questionTXT = question.getText().toString();

                Boolean checkdeletedata = DB.deletedata(questionTXT);
                if (checkdeletedata == true){
                    Toast.makeText(MainActivity.this, "Question Deleted!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Question Not Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No Question Exists!", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Quesiton: " + res.getString(0) + "\n");
                    buffer.append("Answer Option 1: " + res.getString(1) + "\n");
                    buffer.append("Answer Option 2: " + res.getString(2) + "\n");
                    buffer.append("Answer Option 3: " + res.getString(3) + "\n");
                    buffer.append("Answer Option 4: " + res.getString(4) + "\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Questions Lists: ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}