package hu.csanysoft.invaders.MyBaseClasses.Scene2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedOffsetSprite extends OffsetSprite {


    protected final TextureAtlas textureAtlas;
    protected float fps = 30;
    protected boolean running = true;
    protected boolean looping = true;
    protected float animationTime = 0;

    private int actualFrame = 0;
    private int prevFrame = 0;


    private float elapsedTime = 0;

    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public int getActualFrame() {
        return actualFrame;
    }

    public AnimatedOffsetSprite(TextureAtlas textureAtlas, float xOffset, float yOffset) {
        super(textureAtlas.getRegions().get(0).getTexture(), xOffset, yOffset);
        this.textureAtlas = textureAtlas;
        init();
    }

    public AnimatedOffsetSprite(TextureAtlas textureAtlas, float xOffset, float yOffset, float width, float height) {
        super(textureAtlas.getRegions().get(0).getTexture(), xOffset, yOffset, width, height);
        this.textureAtlas = textureAtlas;
        init();
    }


    public void init() {

    }

    public float getFps() {
        return fps;
    }

    public void setFps(float fps) {
        this.fps = fps;
    }



    public void act(float delta) {
        elapsedTime += delta;
        if (running) {
            animationTime+=delta;
            int actualFrame=((int) (animationTime * fps)) % textureAtlas.getRegions().size;
            if (actualFrame<prevFrame){
                repeated();
                if (!looping) {
                    stop();
                    return;
                }
            }
            setFrame(actualFrame);
            prevFrame = actualFrame;
        }
    }

    protected void repeated(){
    }

    public void setFrame(int frame)
    {
        if(textureAtlas == null) return;
        this.setRegion(textureAtlas.getRegions().get(frame % textureAtlas.getRegions().size));
    }

    public void setFramePercent(float percent) {
        setFrame((int)(getFrameCount()*percent));
    }

    public int getFrameCount()
    {
        return textureAtlas.getRegions().size;
    }

    public void start()
    {
        running = true;
    }

    public void stop()
    {
        running = false;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

}
