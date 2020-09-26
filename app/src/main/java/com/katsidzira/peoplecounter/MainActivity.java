package com.katsidzira.peoplecounter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.katsidzira.peoplecounter.controller.CounterController;

public class MainActivity extends AppCompatActivity {
    private static final String TOTAL = "total";
    private static final String PEOPLE = "people";
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

        peopleTv.setText(R.string.people_start);
        totalTv.setText(R.string.total_start);
        removeButton.setVisibility(View.INVISIBLE);

        controller = CounterController.getInstance();
        controller.init();

        addButton.setOnClickListener(v -> {
            controller.addPerson();
            updateViews();
            isEmpty();
            isOverCapacity();
        });

        removeButton.setOnClickListener(v -> {
            controller.removePerson();
            updateViews();
            isEmpty();
            isOverCapacity();
        });

        resetButton.setOnClickListener(v -> {
            controller.resetCounter();
            updateViews();
            isEmpty();
            isOverCapacity();
        });

    }

    private void updateViews(int... values) {
        if (values.length != 0) {
            ifOnSavedInstanceState(values);
        } else {
            peopleTv.setText(controller.getCounter().getPeople() + " people");
            totalTv.setText("Total: " + controller.getCounter().getTotal());
        }
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

    private void ifOnSavedInstanceState(int... values) {
        int total = values[0];
        int people = values[1];

        totalTv.setText("Total: " + total);
        peopleTv.setText(people + " people");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TOTAL, controller.getCounter().getTotal());
        outState.putInt(PEOPLE, controller.getCounter().getPeople());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            updateViews(savedInstanceState.getInt(TOTAL), savedInstanceState.getInt(PEOPLE));
            isEmpty();
            isOverCapacity();
        }
    }
}
