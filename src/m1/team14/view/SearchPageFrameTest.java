package m1.team14.view;

import java.awt.*;

public class SearchPageFrameTest extends BaseGuiFrame {

    @Override
    public void init() {
        setTitle("Search Page");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        int x = (screenDimension.width - 400) / 2;
        int y = (screenDimension.height - 800) / 2;
        setBounds(x, y, 400, 800);
    }

    @Override
    public void create() {

    }

    @Override
    void add(Container container) {

    }
}
