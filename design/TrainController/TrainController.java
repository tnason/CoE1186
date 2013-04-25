/*
 * Author(s): Ben Kisley
 * Updated: 04 - 25- 2013
 * Purpose: This class is the train controller for a specific train.
 */

package TLTTC;

import java.util.*;
// lib/freetts.jar needs to be added to classpath during compilation
/*import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.JavaClipAudioPlayer;*/

public class TrainController
{
    private int trainID;
    private TrainModel tm;
    private TrainContainer cont;
    private TrainControllerGUI gui;

    private boolean underground = false; // Underground status of block
    private boolean inStation = false; // Station status of block
    private String oldNextStation = ""; // Next station one block ago
    private String nextStation = ""; // Next station
    private boolean daytime = false; // True = day, False = night
    private boolean doorsOpen = false; // Doors status
    private boolean lightsOn = false; // Lights status
    private boolean engineFail = false; // Engine failure status
    private boolean signalPickupFail = false; // Signal pickup failure status
    private boolean brakeFail = false; // Brake failure status
    private boolean gpsConnected = false; // GPS connection status

    private final double KP = 80000; // Proportional gain
    private double ek = 0; // Proportional error
    private final double T = 0.1; // Sample period of train model (0.1 seconds)
    private final double KI = 300; // Integral gain
    private double uk = 0; // Integral error
    private double power = 0; // Power of train
    private final double TRAIN_MAX_POWER = 120000.0; // Maximum power of train (120 kW)

    private double trainOperatorVelocity = 0; // Velocity sent from train operator
    private double ctcOperatorVelocity = 0; // Velocity sent from CTC operator
    private double velocitySetpoint = 0; // Velocity setpoint
    private double velocity = 0; // Current velocity of train
    private double trackLimit = 0; // Track's speed limit
    private final double TRAIN_LIMIT = 19.4444; // Train's speed limit (70 km/hr = 19.44 m/s)

    private double fixedBlockAuth = 0; // Fixed block authority
    private double ctcFixedBlockAuth = 0; // Fixed block authority sent from CTC operator
    private double movingBlockAuth = 0; // Moving block authority sent from MBO
    private double ctcMovingBlockAuth = 0; // Moving block authority sent from CTC operator
    private double authority = 0; // Safest authority

    public TrainController(int id, TrainModel t, TrainContainer c, TrainControllerGUI g)
    {
        trainID = id; // Sets train ID
        tm = t; // Associates train model with train controller
        cont = c; // Associates train container with train controller
        gui = g;
        gpsConnected = true; // Connects to GPS

        // Test variables -- Remove later
        velocity = 0;
        trainOperatorVelocity = 15;
        ctcOperatorVelocity = 6;
        trackLimit = 15;

        fixedBlockAuth = 1400;
        ctcFixedBlockAuth = 1400;
        ctcMovingBlockAuth = 1400;
        movingBlockAuth = 1400;
    }

    public void setPower() // this method is called whenever an authority, speed limit, or speed setpoint is received
    {
        boolean[] flags = tm.getFailureFlags();
        engineFail = flags[0];
        signalPickupFail = flags[1];
        brakeFail = flags[2];
        velocity = tm.getVelocity(); // Gets current velocity of train

        if (engineFail || signalPickupFail || brakeFail) // If train failure, stop train
        {
            tm.setPower(0.0);
            gui.addRow(getDate(), velocity, 0.0, 0.0);
        }
        else
        {   
            double power2 = setPower2(power, uk, ek, velocity, fixedBlockAuth, ctcFixedBlockAuth, movingBlockAuth, ctcMovingBlockAuth, trainOperatorVelocity, ctcOperatorVelocity, trackLimit);
            // ^ Calculates power of train in another way (redundant/safety-critical method)
            authority = Math.min(Math.min(fixedBlockAuth, ctcFixedBlockAuth), Math.min(movingBlockAuth, ctcMovingBlockAuth)); // Selects safest authority
            if (authority <= 0)
            {
                authority = 0.1;
            }
            // Max train deceleration = -1.2098 m/s^2
            // Using vf^2 = vi^2 + 2ad = 0 (final speed cannot be > 0), vi = sqrt(-2ad) = (2*1.2098*authority)
            double authorityVelocityLimit = Math.sqrt(2.4196 * authority);

            velocitySetpoint = Math.max(trainOperatorVelocity, ctcOperatorVelocity); // Selects faster of two velocities.

            if (velocitySetpoint > Math.min(Math.min(trackLimit, TRAIN_LIMIT), authorityVelocityLimit)) // If the operator sends a dangerous velocity,
            {
                velocitySetpoint = Math.min(Math.min(trackLimit, TRAIN_LIMIT), authorityVelocityLimit); // set to next highest allowable velocity
            }

            if (power < TRAIN_MAX_POWER) // If power command is allowable, add integral gain to current integral gain
            {
                uk = uk + (T / 2.0) * (ek + (velocitySetpoint - velocity));
            }

            ek = velocitySetpoint - velocity; // Calculates proportional gain
            power = ((KP * ek) + (KI * uk)); // Calculates power
            if (Math.abs(power) < Math.abs(power2) * 0.8 || Math.abs(power) > Math.abs(power2) * 1.2) // If redundant power does not agree with this power by +/-20%, stop train
            {
                power = 0.0;
            }
            gui.addRow(getDate(), velocity, velocitySetpoint, power);
            tm.setPower(power); // Sets power of train
        }
    }

