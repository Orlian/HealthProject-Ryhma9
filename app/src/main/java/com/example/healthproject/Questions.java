package com.example.healthproject;

class Questions {
    private static final Questions ourInstance = new Questions();

    static Questions getInstance() {
        return ourInstance;
    }

    private Questions() {
    }
}
