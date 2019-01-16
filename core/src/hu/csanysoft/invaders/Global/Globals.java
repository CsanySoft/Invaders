package hu.csanysoft.invaders.Global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Globals {

    public static final int WORLD_WIDTH = 720;
    public static final int WORLD_HEIGHT = 1280;

    public static final boolean DEBUG_ALL = true;

    public static boolean ACCELEROMETER_AVAILABLE = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
}