    private double setPower2(double pow, double uK, double eK, double vel, double fixedBlock, double ctcFixedBlock, double movingBlock, double ctcMovingBlock, double trainOpVel, double ctcOpVel, double trackLim)
    { // Redundant method used because train controller is safety-critical
        double auth = Math.min(Math.min(movingBlock, Math.min(ctcFixedBlock, ctcMovingBlock)), fixedBlock);
        if (auth <= 0)
        {
            auth = 0.1;
        }
        double authLim = Math.sqrt(auth * 2.4196);
        double velSetpoint = Math.max(ctcOpVel, trainOpVel);
        if (velSetpoint > Math.min(TRAIN_LIMIT, Math.min(authLim, trackLim)))
        {
            velSetpoint = Math.min(TRAIN_LIMIT, Math.min(authLim, trackLim));
        }

        if (pow < TRAIN_MAX_POWER)
        {
            uK = (T * ((-vel + eK + velSetpoint)/2.0)) + uK;
        }
        eK = -vel + velSetpoint;
        pow = ((uK * KI) + (eK * KP));
        return pow;
    }

    public void setDoors(boolean automatic) // This method is called every time the train enters a new block or manually
    {
        velocity = tm.getVelocity(); // Current train velocity
        doorsOpen = tm.getDoors(); // Current train door status

        if (velocity == 0 && inStation && !doorsOpen && automatic) // If train is stopped and doors are closed in a station, open the doors
        {
            tm.setDoors(true);
        }
        else if (velocity != 0 && doorsOpen && automatic) // If train is moving and doors are open, close the doors
        {
            tm.setDoors(false);
        }
        else if (velocity == 0 && !automatic && !doorsOpen) // If stopped anywhere, allow operator to open doors
        {
            tm.setDoors(true);
        }
        else if (doorsOpen && !automatic) // If doors are open, allow operator to close doors
        {
            tm.setDoors(false);
        }
    }

    @SuppressWarnings("deprecation")
    public void setLights(boolean automatic) // This method is called every time the train enters a new block or manually
    {
        int time = getDate().getHours();
        if (time >= 9 && time < 16)
        {
            daytime = true;
        }
        else
        {
            daytime = false;
        }
        lightsOn = tm.getLights(); // Current light status

        if (!daytime || underground && !lightsOn && automatic) // If nighttime or underground and lights are off, turn them on
        {
            tm.setLights(true);
        }
        else if (daytime && !underground && lightsOn && automatic) // If daytime and aboveground and lights are on, turn them off
        {
            tm.setLights(false);
        }
        else if (!automatic && !lightsOn) // If lights are off, allow operator to turn them on
        {
            tm.setLights(true);
        }
    }

    public void announceStation(boolean automatic) // this method is called every time the train enters a new block or manually
    {
        /*if (automatic && !oldNextStation.equals(nextStation)) // If a new station name is processed, automatically announce it
        {
            oldNextStation = nextStation;
            /*
            VoiceManager voiceManager = VoiceManager.getInstance();
            Voice speaker = voiceManager.getVoice("kevin16");
            speaker.allocate();
            speaker.speak("Next stop " + nextStation + " on " + trainID);
            speaker.deallocate();*/
           // System.out.println("Next station on train " + trainID + " = " + nextStation);
        //}
        if (!automatic) // If button is pressed on GUI, announce station name
        {
            /*VoiceManager voiceManager = VoiceManager.getInstance();
            Voice speaker = voiceManager.getVoice("kevin16");
            speaker.allocate();
            speaker.speak("Next stop " + nextStation + " on " + trainID);
            speaker.deallocate();*/
            System.out.println("Next station on train " + trainID + " = " + nextStation);
        }
    }

    public void setMovingBlockAuth(double m)
    {
        movingBlockAuth = m;
        setPower();
    }

    public void setCtcMovingBlockAuth(double m)
    {
        ctcMovingBlockAuth = m;
        setPower();
    }

    public void setFixedBlockAuth(double f)
    {
        fixedBlockAuth = f;
        setPower();
    }

    public void setCtcFixedBlockAuth(double f)
    {
        ctcFixedBlockAuth = f;
        setPower();
    }

    public void setCtcOperatorVelocity(double v)
    {
        ctcOperatorVelocity = v;
        setPower();
    }

    public void setTrainOperatorVelocity(double v)
    {
        trainOperatorVelocity = v;
        setPower();
    }

    public void setTrackLimit(double v)
    {
        trackLimit = v;
        setPower();
    }

    public void setUnderground(boolean u)
    {
        underground = u;
        setLights(true);
    }

    public void setInStation(boolean i)
    {
        inStation = i;
        setDoors(true);
    }

    public void setNextStation(String s)
    {
        oldNextStation = nextStation;
        nextStation = s;
        announceStation(true);
    }

    //For stopping before a station....
    public double getStoppingDistance()
    {
        return (.5* velocity * velocity) / (1.2); // returns stopping distance in meters  
    } 

    public void setGpsConnected(boolean s)
    {
        gpsConnected = s;
    }

    public double getAuthority()
    {
        return authority;
    }

    public double getVelocity()
    {
        return velocity;
    }

    public double getVelocitySetpoint()
    {
        return velocitySetpoint;
    }

    public double getPower()
    {
        return power;
    }

    public boolean getEngineFail()
    {
        return engineFail;  
    }

    public boolean getSignalPickupFail()
    {
        return signalPickupFail;
    }

    public boolean getBrakeFail()
    {
        return brakeFail;
    }

    public String getNextStation()
    {
        return nextStation;
    }

    public boolean getGpsConnected()
    {
        return gpsConnected;
    }

    public TrainModel getTrain()
    {
        return tm;
    }

    public Date getDate()
    {
        return cont.getSimulationTimeDate();
    }
}
