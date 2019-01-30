package hu.csanysoft.invaders.Actors;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

import static hu.csanysoft.invaders.Global.Globals.random;

public class Explosion extends OneSpriteAnimatedActor {


    public Explosion() {
        super(Assets.manager.get(Assets.EXPLOSION_TEXTURE));
        long f = Assets.manager.get(Assets.SOUND_BOOM).play();
        Assets.manager.get(Assets.SOUND_BOOM).setVolume(f, 0.2f);
        setFps(45);
        setOrigintoCenter();
        setRotation(random(0,360));
    }

    @Override
    protected void repeated() {
        if (getStage() != null)
            getStage().getActors().removeValue(this, true);
        super.repeated();
    }
}
