package m1.team14.view;

import java.awt.*;

public class LoginPageFrameTest extends BaseGuiFrame {

    @Override
    public void init() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        int x = (screenDimension.width - 400) / 2;
        int y = (screenDimension.height - 300) / 2;
        setBounds(x, y, 400, 300);
    }

    public LoginPageFrameTest() {
        setTitle("login");
        init();
    }

    @Override
    public void create() {

    }

    @Override
    void add(Container container) {

    }

    // test
    public static void main(String[] args) {
        new LoginPageFrameTest();
    }
}
