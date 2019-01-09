package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Random;

import hu.csanysoft.invaders.Actors.Ghost;
import hu.csanysoft.invaders.Actors.Laser;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Game.MyLevel;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyActor;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;

public class GameStage extends MyStage {

    MyLevel firstLevel, secondLevel, thirdLevel;
    Ghost ghost, ghost2, ghost3, ghost4, ghost5, ghost6;
    public ArrayList<Laser> lasers = new ArrayList<Laser>();
    ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    boolean nextLevel = false;

    public GameStage(Invaders game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);

        firstLevel = new MyLevel(Assets.manager.get(Assets.SPACE_TEXTURE), this);
        secondLevel = new MyLevel(Assets.manager.get(Assets.SPACE_TEXTURE), this);
        thirdLevel = new MyLevel(Assets.manager.get(Assets.SPACE_TEXTURE), this);
        addLevel(firstLevel);
        addLevel(secondLevel);
        addLevel(thirdLevel);
        Random rand = new Random();
        ghost = new Ghost(rand.nextInt((int)(Globals.WORLD_WIDTH/3 - 128)) + 128,500);
        ghost2 = new Ghost(rand.nextInt((int)(Globals.WORLD_WIDTH/3 - 128)),500);
        ghost3 = new Ghost(rand.nextInt((int)(Globals.WORLD_WIDTH/3 - 128)) + Globals.WORLD_WIDTH/3,500);
        ghost4 = new Ghost(rand.nextInt((int)(Globals.WORLD_WIDTH/3 - 128)),500);
        ghost5 = new Ghost(rand.nextInt((int)(Globals.WORLD_WIDTH/3 - 128)) + Globals.WORLD_WIDTH/3,500);
        ghost6 = new Ghost(rand.nextInt((int)(Globals.WORLD_WIDTH/3 - 128)) + Globals.WORLD_WIDTH/3*2,500);

        ghosts.add(ghost);
        ghosts.add(ghost2);
        ghosts.add(ghost3);
        ghosts.add(ghost4);
        ghosts.add(ghost5);
        ghosts.add(ghost6);

        firstLevel.addActorToLevel(ghost);
        secondLevel.addActorToLevel(ghost2, ghost3);
        thirdLevel.addActorToLevel(ghost4, ghost5, ghost6);
        firstLevel.showActors(true);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Laser laser = new Laser(x,y, true);
                addActor(laser);
                lasers.add(laser);
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (MyLevel level : getLevels()) {
            if(level.isShowingActors()) {
                for (Ghost ghost : ghosts) {
                    if(level.isActorOnLevel(ghost)) {
                        //ghost.moveBy(0, 1);
                        for (Laser laser : lasers) {
                                if (laser.overlaps(ghost)) {
                                    level.removeActorFromLevel(ghost);
                                    if(level.getActors().size()==1) nextLevel = true;
                                }
                        }
                    }
                }
            }
        }
        if(nextLevel) {
            nextLevel = false;
            for(Laser laser : lasers) {
                getActors().removeValue(laser, true);
            }
            lasers.clear();
            nextLevel();
        }
    }
}
