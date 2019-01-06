package hu.csanysoft.invaders.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import hu.csanysoft.invaders.Game.GameStage;
import hu.csanysoft.invaders.Global.Globals;
import hu.csanysoft.invaders.Invaders;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Globals.WORLD_WIDTH;
		config.height = Globals.WORLD_HEIGHT;
		new LwjglApplication(new Invaders(), config);
	}
}
