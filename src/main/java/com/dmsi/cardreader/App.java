package com.dmsi.cardreader;

import java.util.HashSet;
import java.util.Set;

public class App {

  public enum Scan {
    ENTER,
    EXIT
  }

  public static class Log {
    public String employee;
    public Scan scan;

    public Log(String employee, Scan scan) {
      this.employee = employee;
      this.scan = scan;
    }
  }

  /**
   * Takes an array of card scan logs, where each log contains the name of the employee
   * and the type of log (enter or exit).
   * The log begins at the beginning of the day and ends at the end of the day.
   * The room is physically clear at the start and end of each day.
   * Any missing logs are a result of a faulty card or card reader, however each entry is true.
   * Therefore, in the case of an exit with no preceding entry, the employee should
   * be returned in the missingEntries array, to note that their card did not read upon
   * entry of the room.
   *
   * @param logs An Array of Log objects recorded by the card reader
   * @return An Array with 2 items, the first is an Array of employee names with
   * missing entry logs and the second an Array of employee names with
   * missing exit logs
   */
  public static String[][] getMissingLogs(Log[] logs) {

    Set<String> inRoom = new HashSet<String>();

    Set<String> missingEntries = new HashSet<String>();
    Set<String> missingExits = new HashSet<String>();

    for (Log log : logs) {
      if (log.scan == Scan.ENTER) {
        if (inRoom.contains(log.employee)) {
          missingExits.add(log.employee);
        } else {
          inRoom.add(log.employee);
        }
      }
      if (log.scan == Scan.EXIT) {
        if (!inRoom.contains(log.employee)) {
          missingEntries.add(log.employee);
        } else {
          inRoom.remove(log.employee);
        }
      }
    }

    for (String employee : inRoom) {
      missingExits.add(employee);
    }

    return new String[][]{missingEntries.toArray(new String[0]), missingExits.toArray(new String[0])};
  }
}
