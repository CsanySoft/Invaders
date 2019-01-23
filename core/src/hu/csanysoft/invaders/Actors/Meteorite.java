package hu.csanysoft.invaders.Actors;

import hu.csanysoft.invaders.Game.GameStage;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OffsetSprite;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Meteorite extends Enemy {


    public Meteorite(float x, float y) {
        super(x, y);
        addSprite(new OffsetSprite(Assets.manager.get(Assets.METEORITE_TEXTURE), 0, 0),"alap");
        setSize(128,128);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }
}
