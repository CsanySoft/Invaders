package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Laser extends OneSpriteStaticActor {

    boolean fel;

    public Laser(float x, float y, boolean fel) {
        super(Assets.manager.get(Assets.LASER_TEXTURE));
        setPosition(x, y);
        this.fel = fel;
        addBaseCollisionRectangleShape();
    }

    public boolean isFel() {
        return fel;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(fel)moveBy(0, 10);
        else moveBy(0,-2);

    }
}
