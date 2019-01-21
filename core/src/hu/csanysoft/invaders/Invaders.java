package hu.csanysoft.invaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import hu.csanysoft.invaders.Global.Assets;
import hu.csanysoft.invaders.Menu.LoadingScreen;
import hu.csanysoft.invaders.MyBaseClasses.Scene2D.MyScreen;

public class Invaders extends Game {

	public static final int eletErtelme = 42;

	public final Stack<Class> backButtonStack = new Stack();

	@Override
	public void create () {
		Assets.prepare();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}

	@Override
	public void dispose () {
		super.dispose();
		Assets.unload();
	}

	@Override
	public void pause() {
		super.pause();
	}


	public Label.LabelStyle getLabelStyle() {
		Label.LabelStyle style;
		style = new Label.LabelStyle();
		style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		style.fontColor = Color.YELLOW;
		Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGB888);
		p.setColor(0.4f, 0.2f, 0.8f, 0.5f);
		p.fill();
		return style;
	}

	public TextField.TextFieldStyle getTextFieldStyle_White() {
		TextField.TextFieldStyle style = new TextField.TextFieldStyle();
		//style.background.setLeftWidth(style.background.getLeftWidth()+20);
		//style.background.setRightWidth(style.background.getRightWidth()+20);
		style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		style.font.getData().setScale(1.2f);

		style.fontColor = Color.WHITE;
		return style;
	}
	public TextField.TextFieldStyle getTextFieldStyle_Red() {
		TextField.TextFieldStyle style = new TextField.TextFieldStyle();
		//style.background.setLeftWidth(style.background.getLeftWidth()+20);
		//style.background.setRightWidth(style.background.getRightWidth()+20);
		style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		style.font.getData().setScale(1.2f);
		style.fontColor = Color.RED;
		return style;
	}

	public void setScreenBackByStackPop() {
		if (backButtonStack.size() > 1) {
			try {
				setScreen((MyScreen) backButtonStack.pop().getConstructor(Invaders.class).newInstance(this), false);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			Gdx.app.exit();
		}
	}

	public void setScreen(Screen screen) {
		setScreen(screen, true);
	}

	public void setScreen(Screen screen, boolean pushToStack) {
		Screen prevScreen = getScreen();
		if (prevScreen != null) {
			if (pushToStack) {
				backButtonStack.push(prevScreen.getClass());
			}
			prevScreen.dispose();
		}
		super.setScreen(screen);
	}
}
