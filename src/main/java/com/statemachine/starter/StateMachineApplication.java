package com.statemachine.starter;

import java.util.Scanner;

import com.statemachine.core.StateMachine;
import com.statemachine.core.StateMachineBuilder;

public class StateMachineApplication {
  private enum State {
    ZEROSTATE("0S"),
    ONESTATE("1S"), 
    TWOSTATE("2S"),
    THREESTATE("3S"),
    FOURSTATE("4S"), 
    CANCELLED("!CANCELLED"), 
    COMPLETED("!COMPLETED");
    
    private final String value;
    
    private State(final String value) {
      this.value = value;
    }
    
    @Override
    public String toString() {
      return this.value;
    }
  }
  
  private enum TransitionType {
    ONER, 
    TWOR, 
    CANCEL, 
    BUY
  }
  
  public static StateMachine<State, TransitionType> initStateMachine() {
    StateMachine<State, TransitionType> stateMachine = new StateMachineBuilder<State, TransitionType>(State.ZEROSTATE)
        .nextTransition(State.ZEROSTATE, TransitionType.ONER, State.ONESTATE)
        .nextTransition(State.ZEROSTATE, TransitionType.TWOR, State.TWOSTATE)
        .nextTransition(State.ZEROSTATE, TransitionType.CANCEL, State.CANCELLED)
        .nextTransition(State.ONESTATE, TransitionType.ONER, State.TWOSTATE)
        .nextTransition(State.ONESTATE, TransitionType.TWOR, State.THREESTATE)
        .nextTransition(State.ONESTATE, TransitionType.CANCEL, State.CANCELLED)
        .nextTransition(State.TWOSTATE, TransitionType.ONER, State.THREESTATE)
        .nextTransition(State.TWOSTATE, TransitionType.TWOR, State.FOURSTATE)
        .nextTransition(State.TWOSTATE, TransitionType.CANCEL, State.CANCELLED)
        .nextTransition(State.THREESTATE, TransitionType.ONER, State.FOURSTATE)
        .nextTransition(State.THREESTATE, TransitionType.CANCEL, State.CANCELLED)
        .nextTransition(State.FOURSTATE, TransitionType.BUY, State.COMPLETED)
        .nextTransition(State.FOURSTATE, TransitionType.CANCEL, State.CANCELLED)
        .build();
    
    return stateMachine;
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    StateMachine<State, TransitionType> stateMachine = initStateMachine();
    
    while(!stateMachine.getState().equals(State.CANCELLED) && !stateMachine.getState().equals(State.COMPLETED)) {
      String input = in.nextLine();
      try {
        switch (input) {
          case "0S":
            stateMachine = initStateMachine();
            System.out.println(stateMachine.getState().toString());
            break;
          
          case "1R":
            stateMachine.applyTransition(TransitionType.ONER);
            System.out.println(stateMachine.getState().toString());
            break;
            
          case "2R":
            stateMachine.applyTransition(TransitionType.TWOR);
            System.out.println(stateMachine.getState().toString());
            break;
            
          case "CANCEL":
            stateMachine.applyTransition(TransitionType.CANCEL);
            System.out.println(stateMachine.getState().toString());
            break;
            
          case "BUY":
            stateMachine.applyTransition(TransitionType.BUY);
            System.out.println(stateMachine.getState().toString());
            break;
            
          default:
            System.out.println(stateMachine.getState().toString() + ": INVALID TRANSITION");
            break;
        }
      }
      catch(Exception ex) {
        System.out.println(ex.getMessage());
        System.out.println(stateMachine.getState());
      }
    }
  }
}
