package LAB_3.LAB3_JAVA;

import java.util.ArrayList;
import java.util.Scanner;
//
//Iterator pattern:
//
//Provide a way to access the elements of an aggregate object
//sequentially without exposing its underlying representation.
//
//The classes and/or objects participating in this pattern are:

//1. Iterator  (AbstractIterator)
//		defines an interface for accessing and traversing elements.
//2. ConcreteIterator  (Iterator)
//		implements the Iterator interface.
//		keeps track of the current position in the traversal of the aggregate.
//3. Aggregate  (AbstractCollection)
//		defines an interface for creating an Iterator object
//4. ConcreteAggregate  (Collection)
//		implements the Iterator creation interface to return an instance of the proper ConcreteIterator
//

// Task 2 starts.
public class IteratorPattern {	
	static void printAggregate(AbstractIterator i) {
		System.out.println("Iterating over collection:");
		for (i.First(); !i.IsDone(); i.Next()) {
			System.out.println(i.CurrentChannel().toString());
		}
		System.out.println();
	}
	// Task 6 starts.
	public static void main(String[] args) {
		AbstractAggregate aggregate = new Collection();
		aggregate.add(new Channel("Das Erste", "Germany", 10));
		aggregate.add(new Channel("CCTV-1", "China", 657));
		aggregate.add(new Channel("NOW", "Türkiye", 555));
		aggregate.add(new Channel("Show Tv", "Türkiye", 0));
		aggregate.add(new Channel("TVNZ-1", "New Zealand", 999));
		aggregate.add(new Channel("CNC World", "China", 789));
		aggregate.add(new Channel("TRT-1", "Türkiye", 676));
		aggregate.add(new Channel("ZDF", "Germany", 155));
		aggregate.add(new Channel("Mehwar TV", "Egypt", 56));

		AbstractIterator iterator = aggregate.CreateCollectionIterator();
		printAggregate(iterator);

		AbstractIterator tr_iterator = aggregate.CreateTurkiyeIterator();
		printAggregate(tr_iterator);

		AbstractIterator freq_iterator = aggregate.CreateFrequencyIterator();
		printAggregate(freq_iterator);
	}
	// Task 6 ends.
}

// Task 1 starts.
class Channel {
	private String _name;
	private String _countryOrigin;
	private int _frequency;

	public Channel(String name, String countryOrigin, int frequency) {
		_name = name;
		_countryOrigin = countryOrigin;
		_frequency = frequency;
	}

	public String getName() { return _name; }
	public String getCountryOrigin() { return _countryOrigin; }
	public int getFreq() { return _frequency; }

	public String toString() { 
        return _name + "\t" + _countryOrigin + "\t" + _frequency;
    } 
}
// Task 1 ends.

interface AbstractAggregate {
	public AbstractIterator CreateCollectionIterator();
	public TurkiyeIterator CreateTurkiyeIterator();
	public FrequencyIterator CreateFrequencyIterator();
	public void add(Channel ch);
	public int getCount();
	public Channel get(int idx);
}

class Collection implements AbstractAggregate{
	private ArrayList<Channel> _channels = new ArrayList<Channel>();
	public CollectionIterator CreateCollectionIterator() {
		return new CollectionIterator(this);
	}
	// Task 5 starts.
	public TurkiyeIterator CreateTurkiyeIterator() {
		return new TurkiyeIterator(this);
	}
	public FrequencyIterator CreateFrequencyIterator() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter lower bound for frequency iterator: ");
		int lowerBound = sc.nextInt();
		System.out.println("Please enter upper bound for frequency iterator: ");
		int upperBound = sc.nextInt();
		sc.close();
		return new FrequencyIterator(this, lowerBound, upperBound);
	}
	// Task 5 ends.
	public void add(Channel ch) {_channels.add(ch);}
	public int getCount() {return _channels.size();}
	public Channel get(int index) {return _channels.get(index);}
}

interface AbstractIterator {
	void First();
	void Next();
	Boolean IsDone();
	Channel CurrentChannel();
}

class CollectionIterator implements AbstractIterator {
	private int _current;
	private Collection _collection;

	public CollectionIterator(Collection collection) {
		_collection = collection;
		_current = 0;
	}

	public void First() {_current = 0;} 
	public void Next() {_current++;}
	public Boolean IsDone() {return (_current >= _collection.getCount());}
	public Channel CurrentChannel() {return IsDone()?null:_collection.get(_current);}
}
// Task 2 ends.

// Task 3 starts.
class TurkiyeIterator implements AbstractIterator {
	private int _current;
	private Collection _collection;

	public TurkiyeIterator(Collection collection) {
		_collection = collection;
		_current = 0;
	}

	public void First() {
		while(true) {
			Channel currCh = CurrentChannel();
			if(currCh == null) break;
			else if (currCh.getCountryOrigin().equals("Türkiye")) break;
			else _current++;
		}
	}
	public void Next() {
		_current++;
		while (true) {
			Channel currCh = CurrentChannel();
			if(currCh == null) break;
			else if (currCh.getCountryOrigin().equals("Türkiye")) break;
			else _current++;
		}
	}
	public Boolean IsDone() {return (_current >= _collection.getCount());}
	public Channel CurrentChannel() {return IsDone()?null:_collection.get(_current);}
}

// Task 4 starts.
class FrequencyIterator implements AbstractIterator{
	private int _current;
	private Collection _collection;
	private int _upperBound;
	private int _lowerBound;

	public FrequencyIterator(Collection collection, int lowerBound, int upperBound) {
		_collection = collection;
		_lowerBound = lowerBound;
		_upperBound = upperBound;
		_current = 0;
	}

	public void First() {
		while (true) {
			Channel currCh = CurrentChannel();
			if(currCh == null) break;
			int freq = currCh.getFreq();
			if (freq >= _lowerBound && freq <= _upperBound) break;
			else _current++;
		}
	}
	public void Next() {
		_current++;
		while (true) {
			Channel currCh = CurrentChannel();
			if(currCh == null) break;
			int freq = currCh.getFreq();
			if (freq >= _lowerBound && freq <= _upperBound) break;
			else _current++;
		}
	}
	public Boolean IsDone() {return (_current >= _collection.getCount());}
	public Channel CurrentChannel() {return IsDone()?null:_collection.get(_current);}
}
// Task 4 ends.