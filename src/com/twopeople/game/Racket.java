package com.twopeople.game;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Racket extends Entity {
    public static int WIDTH = 60;
    public static int HEIGHT = 12;
    File soundFile = new File("res/audio/drip.wav"); //Звуковой файл
    File soundFile2 = new File("res/audio/bonus12.wav"); //Звуковой файл

    private int glowPosition = 0;

    public Racket(World world, int x, int y) {
        super(world, x, y, WIDTH, HEIGHT);
    }

    @Override
    public void update() {


        InputHandler input = world.getGame().getInput();

        int speed = 8;

        //        if (input.hasMouseMoved() && input.isMouseIn()) {
        //            setX(input.getMouseX());
        //        } else {
        if (input.left.isDown()) {
            move(-speed, 0);
        }
        if (input.right.isDown()) {
            move(speed, 0);
        }
        //        //        }

        if (getX() <= 0) {
            setX(0);
        }

        if (getX() >= Game.WIDTH - WIDTH) {
            setX(Game.WIDTH - WIDTH);
        }





        BonusManager bm = world.getGame().getBonusManager();
        if (bm.isInactive(BonusType.PlatformExtension)) {
            //Получаем AudioInputStream
            //Вот тут могут полететь IOException и UnsupportedAudioFileException
            AudioInputStream ais = null;
            try {
                ais = AudioSystem.getAudioInputStream(soundFile2);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Получаем реализацию интерфейса Clip
            //Может выкинуть LineUnavailableException
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }

            //Загружаем наш звуковой поток в Clip
            //Может выкинуть IOException и LineUnavailableException
            try {
                clip.open(ais);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            clip.setFramePosition(0); //устанавливаем указатель на старт
            clip.start(); //Поехали!!!}

            bm.activate(BonusType.PlatformExtension);
            setWidth((int) (getWidth() +10));
        } else if (bm.isJustRemoved(BonusType.PlatformExtension ) || WIDTH>=350) {
            setWidth(WIDTH);
        }

        if (bm.isInactive(BonusType.PlatformShrink)) {
            bm.activate(BonusType.PlatformShrink);
            setWidth((int) (getWidth() / 1.5));
        } else if (bm.isJustRemoved(BonusType.PlatformShrink)) {
            setWidth(WIDTH);
        }

        glowPosition = 0;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        Game.setOpacity(g, 1f);
        g.drawImage(Art.racket[0][0], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public Rectangle getRectBB() {
        return new Rectangle(getX() - 2, getY(), getWidth() + 4, getHeight());
    }

    // todo
    public void setGlowPosition(int p) {
        System.out.println(getWidth());
        //Получаем AudioInputStream
        //Вот тут могут полететь IOException и UnsupportedAudioFileException
        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Получаем реализацию интерфейса Clip
        //Может выкинуть LineUnavailableException
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        //Загружаем наш звуковой поток в Clip
        //Может выкинуть IOException и LineUnavailableException
        try {
            clip.open(ais);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        clip.setFramePosition(0); //устанавливаем указатель на старт
        clip.start(); //Поехали!!!}
        System.out.println(p);
        glowPosition = p;
    }
}