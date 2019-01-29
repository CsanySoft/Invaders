package hu.csanysoft.invaders.Actors;

public class SubMeteorite extends Meteorite {

    private int direction;
    private int moveX, moveY;

    public SubMeteorite(float x, float y, int direction) {
        super(x, y);
        this.direction = direction;
        setSize(30,30);
        moveX = rand.nextInt(5);
        moveY = rand.nextInt(5);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        switch(direction){
            case 1: moveBy(moveX,moveY); break;
            case 2: moveBy(moveX, -moveY); break;
            case 3: moveBy(-moveX, -moveY); break;
            case 4: moveBy(-moveX, moveY); break;
        }
    }
}
