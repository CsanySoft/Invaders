package hu.csanysoft.invaders.Actors;


import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Laser extends OneSpriteStaticActor {

    boolean fel, bal=false, jobb=false;
    float angle;


    public Laser(float x, float y, float angle) {
        super(Assets.manager.get(Assets.LASER_TEXTURE));
        setPosition(x, y);
        this.fel = angle != 180;
        addBaseCollisionRectangleShape();
        this.bal = angle < 0  && angle != 180;
        this.jobb = angle > 0 && angle != 180;
        setRotation(180-angle);
        setSize(getWidth()/3, getHeight());
        this.angle = angle;
    }

    public boolean isFel() {
        return fel;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(fel)moveBy(0, 20);
        else moveBy(0,-2);
        if(jobb) moveBy(angle/2,0);
        if(bal) moveBy(angle/2,0);
    }
}
