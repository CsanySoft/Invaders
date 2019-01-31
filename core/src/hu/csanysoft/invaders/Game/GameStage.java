package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Random;

import hu.csanysoft.invaders.Actors.Ship;
import hu.csanysoft.invaders.Actors.Background;
import hu.csanysoft.invaders.Actors.Boss;
import hu.csanysoft.invaders.Actors.Decoration;
import hu.csanysoft.invaders.Actors.Enemy;
import hu.csanysoft.invaders.Actors.Explosion;
import hu.csanysoft.invaders.Actors.Ghost;
import hu.csanysoft.invaders.Actors.HPActorPiros;
import hu.csanysoft.invaders.Actors.Laser;
import hu.csanysoft.invaders.Actors.Meteorite;
import hu.csanysoft.invaders.Actors.Popup;
import hu.csanysoft.invaders.Actors.SubMeteorite;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyActor;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;

import static hu.csanysoft.invaders.Global.Globals.random;

public class GameStage extends MyStage {


    final float shoottimer = .5f;
    public Ship ship;
    public int points = 0;
    public boolean isShooting = false;
    public boolean isAlive = true;
    public boolean flyout = false;
    public boolean boss = false;
    boolean vanBoss = false;
    float timer = 0;
    float szorzo = 0.03f;
    float szamolo = 0;
    float speed = 2;
    float lastshot = 0;
    float flytimer = 0;
    float decorationLast = getElapsedTime();
    Background backgroundActors[];
    Background foregroundActors[];
    Random rand = new Random();
    Image white;
    float whiteTimer = 0;
    public short weapon = 1;
    boolean shipAt500 = false;
    public int killsSinceLastShot = 0;



