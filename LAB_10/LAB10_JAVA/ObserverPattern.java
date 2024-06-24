package LAB_10.LAB10_JAVA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 'Subject' ==> SchoolBell
abstract class SchoolBell {

	public SchoolBell(String symbol, int time) {
		_symbol = symbol;
		_time = time;
		_oldTime = time;
	}

	// Register the Observers
	public void Attach (Classroom classroom) {
		classrooms.add(classroom);
	}

	// Unregister from the list of Observers.
	public void Detach (Classroom classroom) {
		for (int i = 0; i < classrooms.size(); i++) {
			if (classrooms.get(i).getName() == classroom.getName()) {
				classrooms.remove(i);
				return;
			}
		}
	}

	// Notify the Observers.
	public void Notify() {
		// set argument to something that helps tell the Observers what happened
		for (int i = 0; i < classrooms.size(); i++) {
			classrooms.get(i).Update(this);
		}
	}

	public String getSymbol() {return _symbol;}
	void setSymbol(String time) {_symbol = time;}
	public int getTime() {return 0;}
	abstract public void setTime(int time);
	protected String _symbol; // Internal Subject state
	protected int _time; // Internal Subject state
	protected int _oldTime; // Internal Subject state
	protected ArrayList<Classroom> classrooms = new ArrayList<Classroom>();
}

// 'ConcreteSubject' ==> Bell20
class Bell1 extends SchoolBell {

	// Constructor
	public Bell1(String symbol, int time) {
		super(symbol, time);
	}
	public int getTime() {return _time;}
	public void setTime(int time) {
		// Whenever a change happens to _time, notify observers.
		_time = time;
		if((time == 20 || time == 50) && _oldTime != time) {
			_oldTime = _time;
			Notify();
		}
	}
}

//'ConcreteSubject' ==> Bell20
class Bell2 extends SchoolBell {
	// Constructor
	public Bell2(String symbol, int time) {
		super(symbol, time);
	}
	public int getTime() {return _time;}
	public void setTime(int time) {
		// Whenever a change happens to _time, notify observers.
		_time = time;
		if((time == 20 || time == 50) && _oldTime != time) {
			_oldTime = _time;
			Notify();
		}
	}
}

//'Observer'  ==> Abstract Observer.
interface Observer {
	public void Update(SchoolBell schoolBell);
}

//'ConcreteObserver' ==> Classroom
class Classroom implements Observer {
	private SchoolBell _schoolBell;
	private String _classroom_name;
	private String _school_bell_name; // Internal Observer state
	private double _school_bell_time; // Internal Observer state

	// Constructor
	public Classroom(String classroomName) {
		_classroom_name = classroomName;
	}
	public void Update(SchoolBell schoolBell) {
        _schoolBell = schoolBell; // Reference to Subject
        _school_bell_time = schoolBell.getTime();
        _school_bell_name = schoolBell.getSymbol();
        System.out.println("Notified " + _classroom_name + " of " + _school_bell_name +
                "'s " + "change to " + _school_bell_time);
        Counter.increaseCounter();
        System.out.println("Update counter: " + Counter.returnUpdateCounter());
	}
	public SchoolBell getSchoolBell() { return _schoolBell; }
	public void setSchoolBell(SchoolBell schoolBell) { _schoolBell = schoolBell; }
	public String getName() { return _classroom_name; }
}

class Counter {
    private static int updateCounter;

    public static void increaseCounter() {
        updateCounter++;
    }

    public static int returnUpdateCounter() { return updateCounter; }
}

// interface Mediator {
// 	public void Register(SchoolBell schoolBell, Observer observer);
// 	public void Unregister(SchoolBell schoolBell, Observer observer);
// 	public void Notify();
// }

// class Principal implements Mediator {
// 	private Map<SchoolBell, ArrayList<Classroom>> bellClassroomMap;

// 	public void Register(SchoolBell schoolBell, Classroom classroom) {
// 		if(bellClassroomMap.putIfAbsent(schoolBell, new ArrayList<Classroom>(List.of(classroom))) == null) {
// 			bellClassroomMap.put(schoolBell, new ArrayList<Classroom>(List.of(classroom)));
// 		}
// 		schoolBell.Attach(classroom);
// 	}

// 	public void Unregister(SchoolBell schoolBell, Classroom classroom) {
// 		if(bellClassroomMap.containsKey(schoolBell)) {
// 			schoolBell.Detach(classroom);
// 			bellClassroomMap.get(schoolBell).remove(classroom);
// 			schoolBell.Detach(classroom);
// 		}
// 	}

// 	public void Notify() {
		
// }
// }

// MainApp test application
public class ObserverPattern {
	public static void main(String[] args) {
		// Create investors
	    Classroom m209 = new Classroom("M 209");
	    Classroom c304 = new Classroom("C 304");

	    // Create IBM stock and attach investors
	    Bell1 bell1 = new Bell1("School Bell 1", 20);
	    Bell2 bell2 = new Bell2("School Bell 2", 50);
	    m209.setSchoolBell(bell1);   
	    c304.setSchoolBell(bell2);	    
	    bell1.Attach(m209);
	    bell2.Attach(c304);

	    // Change price, which notifies investors
	    bell2.setTime(20);
	    bell2.setTime(50);
	    bell2.setTime(50);
	    bell2.setTime(30);
	    bell2.setTime(50);

		System.out.println("-------------------");

	    System.out.println("Removing C 304 from Notification list");
	    bell1.Detach(c304);
	    bell1.setTime(20);
        bell1.setTime(40);
        bell1.setTime(50);
		bell1.setTime(30);
	    bell1.setTime(50);
	    bell1 = null; // Candidate for Garbage Collection  // DANGER !!!!!!!     
	    // We have a dangling reference in our Observer. Remember our
	    // "implementation issues" discussion in the lecture.
	    System.out.println(m209.getSchoolBell()); // Reference has a value. 
	}
}