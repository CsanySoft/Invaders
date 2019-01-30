package hu.csanysoft.invaders.Actors;


import java.util.Random;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import static hu.csanysoft.invaders.Global.Globals.random;

public class Decoration extends OneSpriteStaticActor {


    float rotation = new Random().nextFloat()*2-1;
    int size = random(50, 300);

    public Decoration(int x, int y) {
        super(Assets.manager.get(Assets.getRandomDecoration()));
        setSize(size, size);
        setPosition(x,y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rotateBy(rotation);
        if(elapsedTime > 20) getStage().getActors().removeValue(this, true);
    }

    @Override
    public void init() {
        super.init();
    }
}
