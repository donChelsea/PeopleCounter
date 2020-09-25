package com.katsidzira.peoplecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.katsidzira.peoplecounter.controller.CounterController;

public class MainActivity extends AppCompatActivity {
    TextView peopleTv, totalTv;
    Button addButton, resetButton, removeButton;
    private CounterController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleTv = findViewById(R.id.textview_people);
        totalTv = findViewById(R.id.textview_total);
        addButton = findViewById(R.id.button_add);
        resetButton = findViewById(R.id.button_reset);
        removeButton = findViewById(R.id.button_remove);

        controller = new CounterController();
        controller.init();

        peopleTv.setText(String.valueOf(controller.getCounter().getPeople()));
        totalTv.setText(String.valueOf(controller.getCounter().getTotal()));

    }
}
