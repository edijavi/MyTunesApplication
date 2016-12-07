/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunesapplication.bll;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Adam
 */
public class Playlist extends Item
{

    private SimpleStringProperty name;

    public Playlist()
    {
        this.name = new SimpleStringProperty();
    }

    public void setName(String name)
    {
        this.name = new SimpleStringProperty(name);
    }

    public String getName()
    {
        return name.get();
    }

}
