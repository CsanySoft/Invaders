package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetDescriptor;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.AnimatedOffsetSprite;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OffsetSprite;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Ship extends MultiSpriteActor {

    public float speed = 2;
    float multiplier = 1;
    OffsetSprite sprite;
    AnimatedOffsetSprite flameSprite;

    public Ship() {
        super(100, 100);
        addSprite(sprite = new OffsetSprite(Assets.manager.get(Assets.SHIP_TEXTURE), 0, 0, 100, 100));
        addSprite(flameSprite = new AnimatedOffsetSprite("rocket_anim.txt", 25, -50, 50, 50));
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
        if(getX()>=0){
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                moveBy(-5,0);
            }
            if(Gdx.input.getAccelerometerX() > 0.5f) moveBy((int)(-2 * Math.rint(Gdx.input.getAccelerometerX())), 0);

        }
        if(getX() < Globals.WORLD_WIDTH - getWidth()) {
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveBy(5,0);
            if(Gdx.input.getAccelerometerX() < 0.5f) moveBy((int)(-2 * Math.rint(Gdx.input.getAccelerometerX())), 0);
        }

    }
}
