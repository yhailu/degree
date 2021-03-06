package slpinterpreter.test;

import org.junit.Test;
import slpinterpreter.Interpreter;
import slpinterpreter.MaxArgs;
import slpinterpreter.grammar.Stm;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Marshall Bowers
 */
public class BowersProg5Test {

    static Stm probToRun = TestPrograms.bowersProg5;

    @Test
    public void evaluatesMaxArgs() {
        try {
            int maxargs = MaxArgs.maxargs(probToRun);

            assertEquals(2, maxargs);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void evaluatesInterpreter() {
        // Prepare to capture System.out
        PrintStream originalOut = System.out;
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        // Perform tests
        try {
            String separator = System.getProperty("line.separator");

            Interpreter.interp(probToRun);

            assertEquals("42 42" + separator + "21 42" + separator, os.toString());
        } catch (Exception e) {
            fail(e.toString());
        }

        // Restore normal System.out operation
        System.setOut(originalOut);
    }

}
