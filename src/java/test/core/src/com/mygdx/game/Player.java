package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity
    {
    private int strength;
    private int health;
    private final int defense;
    private int potions;

    public Player(int width, int height, Texture texture, Vector2 vector2, int strength, int health, int defense, int potions)
        {
        super(width, height, texture, vector2);
        this.strength = strength;
        this.health = health;
        this.defense = defense;
        this.potions = potions;
        }

    public Texture getTexture()
        {
        return texture;
        }

    public int getStrength()
        {
        return strength;
        }

    public void setStrength(int strength)
        {
        this.strength = strength;
        }

    public int getHealth()
        {
        return health;
        }

    public void setHealth(int health)
        {
        this.health = health;
        }

    public int getDefense()
        {
        return defense;
        }

    public int getPotions()
        {
        return potions;
        }

    public void setPotions(int potions)
        {
        this.potions = potions;
        }

    @Override
    public String toString()
        {
        return "Player{" +
                "strength=" + strength +
                ", health=" + health +
                ", defense=" + defense +
                ", potions=" + potions +
                '}';
        }
    }
