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

//    @Override
//    public void create()
//        {
//        batch = new SpriteBatch();
//        font = new BitmapFont(Gdx.files.internal("SourceCodePro-VariableFont_wght.ttf"));
//        }
//
//    @Override
//    public void render()
//        {
//        batch.begin();
//        font.draw(batch,text,800,800);
//        batch.end();
//        }

    public void setText(String text)
        {
        this.text = text;
        }
    }
