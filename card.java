public class card {
    
    public static enum Suits {Spades, Clubs, Diamonds, Hearts}
	public static enum Ranks {Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace}
    
    Suits suit;
    Ranks rank;

    public card(Suits s, Ranks r){
        suit =s;
        rank =r;
    }

    public String toString()
	{
		return (rank + "-of-" + suit);
	}

    public int countPoints(card oneCard){
        if(oneCard.suit==Suits.Hearts) return 1;
        else if(oneCard.suit==Suits.Spades && oneCard.rank==Ranks.Queen) return 13;
        else return 0;
    }

    public boolean equals(card one){
        if(one.suit==this.suit && one.rank==this.rank) return true;
        else return false;
    }

    public int compareTo(card one, card two){
        return one.rank.compareTo(two.rank);
    }
}
