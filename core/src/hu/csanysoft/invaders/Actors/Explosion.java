package hu.csanysoft.invaders.Actors;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Explosion extends OneSpriteAnimatedActor {
    public Explosion() {
        super(Assets.manager.get(Assets.EXPLOSION_TEXTURE));
        setFps(20);
        //setSize(getWidth()*2.5f, getHeight()*2.5f);
    }

    @Override
    protected void repeated() {
        if (getStage() != null)
            getStage().getActors().removeValue(this, true);
        super.repeated();
    }
}
