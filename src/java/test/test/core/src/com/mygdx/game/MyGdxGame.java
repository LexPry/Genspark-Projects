package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import sun.font.MFontConfiguration;

import java.awt.*;
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter
    {
    private SpriteBatch batch;
    private Texture hero;
    private Texture goblinTexture;
    private Texture goblinWeaponTexture;
    private Texture background;
    private TextureRegion heroImage;
    private TextureRegion goblinImage;
    private Player player;
    private Enemy goblin;
    private Vector2 playerPosition;
    private Vector2 goblinPosition;
    private BitmapFont font;
    int movementSpeed = 100;
    private String text = "Welcome to Goblins Vs Humans";
    ArrayList<Enemy> enemiesList = new ArrayList<>();

    @Override
    public void create()
        {
        batch = new SpriteBatch();

        // textures
        hero = new Texture(Assets.Characters.Hero);
        goblinTexture = new Texture(Assets.Characters.Goblin);
        goblinWeaponTexture = new Texture(Assets.Characters.WeaponGoblin);
        background = new Texture(Assets.Background.Background);

        // font
        font = new BitmapFont();

        // texture regions
        heroImage = new TextureRegion(hero, 75, 90);
        goblinImage = new TextureRegion(goblinTexture, 75, 90);

        // vectors
        playerPosition = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        goblinPosition = new Vector2(50, 50);

        // instantiating
        player = new Player(heroImage.getRegionWidth(), heroImage.getRegionHeight(), hero, playerPosition, 10, 100, 8, 5);
        goblin = new Enemy(goblinImage.getRegionWidth(), goblinImage.getRegionHeight(), goblinTexture, goblinPosition, 8, 80);

        // adding enemies to ArrayList
        enemiesList.add(goblin);


        }

    @Override
    public void render()
        {
        ScreenUtils.clear(Color.GREEN);
        batch.begin();
        batch.draw(background, 0, 0, 1010, 1010);
        player.draw(batch);
        goblin.draw(batch);
        font.setColor(Color.FIREBRICK);
        font.getData().setScale(1.5f);

        batch.draw(goblinWeaponTexture, 600, 720, 90, 100);

        movePlayer();
        checkCollision(player);
        batch.end();

        batch.begin();
        font.draw(batch, getText(), 100, 950);
        batch.end();
        }

    @Override
    public void dispose() // sprite batches and textures must be disposed
    {
    batch.dispose();
    background.dispose();
    hero.dispose();
    goblinTexture.dispose();
    goblinWeaponTexture.dispose();
    font.dispose();
    }

    private void moveTextureTo(float x, float y)
        {
        playerPosition.x = x;
        playerPosition.y = y;
        }


    private void movePlayer()
        {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))
            {
            moveTextureTo(player.vector2.x,
                    player.vector2.y += movementSpeed * Gdx.graphics.getDeltaTime());
            }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
            {
            moveTextureTo(player.vector2.x,
                    player.vector2.y -= movementSpeed * Gdx.graphics.getDeltaTime());
            }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            {
            moveTextureTo(player.vector2.x -= movementSpeed * Gdx.graphics.getDeltaTime(),
                    player.vector2.y);
            }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            {
            moveTextureTo(player.vector2.x += movementSpeed * Gdx.graphics.getDeltaTime(),
                    player.vector2.y);
            }

        if (player.vector2.x < 0) player.vector2.x = 0;
        if (player.vector2.x > 1000 - 64) player.vector2.x = 1000 - 60;
        if (player.vector2.y < 0) player.vector2.y = 0;
        if (player.vector2.y > 1000 - 64) player.vector2.y = 1000 - 64;
        }

    public void moveEnemy()
        {
        /*
        Move the enemy either based on seconds or just randomly based on
        rand and map height/width
         */
        }

    public void checkDead()
        {
        if (enemiesList.isEmpty())
            {
            System.exit(0);
            }

        for (Enemy enemy : enemiesList)
            {
            if (enemy.getHealth() <= 0)
                {
                enemy.texture.dispose();
                enemiesList.remove(enemy);
                }
            }
        }

    public void checkCollision(Player player)
        {
        for (Enemy enemy : enemiesList)
            {
            if (player.vector2.x == enemy.vector2.x + 10 || player.vector2.x == enemy.vector2.x - 10)
                {
                this.player.combat(enemy);
                setText(enemy.toString());
                enemy.combat(this.player);
                setText(this.player.toString());
                }

            if (player.vector2.y == enemy.vector2.y + 10 || player.vector2.y == enemy.vector2.y - 10)
                {
                player.combat(enemy);
                enemy.combat(player);
                }
            }
        }

    public void setText(String text)
        {
        this.text = text;
        }

    public TextureRegion getHeroImage()
        {
        return heroImage;
        }

    public String getText()
        {
        return this.text;
        }
    }
