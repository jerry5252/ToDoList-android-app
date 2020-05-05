package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.todolist.File.FILE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText editText;
    ListView listView;
    Button btn;

    ArrayList <String> tasks;
    ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);
        btn = findViewById(R.id.btn);

        tasks = File.read(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                String itemEntered = editText.getText().toString();
                adapter.add(itemEntered);
                editText.setText("");

                File.write(tasks, this);

                Toast.makeText(this, "Task Entered", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tasks.remove(position);
        adapter.notifyDataSetChanged();
        File.write(tasks, this);
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
    }
}
