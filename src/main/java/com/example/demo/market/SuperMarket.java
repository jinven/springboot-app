package com.example.demo.market;

public class SuperMarket {
    private DrinkProvider drink;
    private FruitProvider fruit;

    public SuperMarket(){
        
    }

    public SuperMarket (DrinkProvider drink, FruitProvider fruit){
        this.drink = drink;
        this.fruit = fruit;
    }

    public void setDrink(DrinkProvider drink){
        this.drink = drink;
    }

    public void setFruit(FruitProvider fruit){
        this.fruit = fruit;
    }

    @Override
    public String toString(){
        return "drink: " + drink + ", fruit: " + fruit;
    }
}