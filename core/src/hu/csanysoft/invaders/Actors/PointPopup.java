package hu.csanysoft.invaders.Actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import hu.csanysoft.invaders.Invaders;
import hu.csanysoft.invaders.MyBaseClasses.UI.MyLabel;

public class PointPopup extends MyLabel {
    public PointPopup(int points, int x, int y) {
        super(points > 0 ? "+"+points : ""+points, points > 0 ? Invaders.getColorLabelStyle(Color.GREEN) : Invaders.getColorLabelStyle(Color.RED));
        setPosition(x,y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0,1);
        if(elapsedtime > 1) addAction(Actions.alpha(0, 1));
        if(elapsedtime > 2) getStage().getActors().removeValue(this, true);
    }
}
