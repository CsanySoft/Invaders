package hu.csanysoft.invaders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;

import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyScreen;

public class SelectScreen extends MyScreen {

    SelectStage selectStage;
    InputMultiplexer inputMultiplexer;

    public SelectScreen(Invaders game) {
        super(game);
        inputMultiplexer = new InputMultiplexer();
        selectStage = new SelectStage(game);
        inputMultiplexer.addProcessor(selectStage);
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
        selectStage.act();
        selectStage.draw();
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
