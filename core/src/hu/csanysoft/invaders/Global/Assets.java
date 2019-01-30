package hu.csanysoft.invaders.Global;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import static hu.csanysoft.invaders.Global.Globals.random;

public class Assets {
    public static AssetManager manager;

    public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";

    static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();

    static {

        fontParameter.fontFileName = "arial.ttf";

        fontParameter.fontParameters.size = 30;

        fontParameter.fontParameters.characters = CHARS;

        fontParameter.fontParameters.color = Color.WHITE;

    }

    public static final AssetDescriptor<BitmapFont> ARIAL_30_FONT
            = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);

    public static final AssetDescriptor<Texture> SPACE_TEXTURE
            = new AssetDescriptor<Texture>("stars.png", Texture.class);
public static final AssetDescriptor<Texture> GHOST_ALAP_TEXTURE
            = new AssetDescriptor<Texture>("ghost_alap.png", Texture.class);

    public static final AssetDescriptor<Texture> GHOST_SZEM_TEXTURE
            = new AssetDescriptor<Texture>("ghost_szem.png", Texture.class);

    public static final AssetDescriptor<Texture> LASER_TEXTURE
            = new AssetDescriptor<Texture>("laser.png", Texture.class);

    public static final AssetDescriptor<Texture> EMPTY_TEXTURE
            = new AssetDescriptor<Texture>("zolipls.png", Texture.class);

    public static final AssetDescriptor<Texture> SHIP_TEXTURE
            = new AssetDescriptor<Texture>("spaceship.png", Texture.class);

    public static final AssetDescriptor<Texture> SHIP2_TEXTURE
            = new AssetDescriptor<Texture>("spaceship2.png", Texture.class);

    public static final AssetDescriptor<Texture> GAMEOVER_TEXTURE
            = new AssetDescriptor<Texture>("gameover.png", Texture.class);

    public static final AssetDescriptor<Texture> ENEMY1_TEXTURE
            = new AssetDescriptor<Texture>("enemy1.png", Texture.class);

    public static final AssetDescriptor<Texture> WHITE_TEXTURE
            = new AssetDescriptor<Texture>("white.jpg", Texture.class);

    public static final AssetDescriptor<TextureAtlas> ROCKET_ATLAS
            = new AssetDescriptor<TextureAtlas>("rocket_anim.txt", TextureAtlas.class);

    public static final AssetDescriptor<Texture> METEORITE_TEXTURE
            = new AssetDescriptor<Texture>("meteorite.png", Texture.class);

    public static final AssetDescriptor<Sound> SOUND_LASER
            = new AssetDescriptor<Sound>("sound/laser.wav", Sound.class);

    public static final AssetDescriptor<Sound> SOUND_BOOM
            = new AssetDescriptor<Sound>("sound/boom.mp3", Sound.class);

    public static final AssetDescriptor<Music>  MUSIC_BACKGROUND
            = new AssetDescriptor<Music>("sound/popcorn.mp3", Music.class);

    public static final AssetDescriptor<Texture> TUTORIAL_TEXTURE
            = new AssetDescriptor<Texture>("tutorial.png", Texture.class);

    public static final AssetDescriptor<Texture> DOUBLE_TEXTURE
            = new AssetDescriptor<Texture>("double.png", Texture.class);

    public static final AssetDescriptor<Texture> TRIPLE_TEXTURE
            = new AssetDescriptor<Texture>("triple.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> EXPLOSION_TEXTURE
            = new AssetDescriptor<TextureAtlas>("animations/explosion2.atlas", TextureAtlas.class);

    public static final AssetDescriptor<Texture> SATURN_TEXTURE
            = new AssetDescriptor<Texture>("planets/saturn.png", Texture.class);

    public static final AssetDescriptor<Texture> JUPITER_TEXTURE
            = new AssetDescriptor<Texture>("planets/jupiter.png", Texture.class);

    public static final AssetDescriptor<Texture> TESLA_TEXTURE
            = new AssetDescriptor<Texture>("planets/tesla.png", Texture.class);

    public static final AssetDescriptor<Texture> VENUS_TEXTURE
            = new AssetDescriptor<Texture>("planets/venus.png", Texture.class);


    public static void prepare() {
        manager = new AssetManager();
        Texture.setAssetManager(manager);
    }


    public static void load() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

        manager.load(ARIAL_30_FONT);
        manager.load(SPACE_TEXTURE);
        manager.load(GHOST_ALAP_TEXTURE);
        manager.load(GHOST_SZEM_TEXTURE);
        manager.load(LASER_TEXTURE);
        manager.load(EMPTY_TEXTURE);
        manager.load(SHIP_TEXTURE);
        manager.load(SHIP2_TEXTURE);
        manager.load(GAMEOVER_TEXTURE);
        manager.load(ENEMY1_TEXTURE);
        manager.load(WHITE_TEXTURE);
        manager.load(ROCKET_ATLAS);
        manager.load(METEORITE_TEXTURE);
        manager.load(SOUND_LASER);
        manager.load(TUTORIAL_TEXTURE);
        manager.load(MUSIC_BACKGROUND);
        manager.load(EXPLOSION_TEXTURE);
        manager.load(SOUND_BOOM);
        manager.load(DOUBLE_TEXTURE);
        manager.load(TRIPLE_TEXTURE);
        manager.load(SATURN_TEXTURE);
        manager.load(JUPITER_TEXTURE);
        manager.load(TESLA_TEXTURE);
        manager.load(VENUS_TEXTURE);
    }

    public static void afterLoaded() {

    }
    public static void unload() {
        manager.dispose();
    }




    public static AssetDescriptor<Texture> getRandomDecoration(){
        switch(random(1,2)){
            case 1: return GAMEOVER_TEXTURE;
            case 2: return DOUBLE_TEXTURE;
        }
        return EMPTY_TEXTURE;
    }
}
