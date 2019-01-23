package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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


    final float shoottimer = .5f;
    public ArrayList<Laser> lasers = new ArrayList<Laser>();
    public Ship ship;
    public int points = 0;
    public boolean isShooting = false;
    public boolean isAlive = true;
    public boolean flyout = false;
    ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    float timer = 0;
    float szorzo = 0.03f;
    float szamolo = 0;
    float speed = 2;
    float lastshot = 0;
    float flytimer = 0;
    Background backgroundActors[];
    Background foregroundActors[];
    OneSpriteStaticActor gameover;
    Random rand = new Random();
    Image white;
    float whiteTimer = 0;
    short weapon = 1;


    public GameStage(Invaders game, Texture texture) {
        super(new ExtendViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        backgroundActors = new Background[3];
        foregroundActors = new Background[3];
        for (int i = 0; i < 3; i++) {
            Background x =  new Background();
            backgroundActors[i] = x;
            addActor(x);
            x.setPosition(0, Globals.WORLD_HEIGHT * i);
            x.setSpeed(speed / 2);
            x.setSize(x.getWidth()*(rand.nextFloat()/4+1),x.getHeight());
        }
        for (int i = 0; i < 3; i++) {
            Background x =  new Background();
            foregroundActors[i] = x;
            addActor(x);
            x.setPosition(0, Globals.WORLD_HEIGHT * i);
            x.setSpeed(speed / 4);
            x.setSize(x.getWidth()*(rand.nextFloat()/4+1),x.getHeight());
        }


        ship = new Ship(texture);
        addActor(ship);
        ship.setPosition(getWidth() / 2 - ship.getWidth() / 2, ship.getHeight() * .5f);
        white = new Image(Assets.manager.get(Assets.WHITE_TEXTURE));
        white.setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);
        addActor(white);
        white.setColor(255, 255, 255, 0);
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        timer += delta;
        lastshot += delta;
        if (!flyout) {
            if (ship.getY() > 500) {
                setCameraMoveToY(Globals.WORLD_HEIGHT / 2 + ship.getY() - ship.getHeight() * .5f);
                setCameraMoveToX(Globals.WORLD_WIDTH / 2);
                getViewport().setScreenPosition(getViewport().getScreenX(), getViewport().getScreenY() + 1);
            } else {
                setCameraMoveToY(Globals.WORLD_HEIGHT / 2 + 500 - ship.getHeight() * .5f);
                setCameraMoveToX(Globals.WORLD_WIDTH / 2);
                getViewport().setScreenPosition(getViewport().getScreenX(), getViewport().getScreenY() + 1);
            }
        } else {
            flytimer += delta;
            ship.setMultiplier(flytimer * 3);
        }

        szamolo = elapsedTime * szorzo;
        if (szamolo > 4.6f) szamolo = 4.6f;
        if (timer > 5 - szamolo && !flyout) {
            timer = 0;
            Ghost ghost = new Ghost(new Random().nextInt(Globals.WORLD_WIDTH - 129) + new Random().nextFloat(), getCameraMoveToY() + Globals.WORLD_HEIGHT);
            ghost.getSprite("alap").setColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
            ghost.getSprite("szem").setColor(1, 1 - szamolo / 4, 1 - szamolo / 4, 1);
            ghosts.add(ghost);
            addActor(ghost);
        }

        if (speed < 5) {
            speed += delta / 10;
            for (Background bg : backgroundActors) bg.setSpeed(speed / 2);
            for (Background bg : foregroundActors) bg.setSpeed(speed / 4);
        }

        ship.setSpeed(speed);
        for (Ghost ghost : ghosts) {
            if (ghost != null && ghost.isVisible()) {
                if (ghost.overlaps(ship) && isAlive) {
                    gameover();
                }
                if (ghost.getY() + ghost.getHeight() < getCameraMoveToY() - Globals.WORLD_HEIGHT / 2) {
                    getActors().removeValue(ghost, true);
                    ghost.remove();
                    ghost = null;
                }
                for (Laser laser : lasers) {
                    if (laser != null && ghost != null) {

                        if (flyout) {
                            if (whiteTimer > 1) whiteTimer = 1;
                            ghost.getSprite("alap").setColor(ghost.getSprite("alap").getColor().r, ghost.getSprite("alap").getColor().g, ghost.getSprite("alap").getColor().b, 1 - whiteTimer);
                            ghost.getSprite("szem").setColor(ghost.getSprite("szem").getColor().r, ghost.getSprite("szem").getColor().g, ghost.getSprite("szem").getColor().b, 1 - whiteTimer);
                            laser.getSprite().setColor(laser.getSprite().getColor().r, laser.getSprite().getColor().g, laser.getSprite().getColor().b, 1 - whiteTimer);
                        }

                        if (laser.overlaps(ghost) && laser.isFel() && laser.isVisible() && ghost.isVisible()) {
                            getActors().removeValue(ghost, true);
                            getActors().removeValue(laser, true);
                            ghost.remove();
                            laser.remove();
                            ghost.setVisible(false);
                            laser.setVisible(false);
                            ghost = null;
                            laser = null;
                            points += 10;
                        } else if (!laser.isFel() && laser.overlaps(ship) && isAlive) {
                            gameover();
                        }
                    }
                }
            }
        }
        for (Laser laser : lasers) {
            if (laser != null) {
                if (laser.isFel() && laser.getY() > getCameraMoveToY() + Globals.WORLD_HEIGHT / 2) {
                    getActors().removeValue(laser, true);
                    laser.remove();
                } else if (!laser.isFel() && laser.getY() < getCameraMoveToY() - Globals.WORLD_HEIGHT + laser.getHeight()) {
                    getActors().removeValue(laser, true);
                    laser.remove();
                }
            }
        }

        if (lastshot > shoottimer && isShooting && isAlive) {
            Laser laser = new Laser(ship.getX() + ship.getWidth() / 2 - 15, ship.getY() + ship.getHeight(), true);
            Laser laserb = new Laser(ship.getX() + ship.getWidth() / 2 - 15, ship.getY() + ship.getHeight(), true, true, false);
            Laser laserj = new Laser(ship.getX() + ship.getWidth() / 2 - 15, ship.getY() + ship.getHeight(), true, false, true);
            switch (weapon) {
                case 1:
                    addActor(laser);
                    lasers.add(laser);
                    lasers.add(laserb);
                    lasers.add(laserj);
                    break;
                case 2:
                    addActor(laserb);
                    addActor(laserj);
                    lasers.add(laserb);
                    lasers.add(laserj);
                    break;
                case 3:
                    addActor(laser);
                    addActor(laserb);
                    addActor(laserj);
                    lasers.add(laser);
                    lasers.add(laserb);
                    lasers.add(laserj);
                    break;
            }

            lastshot = 0;
        }

        ControlStage.setPoints(points);

        moveBackgrounds();


        if (flyout) {
            white.setColor(255, 255, 255, whiteTimer += 0.008f);
        }
        if (gameover != null) {
            gameover.setPosition(
                    getCameraMoveToX() - gameover.getWidth() / 2,
                    getCameraMoveToY() - gameover.getHeight() / 2
            );
        }
    }

    void moveBackgrounds() {
        if (backgroundActors[0].getY() < getCameraMoveToY() - Globals.WORLD_HEIGHT * 1.5)
            backgroundActors[0].setY(backgroundActors[2].getY() + Globals.WORLD_HEIGHT);
        if (backgroundActors[1].getY() < getCameraMoveToY() - Globals.WORLD_HEIGHT * 1.5)
            backgroundActors[1].setY(backgroundActors[0].getY() + Globals.WORLD_HEIGHT);
        if (backgroundActors[2].getY() < getCameraMoveToY() - Globals.WORLD_HEIGHT * 1.5)
            backgroundActors[2].setY(backgroundActors[1].getY() + Globals.WORLD_HEIGHT);
        if (foregroundActors[0].getY() < getCameraMoveToY() - Globals.WORLD_HEIGHT * 1.5)
            foregroundActors[0].setY(foregroundActors[2].getY() + Globals.WORLD_HEIGHT);
        if (foregroundActors[1].getY() < getCameraMoveToY() - Globals.WORLD_HEIGHT * 1.5)
            foregroundActors[1].setY(foregroundActors[0].getY() + Globals.WORLD_HEIGHT);
        if (foregroundActors[2].getY() < getCameraMoveToY() - Globals.WORLD_HEIGHT * 1.5)
            foregroundActors[2].setY(foregroundActors[1].getY() + Globals.WORLD_HEIGHT);
    }


    void nextStage() {
        flyout = true;
        for (Background bg : backgroundActors) bg.setMoving(false);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.X) {
            flyout = true;
            white.setPosition(getCameraMoveToX() - Globals.WORLD_WIDTH / 2, getCameraMoveToY() - Globals.WORLD_HEIGHT / 2);
        } else if (keyCode == Input.Keys.U) {
            if (weapon < 4 && points >= -5000) {
                weapon++;
                points -= 100;
            }
        }
        return super.keyDown(keyCode);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void gameover() {
        ship.setVisible(false);
        isAlive = false;
        gameover = new OneSpriteStaticActor(Assets.manager.get(Assets.GAMEOVER_TEXTURE));
        gameover.setSize(gameover.getWidth() / 3.6f, gameover.getHeight() / 3.6f);
        addActor(gameover);
    }
}
