/*
Software code in this file is (c) The College Entrance Examination Board and the
Advanced Placement (r) Program and is used with permission.
*/

/**
 *  The <code>SimpleMBSDemo2</code> class provides a main method that creates
 *  a simulation of a number of fish swimming in a bounded environment.
 *  It also creates a simple window in which to view the environment
 *  after each timestep in the simulation.  This version of the MBS demo uses
 *  an object of the <code>Simulation</code> class.
 **/

public class SimpleMBSDemo2
{
    // Specify number of rows and columns in environment.
    private static final int ENV_ROWS = 10;      // rows in environment
    private static final int ENV_COLS = 10;      // columns in environment

    // Specify how many timesteps to run the simulation.
    private static final int NUM_STEPS = 15;     // number of timesteps

    // Specify the time delay for each step
    private static final int DELAY = 1000;        // delay in milliseconds

    /** Start the Marine Biology Simulation program.
     *  The String arguments (args) are not used in this application.
     **/
    public static void main(String[] args)
    {
        // Construct an empty environment and several fish in the context
        // of that environment.
        BoundedEnv env = new BoundedEnv(ENV_ROWS, ENV_COLS);
        Fish f1 = new Fish(env, new Location(2, 2));
        Fish f2 = new Fish(env, new Location(2, 3));
		//DarterFish f3 = new DarterFish(env, new Location(5, 8), Direction.EAST, new Color(0,0,0));

        // Construct an object that knows how to draw the environment with
        // a delay.
        SimpleMBSDisplay display = new SimpleMBSDisplay(env, DELAY);

        // Construct the simulation object.  It needs to have the environment
        // and the object that can draw the environment.
        Simulation sim = new Simulation(env, display);

        // Run the simulation for the specified number of steps.
        for ( int i = 0; i < NUM_STEPS; i++ )
        {
            sim.step();
        }
    }
}

