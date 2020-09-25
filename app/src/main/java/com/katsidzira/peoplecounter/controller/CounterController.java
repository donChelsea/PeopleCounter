package com.katsidzira.peoplecounter.controller;

import com.katsidzira.peoplecounter.model.Counter;

public class CounterController {

    private static CounterController controller;
    private Counter counter;
    private static Boolean isAtMaxCapacity;

    public void init() {
        if (counter != null) return;
        counter = new Counter();
    }

    public static CounterController getInstance() {
        if (controller == null) {
            controller = new CounterController();
        }
        return controller;
    }

    public void addPerson() {
        checkCapacity();

        counter.setTotal(counter.getTotal() + 1);
        counter.setPeople(counter.getPeople() + 1);
    }

    public void removePerson() {
        checkCapacity();

        counter.setPeople(counter.getPeople() - 1);
    }

    public void resetCounter() {
        counter.setTotal(0);
        counter.setPeople(0);
    }

    private void checkCapacity() {
        if (counter.getPeople() >= 15) {
            isAtMaxCapacity = true;
        } else {
            isAtMaxCapacity = false;
        }
    }

    public Counter getCounter() {
        return counter;
    }

    public Boolean getAtMaxCapacity() {
        return isAtMaxCapacity;
    }
}
