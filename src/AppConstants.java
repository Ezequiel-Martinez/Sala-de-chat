

public class AppConstants {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	public static final int RELATION_ASPECT_X = 16;
	public static final int RELATION_ASPECT_Y = 9;
	
	public static int getWidthRelationAspect() {
		return WIDTH*RELATION_ASPECT_X;
	}
}
