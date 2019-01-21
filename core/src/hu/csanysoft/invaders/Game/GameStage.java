package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.Input;
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
    float flytimer = 0;
    Background backgroundActor;
    Random rand = new Random();

    public boolean isShooting = false;
    public boolean isAlive = true;
    public boolean flyout = false;



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
        if(!flyout){
            if(ship.getY() > 500) {
                setCameraMoveToY(Globals.WORLD_HEIGHT / 2 + ship.getY() - ship.getHeight() * 1.5f);
                setCameraMoveToX(Globals.WORLD_WIDTH / 2);
                getViewport().setScreenPosition(getViewport().getScreenX(), getViewport().getScreenY() + 1);
            }else{
                setCameraMoveToY(Globals.WORLD_HEIGHT / 2 + 500 - ship.getHeight() * 1.5f);
                setCameraMoveToX(Globals.WORLD_WIDTH / 2);
                getViewport().setScreenPosition(getViewport().getScreenX(), getViewport().getScreenY() + 1);
                backgroundActor.setPosition(getCameraMoveToX()-Globals.WORLD_WIDTH/2, getCameraMoveToY()-Globals.WORLD_HEIGHT/2);
            }
        }else{
            flytimer += delta;
            ship.setMultiplier(flytimer*3);
            backgroundActor.setPosition(getCameraMoveToX()-Globals.WORLD_WIDTH/2, getCameraMoveToY()-Globals.WORLD_HEIGHT/2);
        }

        szamolo = elapsedTime * szorzo;
        if(szamolo > 4) szamolo = 4f;
        if(timer > 5 - szamolo && !flyout) {
            timer = 0;
            Ghost ghost = new Ghost(new Random().nextInt(Globals.WORLD_WIDTH - 129) + new Random().nextFloat(),getCameraMoveToY() + Globals.WORLD_HEIGHT);
            ghost.getSprite("alap").setColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
            ghost.getSprite("szem").setColor(1, 1 - szamolo/4, 1 - szamolo/4, 1);
            ghosts.add(ghost);
            addActor(ghost);
        }

        if(speed < 5)
            speed+=delta/10;

        backgroundActor.setSpeed(speed);
        ship.setSpeed(speed);
        for (Ghost ghost : ghosts) {
            if(ghost != null) {
                if(ghost.overlaps(ship) && isAlive) {
                    ship.setVisible(false);
                    isAlive = false;
                }
                if(ghost.getY() + ghost.getHeight() < getCameraMoveToY() - Globals.WORLD_HEIGHT/2) {
                    getActors().removeValue(ghost, true);
                    ghost.remove();
                    ghost = null;
                }
                for (Laser laser:lasers) {
                    if(laser!=null && ghost!=null) {
                        if(laser.overlaps(ghost) && laser.isFel() && laser.isVisible() && ghost.isVisible()) {
                            getActors().removeValue(ghost, true);
                            getActors().removeValue(laser, true);
                            ghost.remove();
                            laser.remove();
                            ghost.setVisible(false);
                            laser.setVisible(false);
                            laser = null;
                            ghost = null;
                        } else if(!laser.isFel() && laser.overlaps(ship) && isAlive) {
                            ship.setVisible(false);
                            isAlive = false;
                        }
                    }
                }
            }

        }
        for (Laser laser : lasers) {
            if(laser != null) {
                if(laser.isFel() && laser.getY() > getCameraMoveToY() + Globals.WORLD_HEIGHT/2) {
                    getActors().removeValue(laser, true);
                    laser.remove();
                }
                else if(!laser.isFel() && laser.getY() < getCameraMoveToY() - Globals.WORLD_HEIGHT + laser.getHeight()){
                    getActors().removeValue(laser, true);
                    laser.remove();
                }
            }
        }

        if(lastshot > shoottimer && isShooting && isAlive){
            Laser laser = new Laser(ship.getX() + ship.getWidth() / 2 - 15, ship.getY() + ship.getHeight(), true);
            Laser laserb = new Laser(ship.getX() + ship.getWidth() / 2 - 15, ship.getY() + ship.getHeight(), true,true, false);
            Laser laserj = new Laser(ship.getX() + ship.getWidth() / 2 - 15, ship.getY() + ship.getHeight(), true, false, true);
            addActor(laser);
            addActor(laserb);
            addActor(laserj);
            lasers.add(laser);
            lasers.add(laserb);
            lasers.add(laserj);
            lastshot = 0;
        }


    }

    void nextStage(){
        flyout = true;
        backgroundActor.setMoving(false);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if(keyCode == Input.Keys.X){
            flyout = true;

        }
        return super.keyDown(keyCode);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
