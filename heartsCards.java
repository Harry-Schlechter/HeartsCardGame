import java.util.*;
import java.util.Scanner;
//Java FX

public class heartsCards {

    public static card Queen;
    public static card King;
    public static card Ace;
    ArrayList<card> cards;
    ArrayList<card> pass;
    Scanner input;
    ArrayList<card> h;
    ArrayList<card> s;
    ArrayList<card> c;
    ArrayList<card> d;
    card two = new card(card.Suits.Clubs, card.Ranks.Two);
    

    public heartsCards(){
        h = new ArrayList<card>();
        s = new ArrayList<card>();
        c = new ArrayList<card>();
        d = new ArrayList<card>();
        cards = new ArrayList<card>();
        input  = new Scanner(System.in);
        Queen = new card(card.Suits.Spades, card.Ranks.Queen);
        King = new card(card.Suits.Spades, card.Ranks.King);
        Ace = new card(card.Suits.Spades, card.Ranks.Ace);
        pass = new ArrayList<card>();
    }

    public void showHand(){
        this.sort();
        System.out.println();
        System.out.println("Your Hand: ");
        int  i = 0;
        for(card temp:this.h){
            System.out.print(i+" "+temp.toString()+" ");
            i++;
        }
        System.out.println();
        for(card temp:this.d){
            System.out.print(i+" "+temp.toString()+" ");
            i++;
        }
        System.out.println();
        for(card temp:this.c){
            System.out.print(i+" "+temp.toString()+" ");
            i++;
        }
        System.out.println();
        for(card temp:this.s){
            System.out.print(i+" "+temp.toString()+" ");
            i++;
        }
        System.out.println();
        System.out.println();
    }

    public void sort(){
        this.d = new ArrayList<card>();
        this.c = new ArrayList<card>();
        this.s = new ArrayList<card>();
        this.h = new ArrayList<card>();
        for(int i=0; i<this.cards.size(); i++){
            card temp  = cards.get(i);
            if(temp.suit==card.Suits.Diamonds) d.add(temp);
            else if(temp.suit==card.Suits.Spades) s.add(temp);
            else if(temp.suit==card.Suits.Clubs) c.add(temp);
            else h.add(temp);
        }
        this.cards= new ArrayList<card>();
        h = sortSuits(h);
        d = sortSuits(d);
        s = sortSuits(s);
        c = sortSuits(c);
        addCards(h);
        addCards(d);
        addCards(c);
        addCards(s);
    }

    public void playerPass(){
        pass = new ArrayList<card>();
        System.out.println("Pick one card to pass by number (ex 3)");
        card temp = cards.get(input.nextInt());
        pass.add(temp);
        System.out.println("Pick another card to pass by number (ex 3)");
        temp = cards.get(input.nextInt());
        pass.add(temp);
        System.out.println("Pick a third card to pass by number (ex 3)");
        temp = cards.get(input.nextInt());
        pass.add(temp);
        for(int i=0; i<pass.size(); i++) cards.remove(pass.get(i));
        this.sort();
    }

    public void pass(){
        this.sort();
        pass = new ArrayList<card>();
        int i=0;
        if(d.size()<4){
            for(card temp: this.d){
                pass.add(temp);
                cards.remove(temp);
                i++;
            }
        }
        if(c.size()<4 && i==0){
            for(card temp: this.c){
                pass.add(temp);
                cards.remove(temp);
                i++;
            }
        }
        this.sort();
        while(i<3){
            card temp = getHighestPass();
            pass.add(temp);
            cards.remove(temp);
            this.sort();
            i++;
        }
   }

    public card getHighestPass(){
        card ans;
        if(this.contains(Queen)) ans =Queen;
        if(this.contains(Ace)) ans =  Ace;
        if(this.contains(King) ) ans = King;
        if(this.h.size()>0 && this.h.get(h.size()-1).rank.compareTo(card.Ranks.Five)>0){
            ans = this.h.get(h.size()-1);
        }
        else{
            cards = sortSuits(cards);
            ans = cards.get(cards.size()-1);
        }
        return ans;
    }
    