    public GameStage(Invaders game, int shipVariety, short weapon) {
        super(new StretchViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        backgroundActors = new Background[3];
        foregroundActors = new Background[3];
        for (int i = 0; i < 3; i++) {
            Background x =  new Background();
            backgroundActors[i] = x;
            addActor(x,1);
            x.setSize(x.getWidth()*(rand.nextFloat()/2+1.01f), x.getHeight());
            x.setPosition(-rand.nextInt((int)x.getWidth() - Globals.WORLD_WIDTH), Globals.WORLD_HEIGHT * i);
            x.setSpeed(0);
        }
        for (int i = 0; i < 3; i++) {
            Background x =  new Background();
            foregroundActors[i] = x;
            addActor(x,1);
            x.setSize(x.getWidth()*(rand.nextFloat()/2+1.01f),x.getHeight());
            x.setPosition(-rand.nextInt((int)x.getWidth() - Globals.WORLD_WIDTH), Globals.WORLD_HEIGHT * i);
            x.setSpeed(0);
            x.getSprite().flip(false, true);
        }


        this.weapon = weapon;
        ship = new Ship(shipVariety);
        addActor(ship, Integer.MAX_VALUE);
        ship.setPosition(getWidth() / 2 - ship.getWidth() / 2, ship.getHeight() * .5f);
        white = new Image(Assets.manager.get(Assets.WHITE_TEXTURE));
        white.setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);
        //addActor(white);
        white.setColor(255, 255, 255, 0);
        ControlStage.setLevelIndicator(weapon);
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
                if(!shipAt500){
                    shipAt500 = true;
                }
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
        if (timer > 5 - szamolo && !flyout && isAlive && !boss) {
            timer = 0;
            if(rand.nextInt(10) > 3) {
                Ghost enemy = new Ghost(new Random().nextInt(Globals.WORLD_WIDTH - 129) + new Random().nextFloat(), getCameraMoveToY() + Globals.WORLD_HEIGHT);
                enemy.getSprite("alap").setColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
                enemy.getSprite("szem").setColor(1, 1 - szamolo / 4, 1 - szamolo / 4, 1);
                addActor(enemy);
            }else{
                Meteorite met = new Meteorite(new Random().nextInt(Globals.WORLD_WIDTH - 129) + new Random().nextFloat(), getCameraMoveToY() + Globals.WORLD_HEIGHT);
                addActor(met);
            }
        }

        if (speed < 5) {
            speed += delta / 10;
            for (Background bg : backgroundActors) bg.setSpeed(-1 * (speed - 2));
            for (Background bg : foregroundActors) bg.setSpeed(-2 * (speed - 2));
        }

        ship.setSpeed(speed);
        for (Actor a : getActors()) {
            if (a instanceof Enemy) {
                Enemy enemy = (Enemy) a;
                    if (enemy.overlaps(ship) && isAlive && enemy.isVisible()) {
                        gameover();
                    }
                    if (enemy.getY() + enemy.getHeight() < getCameraMoveToY() - Globals.WORLD_HEIGHT / 2) {
                        if(enemy.isVisible() & !(enemy instanceof  SubMeteorite) & isAlive) {
                            points -= 10;
                            addActor(new Popup(-10, (int)enemy.getX(), (int)getCameraMoveToY()-Globals.WORLD_HEIGHT/2+60));
                        }
                        getActors().removeValue(enemy, true);
                    }
                    for (Actor b : getActors().toArray()) {
                        if (b instanceof  Laser) {
                            Laser  laser = (Laser)b;
                            if (flyout) {
                                if (whiteTimer > 1)
                                    whiteTimer = 1;
                                if(enemy instanceof Ghost) {
                                    enemy.getSprite("alap").setColor(enemy.getSprite("alap").getColor().r, enemy.getSprite("alap").getColor().g, enemy.getSprite("alap").getColor().b, 1 - whiteTimer);
                                    enemy.getSprite("szem").setColor(enemy.getSprite("szem").getColor().r, enemy.getSprite("szem").getColor().g, enemy.getSprite("szem").getColor().b, 1 - whiteTimer);
                                } else if (enemy instanceof Meteorite) {
                                    enemy.getSprite("alap").setColor(enemy.getSprite("alap").getColor().r, enemy.getSprite("alap").getColor().g, enemy.getSprite("alap").getColor().b, 1 - whiteTimer);
                                }
                                laser.getSprite().setColor(laser.getSprite().getColor().r, laser.getSprite().getColor().g, laser.getSprite().getColor().b, 1 - whiteTimer);
                            }

                            if (laser.overlaps(enemy) && laser.isFel() && laser.isVisible() && enemy.isVisible()) {
                                if (++killsSinceLastShot == 2) {
                                    points += 10;
                                    addActor(new Popup("Double shot!",(int)laser.getX(), (int)laser.getY()));
                                    addActor(new Popup(10,(int)laser.getX(), (int)laser.getY()-30));
                                } else if(killsSinceLastShot == 3) {
                                    points += 10;
                                    addActor(new Popup("Triple shot!",(int)laser.getX(), (int)laser.getY()));
                                    addActor(new Popup(10,(int)laser.getX(), (int)laser.getY()-30));
                                }
                                if(enemy instanceof Meteorite && ! (enemy instanceof SubMeteorite)){
                                    SubMeteorite m1 = new SubMeteorite(enemy.getX(), enemy.getY(), 1); SubMeteorite m2 = new SubMeteorite(enemy.getX(), enemy.getY(), 2);
                                    SubMeteorite m3 = new SubMeteorite(enemy.getX(), enemy.getY(), 3); SubMeteorite m4 = new SubMeteorite(enemy.getX(), enemy.getY(), 4);
                                    addActor(m1); addActor(m2); addActor(m3); addActor(m4);
                                }
                                if(enemy instanceof SubMeteorite){
                                    points += 5;
                                    addActor(new Popup(5, (int)enemy.getX(), (int)enemy.getY()));
                                }else if (enemy instanceof Boss){
                                    ((Boss) enemy).getShot();
                                    if(((Boss) enemy).getHealth() <= 0) {
                                        flyout = true;
                                        explode(enemy);
                                        enemy.remove();
                                        getActors().removeValue(enemy, true);
                                        enemy.setVisible(false);
                                    }
                                } else{
                                    points += 10;
                                    addActor(new Popup(10, (int)enemy.getX(), (int)enemy.getY()));
                                }
                               if(!(enemy instanceof Boss)) {
                                   explode(enemy);
                                   enemy.remove();
                                   getActors().removeValue(enemy, true);
                                   enemy.setVisible(false);
                               }
                                laser.remove();
                                getActors().removeValue(laser, true);

                            } else if (!laser.isFel() && laser.overlaps(ship) && isAlive && !flyout) {
                                gameover();
                            }
                        }
                }
            }
            else if (a instanceof  Laser) {
               Laser laser = (Laser)a;
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
                killsSinceLastShot=0;
                switch (weapon) {
                    case 1:
                        Laser laser1 = new Laser(ship.getX() + ship.getWidth() / 2 - 5, ship.getY() + ship.getHeight(), 0);
                        addActor(laser1);
                        break;
                    case 2:
                        Laser laser2 = new Laser(ship.getX() + ship.getWidth() / 2 - 5, ship.getY() + ship.getHeight(), 15);
                        Laser laser3 = new Laser(ship.getX() + ship.getWidth() / 2 - 5, ship.getY() + ship.getHeight(), -15);
                        addActor(laser2);
                        addActor(laser3);
                        break;

                    default:
                        Laser laser4 = new Laser(ship.getX() + ship.getWidth() / 2 - 5, ship.getY() + ship.getHeight(), 0);
                        Laser laser5 = new Laser(ship.getX() + ship.getWidth() / 2 - 5, ship.getY() + ship.getHeight(), -20);
                        Laser laser6 = new Laser(ship.getX() + ship.getWidth() / 2 - 5, ship.getY() + ship.getHeight(), 20);
                        addActor(laser4);
                        addActor(laser5);
                        addActor(laser6);
                        break;
                }

                lastshot = 0;
            }

            ControlStage.setPoints(points * 10);

            moveBackgrounds();

            if (points >= 100 * weapon) {
                boss = true;
                if(!vanBoss) {
                    Boss boss = new Boss(Globals.WORLD_WIDTH / 2 - 384 / 2, getCameraMoveToY()+Globals.WORLD_HEIGHT/2);
                    addActor(boss);
                    addActor(new HPActorPiros(boss));
                    vanBoss = true;
                }
            }

            if (flyout) {
                white.setPosition(getCameraMoveToX() - Globals.WORLD_WIDTH / 2, getCameraMoveToY() - Globals.WORLD_HEIGHT / 2);
                white.setColor(255, 255, 255, whiteTimer += 0.0006f);
                if (!ship.isInFrustum(4)) {
                    nextStage();
                }
                for (Background bg : backgroundActors) bg.setMoving(false);
                for (Background bg : foregroundActors) bg.setMoving(false);
            }
        }
        if(points < 0)
            gameover();

        if(decorationLast - elapsedTime > 20){
            decorationLast = elapsedTime;
            if(random(1,1) == 1){
                Decoration deco = new Decoration(0,0);
                deco.setPosition(random(0, (int)(Globals.WORLD_WIDTH-deco.getWidth())), (int)getCameraMoveToY()+Globals.WORLD_HEIGHT+400);
                addActor(deco,0);
            }
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
        for (Background bg : backgroundActors) bg.setMoving(false);
        game.setScreen(new GameScreen(game, ship.getVariety(), ++weapon));
        dispose();
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.X) {
            flyout=true;
        } else if (keyCode == Input.Keys.U && weapon < 4) {
            weapon++;
        } else if(keyCode == Input.Keys.B) {
            points = 100*weapon;
        }
        else if(keyCode == Input.Keys.SPACE)
            shoot();
        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {
        if(keyCode == Input.Keys.SPACE)
            unshoot();
        return super.keyUp(keyCode);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void gameover() {
        if(flyout | !isAlive)
            return;
        ship.setVisible(false);
        explode(ship);
        isAlive = false;
        ControlStage.gameover.setVisible(true);
    }

    public void explode(MyActor actor){
        Explosion ex = new Explosion();
        //ex.setWidth(actor.getWidth()*3);
        //ex.setHeight(actor.getHeight()*3);
        //ex.setOrigintoCenter();
        ex.setSize(actor.getWidth()*4, actor.getHeight()*4);
        ex.setPosition(actor.getX()+actor.getWidth()/2-ex.getWidth()/2, actor.getY()+actor.getHeight()/2-ex.getWidth()/2);


        addActor(ex);
    }
    public void shoot(){
        isShooting = true;
        killsSinceLastShot = 0;
        if(!isAlive)
            game.setScreenBackByStackPop();
    }
    public void unshoot(){
        isShooting = false;
    }
}
