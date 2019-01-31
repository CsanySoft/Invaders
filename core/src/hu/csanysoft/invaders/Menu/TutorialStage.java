package hu.csanysoft.invaders.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.invaders.Game.SelectScreen;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class TutorialStage extends MyStage {

    public TutorialStage(Viewport viewport, Batch batch, Invaders game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
        setDebugAll(Globals.DEBUG_ALL);
    }

    public void init() {
        OneSpriteStaticActor background = new OneSpriteStaticActor(Assets.manager.get(Assets.SPACE_TEXTURE)){
            @Override
            public void act(float delta) {
                super.act(delta);
                rotateBy(delta*20);
            }
        };




        addActor(background);
        float size = (float)Math.sqrt(Globals.WORLD_WIDTH*Globals.WORLD_WIDTH + Globals.WORLD_HEIGHT*Globals.WORLD_HEIGHT);
        background.setSize(size,size);
        background.setPositionCenterOfActorToCenterOfViewport();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            game.setScreenBackByStackPop();
        }
        return false;
    }
}
