package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Laser extends OneSpriteStaticActor {

    public Laser(float x, float y) {
        super(Assets.manager.get(Assets.LASER_TEXTURE));
        setPosition(x, y);
        addBaseCollisionRectangleShape();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0, 2);
    }
}
