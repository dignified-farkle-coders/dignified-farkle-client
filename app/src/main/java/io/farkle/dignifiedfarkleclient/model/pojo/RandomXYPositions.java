package io.farkle.dignifiedfarkleclient.model.pojo;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
public class RandomXYPositions {
  private static boolean arrayHasZeros = true;
  public static Random rng = new Random();
  private static int randomValue;
  public static int[] xValues = new int[6];
  private static int[] yValues = new int[6];
  private static int randomChoosenX;
  private static int randomChoosenY;
  private static int[] choosenXValues = new int[6];
  private static int[] choosenYValues = new int[6];
  private static int sumChecker;
  private static int[] checkerArrayX = new int[6];
  private static int[] checkerArrayY = new int[6];
  private static int sumX = 0;
  private static int sumY = 0;
  public static int[] method() {
    Random rng = new Random();
    int sizeX = 130;
    int sizeY = 90;
    int[] myFinishedArray = new int[12];
    int[] myFinishedArrayX = new int[6];
    int[] myFinishedArrayY = new int[6];
    for (int i = 0; i < 6; i++) {
      randomChoosenX = rng.nextInt(1000) + 100;
      randomChoosenY = rng.nextInt(230) + 100;
      for (int j = 0; j < choosenXValues.length - 1; j++) {
        if (Math.abs(randomChoosenX - choosenXValues[j]) > sizeX) {
          checkerArrayX[j] = 0;
        } else {
          checkerArrayX[j] = 1;
        }
        if (Math.abs(randomChoosenY - choosenYValues[j]) > sizeY) {
          checkerArrayY[j] = 0;
        } else {
          checkerArrayY[j] = 1;
        }
      }
      for (int k = 0; k < checkerArrayX.length - 1; k++) {
        sumX = sumX + checkerArrayX[k];
      }
      for (int k = 0; k < checkerArrayY.length - 1; k++) {
        sumY = sumY + checkerArrayY[k];
      }
      if (sumX == 0 || sumY == 0) {
        myFinishedArrayX[i] = randomChoosenX;
        choosenXValues[i] = randomChoosenX;
        myFinishedArrayY[i] = randomChoosenY;
        choosenYValues[i] = randomChoosenY;
      } else {
        sumX = 0;
        sumY = 0;
        System.out.println("Could Not Find");
        i--;
      }
    }
    System.out.println(Arrays.toString(myFinishedArrayX));
    System.out.println(Arrays.toString(myFinishedArrayY));
    for (int i = 0; i < myFinishedArrayX.length; i++) {
      myFinishedArray[i] = myFinishedArrayX[i];
    }
    for (int i = 1; i < myFinishedArrayY.length + 1 ; i++) {
      myFinishedArray[i + 5] = myFinishedArrayY[myFinishedArrayY.length - i];
    }
    System.out.println(Arrays.toString(myFinishedArray));
    return myFinishedArray;
  }
}