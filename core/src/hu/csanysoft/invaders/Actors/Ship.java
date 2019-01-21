package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Ship extends OneSpriteStaticActor {

    public float speed = 2;
    float multiplier = 1;

    public Ship() {
        super(Assets.manager.get(Assets.SHIP2_TEXTURE));
        setSize(getWidth() * 0.2f, getHeight() * 0.4f);
        addBaseCollisionRectangleShape();
        
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0,speed * multiplier);
        if(getX()>0){
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                moveBy(-5,0);
            }
            if(Gdx.input.getAccelerometerX() < 0.5f) moveBy((int)(-2 * Math.rint(Gdx.input.getAccelerometerX())), 0);

        }
        if(getX() < Globals.WORLD_WIDTH - getWidth()) {
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveBy(5,0);
            if(Gdx.input.getAccelerometerX() > 0.5f) moveBy((int)(-2 * Math.rint(Gdx.input.getAccelerometerX())), 0);
        }

    }
}
