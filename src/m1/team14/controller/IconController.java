package m1.team14.controller;

public class IconController extends AbstractController {

    public void changeCurrentDealer(String curDealer){
        try{
            this.setModelProperty("CurrentDealer", curDealer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
