package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Handles command processing from a file.
 */
public class FileReader {
  private PhotoalbumController controller;

  /**
   * Initializes with a PhotoalbumController instance.
   * @param controller Processes the commands.
   */
  public FileReader(PhotoalbumController controller) {
    this.controller = controller;
  }

  /**
   * Reads and processes commands from the specified file.
   * @param filename Name of the file containing commands.
   */
  public void readfile(String filename) {
    try (Scanner scanner = new Scanner(new File(filename))) {
      while (scanner.hasNextLine()) {
        String command = scanner.nextLine().trim();
        if (!command.isEmpty()) {
          controller.readCommand(command);
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("Error: File not found ( " + filename + " )");
    }
  }
}
