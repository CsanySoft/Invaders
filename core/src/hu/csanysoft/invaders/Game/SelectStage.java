package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanysoft.invaders.MyBaseClasses.UI.MyLabel;

public class SelectStage extends MyStage {

    Image ship1, ship2;
    Texture texture1, texture2;

    public SelectStage(final Invaders game) {
        super(new StretchViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);

        texture1 = Assets.manager.get(Assets.SHIP_TEXTURE);
        texture2 = Assets.manager.get(Assets.SHIP2_TEXTURE);

        ship1 = new Image(texture1);
        ship2 = new Image(texture2);
        OneSpriteStaticActor background = new OneSpriteStaticActor(Assets.manager.get(Assets.SPACE_TEXTURE)){
            @Override
            public void act(float delta) {
                super.act(delta);
                rotateBy(delta*20);
            }
        };
        float size = (float)Math.sqrt(Globals.WORLD_WIDTH*Globals.WORLD_WIDTH + Globals.WORLD_HEIGHT*Globals.WORLD_HEIGHT);
        background.setSize(size,size);

        addActor(background);
        addActor(ship1);
        addActor(ship2);

        background.setPositionCenterOfActorToCenterOfViewport();

        ship1.setSize(ship1.getWidth()/2, ship1.getHeight()/2);
        ship2.setSize(ship2.getWidth()/2, ship2.getHeight()/2);

        ship1.setPosition(getWidth()/4 - ship1.getWidth()/2, 200);
        ship2.setPosition(getWidth()/4*3 - ship2.getWidth()/2, 200);

        ship1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game, 1, (short)1), true);
                dispose();
            }
        });


        ship2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game, 2, (short)1), true);
                dispose();
            }
        });

        addActor(new MyLabel("Válassz űrhajót!", game.getColorLabelStyle(Color.WHITE)) {
            @Override
            public void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH / 2 - getWidth() / 2, Globals.WORLD_HEIGHT / 3 * 2);
            }
        });
    }

    @Override
    public boolean keyDown(int keyCode) {
        if(keyCode == Input.Keys.BACK){
            game.setScreenBackByStackPop();
        }
        return super.keyDown(keyCode);
    }

    @Override
    public void init() {
    }
}
