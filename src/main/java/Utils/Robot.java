package Utils;
import java.awt.*;
import java.awt.event.KeyEvent;
/**
 * Created by Viachaslau Balashevich.
 * https://www.linkedin.com/in/viachaslau
 */
public class Robot extends java.awt.Robot {
    public Robot() throws AWTException {
    }

    public static void pageUp() throws AWTException {
        java.awt.Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
        robot.keyRelease(KeyEvent.VK_PAGE_UP);
    }

}
