package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Ghost extends OneSpriteStaticActor {
    public Ghost() {
        super(Assets.manager.get(Assets.GHOST_TEXTURE));
        setSize(128,128);
        addBaseCollisionRectangleShape();
    }
}
