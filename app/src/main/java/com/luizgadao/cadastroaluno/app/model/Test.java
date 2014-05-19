package com.luizgadao.cadastroaluno.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luizcarlos on 19/05/14.
 */
public class Test implements Serializable
{
    private String name;
    private String description;
    private String data;
    private List<String> topics = new ArrayList<String>();

    public Test()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.data;
    }
}
