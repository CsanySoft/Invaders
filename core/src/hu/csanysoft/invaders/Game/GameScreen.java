package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyScreen;

public class GameScreen extends MyScreen {

    GameStage stage;
    ControlStage controlStage;
    InputMultiplexer inputMultiplexer;

    public GameScreen(Invaders game) {
        super(game);
        stage = new GameStage(game, Assets.manager.get(Assets.SHIP_TEXTURE));
        controlStage = new ControlStage(game, stage);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(controlStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    public GameScreen(Invaders game, Texture texture) {
        super(game);
        stage = new GameStage(game, texture);
        controlStage = new ControlStage(game, stage);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(controlStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
        controlStage.act();
        controlStage.draw();
    }


    @Override
    public void init() {
        setBackGroundColor(0,0,0);
    }

    @Override
    public void resume() {
        super.resume();
    }

}
