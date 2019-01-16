package hu.csanysoft.invaders.Actors;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Background extends OneSpriteStaticActor {

    float speed = 2;

    public Background() {
        super(Assets.manager.get(Assets.SPACE_TEXTURE));
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0, speed);
        setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);
    }
}
