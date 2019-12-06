package m1.team14.view;

import java.awt.*;

public class HistoryPageFrameTest extends BaseGuiFrame {

    @Override
    public void init() {
        setTitle("History Page");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        int x = (screenDimension.width - 300) / 2;
        int y = (screenDimension.height - 900) / 2;
        setBounds(x, y, 300, 900);
    }

    @Override
    public void create() {

    }

    @Override
    void add(Container container) {

    }
}
