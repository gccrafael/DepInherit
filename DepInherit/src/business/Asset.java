/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.swing.JRadioButton;

/**
 *
 * @author TechWiz
 */
public class Asset 
{
    private String name, errmsg;
    private double cost, salvage, straightDep, doubledeclinerate;  
    private boolean built;
    private int life;
    
    public Asset()
    { 
        name = "";
        cost = 0;
        salvage = 0;
        life = 0;
        errmsg = "";
        built = false;
    }
    
    public Asset(String name, double cost, double salvage, int life)
    {
        this.name = name;
        this.cost = cost;
        this.salvage = salvage;
        this.life = life;        
        if (!isValid()) {
            this.built = false;
        };
               
    }
    private boolean isValid()
    {
        this.errmsg = "";
        if (this.cost <= 0)
        {
            errmsg += "cost not a positive value;";
        }    
        else if (this.salvage >= this.cost)
        {
            errmsg += "Salvage must be less than cost;";
        } 
        if (this.salvage < 0)
        {
            errmsg += "Salvage must be >= 0;";
        }
        if (this.life < 1)
        {
            errmsg += "Life must be 1 year or more;";
        }    
        if (errmsg.isEmpty())
        {
            return true;
        } 
        return false;
    }        
    
    public String getErrorMsg()
    {
        return errmsg;
    }   
    
    public String getAsset() {
        return name;
    }

    public void setAsset(String asset) {
        this.name = asset;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
        this.built = false;
    }

    public double getSalvage() {
        return salvage;
    }

    public void setSalvage(double salvage) {
        this.salvage = salvage;
        this.built = false;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
        this.built = false;
    } 
    protected boolean getBuilt() {
        return this.built;
    }
    protected void setBuilt(boolean b) {
        this.built = b;
    }   
}
