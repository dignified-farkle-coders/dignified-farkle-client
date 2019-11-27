package io.farkle.dignifiedfarkleclient.model.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomXYPositions {
  public static int[] method() {
    Random rand = new Random();
    int xBound = 11;
    int yBound = 11;
    int size = 2;
    List<Integer> wrapped = IntStream.range(0, xBound * yBound).boxed()
        .collect(Collectors.toList());
    List<Integer> points = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
      int start = rand.nextInt(wrapped.size() - size * yBound);
      points.add(start);
      List<Integer> temp = new ArrayList<>();
      for (int j = 0; j < size; j++) {
        temp.addAll(wrapped.subList(start - size + 1 + j * xBound, start + size + j * xBound));
      }
      wrapped.removeAll(temp);
    }
    int[] xCoords = new int[12];
    int[] yCoords = new int[6];
    int i = 0;
    for (Integer point : points) {
      xCoords[i] = point % xBound;
      yCoords[i] = point / yBound;
      i++;
    }
    for (int j = 1; j < xCoords.length - 5; j++) {
      xCoords[j + 5] = yCoords[yCoords.length - j];
    }
    return xCoords;
  }

}