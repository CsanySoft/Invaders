package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;
import java.util.Random;

import hu.csanysoft.invaders.Actors.Background;
import hu.csanysoft.invaders.Actors.Ghost;
import hu.csanysoft.invaders.Actors.Laser;
import hu.csanysoft.invaders.Actors.Ship;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class GameStage extends MyStage {


    public ArrayList<Laser> lasers = new ArrayList<Laser>();
    ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    public Ship ship;
    float timer = 0;
    float szorzo = 0.03f;
    float szamolo = 0;
    float speed = 2;
    final float shoottimer = .5f;
    float lastshot = 0;
    Background backgroundActor;
    Random rand = new Random();

    public boolean isShooting = false;



    public GameStage(Invaders game) {
        super(new ExtendViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);

        backgroundActor = new Background();
        addActor(backgroundActor);

        ship = new Ship();
        addActor(ship);
        ship.setPosition(getWidth()/2 - ship.getWidth() / 2, ship.getHeight() * 1.5f);


    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        timer += delta;
        lastshot += delta;
        setCameraMoveToY(Globals.WORLD_HEIGHT / 2 + ship.getY() - ship.getHeight() * 1.5f);
        setCameraMoveToX(Globals.WORLD_WIDTH / 2);
        getViewport().setScreenPosition(getViewport().getScreenX(),getViewport().getScreenY()+1);

        szamolo = elapsedTime * szorzo;
        if(szamolo > 4) szamolo = 4f;
        if(timer > 5 - szamolo) {
            timer = 0;
            Ghost ghost = new Ghost(new Random().nextInt(Globals.WORLD_WIDTH - 129) + new Random().nextFloat(),getCameraMoveToY() + Globals.WORLD_HEIGHT);
            ghost.getSprite().setColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
            ghosts.add(ghost);
            addActor(ghost);
        }

        if(speed < 5)
            speed+=delta/10;

        backgroundActor.setSpeed(speed);
        ship.setSpeed(speed);
        for (Ghost ghost : ghosts) {
            if(ghost != null) {
                if(ghost.getY() + ghost.getHeight() * 4 < getCameraMoveToY()) {
                    getActors().removeValue(ghost, true);
                    ghost.remove();
                    ghost = null;
                }
                for (Laser laser:lasers) {
                    if(laser!=null && ghost!=null) {
                        if(laser.overlaps(ghost) && laser.isFel()) {

                            getActors().removeValue(ghost, true);
                            ghost.remove();
                            ghost = null;
                        } else if(!laser.isFel() && laser.overlaps(ship)) ship.setVisible(false);
                    }
                }
            }

        }

        for (Laser laser : lasers) {
            if(laser != null) {
                if(laser.getY() + laser.getHeight() * 4 < getCameraMoveToY()) {
                    getActors().removeValue(laser, true);
                    laser.remove();
                    laser = null;
                }
            }
        }

        if(lastshot > shoottimer && isShooting){
            Laser laser = new Laser(ship.getX() + ship.getWidth() / 2 - 15, ship.getY() + ship.getHeight(), true);
            addActor(laser);
            lasers.add(laser);
            lastshot = 0;
        }


    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
