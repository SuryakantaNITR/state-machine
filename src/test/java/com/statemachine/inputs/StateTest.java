package com.statemachine.inputs;

public enum StateTest {

  ZEROSTATE("0S"),
  ONESTATE("1S"), 
  TWOSTATE("2S"),
  THREESTATE("3S"),
  FOURSTATE("4S"), 
  CANCELLED("!CANCELLED"), 
  COMPLETED("!COMPLETED");
  
  private final String value;
  
  private StateTest(final String value) {
    this.value = value;
  }
  
  @Override
  public String toString() {
    return this.value;
  }
  
}
