package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.mygdx.game.MyGdxGame;
import org.lwjgl.system.SharedLibrary;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher
    {
    public static final int SCREEN_HEIGHT = 1000;
    public static final int SCREEN_WIDTH = 1000;

    public static void main(String[] arg)
        {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setIdleFPS(30);
        config.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
        config.setTitle("Goblins V Humans");
        new Lwjgl3Application(new MyGdxGame(), config);
        }
    }
