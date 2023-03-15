import java.util.Scanner;


public class hearts {

    public static enum lead {P1, P2, P3, P4};

    public static void main(String[] args){

        card two = new card(card.Suits.Clubs, card.Ranks.Two);
        card.Suits hearts = card.Suits.Hearts;

        Scanner input = new Scanner(System.in);

        Player p1 = new Player();
        Player p2  = new Player();
        Player p3 = new Player();
        Player p4 = new Player();
        RandIndexQueue<card> deck = new RandIndexQueue<card>(52);
        for (card.Suits s: card.Suits.values()){
            for (card.Ranks r: card.Ranks.values()){
              card card = new card(s, r);
              deck.enqueue(card);
            }
          }
        deck.shuffle();

        System.out.println("Starting Hearts game!");
        System.out.println();

        int num = 0;

        while(p1.total<=100 && p2.total<=100 && p3.total<=100 && p4.total<=100){

            System.out.println("You have "+p1.total+" points");
            System.out.println("Player 2 has "+p2.total+" points");
            System.out.println("Player 3 has "+p3.total+" points");
            System.out.println("Player 4 has "+p4.total+" points");
            System.out.println("Hand starting...");

            //dealing
            for(int i=0; i<13; i++){
                p1.hand.cards.add(deck.dequeue());
                p2.hand.cards.add(deck.dequeue());
                p3.hand.cards.add(deck.dequeue());
                p4.hand.cards.add(deck.dequeue());
            }
            p1.hand.sort();
            p2.hand.sort();
            p3.hand.sort();
            p4.hand.sort();
            p1.hand.showHand();
            //passing
            if(num!=3){
                p1.hand.playerPass();
                p2.hand.pass();
                p3.hand.pass();
                p4.hand.pass();
                if(num==0){
                    p1.hand.passCards(p2.hand);
                    p2.hand.passCards(p3.hand);
                    p3.hand.passCards(p4.hand);
                    p4.hand.passCards(p1.hand);
                }
                else if(num==1){
                    p1.hand.passCards(p4.hand);
                    p4.hand.passCards(p3.hand);
                    p3.hand.passCards(p2.hand);
                    p2.hand.passCards(p1.hand);
                }
                else{
                    p1.hand.passCards(p3.hand);
                    p2.hand.passCards(p4.hand);
                    p3.hand.passCards(p1.hand);
                    p4.hand.passCards(p2.hand);
                }
            }
            p1.hand.showHand();
            //find 2
            lead leader;
            if(p1.hand.contains(two)) leader = lead.P1;
            else if(p2.hand.contains(two)) leader = lead.P2;
            else if(p3.hand.contains(two)) leader = lead.P3;
            else leader = lead.P4;
            boolean broken = false;
            boolean q = false;
            card[] trick = new card[4];
            int points;
            int player;
            //13 tricks loop
          
            for(int i=0; i<13; i++){
                System.out.println();
                // System.out.println("Player 1");
                // p1.hand.showHand();
                // System.out.println("Player 2");
                // p2.hand.showHand();
                // System.out.println("Player 3");
                // p3.hand.showHand();
                // System.out.println("Player 4");
                // p4.hand.showHand();
                if(leader==lead.P1){
                    p1.card = p1.hand.playerLeadCard(broken);
                    p2.card = p2.hand.playCard(p1.card, broken,q);
                    System.out.println("Player 2 plays "+p2.card.toString());
                    if(p2.card.suit==hearts || p2.card.equals(heartsCards.Queen)) broken = true;
                    if(p2.card.equals(heartsCards.Queen)) q = true;
                    p3.card = p3.hand.playCard(p1.card, p2.card, broken,q);
                    System.out.println("Player 3 plays "+p3.card.toString());
                    if(p3.card.suit==hearts || p3.card.equals(heartsCards.Queen)) broken = true;
                    if(p3.card.equals(heartsCards.Queen)) q = true;
                    p4.card = p4.hand.playCard(p1.card, p2.card, p3.card, broken, q);
                    System.out.println("Player 4 plays "+p4.card.toString());
                    if(p4.card.suit==hearts || p4.card.equals(heartsCards.Queen)) broken = true;
                    if(p2.card.equals(heartsCards.Queen)) q = true;
                }
                else if(leader==lead.P2){
                    p2.card = p2.hand.leadCard(broken,q);
                    System.out.println("Player 2 leads "+p2.card.toString());
                    p3.card = p3.hand.playCard(p2.card, broken,q);
                    System.out.println("Player 3 plays "+p3.card.toString());
                    if(p3.card.suit==hearts || p3.card.equals(heartsCards.Queen)) broken = true;
                    if(p3.card.equals(heartsCards.Queen)) q = true;
                    p4.card = p4.hand.playCard(p2.card, p3.card, broken,q);
                    System.out.println("Player 4 plays "+p4.card.toString());
                    if(p4.card.suit==hearts || p4.card.equals(heartsCards.Queen)) broken = true;
                    if(p2.card.equals(heartsCards.Queen)) q = true;
                    p1.card = p1.hand.playerPlayCard(p2.card, broken);
                    if(p1.card.suit==hearts || p1.card.equals(heartsCards.Queen)) broken = true;
                    if(p1.card.equals(heartsCards.Queen)) q = true;
                }
                else if(leader==lead.P3){
                    p3.card = p3.hand.leadCard(broken,q);
                    System.out.println("Player 3 leads "+p3.card.toString());
                    p4.card = p4.hand.playCard(p3.card, broken,q);
                    System.out.println("Player 4 plays "+p4.card.toString());
                    if(p4.card.suit==hearts ||p4.card.equals(heartsCards.Queen)) broken = true;
                    if(p4.card.equals(heartsCards.Queen)) q = true;
                    p1.card = p1.hand.playerPlayCard(p3.card, broken);
                    if(p1.card.suit==hearts || p1.card.equals(heartsCards.Queen)) broken = true;
                    if(p1.card.equals(heartsCards.Queen)) q = true;
                    p2.card = p2.hand.playCard(p3.card, p4.card, p1.card, broken,q);
                    System.out.println("Player 2 plays "+p2.card.toString());
                    if(p2.card.suit==hearts || p2.card.equals(heartsCards.Queen)) broken = true;
                    if(p2.card.equals(heartsCards.Queen)) q = true;
                }
                else{
                    p4.card = p4.hand.leadCard(broken,q);
                    System.out.println("Player 4 leads "+p4.card.toString());
                    p1.card = p1.hand.playerPlayCard(p4.card, broken);
                    if(p1.card.suit==hearts || p1.card.equals(heartsCards.Queen)) broken = true;
                    if(p1.card.equals(heartsCards.Queen)) q = true;
                    p2.card = p2.hand.playCard(p4.card, p1.card, broken,q);
                    System.out.println("Player 2 leads "+p2.card.toString());
                    if(p2.card.suit==hearts || p2.card.equals(heartsCards.Queen)) broken = true;
                    if(p2.card.equals(heartsCards.Queen)) q = true;
                    p3.card = p3.hand.playCard(p4.card, p1.card, p2.card, broken,q);
                    System.out.println("Player 3 plays "+p3.card.toString());
                    if(p3.card.suit==hearts || p3.card.equals(heartsCards.Queen)) broken = true;
                    if(p3.card.equals(heartsCards.Queen)) q = true;
                }
                trick[0] = p1.card;
                trick[1] = p2.card;
                trick[2] = p3.card;
                trick[3] = p4.card;
                points = points(trick);
                player = handWinner(trick, leader);
                if(player==0){
                     p1.handTotal+=points;
                     leader = lead.P1;
                     System.out.println("You won trick and collected "+points+" points");
                }
                else if(player==1){
                    p2.handTotal+=points;
                    leader = lead.P2;
                    System.out.println("Player 2 won trick and collected "+points+" points");
                } 
                else if(player==2){
                    p3.handTotal+=points;
                    leader = lead.P3;
                    System.out.println("Player 3 won trick and collected "+points+" points");
                } 
                else{
                    p4.handTotal+=points;
                    leader = lead.P4;
                    System.out.println("Player 4 won trick and collected "+points+" points");
                } 
                deck.add(p1.card, p2.card, p3.card, p4.card);
                trick = new card[4];
            }
            //hand loop
            System.out.println("Hand results.......");
            System.out.println("You had "+p1.handTotal+" points");
            System.out.println("Player 2 had  "+p2.handTotal+" points");
            System.out.println("Player 3 had "+p3.handTotal+" points");
            System.out.println("Player 4 had "+p4.handTotal+" points");
            System.out.println();
            System.out.println("Adding to totals......");
            p1.addPoints();
            p2.addPoints();
            p3.addPoints();
            p4.addPoints();
            int cont = 0;
            while(cont!=1 && cont!=2){
                if(cont==0) System.out.println("Would you like to continue (1:yes 2:no)");
                else System.out.println("Try Again, would you like to continue (1:yes 2:no) ");
                cont = input.nextInt();
            }
            if(cont==2) break;
           //resetting for next hand
           deck.shuffle();
           num = increase(num);
        }
        System.out.println("GAME OVER");
        if(p1.total>=100 && p2.total>=100 && p3.total>=100 && p4.total>=100){
            if(p1.total<p2.total && p1.total<p3.total && p1.total<p4.total) System.out.println("YOU WIN!!!!!!!");
            else if(p2.total<p1.total && p2.total<p3.total && p2.total<p4.total) System.out.println("Player 2 wins!");
            else if(p3.total<p1.total && p3.total<p2.total && p3.total<p4.total) System.out.println("Player 3 wins!"); 
            else System.out.println("Player 4 wins!");
        }
        else System.out.println("No winner declared because the game was ended early");
        System.out.println("Results........");
        System.out.println("You finished with "+p1.total+" points");
        System.out.println("Player 2 finished with "+p2.total+" points");
        System.out.println("Player 3 finished with "+p3.total+" points");
        System.out.println("Player 4 finished with "+p4.total+" points");
        input.close();
    }
    public static int points(card[] trick){
        int points = 0;
        for(int i=0; i<4; i++){
            card temp = trick[i];
            if(temp.equals(heartsCards.Queen)) points+=13;
            if(temp.suit==card.Suits.Hearts) points+=1;
        }
        return points;
    }

    public static int handWinner(card[] trick, lead win){
        int lead;
        if(win==hearts.lead.P1) lead = 0;
        else if(win==hearts.lead.P2) lead = 1;
        else if(win==hearts.lead.P3) lead = 2;
        else lead = 3;
        card.Suits suit = trick[lead].suit;
        int maxnum = lead;
        card max = trick[lead];
        lead = increase(lead);
        card temp = trick[lead];
        if(temp.suit==suit){
            if(temp.rank.compareTo(max.rank)>0){
                maxnum = lead;
                max = trick[lead];
            }
        }
        lead = increase(lead);
        temp = trick[lead];
        if(temp.suit==suit){
            if(temp.rank.compareTo(max.rank)>0){
                maxnum = lead;
                max = trick[lead];
            }
        }
        lead = increase(lead);
        temp = trick[lead];
        if(temp.suit==suit){
            if(temp.rank.compareTo(max.rank)>0){
                maxnum = lead;
                max = trick[lead];
            }
        }
        return maxnum;
    }

    public static int increase(int num){
        if(num==3) return 0;
        else return num+1;
    }
}