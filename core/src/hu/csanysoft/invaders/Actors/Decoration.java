package hu.csanysoft.invaders.Actors;


import java.util.Random;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import static hu.csanysoft.invaders.Global.Globals.random;

public class Decoration extends OneSpriteStaticActor {


    int size = random(50, 300);
    float rotation = new Random().nextFloat();
    float opacity = size/300f;

    public Decoration(int x, int y) {
        super(Assets.manager.get(Assets.getRandomDecoration()));
        setSize(size, size);
        setPosition(x,y);
        setZIndex(1);
        getSprite().setAlpha(opacity);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rotateBy(rotation);
        moveBy(0,250/size);
        if(elapsedTime > 20) getStage().getActors().removeValue(this, true);
    }

    @Override
    public void init() {
        super.init();
    }
}
