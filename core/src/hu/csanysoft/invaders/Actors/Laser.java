package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Laser extends OneSpriteStaticActor {

    boolean fel, bal=false, jobb=false;

    public Laser(float x, float y, boolean fel) {
        super(Assets.manager.get(Assets.LASER_TEXTURE));
        setPosition(x, y);
        this.fel = fel;
        addBaseCollisionRectangleShape();
        setSize(getWidth()/3, getHeight());
    }

    public Laser(float x, float y, boolean fel, boolean bal, boolean jobb) {
        super(Assets.manager.get(Assets.LASER_TEXTURE));
        setPosition(x, y);
        this.fel = fel;
        addBaseCollisionRectangleShape();
        this.bal = bal;
        this.jobb = jobb;
        if(bal) setRotation(45);
        else if (jobb) setRotation(-45);
        setSize(getWidth()/3, getHeight());
    }

    public boolean isFel() {
        return fel;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(fel)moveBy(0, 20);
        else moveBy(0,-2);
        if(jobb) moveBy(20,0);
        if(bal) moveBy(-20,0);
    }
}
