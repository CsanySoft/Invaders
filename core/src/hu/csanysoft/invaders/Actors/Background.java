package hu.csanysoft.invaders.Actors;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Background extends OneSpriteStaticActor {

    float speed = 2;
    boolean moving = true;

    public Background() {
        super(Assets.manager.get(Assets.SPACE_TEXTURE));
        setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);
        setZIndex(100);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(moving)moveBy(0, speed);

    }
}
