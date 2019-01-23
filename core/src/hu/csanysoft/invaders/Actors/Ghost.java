package hu.csanysoft.invaders.Actors;

import hu.csanysoft.invaders.Game.GameStage;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OffsetSprite;

public class Ghost extends Enemy {

    public Ghost(float x, float y) {
        super(x, y);
        addSprite(new OffsetSprite(Assets.manager.get(Assets.GHOST_ALAP_TEXTURE), 0, 0),"alap");
        addSprite(new OffsetSprite(Assets.manager.get(Assets.GHOST_SZEM_TEXTURE), 30, 130), "szem");
        setSize(128,128);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        timer-=delta;
        moveTime += delta*speed;
        if(isVisible() && elapsedTime > Math.random()*(5-3+1)+3) {
            Laser laser = new Laser(getX()+getWidth()/2, getY()-getHeight(), 180);
            getStage().addActor(laser);
            GameStage gameStage = (GameStage) getStage();
            gameStage.lasers.add(laser);
            elapsedTime=0;
        }

        getSprite("alap").setX((float)(x + Math.sin(moveTime*5) * 30));
        getSprite("szem").setX((float)(x+12.8 + Math.sin((moveTime-.05f)*5) * 30));
    }
}
