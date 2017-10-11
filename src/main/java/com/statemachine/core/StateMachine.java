package com.statemachine.core;

import com.statemachine.exceptions.StateException;

public final class StateMachine<State extends Enum<State>, TransitionType extends Enum<TransitionType>> {

  private StateMachineNode<State, TransitionType> root;
  
  public StateMachine(StateMachineNode<State, TransitionType> root) {
    this.root = root;
  }
  
  public State getState() {
    return root.getState();
  }
  
  public void applyTransition(TransitionType transitionType) {
    StateMachineNode<State, TransitionType> nextNode = root.getNextNode(transitionType);
    
    if(nextNode == null)
      throw new StateException(root.getState(), transitionType);
    
    root = nextNode;
  }
}
