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

    public AnimatedOffsetSprite(String file, float xOffset, float yOffset) {
        super(new TextureAtlas(Gdx.files.internal(file)).getRegions().get(0).getTexture(), xOffset, yOffset);
        textureAtlas = new TextureAtlas(Gdx.files.internal(file));
        setTexture(textureAtlas.getRegions().get(0).getTexture());
        init();
    }

    public AnimatedOffsetSprite(String file, float xOffset, float yOffset, float width, float height) {
        super(new TextureAtlas(Gdx.files.internal(file)).getRegions().get(0).getTexture(), xOffset, yOffset, width, height);
        textureAtlas = new TextureAtlas(Gdx.files.internal(file));
        setTexture(textureAtlas.getRegions().get(0).getTexture());
        //setSize(width, height);
        //setPosition(xOffset, yOffset);
        init();
    }


    public void init() {
        setSize(textureAtlas.getRegions().get(0).getRegionWidth(), textureAtlas.getRegions().get(0).getRegionHeight());
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

    @Override
    public void setRotation(float degrees) {
        super.setRotation(degrees);
        setFrame(((int) (elapsedTime * fps)));
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setFrame(((int) (elapsedTime * fps)));
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        setFrame(((int) (elapsedTime * fps)));
    }
}
