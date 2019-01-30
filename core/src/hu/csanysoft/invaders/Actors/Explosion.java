package hu.csanysoft.invaders.Actors;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class Explosion extends OneSpriteAnimatedActor {
    public Explosion() {
        super(Assets.manager.get(Assets.EXPLOSION_TEXTURE));
        long f = Assets.manager.get(Assets.SOUND_BOOM).play();
        Assets.manager.get(Assets.SOUND_BOOM).setVolume(f, 0.2f);
        setFps(15);
        setSize(getWidth()*3.5f, getHeight()*3.5f);
    }

    @Override
    protected void repeated() {
        if (getStage() != null)
            getStage().getActors().removeValue(this, true);
        super.repeated();
    }
}
