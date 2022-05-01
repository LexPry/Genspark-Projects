package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Entity
    {
    protected int width;
    protected int height;
    protected Texture texture;
    protected Vector2 vector2;

    public Entity(int width, int height, Texture texture, Vector2 vector2)
        {
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.vector2 = vector2;
        }

    void draw(SpriteBatch batch)
        {
        batch.draw(texture, getVectorX(), getVectorY(), width, height);
        }

    public float getVectorX()
        {
        return this.vector2.x;
        }

    public float getVectorY()
        {
        return this.vector2.y;
        }

    public void combat()
        {

        }
    }
