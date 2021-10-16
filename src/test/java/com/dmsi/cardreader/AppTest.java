package com.dmsi.cardreader;

import org.junit.Test;

import java.util.Arrays;

import static com.dmsi.cardreader.App.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void shouldReturnArrayOfSizeTwo() {
        String[][] missingLogs = App.getMissingLogs(new Log[0]);

        assertEquals("Incorrect number of Arrays.  Should be a tuple.", 2, missingLogs.length);
    }

    @Test
    public void shouldReturnMissingExits() {
        Log[] logs = new Log[]{
                new Log("Paul", Scan.ENTER),
                new Log("Mary", Scan.ENTER),
                new Log("Mary", Scan.EXIT),
                new Log("Paul", Scan.ENTER),
                new Log("Paul", Scan.EXIT),
        };

        String[] expectedMissingEntries = new String[]{};
        String[] expectedMissingExits = new String[]{"Paul"};
        String[][] expected = new String[][]{expectedMissingEntries, expectedMissingExits};

        String[][] actual = getMissingLogs(logs);
        Arrays.sort(actual[0]);
        Arrays.sort(actual[1]);

        assertArrayEquals("Missing entry logs did not match.", expected[0], actual[0]);
        assertArrayEquals("Missing exit logs did not match.", expected[1], actual[1]);
    }

    @Test
    public void shouldReturnMissingEntries() {
        Log[] logs = new Log[]{
                new Log("Paul", Scan.EXIT),
                new Log("Mary", Scan.ENTER),
                new Log("Mary", Scan.EXIT),
                new Log("Gregory", Scan.EXIT),
                new Log("Mary", Scan.ENTER),
                new Log("Mary", Scan.EXIT),
                new Log("Gregory", Scan.ENTER),
                new Log("Gregory", Scan.EXIT),
        };

        String[] expectedMissingEntries = new String[]{"Gregory", "Paul"};
        String[] expectedMissingExits = new String[]{};
        String[][] expected = new String[][]{expectedMissingEntries, expectedMissingExits};

        String[][] actual = getMissingLogs(logs);
        Arrays.sort(actual[0]);
        Arrays.sort(actual[1]);

        assertArrayEquals("Missing entry logs did not match.", expected[0], actual[0]);
        assertArrayEquals("Missing exit logs did not match.", expected[1], actual[1]);
    }

    @Test
    public void shouldReturnMissingEntriesAndExits() {
        Log[] logs = new Log[]{
                new Log("Paul", Scan.EXIT),
                new Log("Mary", Scan.ENTER),
                new Log("Mary", Scan.EXIT),
                new Log("Ignatius", Scan.EXIT),
                new Log("Benedict", Scan.ENTER),
                new Log("Benedict", Scan.ENTER),
                new Log("Benedict", Scan.EXIT),
                new Log("Mary", Scan.ENTER),
                new Log("Mary", Scan.EXIT),
                new Log("Ignatius", Scan.ENTER),
        };
        String[] expectedMissingEntries = new String[]{"Ignatius", "Paul"};
        String[] expectedMissingExits = new String[]{"Benedict", "Ignatius"};
        String[][] expected = new String[][]{expectedMissingEntries, expectedMissingExits};

        String[][] actual = getMissingLogs(logs);
        Arrays.sort(actual[0]);
        Arrays.sort(actual[1]);

        assertArrayEquals("Missing entry logs did not match.", expected[0], actual[0]);
        assertArrayEquals("Missing exit logs did not match.", expected[1], actual[1]);
    }

    @Test
    public void shouldReturnSingleExitPerEmployee() {
        Log[] logs = new Log[]{
                new Log("Paul", Scan.ENTER),
                new Log("Paul", Scan.ENTER),
                new Log("Paul", Scan.EXIT),
                new Log("Paul", Scan.ENTER),
        };
        String[] expectedMissingEntries = new String[]{};
        String[] expectedMissingExits = new String[]{"Paul"};
        String[][] expected = new String[][]{expectedMissingEntries, expectedMissingExits};

        String[][] actual = getMissingLogs(logs);
        Arrays.sort(actual[0]);
        Arrays.sort(actual[1]);

        assertArrayEquals("Missing entry logs did not match.", expected[0], actual[0]);
        assertArrayEquals("Missing exit logs did not match.", expected[1], actual[1]);
    }

    @Test
    public void shouldReturnSingleEntryPerEmployee() {
        Log[] logs = new Log[]{
                new Log("Paul", Scan.EXIT),
                new Log("Paul", Scan.ENTER),
                new Log("Paul", Scan.EXIT),
                new Log("Paul", Scan.EXIT),
                new Log("Paul", Scan.ENTER),
                new Log("Paul", Scan.EXIT),
        };
        String[] expectedMissingEntries = new String[]{"Paul"};
        String[] expectedMissingExits = new String[]{};
        String[][] expected = new String[][]{expectedMissingEntries, expectedMissingExits};

        String[][] actual = getMissingLogs(logs);
        Arrays.sort(actual[0]);
        Arrays.sort(actual[1]);

        assertArrayEquals("Missing entry logs did not match.", expected[0], actual[0]);
        assertArrayEquals("Missing exit logs did not match.", expected[1], actual[1]);
    }

    @Test
    public void shouldReturnSingleScanOfTypePerEmployee() {
        Log[] logs = new Log[]{
                new Log("Paul", Scan.EXIT),
                new Log("Paul", Scan.ENTER),
                new Log("Paul", Scan.EXIT),
                new Log("Paul", Scan.EXIT),
                new Log("Paul", Scan.ENTER),
                new Log("Paul", Scan.EXIT),
                new Log("Paul", Scan.ENTER),
                new Log("Paul", Scan.ENTER),
                new Log("Paul", Scan.EXIT),
                new Log("Paul", Scan.ENTER),
        };
        String[] expectedMissingEntries = new String[]{"Paul"};
        String[] expectedMissingExits = new String[]{"Paul"};
        String[][] expected = new String[][]{expectedMissingEntries, expectedMissingExits};

        String[][] actual = getMissingLogs(logs);
        Arrays.sort(actual[0]);
        Arrays.sort(actual[1]);

        assertArrayEquals("Missing entry logs did not match.", expected[0], actual[0]);
        assertArrayEquals("Missing exit logs did not match.", expected[1], actual[1]);
    }
}
