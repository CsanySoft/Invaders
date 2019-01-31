package hu.csanysoft.invaders.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.invaders.Game.SelectScreen;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.Game.GameScreen;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.MyBaseClasses.MyTextButton;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class MenuStage extends MyStage {

    public MenuStage(Viewport viewport, Batch batch, Invaders game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
        setDebugAll(Globals.DEBUG_ALL);
    }

    public void init() {
        OneSpriteStaticActor spiral = new OneSpriteStaticActor(Assets.manager.get(Assets.SPACE_TEXTURE)){
            @Override
            public void act(float delta) {
                super.act(delta);
                rotateBy(delta*20);
            }
        };

        OneSpriteStaticActor logo = new OneSpriteStaticActor(Assets.manager.get(Assets.LOGO)){
            @Override
            public void act(float delta) {
                super.act(delta);
                setRotation((float) (Math.sin(elapsedTime)*10));
            }
        };

        OneSpriteStaticActor start = new OneSpriteStaticActor(Assets.manager.get(Assets.START)){
            @Override
            public void init() {
                super.init();
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        setTexture(Assets.manager.get(Assets.START_DOWN));
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        game.setScreen(new SelectScreen(game), true);
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        };

        OneSpriteStaticActor exit = new OneSpriteStaticActor(Assets.manager.get(Assets.EXIT)){
            @Override
            public void init() {
                super.init();
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        System.exit(0);
                        setTexture(Assets.manager.get(Assets.GAMEOVER_TEXTURE));
                        setTexture(Assets.manager.get(Assets.EXIT_DOWN));
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        System.exit(0);
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        };

        OneSpriteStaticActor tutorial = new OneSpriteStaticActor(Assets.manager.get(Assets.START)){
            @Override
            public void init() {
                super.init();
                addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        setTexture(Assets.manager.get(Assets.GAMEOVER_TEXTURE));
                        return true;
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        game.setScreen(new SelectScreen(game),true);
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        };

        addActor(logo);
        addActor(spiral);
        addActor(start);
        addActor(tutorial);
        addActor(exit);

        start.magnify(2);
        tutorial.magnify(2);
        exit.magnify(2);
        float size = (float)Math.sqrt(Globals.WORLD_WIDTH*Globals.WORLD_WIDTH + Globals.WORLD_HEIGHT*Globals.WORLD_HEIGHT);
        spiral.setSize(size,size);

        start.setPosition(Globals.WORLD_WIDTH/2-start.getWidth()/2, 600);
        exit.setPosition(Globals.WORLD_WIDTH/2-exit.getWidth()/2, 200);
        tutorial.setPosition(Globals.WORLD_WIDTH/2-tutorial.getWidth()/2, 350);
        logo.setPosition(Globals.WORLD_WIDTH/2-logo.getWidth()/2, 900);



        spiral.setZIndex(10);
        spiral.setOrigintoCenter();
        spiral.setPositionCenterOfActorToCenterOfViewport();
        spiral.setZIndex(0);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            game.setScreenBackByStackPop();
        }
        return false;
    }

}
