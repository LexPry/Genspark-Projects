package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.sun.jndi.ldap.LdapPoolManager;
import sun.nio.cs.ext.MacArabic;

import java.util.Random;

public class Enemy extends Entity
    {
    private final int strength;
    private int health;

    // component
    Random rand = new Random();

    public Enemy(int width, int height, Texture texture, Vector2 vector2, int strength, int health)
        {
        super(width, height, texture, vector2);
        this.strength = strength;
        this.health = health;
        }

    public void combat(Player player)
        {
        int damage = rand.nextInt(this.getStrength());
        player.setHealth(player.getHealth() - damage);
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
