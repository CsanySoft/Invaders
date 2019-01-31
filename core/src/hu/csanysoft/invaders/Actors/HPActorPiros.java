package hu.csanysoft.invaders.Actors;


import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by Majzer on 30/11/2017.
 */

public class HPActorPiros extends OneSpriteStaticActor {

    float alapHossz;
    Boss boss;

    public HPActorPiros(Boss boss) {
        super(Assets.manager.get(Assets.HP_PIROS_TEXTURE));
        setSize(Globals.WORLD_WIDTH / 2,10f);
        setPosition(Globals.WORLD_WIDTH/2 - getWidth() / 2, boss.getY() + boss.getHeight() - 40);
        this.boss = boss;
        alapHossz = getWidth();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(Globals.WORLD_WIDTH/2 - getWidth() / 2, boss.getY() + boss.getHeight() - 40);
        setSize(alapHossz*(boss.getHealth()/100), 10);
        if(getWidth() < 0) setWidth(0);
    }
}