    public card getOffsuit(card.Suits suit, boolean q){
        if(this.contains(Queen, s)) return Queen;
        if(!q){
            if(this.contains(Ace, s)) return Ace;
            if(this.contains(King,s)) return King;
        }
        if(suit==card.Suits.Hearts){
            if(this.cards.size()>7){
                if(q && s.size()==1) return s.get(0);
                if(d.size()==1) return d.get(0);
                if(c.size()==1) return c.get(0);
            }
            return getHighestRank(card.Suits.Hearts);
        }
        else if(suit==card.Suits.Diamonds){
            if(this.cards.size()>7){
                if(q && s.size()==1) return s.get(0);
                if(h.size()==1) return h.get(0);
                if(c.size()==1) return c.get(0);
            }
            else{
                if(!h.isEmpty()) return h.get(h.size()-1);
            }
            return getHighestRank(card.Suits.Diamonds);
        }
        else if(suit==card.Suits.Clubs){
            if(this.cards.size()>7){
                if(q && s.size()==1) return s.get(0);
                if(d.size()==1) return d.get(0);
                if(h.size()==1) return h.get(0);
            }
            else{
                if(!h.isEmpty()) return h.get(h.size()-1);
            }
            return getHighestRank(card.Suits.Clubs);
        } 
        else{
            if(this.cards.size()>7){
                if(h.size()==1) return h.get(0);
                if(d.size()==1) return d.get(0);
                if(c.size()==1) return c.get(0);
            }
            else{
                if(!h.isEmpty()) return h.get(h.size()-1);
            }
            return getHighestRank(card.Suits.Spades);
        }
    }
    public card playPoints(){
        return null;
    }
    public card lowestRank(){
        ArrayList<card> temp = new ArrayList<card>();
        temp = sortSuits(this.cards);
        return temp.get(0);
    }

    public card highestRank(){
        ArrayList<card> temp = new ArrayList<card>();
        temp = sortSuits(this.cards);
        return temp.get(temp.size()-1);
    }

    public card getHighestRank(card.Suits suit){
        ArrayList<card> temp = new ArrayList<card>(this.cards);
        temp = sortSuits(temp);
        int size = temp.size()-1;
        card tempy = temp.get(size);
        size--;
        if(suit==card.Suits.Hearts){
            while(tempy.suit==card.Suits.Hearts){
                tempy = temp.get(size);
                size--;
            }
        }
        else if(suit==card.Suits.Diamonds){
            while(tempy.suit==card.Suits.Diamonds){
                tempy = temp.get(size);
                size--;
            }
        }
        else if(suit==card.Suits.Clubs){
            while(tempy.suit==card.Suits.Clubs){
                tempy = temp.get(size);
                size--;
            }
        }
        else{
            while(tempy.suit==card.Suits.Spades){
                tempy = temp.get(size);
                size--;
            }
        }
        return tempy;
    }

    private ArrayList<card> sortSuits(ArrayList<card> set){
        int n = set.size();
        for(int i=1; i<n; ++i){
            card test = set.get(i);
            int j = i-1;

            while(j>=0 && compareTo(set.get(j), test)>0){
                set.set(j+1, set.get(j));
                j = j-1;
            }
            set.set(j+1, test);
        }
        return set;
    }
    private void addCards(ArrayList<card> cards){
        for(int i=0; i<cards.size(); i++){
            this.cards.add(cards.get(i));
        }
    }
    public int compareTo(card one, card two){
        return one.rank.compareTo(two.rank);
    }

    public void passCards(heartsCards p2){
        p2.cards.add(this.pass.get(0));
        p2.cards.add(this.pass.get(1));
        p2.cards.add(this.pass.get(2));
        this.sort();
    }

    public card playerLeadCard(boolean broken){
        this.sort();
        System.out.println("It is now your lead, please select a card to lead by corresponsing value.....");
        System.out.println();
        this.showHand();
        card temp =  this.cards.get(input.nextInt());
        if(this.contains(two, c)){
            while(!temp.equals(two)){
                System.out.println("You must lead the two of clubs to start the game!");
                this.showHand();
                temp =  this.cards.get(input.nextInt());
            }
        }
        if(!broken){
            while(temp.suit==card.Suits.Hearts){
                System.out.println("Hearts has not been broken yet, please select a new card!");
                this.showHand();
                temp =  this.cards.get(input.nextInt());
            }
        }
        this.remove(temp);
        System.out.println("You lead: "+temp.toString());
        return temp;
    }
    public card playerPlayCard(card lead, boolean broken){
        this.sort();
        System.out.println("Select a card to play!");
        card.Suits follow = lead.suit;
        this.showHand();
        card temp =  this.cards.get(input.nextInt());
        if(temp.suit!=follow){ 
            if(getSuitedCardsSize(lead.suit)>0){
                while(temp.suit!=follow){
                    printSuit(lead.suit);
                    System.out.print("was led, you must follow suit!");
                    this.showHand();
                    temp = this.cards.get(input.nextInt());
                }
            }
        }
        this.remove(temp);
        System.out.println("You played: "+temp.toString());
        return temp;
    }
    private int getSuitedCardsSize(card.Suits suit){
        this.sort();
        if(suit==card.Suits.Hearts) return this.h.size();
        else if(suit==card.Suits.Diamonds) return this.d.size();
        else if(suit==card.Suits.Clubs) return this.c.size();
        else return this.s.size();
    }

