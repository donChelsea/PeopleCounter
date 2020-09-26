package com.katsidzira.peoplecounter.controller;


import com.katsidzira.peoplecounter.model.Counter;

public class CounterController {

    private static CounterController controller;
    private Counter counter;
    private static Boolean isAtMaxCapacity = false;

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
        counter.setTotal(counter.getTotal() + 1);
        counter.setPeople(counter.getPeople() + 1);

        checkCapacity();
    }

    public void removePerson() {
        counter.setPeople(counter.getPeople() - 1);

        checkCapacity();
    }

    public void resetCounter() {
        counter.setTotal(0);
        counter.setPeople(0);
    }

    private void checkCapacity() {
        if (counter.getPeople() >= 16) {
            isAtMaxCapacity = true;
        } else {
            isAtMaxCapacity = false;
        }
    }

    public Counter getCounter() {
        return counter;
    }

    public Boolean getIsAtMaxCapacity() {
        return isAtMaxCapacity;
    }
}
