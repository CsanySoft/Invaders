package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanysoft.invaders.MyBaseClasses.UI.MyLabel;

public class ControlStage extends MyStage {

    GameStage gameStage;
    static MyLabel pointCounter, levelLabel = new MyLabel("0", Invaders.getColorLabelStyle(Color.WHITE));
    public static OneSpriteStaticActor gameover;

    public ControlStage(Invaders game, GameStage gs) {
        super(new StretchViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        gameover = new OneSpriteStaticActor(Assets.manager.get(Assets.GAMEOVER_TEXTURE));
        gameover.setSize(gameover.getWidth() / 3.6f, gameover.getHeight() / 3.6f);
        gameover.setVisible(false);
        addActor(gameover);
        gameStage = gs;
        gameover.setPositionCenterOfActorToCenterOfViewport();
    }

    @Override
    public void init() {
        addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.EMPTY_TEXTURE)){
            @Override
            public void init() {
                super.init();
                setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);
                addListener(new ClickListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        gameStage.shoot();
                        return super.touchDown(event, x, y, pointer, button);
                    }
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        gameStage.unshoot();
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        });
        addActor(pointCounter = new MyLabel("0 pont", game.getColorLabelStyle(Color.WHITE)){
            @Override
            public void init() {
                super.init();
                setPosition(10, Globals.WORLD_HEIGHT-getHeight()-10);
            }
        });
        addActor(levelLabel);
        levelLabel.setPosition(10, Globals.WORLD_HEIGHT-90);
    }

    public static void setPoints(int points){
        pointCounter.setText(points+" pont");
    }
    public static void setLevelIndicator(int level){levelLabel.setText(level+". szint");}

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
