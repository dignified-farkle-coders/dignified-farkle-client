package io.farkle.dignifiedfarkleclient.model;
/*
 *  Copyright 2019 Nicholas Bennett & Deep Dive Coding/CNM Ingenuity
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;

/**
 * Class encapsulating the basic properties of a passphrase.
 *
 * @author Nicholas Bennett, Todd Nordquist, Brian Bleck, Deep Dive Coding Java + Android Cohort 8
 */
public class Points {

  @Expose
  private long id;

  @Expose
  private String key;

  @Expose
  private int points;

  /**
   * Returns the ID of the passphrase. For an instance received from the service, this will be a
   * unique, positive value; for a newly created instance, this value is zero.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the ID of the passphrase. (In general, there should be no need to invoke this method
   * directly.)
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the key (name) of the passphrase.
   */
  public String getKey() {
    return key;
  }

  /**
   * Sets the key (name) of the passphrase.
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * Returns the points
   */
  public int getPoints() {
    return points;
  }

  /**
   * Sets the points. To avoid issues when
   * displaying/editing, none of the list items should contain whitespace.
   */
  public void setPoints(int points) {
    this.points = points;
  }

}
