package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextDisplay extends ApplicationAdapter
    {
    SpriteBatch batch;
    BitmapFont font;
    String text;

    void draw(BitmapFont font, SpriteBatch batch)
        {
        font.draw(batch, text,850,850);
        }

    public void setText(String text)
        {
        this.text = text;
        }
    }
