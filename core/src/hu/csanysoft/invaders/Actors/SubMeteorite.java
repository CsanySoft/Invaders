package hu.csanysoft.invaders.Actors;

public class SubMeteorite extends Meteorite {

    private int direction;

    public SubMeteorite(float x, float y, int direction) {
        super(x, y);
        this.direction = direction;
        setSize(30,30);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        switch(direction){
            case 1: moveBy(2,2); rotateBy(2); break;
            case 2: moveBy(2, -2); rotateBy(-2); break;
            case 3: moveBy(-2, -2); rotateBy(-2); break;
            case 4: moveBy(-2, 2); rotateBy(2); break;
        }
    }
}
