package hu.csanysoft.invaders.Actors;

import hu.csanysoft.invaders.Game.GameStage;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OffsetSprite;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Boss extends Enemy {

    int weapon = 2;
    float switchTimer = 0, shoot = 0.75f, health = 100;
    GameStage gameStage;

    public Boss(float x, float y) {
        super(x, y);
        addSprite(new OffsetSprite(Assets.manager.get(Assets.GHOST_ALAP_TEXTURE), 0, 0),"alap");
        addSprite(new OffsetSprite(Assets.manager.get(Assets.GHOST_SZEM_TEXTURE), 30, 130), "szem");
        setSize(384,384);
        setX(Globals.WORLD_WIDTH / 2 - getWidth() / 2);
        getSprite("szem").setColor(1, 0, 0, 1);
        isBoss=true;
    }

    public void switchWeapon() {
        if(weapon == 1) {
            weapon = 2;
            shoot = 0.75f;
        }
        else {
            weapon = 1;
            shoot = 3;
        }
    }

    public void getShot() {health-=12-gameStage.weapon*3;}

    public float getHealth() {
        return health;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveTime += delta*speed;
        switchTimer += delta;
        gameStage = (GameStage) getStage();
        if(isVisible() && elapsedTime > shoot) {
            if(weapon == 1)
            for (int i = 0; i <=40 ; i+=10) {
                Laser laser = new Laser(getSprite("alap").getX() + getWidth() / 2 - 30, getY()-120, -i);
                laser.fel = false;
                laser.setRotation(-i);
                getStage().addActor(laser);
                laser = new Laser(getX()+getWidth()/2, getY()-getHeight(), i);
                laser.fel = false;
                laser.setRotation(i);
                getStage().addActor(laser);
            }
            else if (weapon == 2) {
                Laser laser = new Laser(getSprite("alap").getX() + getWidth() / 2 - 30, getY()-240, 180);
                laser.setSize(laser.getWidth()*2, laser.getHeight()*2);
                getStage().addActor(laser);
            }


            elapsedTime=0;
            if(switchTimer > 10 / gameStage.weapon) {
                switchTimer = 0;
                switchWeapon();
            }
        }


        if(getY() < gameStage.getCameraMoveToY() + Globals.WORLD_HEIGHT / 2 - getHeight())
            setY(gameStage.getCameraMoveToY() + Globals.WORLD_HEIGHT / 2 - getHeight());


        getSprite("alap").setX((float)(x + Math.sin(moveTime*5) * 90));
        getCollisionShape("BaseRectangle").setX(getSprite("alap").getX());
        getSprite("szem").setX((float)(x+38.4 + Math.sin((moveTime-.05f)*5) * 90));


    }
}
