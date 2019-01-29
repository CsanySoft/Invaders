package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Popup extends OneSpriteStaticActor {
    public Popup(Texture texture) {
        super(texture);
        setElapsedTime(1);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setSize(getWidth()+elapsedTime, getHeight()+elapsedTime);
        if(elapsedTime > 2)
            remove();
    }
}
