package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity
    {
    private final int strength;
    private int health;

    public Enemy(int width, int height, Texture texture, Vector2 vector2, int strength, int health)
        {
        super(width, height, texture, vector2);
        this.strength = strength;
        this.health = health;
        }

    public int getStrength()
        {
        return strength;
        }

    public int getHealth()
        {
        return health;
        }

    public void setHealth(int health)
        {
        this.health = health;
        }

    @Override
    public String toString()
        {
        return "Enemy{" +
                "strength=" + strength +
                ", health=" + health +
                '}';
        }
    }
