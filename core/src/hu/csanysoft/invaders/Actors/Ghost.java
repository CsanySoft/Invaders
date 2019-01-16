package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import hu.csanysoft.invaders.Game.GameStage;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OffsetSprite;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Ghost extends MultiSpriteActor {

    float x, y;
    boolean balra, szemBalra;
    Random rand;
    float speed;
    float timer = 2;
    float moveTime = 0;

    public Ghost(float x, float y) {
        super(300,300);
        addSprite(new OffsetSprite(Assets.manager.get(Assets.GHOST_ALAP_TEXTURE), 0, 0),"alap");
        addSprite(new OffsetSprite(Assets.manager.get(Assets.GHOST_SZEM_TEXTURE), 30, 130), "szem");
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
        timer-=delta;
        moveTime += delta*speed;
        if(isVisible() && elapsedTime > Math.random()*(5-3+1)+3) {
            Laser laser = new Laser(getX()+getWidth()/2, getY()-getHeight(), false);
            getStage().addActor(laser);
            GameStage gameStage = (GameStage) getStage();
            gameStage.lasers.add(laser);
            elapsedTime=0;
        }


        getSprite("alap").setX((float)(x + Math.sin(moveTime*5) * 30));
        getSprite("szem").setX((float)(x+12.8 + Math.sin((moveTime-.05f)*5) * 30));

        /*if(balra) {
            getSprite("alap").setX(getSprite("alap").getX()-speed);
            //moveBy(0-speed, 0);
            if(x-getSprite("alap").getX() > 30) {
                balra = false;
                timer = 0;
            }
        } else {
            getSprite("alap").setX(getSprite("alap").getX()+speed);
            //moveBy(speed, 0);
            if(getSprite("alap").getX() - x > 30) {
                balra = true;
                timer = 0;
            }
        }

        if(szemBalra) {
            getSprite("szem").setX(getSprite("szem").getX()-speed);
        } else {
            getSprite("szem").setX(getSprite("szem").getX()+speed);
        }*/
    }
}
