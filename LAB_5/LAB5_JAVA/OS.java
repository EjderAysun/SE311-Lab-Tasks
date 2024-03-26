package LAB_5.LAB5_JAVA;

import java.util.ArrayList;

//
//The classes and/or objects participating in this pattern are:
//
//1. Command  (Command)
//		- declares an interface for executing an operation.
//2. ConcreteCommand  (CalculatorCommand)
//		- defines a binding between a Receiver object and an action.
//		- implements Execute by invoking the corresponding operation(s) on Receiver
//3. Client  (Calculator Application)
//		- creates a ConcreteCommand object and sets its receiver.
//4. Invoker  (User)
//		- asks the command to carry out the request
//5. Receiver  (Calculator)
//		- knows how to perform the operations associated with carrying out
//		  a request. Any class may serve as a Receiver.
//
//

public class OS {
	public static void main(String[] args) {

		// Create cpu and let it do the task
		ConcreteTask task = null;
		TaskScheduler taskScheduler = new TaskScheduler();
		CPU cpu = new CPU();

		task = new Task(cpu, "red");
		taskScheduler.run(task);
		task = new Task(cpu, "blue");
		taskScheduler.run(task);
		task = new Task(cpu, "green");
		taskScheduler.run(task);
		task = new Task(cpu, "yellow");
		taskScheduler.run(task);

		// Undo 4 commands
		taskScheduler.Undo();
		taskScheduler.Undo();
		taskScheduler.Undo();
		taskScheduler.Undo();

		// Redo 2 commands
		taskScheduler.Redo();
		taskScheduler.Redo();
	}
}

//"Command"
interface ConcreteTask {
	public void Execute();
}

//"ConcreteCommand"
class Task implements ConcreteTask {

	private CPU _cpu;
	private String _color;

	public Task(CPU cpu, String color) {
		_cpu = cpu;
		_color = color;
	}

	public void Execute() {
		_cpu.Action(_color);
	}
}

// "Receiver"
class CPU {

	private String screen_color;

	public CPU() {
		screen_color = null; 
	}

	private void makeRed() { screen_color = "RED"; }
	private void makeBlue() { screen_color = "BLUE"; }
	private void makeGreen() { screen_color = "GREEN"; }
	private void makeYellow() { screen_color = "YELLOW"; }

	public void Action(String color) {
		switch (color) {
		case "red": makeRed(); break;
		case "blue": makeBlue(); break;
		case "green": makeGreen(); break;
		case "yellow": makeYellow(); break;
		}
		System.out.println("The screen now shows " + screen_color);
	}
}

// "Invoker"
class TaskScheduler {

	private int currentIndex;
	private ArrayList<ConcreteTask> _tasks = new ArrayList<ConcreteTask>();
	
	public TaskScheduler() {currentIndex = -1;}

	public void Redo() {
		// Perform redo operations
		if (currentIndex < _tasks.size()-1) {
			ConcreteTask task = _tasks.get(++currentIndex);
			task.Execute();
		}
	}

	public void Undo() {
		// Perform undo operations
		if(currentIndex > 0) {
			ConcreteTask task = _tasks.get(--currentIndex);
			task.Execute();
		}
	}

	void run(ConcreteTask task) {
		task.Execute();
		// Add command to undo-redo list
		_tasks.add(task);
		currentIndex++;
	}
}