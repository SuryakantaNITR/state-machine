package com.statemachine.core;

import java.util.HashMap;
import java.util.Map;

public final class StateMachineBuilder<State extends Enum<State>, TransitionType extends Enum<TransitionType>> {

  private final StateMachineNode<State, TransitionType> root;
  private final Map<State, StateMachineNode<State, TransitionType>> nodeMap;
  
  public StateMachineBuilder(State state) {
    this.root = new StateMachineNode<>(state);
    this.nodeMap = new HashMap<>();
    nodeMap.put(state, root);
  }
  
  public StateMachine<State, TransitionType> build() {
    return new StateMachine<>(root);
  }
  
  public StateMachineBuilder<State, TransitionType> nextTransition(State originalState, TransitionType transitionType, State finalState) {
    StateMachineNode<State, TransitionType> startNode = nodeMap.get(originalState);
    StateMachineNode<State, TransitionType> endNode = nodeMap.get(finalState);
    
    if(startNode == null) {
      startNode = new StateMachineNode<>(originalState);
      nodeMap.put(originalState, startNode);
    }
    
    if(endNode == null) {
      endNode = new StateMachineNode<>(finalState);
      nodeMap.put(finalState, endNode);
    }
    
    startNode.addNextNode(transitionType, endNode);
    return this;
  }
}
