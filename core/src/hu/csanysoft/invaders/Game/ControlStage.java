package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.invaders.Actors.Laser;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class ControlStage extends MyStage {

    GameStage gameStage;

    public ControlStage(Invaders game, GameStage gs) {
        super(new ExtendViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        gameStage = gs;
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
                        gameStage.isShooting = true;
                        return super.touchDown(event, x, y, pointer, button);
                    }
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        gameStage.isShooting = false;
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
