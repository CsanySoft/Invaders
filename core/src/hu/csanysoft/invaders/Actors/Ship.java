package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.AnimatedOffsetSprite;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyCircle;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OffsetSprite;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Ship extends MultiSpriteActor {

    public float speed = 2;
    float multiplier = 1;
    OffsetSprite sprite;
    AnimatedOffsetSprite flameSprite, flameSprite2;
    Texture texture;

    public Ship(Texture texture) {
        super(100, 100);
        if(texture == Assets.manager.get(Assets.SHIP2_TEXTURE)) {
            addSprite(sprite = new OffsetSprite(texture, 0, 0, 100, 100));
            addSprite(flameSprite = new AnimatedOffsetSprite(Assets.manager.get(Assets.ROCKET_ATLAS), 25, -50, 50, 50));
        } else {
            addSprite(sprite = new OffsetSprite(texture, 0, 0, 100, 100));
            addSprite(flameSprite = new AnimatedOffsetSprite(Assets.manager.get(Assets.ROCKET_ATLAS), -15.75f, -50, 50, 50));
            addSprite(flameSprite2 = new AnimatedOffsetSprite(Assets.manager.get(Assets.ROCKET_ATLAS), sprite.getWidth()-35.75f, -50, 50, 50));
        }

        //addBaseCollisionRectangleShape();
        //addCollisionShape("asd", new MyRectangle(70, 70, 15,15,));
//        addCollisionShape("asd", new MyCircle(70, 15,15));
        //addCollisionShape("BaseRectangle",new MyRectangle(70,70,15,0,getOriginX(), getOriginY(), getRotation(), 45, true));
        addCollisionShape("BaseCircle", new MyCircle(35, 10, 0, getOriginX(), getOriginY(), getX(), getY(), true));
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
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
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
                moveBy(-5,0);
            if(Gdx.input.getAccelerometerX() > 0.5f)
                moveBy((int)(-2 * Math.rint(Gdx.input.getAccelerometerX())), 0);
        }
        if(getX() < Globals.WORLD_WIDTH - getWidth()) {
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveBy(5,0);
            if(Gdx.input.getAccelerometerX() < 0.5f) moveBy((int)(-2 * Math.rint(Gdx.input.getAccelerometerX())), 0);
        }
    }
}
