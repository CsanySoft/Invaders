package hu.csanysoft.invaders.MyBaseClasses;

import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import hu.csanysoft.invaders.MyBaseClasses.Game.InitableInterface;

/**
 * Created by davimatyi on 2017. 01. 10..
 */
public class MyTextField extends TextField implements InitableInterface {

    public MyTextField(String text, TextFieldStyle style) {
        super(text, style);
        setWidth(300);
        setHeight(50);
        init();
    }

    @Override
    public void init() {

    }
}
