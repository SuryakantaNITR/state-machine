package com.statemachine.core;

import java.util.HashMap;
import java.util.Map;

public final class StateMachineNode<State extends Enum<State>, TransitionType extends Enum<TransitionType>> {
  
  private final State state;
  
  private final Map<TransitionType, StateMachineNode<State, TransitionType>> nextNodes;
  
  public StateMachineNode(State state) {
    this.state = state;
    this.nextNodes = new HashMap<>();
  }
  
  public void addNextNode(TransitionType transitionType, StateMachineNode<State, TransitionType> stateMachineNode) {
    nextNodes.put(transitionType, stateMachineNode);
  }
  
  public StateMachineNode<State, TransitionType> getNextNode(TransitionType transitionType) {
    return nextNodes.get(transitionType);
  }

  public State getState() {
    return state;
  }
}
