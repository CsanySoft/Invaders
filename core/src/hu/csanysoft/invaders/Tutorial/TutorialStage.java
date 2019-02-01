package hu.csanysoft.invaders.Tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.invaders.Game.SelectScreen;
import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.MyTextButton;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanysoft.invaders.MyBaseClasses.UI.MyLabel;

public class TutorialStage extends MyStage {

    public TutorialStage(Viewport viewport, Batch batch, Invaders game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
        setDebugAll(Globals.DEBUG_ALL);
        Gdx.input.setInputProcessor(this);
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

        MyLabel label = new MyLabel("Irányításhoz döntse meg eszközét!\n" +
                "Ha lőni akar tartsa ujját a képernyőn.\n" +
                "Vigyázzon! A meteoridok darabokra törnek\nha szétlövi, " +
                "és ha a hajónak ütköznek akkor meghal!" ,  Invaders.getColorLabelStyle(Color.YELLOW));
        addActor(label);
        label.scaleBy(.5f);
        label.setPosition(10,(Globals.WORLD_HEIGHT-label.getHeight())/2+150);
        addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.TUTORIAL_TEXTURE)){
            @Override
            public void init() {
                super.init();
                setSize(300,400);
                setPosition(Globals.WORLD_WIDTH/2-getWidth()/2, 250);
            }
        });


        addActor(new MyTextButton("Vissza"){
            @Override
            protected void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH/2-getWidth()/2, 50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreenBackByStackPop();
                    }
                });
            }
        });




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
