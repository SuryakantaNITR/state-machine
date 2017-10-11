package com.statemachine.exceptions;

public class StateException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -2639099392761671580L;
  private final Enum<?> state;
  private final Enum<?> transitionType;
  
  public StateException(Enum<?> state, Enum<?> transitionType) {
    this.state = state;
    this.transitionType = transitionType;
  }

  public Enum<?> getState() {
    return state;
  }

  public Enum<?> getTransitionType() {
    return transitionType;
  }
  
  @Override
  public String getMessage() {
    return "Exception in " + state.name() + " for transition " + transitionType;
  }
}