    private boolean containSuits(card.Suits suit){
        if(suit==card.Suits.Hearts) return !this.h.isEmpty();
        else if(suit==card.Suits.Diamonds) return !this.d.isEmpty();
        else if(suit==card.Suits.Clubs) return !this.c.isEmpty();
        else return !this.s.isEmpty();
    }

    private void printSuit(card.Suits suit){
        if(suit==card.Suits.Hearts) System.out.print(" Hearts ");
        else if(suit==card.Suits.Diamonds) System.out.print(" Diamonds ");
        else if(suit==card.Suits.Clubs) System.out.print(" Clubs ");
        else System.out.print(" Spades ");
    }

    public card leadCard(boolean hearts, boolean q){
        this.sort();
        if(this.contains(two)){
            this.remove(two);
            return two;
        }
        card temp;
        if(!hearts && !q){
            if(!this.highSpades() && !this.s.isEmpty()){
                temp = s.get(s.size()-1);
            }
            else if(!c.isEmpty() || !d.isEmpty()){
                if(c.isEmpty()) temp = d.get(d.size()-1);
                else if(d.isEmpty()) temp = c.get(c.size()-1);
                else{
                    if(c.size()>d.size()){
                        temp = c.get(c.size()-1);
                    }
                    else temp = d.get(d.size()-1);
                }
            }
            else if(!s.isEmpty()) temp = s.get(s.size()-1);
            else temp = h.get(0);
        }
        else if(hearts && !q){
            if(!this.highSpades() && !this.s.isEmpty()){
                temp = s.get(s.size()-1);
            }
            else if(!c.isEmpty() || !h.isEmpty() || !d.isEmpty()){
                temp = lowestRank();
            }
            else temp = this.lowestSpade();
        }
        else{
            temp = lowestRank();
        }
        this.remove(temp);
        this.sort();
        return temp;
    }
    public card playCard(card lead, boolean hearts, boolean q){
        this.sort();
        card temp;
        card.Suits follow = lead.suit;
        if(this.containSuits(follow)){
            if(!hearts) temp = getHighest(follow);
            else temp = getLowest(follow);
        }
        else temp = getOffsuit(follow, q);
        this.remove(temp);
        return temp;
    }
    public card playCard(card lead, card next, boolean  hearts, boolean q){
        this.sort();
        card temp;
        card test = this.max(lead, next);
        card.Suits follow = lead.suit;
        if(this.containSuits(follow)){
            if(!hearts) temp = getHighest(follow);
            else temp = getLowest(follow, test);
        }
        else{
            temp = getOffsuit(follow, q);
        }
        this.remove(temp);
        return temp;
    }
    public card playCard(card lead, card next, card then, boolean hearts, boolean q){
        this.sort();
        card temp;
        card test = this.max(lead, next, then);
        card.Suits follow = lead.suit;
        if(this.containSuits(follow)){
            if(!hearts) temp = getHighest(follow);
            else if(points(lead, next, then)) temp = getLowestorHighest(follow, test);
            else temp = getHighest(follow);
        }
        else temp = getOffsuit(follow, q);
        this.remove(temp);
        return temp;
    }
    private boolean highSpades(){
        this.sort();
        return this.contains(Queen, s) || this.contains(King, s) || this.contains(Ace,s);
    }
    private boolean points(card one, card two, card three){
        if(one==Queen || two==Queen || three==Queen) return true;
        else if(one.suit==card.Suits.Hearts || two.suit==card.Suits.Hearts || three.suit==card.Suits.Hearts) return true;
        else return false;
    }
    private card getLowestorHighest(card.Suits suit, card test){
        card temp;
        ArrayList<card> choices = new ArrayList<card>();
        if(suit==card.Suits.Hearts){
            temp = h.get(0);
            if(h.size()==1) return temp;
            if(compareTo(temp, test)<0){
                for(int i=0; i<h.size(); i++){
                    temp = h.get(i);
                    if(compareTo(temp, test)<0) choices.add(temp);
                    else break;
                }
                return choices.get(choices.size()-1);
            }
            else return getHighest(suit);
        }
        else if(suit==card.Suits.Diamonds){
            temp = d.get(0);
            if(d.size()==1) return temp;
            if(compareTo(temp, test)<0){
                for(int i=0; i<d.size(); i++){
                    temp = d.get(i);
                    if(compareTo(temp, test)<0) choices.add(temp);
                    else break;
                }
                return choices.get(choices.size()-1);
            }
            else return getHighest(suit);
        } 
        else if(suit==card.Suits.Clubs){
            temp = c.get(0);
            if(c.size()==1) return temp;
            if(compareTo(temp, test)<0){
                for(int i=0; i<c.size(); i++){
                    temp = c.get(i);
                    if(compareTo(temp, test)<0) choices.add(temp);
                    else break;
                }
                return choices.get(choices.size()-1);
            }
            else return getHighest(suit);
        }
        else{
            temp = s.get(0);
            if(s.size()==1) return temp;
            if(compareTo(temp, test)<0){
                for(int i=0; i<s.size(); i++){
                    temp = s.get(i);
                    if(compareTo(temp, test)<0) choices.add(temp);
                    else break;
                }
                return choices.get(choices.size()-1);
            }
            else return getHighest(suit);
        }
    }

