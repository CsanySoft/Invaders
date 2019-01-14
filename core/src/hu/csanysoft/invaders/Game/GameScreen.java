package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyScreen;

public class GameScreen extends MyScreen {

    GameStage stage;
    ControlStage controlStage;
    InputMultiplexer inputMultiplexer;

    public GameScreen(Invaders game) {
        super(game);
        stage = new GameStage(game);
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
        setBackGroundColor(1,1,1);
    }

    @Override
    public void resume() {
        super.resume();
    }

}
