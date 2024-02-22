package utiliz;

public class Constants {
    public static class Resolution{
        public final static int TILES_DEFAULT_SIZE = 32;
        public final static float SCALE = 1.5f;
        public final static int TILES_IN_WIDTH = 26;
        public final static int TILES_IN_HEIGHT = 14;
        public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
        public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
        public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    }

    public static class Directions{
        public static final int UP=0;
        public static final int RIGHT=1;
        public static final int DOWN=2;
        public static final int LEFT=3;
    }
    
    public static class PlayerConstants{
        public static final int IDLE=0;
        public static final int RUNNING=1;
        public static final int JUMPING=2;
        public static final int FALLING=3;
        public static final int ATTACKING=4;
        public static final int TAKING_DAMAGE=5;
        public static final int DYING=6;
        public static final int DASHING=7;

        public static int setMaxFrame(int action){
            switch(action){
                case FALLING:
                return 1;
                case IDLE:
                return 5;
                case RUNNING:
                return 6;
                case JUMPING:
                return 3;
                case ATTACKING:
                return 3;
                case TAKING_DAMAGE:
                return 4;
                case DYING:
                return 8;
                case DASHING:
                return 4;

                default: return 1;
            
            }
        }
    }
}
