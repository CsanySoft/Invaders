package hu.csanysoft.invaders.Loading;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyStage;

public class LoadingStage extends MyStage {
    public LoadingStage(Batch batch, Invaders game) {
        super(new ExtendViewport(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT), batch, game);
        System.out.println("Kész");
        fitWorldToWidth();

    }

    @Override
    public void init() {

    }

    @Override
    public void setViewport(Viewport viewport){
        super.setViewport(viewport);
    }

    @Override
    public void resize(int screenWidth, int screenHeight) {
        super.resize(screenWidth, screenHeight);
        System.out.println("fkfk");
        System.out.println("getViewport().getWorldWidth() = " + getViewport().getWorldWidth());
        System.out.println("getViewport().getScreenWidth() = " + getViewport().getScreenWidth());

        getViewport().setScreenSize(screenWidth, screenHeight);
        fitWorldToWidth();
        System.out.println(screenWidth);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw() {
        super.draw();
    }
}
