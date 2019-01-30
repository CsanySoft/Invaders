package hu.csanysoft.invaders.Actors;


import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import static hu.csanysoft.invaders.Global.Globals.random;

public class Decoration extends OneSpriteStaticActor {


    int rotation = random(-1,1);

    public Decoration(int x, int y) {
        super(Assets.manager.get(Assets.getRandomDecoration()));
        setSize(200,200);
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
