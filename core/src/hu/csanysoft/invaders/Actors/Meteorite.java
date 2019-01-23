package hu.csanysoft.invaders.Actors;

import hu.csanysoft.invaders.Game.GameStage;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Meteorite extends OneSpriteStaticActor {

    float x,y;
    float speed;
    float timer = 2;
    float moveTime = 0;

    public Meteorite(float x, float y) {
        super(Assets.manager.get(Assets.METEORITE_TEXTURE));
        setSize(128,128);
        addBaseCollisionRectangleShape();
        setPosition(x,y);
        this.x = x; this.y = y;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        timer -= delta;
        moveTime += delta * speed;
        if (isVisible() && elapsedTime > Math.random() * (5 - 3 + 1) + 3) {
            Laser laser = new Laser(getX() + getWidth() / 2, getY() - getHeight(), 180);
            getStage().addActor(laser);
            GameStage gameStage = (GameStage) getStage();
            gameStage.lasers.add(laser);
            elapsedTime = 0;
        }
    }
}
