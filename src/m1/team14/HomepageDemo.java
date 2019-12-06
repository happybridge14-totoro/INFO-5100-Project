package m1.team14;

import m1.team14.controller.IconController;
import m1.team14.model.HomepageModel;
import m1.team14.view.HomepageFrame;

public class HomepageDemo {

    public static void main(String[] args) {
//        HomepageFrame homepageFrame = new HomepageFrame();

        //////////////=============== mvc
        IconController iconController = new IconController();
        HomepageFrame homepageFrame = new HomepageFrame(iconController);
        HomepageModel homepageModel = new HomepageModel();

        iconController.addView(homepageFrame);
        iconController.addModel(homepageModel);
        //////////////===============
    }

}
