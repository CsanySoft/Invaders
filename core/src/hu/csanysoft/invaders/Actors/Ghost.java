package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import hu.csanysoft.invaders.Game.GameStage;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Ghost extends OneSpriteStaticActor {

    float x, y;
    boolean balra;
    Random rand;
    float speed;

    public Ghost(float x, float y) {
        super(Assets.manager.get(Assets.GHOST_TEXTURE));
        setSize(128,128);
        addBaseCollisionRectangleShape();
        setPosition(x,y);
        this.x = x; this.y = y;
        rand = new Random();
        balra = rand.nextBoolean();
        speed = rand.nextInt(2)+1;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime+=delta;
        if(isVisible() && elapsedTime > Math.random()*(5-3+1)+3) {
            Laser laser = new Laser(getX()+getWidth()/2, getY()-getHeight(), false);
            getStage().addActor(laser);
            GameStage gameStage = (GameStage) getStage();
            gameStage.lasers.add(laser);
            elapsedTime=0;
        }

        if(balra) {
            moveBy(0-speed, 0);
            if(x-getX() > 30) balra = false;
        } else {
            moveBy(speed, 0);
            if(getX() - x > 30) balra = true;
        }
    }
}