    private card max(card one, card two, card three){
        int oneTwo = compareTo(one, two);
        int twoThree = compareTo(two, three);
        int oneThree = compareTo(one, three);
        if(oneTwo>0 && oneThree>0) return one;
        else if(oneThree<0 && twoThree<0) return three;
        else return two;
    }

    private  card lowestSpade(){
        card temp;
        if(s.size()==1) return s.get(0);
        else{
            temp = s.get(0);
            if(temp.equals(Queen)) temp = s.get(1);
        }
        return temp;
    }
    private card getHighest(card.Suits suit){
        if(suit==card.Suits.Hearts) return this.h.get(h.size()-1);
        else if(suit==card.Suits.Diamonds) return this.d.get(d.size()-1);
        else if(suit==card.Suits.Clubs) return this.c.get(c.size()-1);
        else return this.s.get(s.size()-1);
    }
    private ArrayList<card> getSuitArray(card.Suits suit){
        if(suit==card.Suits.Hearts) return this.h;
        else if(suit==card.Suits.Diamonds) return this.d;
        else if(suit==card.Suits.Clubs) return this.c;
        else return this.s;
    }
    private card getLowest(card.Suits suit){
        if(suit==card.Suits.Hearts) return this.h.get(0);
        else if(suit==card.Suits.Diamonds) return this.d.get(0);
        else if(suit==card.Suits.Clubs) return this.c.get(0);
        else return this.s.get(0);
    }
    private card getLowest(card.Suits suit, card test){
        card temp;
        ArrayList<card> choices = new ArrayList<card>();
        if(suit==card.Suits.Hearts){
            temp = h.get(0);
            if(h.size()==1) return temp;
            if(compareTo(temp, test)<0){
                for(int i=0; i<h.size(); i++){
                    temp = h.get(i);
                    if(compareTo(temp, test)<0) choices.add(temp);
                    else break;
                }
                return choices.get(choices.size()-1);
            }
            else return temp;
        }
        else if(suit==card.Suits.Diamonds){
            temp = d.get(0);
            if(d.size()==1) return temp;
            if(compareTo(temp, test)<0){
                for(int i=0; i<d.size(); i++){
                    temp = d.get(i);
                    if(compareTo(temp, test)<0) choices.add(temp);
                    else break;
                }
                return choices.get(choices.size()-1);
            }
            else return temp;
        } 
        else if(suit==card.Suits.Clubs){
            temp = c.get(0);
            if(c.size()==1) return temp;
            if(compareTo(temp, test)<0){
                for(int i=0; i<c.size(); i++){
                    temp = c.get(i);
                    if(compareTo(temp, test)<0) choices.add(temp);
                    else break;
                }
                return choices.get(choices.size()-1);
            }
            else return temp;
        }
        else{
            temp = s.get(0);
            if(s.size()==1) return temp;
            if(compareTo(temp, test)<0){
                for(int i=0; i<s.size(); i++){
                    temp = s.get(i);
                    if(compareTo(temp, test)<0) choices.add(temp);
                    else break;
                }
                return choices.get(choices.size()-1);
            }
            else return temp;
        }
    }
    private card max(card one, card two){
        if(compareTo(one, two)>0) return one;
        else return two;
    }

    public boolean contains(card test){
        ArrayList<card> temp = getSuitArray(test.suit);
        for(card tempy:temp){
            if(tempy.equals(test)) return true;
        }
        return false;
    }
    public boolean contains(card test, ArrayList<card> set){
        for(card tempy:set){
            if(tempy.equals(test)) return true;
        }
        return false;
    }
    public void remove(card test){
        card r = null;
        for(card temp:this.cards){
            if(temp.equals(test)) r = temp;
        }
        if(r!=null) this.cards.remove(r);
    }
}