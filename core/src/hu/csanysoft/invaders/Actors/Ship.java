package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Ship extends OneSpriteStaticActor {
    public Ship() {
        super(Assets.manager.get(Assets.SHIP_TEXTURE));
        setSize(getWidth() * 0.2f, getHeight() * 0.2f);
        addBaseCollisionRectangleShape();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0,2);
        if(getX()>0) if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) moveBy(-1,0);
        if(getX() < Globals.WORLD_WIDTH - getWidth()) if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveBy(1,0);
    }
}
