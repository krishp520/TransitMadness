import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransitMadnessTest {
    /**
     * Helper method to simulate input and capture output.
     */
    private String runProgram(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Run the main method
        TransitMadness.main(new String[]{});

        return out.toString().trim();
    }

    @Test
    public void testSampleInput1() {
        String input = "1\n2\n3\nRob\nMichael\ndepart\n";
        String expectedOutput = "Rob\nMichael";
        assertEquals(expectedOutput, runProgram(input));
    }

    @Test
    public void testSampleInput2() {
        String input = "2\n1\n1\n5\nMillie\nEthel\ndepart\nGertrude\ndepart\n";
        String expectedOutput = "Ethel\nMillie\nGertrude";
        assertEquals(expectedOutput, runProgram(input));
    }

    @Test
    public void testEmptyManifest() {
        String input = "2\n1\n2\n0\n"; // No manifest lines
        String expectedOutput = ""; // No output expected
        assertEquals(expectedOutput, runProgram(input));
    }

    @Test
    public void testNoBuses() {
        String input = "0\n0\n"; // No buses, no manifest
        String expectedOutput = ""; // No output expected
        assertEquals(expectedOutput, runProgram(input));
    }

    @Test
    public void testEdgeCaseLargeNumberOfPassengers() {
        StringBuilder inputBuilder = new StringBuilder("1\n2\n1001\n");
        for (int i = 0; i < 1000; i++) {
            inputBuilder.append("Passenger").append(i).append("\n");
        }
        inputBuilder.append("depart\n");
        StringBuilder expectedOutputBuilder = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            expectedOutputBuilder.append("Passenger").append(i).append("\n");
        }
        String input = inputBuilder.toString();
        String expectedOutput = expectedOutputBuilder.toString().trim();
        assertEquals(expectedOutput, runProgram(input));
    }

    @Test
    public void testEdgeCaseNoPassengersDepart() {
        String input = "2\n1\n2\n2\ndepart\ndepart\n";
        String expectedOutput = ""; // No passengers to unload
        assertEquals(expectedOutput, runProgram(input));
    }

    @Test
    public void testEdgeCaseLoopWithoutDepart() {
        String input = "2\n1\n2\n3\nAlice\nloop\nBob\n"; // Loop without a "depart"
        String expectedOutput = ""; // No buses depart
        assertEquals(expectedOutput, runProgram(input));
    }
}
