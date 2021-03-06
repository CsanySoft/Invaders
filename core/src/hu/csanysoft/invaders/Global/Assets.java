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
            = new AssetDescriptor<Texture>("sprites/ghost_alap.png", Texture.class);

    public static final AssetDescriptor<Texture> GHOST_SZEM_TEXTURE
            = new AssetDescriptor<Texture>("sprites/ghost_szem.png", Texture.class);

    public static final AssetDescriptor<Texture> LASER_TEXTURE
            = new AssetDescriptor<Texture>("sprites/laser.png", Texture.class);

    public static final AssetDescriptor<Texture> EMPTY_TEXTURE
            = new AssetDescriptor<Texture>("zolipls.png", Texture.class);

    public static final AssetDescriptor<Texture> SHIP_TEXTURE
            = new AssetDescriptor<Texture>("sprites/spaceship.png", Texture.class);

    public static final AssetDescriptor<Texture> SHIP2_TEXTURE
            = new AssetDescriptor<Texture>("sprites/spaceship2.png", Texture.class);

    public static final AssetDescriptor<Texture> GAMEOVER_TEXTURE
            = new AssetDescriptor<Texture>("gameover.png", Texture.class);

    public static final AssetDescriptor<Texture> WHITE_TEXTURE
            = new AssetDescriptor<Texture>("white.jpg", Texture.class);

    public static final AssetDescriptor<TextureAtlas> ROCKET_ATLAS
            = new AssetDescriptor<TextureAtlas>("sprites/rocket_anim.txt", TextureAtlas.class);

    public static final AssetDescriptor<Texture> METEORITE_TEXTURE
            = new AssetDescriptor<Texture>("sprites/meteorite.png", Texture.class);

    public static final AssetDescriptor<Sound> SOUND_LASER
            = new AssetDescriptor<Sound>("sound/laser.wav", Sound.class);

    public static final AssetDescriptor<Sound> SOUND_BOOM
            = new AssetDescriptor<Sound>("sound/boom.mp3", Sound.class);

    public static final AssetDescriptor<Music>  MUSIC_BACKGROUND
            = new AssetDescriptor<Music>("sound/popcorn.mp3", Music.class);

    public static final AssetDescriptor<Texture> TUTORIAL_TEXTURE
            = new AssetDescriptor<Texture>("tutorial.png", Texture.class);

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

    public static final AssetDescriptor<TextureAtlas> SHIP1_ATLAS
            = new AssetDescriptor<TextureAtlas>("ships/ship1.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> SHIP2_ATLAS
            = new AssetDescriptor<TextureAtlas>("ships/ship2.atlas", TextureAtlas.class);


    public static final AssetDescriptor<Texture> LOGO
            = new AssetDescriptor<Texture>("invaders.png", Texture.class);

    public static final AssetDescriptor<Texture> START
            = new AssetDescriptor<Texture>("buttons/start_red.png", Texture.class);

    public static final AssetDescriptor<Texture> START_DOWN
            = new AssetDescriptor<Texture>("buttons/start_down_red.png", Texture.class);

    public static final AssetDescriptor<Texture> EXIT
            = new AssetDescriptor<Texture>("buttons/exit_red.png", Texture.class);

    public static final AssetDescriptor<Texture> EXIT_DOWN
            = new AssetDescriptor<Texture>("buttons/exit_down_red.png", Texture.class);

    public static final AssetDescriptor<Texture> HP_PIROS_TEXTURE
            = new AssetDescriptor<Texture>("hp piros.png", Texture.class);




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
        manager.load(WHITE_TEXTURE);
        manager.load(ROCKET_ATLAS);
        manager.load(METEORITE_TEXTURE);
        manager.load(SOUND_LASER);
        manager.load(TUTORIAL_TEXTURE);
        manager.load(MUSIC_BACKGROUND);
        manager.load(EXPLOSION_TEXTURE);
        manager.load(SOUND_BOOM);
        manager.load(SATURN_TEXTURE);
        manager.load(JUPITER_TEXTURE);
        manager.load(TESLA_TEXTURE);
        manager.load(VENUS_TEXTURE);
        manager.load(SHIP1_ATLAS);
        manager.load(SHIP2_ATLAS);
        manager.load(LOGO);
        manager.load(START);
        manager.load(START_DOWN);
        manager.load(EXIT);
        manager.load(EXIT_DOWN);
        manager.load(HP_PIROS_TEXTURE);
    }

    public static void afterLoaded() {
        manager.get(MUSIC_BACKGROUND).setLooping(true);
    }
    public static void unload() {
        manager.dispose();
    }




    public static AssetDescriptor<Texture> getRandomDecoration(){
        switch(random(1,4)){
            case 1: return SATURN_TEXTURE;
            case 2: return VENUS_TEXTURE;
            case 3: return JUPITER_TEXTURE;
            case 4: return TESLA_TEXTURE;
        }
        return EMPTY_TEXTURE;
    }
}
