package com.statemachine.starter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.statemachine.core.StateMachine;
import com.statemachine.core.StateMachineBuilder;
import com.statemachine.exceptions.StateException;
import com.statemachine.inputs.StateTest;
import com.statemachine.inputs.TransitionTypeTest;

public class StateMachineApplicationTest {
  
  private StateMachine<StateTest, TransitionTypeTest> stateMachine;
  
  @Before
  public void preRunConfiguration() {
    this.stateMachine = new StateMachineBuilder<StateTest, TransitionTypeTest>(StateTest.ZEROSTATE)
        .nextTransition(StateTest.ZEROSTATE, TransitionTypeTest.ONER, StateTest.ONESTATE)
        .nextTransition(StateTest.ZEROSTATE, TransitionTypeTest.TWOR, StateTest.TWOSTATE)
        .nextTransition(StateTest.ZEROSTATE, TransitionTypeTest.CANCEL, StateTest.CANCELLED)
        .nextTransition(StateTest.ONESTATE, TransitionTypeTest.ONER, StateTest.TWOSTATE)
        .nextTransition(StateTest.ONESTATE, TransitionTypeTest.TWOR, StateTest.THREESTATE)
        .nextTransition(StateTest.ONESTATE, TransitionTypeTest.CANCEL, StateTest.CANCELLED)
        .nextTransition(StateTest.TWOSTATE, TransitionTypeTest.ONER, StateTest.THREESTATE)
        .nextTransition(StateTest.TWOSTATE, TransitionTypeTest.TWOR, StateTest.FOURSTATE)
        .nextTransition(StateTest.TWOSTATE, TransitionTypeTest.CANCEL, StateTest.CANCELLED)
        .nextTransition(StateTest.THREESTATE, TransitionTypeTest.ONER, StateTest.FOURSTATE)
        .nextTransition(StateTest.THREESTATE, TransitionTypeTest.CANCEL, StateTest.CANCELLED)
        .nextTransition(StateTest.FOURSTATE, TransitionTypeTest.BUY, StateTest.COMPLETED)
        .nextTransition(StateTest.FOURSTATE, TransitionTypeTest.CANCEL, StateTest.CANCELLED)
        .build();
  }
  
  @Test
  public void testStateMachineApplication() {
    Assert.assertEquals(stateMachine.getState(), StateTest.ZEROSTATE);
    stateMachine.applyTransition(TransitionTypeTest.ONER);
    Assert.assertEquals(stateMachine.getState(), StateTest.ONESTATE);
    stateMachine.applyTransition(TransitionTypeTest.TWOR);
    Assert.assertEquals(stateMachine.getState(), StateTest.THREESTATE);
    stateMachine.applyTransition(TransitionTypeTest.ONER);
    Assert.assertEquals(stateMachine.getState(), StateTest.FOURSTATE);
    stateMachine.applyTransition(TransitionTypeTest.BUY);
    Assert.assertEquals(stateMachine.getState(), StateTest.COMPLETED);
  }

  @Test
  public void testStateMachineApplicationCaseTwo() {
    Assert.assertEquals(stateMachine.getState(), StateTest.ZEROSTATE);
    stateMachine.applyTransition(TransitionTypeTest.TWOR);
    Assert.assertEquals(stateMachine.getState(), StateTest.TWOSTATE);
    stateMachine.applyTransition(TransitionTypeTest.TWOR);
    Assert.assertEquals(stateMachine.getState(), StateTest.FOURSTATE);
    stateMachine.applyTransition(TransitionTypeTest.BUY);
    Assert.assertEquals(stateMachine.getState(), StateTest.COMPLETED);
  }
  
  @Test
  public void testStateMachineApplicationCaseThree() {
    Assert.assertEquals(stateMachine.getState(), StateTest.ZEROSTATE);
    stateMachine.applyTransition(TransitionTypeTest.TWOR);
    Assert.assertEquals(stateMachine.getState(), StateTest.TWOSTATE);
    stateMachine.applyTransition(TransitionTypeTest.CANCEL);
    Assert.assertEquals(stateMachine.getState(), StateTest.CANCELLED);
  }
  
  @Test(expected=StateException.class)
  public void testStateMachineApplicationCaseException() {
    Assert.assertEquals(stateMachine.getState(), StateTest.ZEROSTATE);
    stateMachine.applyTransition(TransitionTypeTest.ONER);
    Assert.assertEquals(stateMachine.getState(), StateTest.ONESTATE);
    stateMachine.applyTransition(TransitionTypeTest.TWOR);
    Assert.assertEquals(stateMachine.getState(), StateTest.THREESTATE);
    stateMachine.applyTransition(TransitionTypeTest.TWOR);
  }
  
  @After
  public void postRun() {
    this.stateMachine = null;
  }
}
