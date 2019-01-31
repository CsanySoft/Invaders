package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.AnimatedOffsetSprite;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyCircle;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OffsetSprite;

public class Ship extends MultiSpriteActor {

    public float speed = 2;
    float multiplier = 1;
    AnimatedOffsetSprite sprite;
    AnimatedOffsetSprite flameSprite, flameSprite2;
    Texture texture;

    public Ship(Texture texture) {
        super(100, 100);
        if(texture == Assets.manager.get(Assets.SHIP2_TEXTURE)) {
            addSprite(sprite = new AnimatedOffsetSprite(Assets.manager.get(Assets.SHIP2_ATLAS), 0,0, 100,100));
            addSprite(flameSprite = new AnimatedOffsetSprite(Assets.manager.get(Assets.ROCKET_ATLAS), 25, -50, 50, 50));
            flameSprite.setFps(20);
        } else {
            addSprite(sprite = new AnimatedOffsetSprite(Assets.manager.get(Assets.SHIP1_ATLAS), 0,0, 100,100));
            addSprite(flameSprite = new AnimatedOffsetSprite(Assets.manager.get(Assets.ROCKET_ATLAS), -15.75f, -50, 50, 50));
            addSprite(flameSprite2 = new AnimatedOffsetSprite(Assets.manager.get(Assets.ROCKET_ATLAS), sprite.getWidth()-35.75f, -50, 50, 50));
            flameSprite.setFps(20);
            flameSprite2.setFps(20);
        }

        addCollisionShape("BaseCircle", new MyCircle(35, 10, 0, getOriginX(), getOriginY(), getX(), getY(), true));
        this.texture = texture;

        sprite.stop();
        sprite.setFrame(0);
        setZIndex(Integer.MAX_VALUE);
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
        sprite.setFrame(0);
        moveBy(0,speed * multiplier);
        if(getX()>=0){
            if(Globals.ACCELEROMETER_AVAILABLE && Gdx.input.getAccelerometerX() > 0.5f) {
                int move = (int) (-2 * Math.rint(Gdx.input.getAccelerometerX()));
                moveBy(move, 0);
                if(move < -10) move = -10;
                if(move != 0)sprite.setFrame(-move / 2);
                else sprite.setFrame(0);
                System.out.println("MOVING BY "+move);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                moveBy(-5, 0);
                sprite.setFrame(5);
            }
        }
        if(getX() < Globals.WORLD_WIDTH - getWidth()) {
            if(Globals.ACCELEROMETER_AVAILABLE && Gdx.input.getAccelerometerX() < 0.5f){
                int move = (int)(-2 * Math.rint(Gdx.input.getAccelerometerX()));
                moveBy(move, 0);
                if(move > 10) move = 10;
                if(move != 0)sprite.setFrame(5+move/2);
                else sprite.setFrame(0);
                System.out.println("MOVING BY "+move);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                moveBy(5,0);
                sprite.setFrame(10);

            }
        }

    }
}
