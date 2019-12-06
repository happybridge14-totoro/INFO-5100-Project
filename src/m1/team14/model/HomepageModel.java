package m1.team14.model;

import m1.team14.Events;

public class HomepageModel extends AbstractModel{
    private String currentDealer;

    public void setCurrentDealer(String curDealer){
        String oldCurDealer = this.currentDealer;
        this.currentDealer = curDealer;
        if (!currentDealer.equals(oldCurDealer)){
            firePropertyChange(Events.DEALER_ID_CHANGE, oldCurDealer, curDealer);
        }
    }

    public String getCurrentDealer(){
        return this.currentDealer;
    }
}
