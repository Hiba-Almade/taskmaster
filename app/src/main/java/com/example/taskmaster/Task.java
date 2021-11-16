package com.example.taskmaster;

import javax.xml.transform.sax.SAXResult;

public class Task {
    public String title;
    public String body;
    public String state;

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }
}
