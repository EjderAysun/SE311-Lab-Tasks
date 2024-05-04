package LAB_7.LAB7_JAVA.Part1;

class Facade {

    public static void main(String[] args) {
        EntertainmentRoom er = new EntertainmentRoom();
        
        // Test Case 1: All parameter values are valid
        System.out.println("-------------");
        System.out.println("I want to watch a movie with Cinema mod on Netflix...");
        Boolean streamLine1 = er.streamLine("Cinema", "Netflix");
        if(streamLine1) System.out.println("Enjoy...");
        System.out.println("-------------");
        // Test Case 2: First parameter value is invalid, second parameter value is valid
        System.out.println("I want to watch a movie with Documentary mod on Netflix...");
        Boolean streamLine2 = er.streamLine("Documentary", "Netflix");
        if(streamLine2) System.out.println("Enjoy...");
        System.out.println("-------------");
        // Test Case 3: First parameter value is valid, second parameter value is invalid
        System.out.println("I want to watch a movie with Cinema mod on Apple TV...");
        Boolean streamLine3 = er.streamLine("Cinema", "Apple TV");
        if(streamLine3) System.out.println("Enjoy...");
        System.out.println("-------------");
        // Test Case 4: All parameter values are invalid
        System.out.println("I want to watch a movie with Documentary mod on Apple TV...");
        Boolean streamLine4 = er.streamLine("Documentary", "Apple TV");
        if(streamLine4) System.out.println("Enjoy...");
        System.out.println("-------------");
    }
}

class EntertainmentRoom {
    private TV led;
    private SoundBar lg;
    private StreamingDevice viewsonic;

    public EntertainmentRoom() {
        led = new TV();
        lg = new SoundBar();
        viewsonic = new StreamingDevice();
    }

    public Boolean streamLine(String mod, String app) {
        led.turnOn();
        lg.on();
        if(lg.changeMod(mod)) {
            viewsonic.startDevice();
            if(viewsonic.chooseApp(app)) return true;
        }
        return false;
    }
}

class TV{
    public void turnOn(){System.out.println("TV turned on...");}
}

class SoundBar{
    public void on(){System.out.println("The SoundBar is on now...");}
    
    public boolean changeMod(String mod){
        if(mod.equals("Cinema") || mod.equals("Tv Show")) {
            System.out.println("The SoundBar mod changed to " + mod);
            return true;
        } else {
            System.out.println("Wrong mod...");
            return false;
        }
    }
}

class StreamingDevice{
    public void startDevice(){System.out.println("Device is ready to stream...");}
    
    public Boolean chooseApp(String app){
        if(app.equals("Netflix") || app.equals("Exxen") || app.equals("Amazon Prime")) {
            System.out.println("Streaming from " + app);
            return true;
        } else {
            System.out.println("Wrong app name...");
            return false;
        }
    }
}
