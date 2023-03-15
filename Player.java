public class Player {
    
    int total;
    int handTotal;
    heartsCards hand;
    card card;

    public Player(){
        total =0;
        handTotal=0;
        hand = new heartsCards();
    }
    public void addPoints(){
        this.total+=this.handTotal;
        this.handTotal = 0;
    }
}
