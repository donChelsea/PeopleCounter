package com.katsidzira.peoplecounter.controller;

import com.katsidzira.peoplecounter.model.Counter;

public class CounterController {

    private Counter counter;
    private Boolean isAtMaxCapacity;

    public void init() {
        if (counter != null) return;
        counter = new Counter();
    }

    public void addPerson() {
        checkCapacity();

        counter.setTotal(counter.getTotal() + 1);
        counter.setPeople(counter.getPeople() + 1);
    }

    public void removePerson() {
        checkCapacity();

        counter.setPeople(counter.getPeople() + 1);
    }

    public void resetCounter() {
        counter.setTotal(0);
        counter.setPeople(0);
    }

    public void checkCapacity() {
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
