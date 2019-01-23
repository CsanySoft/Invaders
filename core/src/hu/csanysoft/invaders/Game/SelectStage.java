package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;

public class SelectStage extends MyStage {

    Image ship1, ship2;
    Texture texture1, texture2;

    public SelectStage(final Invaders game) {
        super(new ExtendViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);

        texture1 = Assets.manager.get(Assets.SHIP_TEXTURE);
        texture2 = Assets.manager.get(Assets.SHIP2_TEXTURE);

        ship1 = new Image(texture1);
        ship2 = new Image(texture2);
        Image background = new Image(Assets.manager.get(Assets.SPACE_TEXTURE));
        background.setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);

        addActor(background);
        addActor(ship1);
        addActor(ship2);


        ship1.setSize(ship1.getWidth()/2, ship1.getHeight()/2);
        ship2.setSize(ship2.getWidth()/2, ship2.getHeight()/2);

        ship1.setPosition(getWidth()/2 - ship1.getWidth()/2, getHeight()/2 + ship1.getHeight());
        ship2.setPosition(getWidth()/2 - ship2.getWidth()/2, getHeight()/2 - ship2.getHeight());

        ship1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game, texture1, (short)1), true);
                dispose();
            }
        });


        ship2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game, texture2, (short)1), true);
                dispose();
            }
        });
    }

    @Override
    public void init() {
        //TODO ezt megcsinálni: Assets.manager.get(Assets.MUSIC_BACKGROUND).setLooping(true);
    }
}
