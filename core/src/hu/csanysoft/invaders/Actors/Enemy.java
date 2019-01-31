package hu.csanysoft.invaders.Actors;


import java.util.Random;

import hu.csanysoft.invaders.Game.GameStage;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OffsetSprite;

public class Enemy extends MultiSpriteActor {

    float x, y;
    Random rand;
    float speed;
    float timer = 2;
    float moveTime = 0;
    boolean isBoss = false;

    public Enemy(float x, float y) {
        super(300,300);
        addBaseCollisionRectangleShape();
        setPosition(x,y);
        this.x = x; this.y = y;
        rand = new Random();
        speed = rand.nextInt(2)+1;
        setZIndex(500);
    }

    @Override
    public void act(float delta) {
        super.act(delta);


    }


    public boolean isBoss() {
        return isBoss;
    }

    public void setBoss(boolean boss) {
        isBoss = boss;
    }
}
