package com.katsidzira.peoplecounter;

import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        removeButton.setVisibility(View.INVISIBLE);

        controller = CounterController.getInstance();
        controller.init();

        if (savedInstanceState != null) {
            updateViews();
            isEmpty();
            isOverCapacity();
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.addPerson();
                updateViews();
                isEmpty();
                isOverCapacity();
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.removePerson();
                updateViews();
                isEmpty();
                isOverCapacity();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.resetCounter();
                updateViews();
                isEmpty();
                isOverCapacity();
            }
        });

    }

    private void updateViews() {
        peopleTv.setText(controller.getCounter().getPeople() + " people");
        totalTv.setText("Total: " + controller.getCounter().getTotal());
    }

    private void isEmpty() {
        if (controller.getCounter().getPeople() == 0) {
            removeButton.setVisibility(View.INVISIBLE);
        } else {
            removeButton.setVisibility(View.VISIBLE);
        }
    }

    private void isOverCapacity() {
        if (controller.getCounter().getPeople() >= 15) {
            peopleTv.setTextColor(Color.RED);
        } else {
            peopleTv.setTextColor(Color.BLACK);
        }
    }


}
