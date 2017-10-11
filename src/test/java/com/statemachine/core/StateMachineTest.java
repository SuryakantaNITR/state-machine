package com.statemachine.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.statemachine.inputs.StateTest;
import com.statemachine.inputs.TransitionTypeTest;


public class StateMachineTest {
  private StateMachine<StateTest, TransitionTypeTest> stateMachine;
  
  @Before
  public void preRunConfigurations() {
    StateMachineNode<StateTest, TransitionTypeTest> stateMachineNode = new StateMachineNode<>(StateTest.ZEROSTATE);
    this.stateMachine = new StateMachine<>(stateMachineNode);
  }
  
  @Test
  public void testGetState() {
    Assert.assertEquals(stateMachine.getState(), StateTest.ZEROSTATE);
  }
  
  @After
  public void postRunDestroy() {
    this.stateMachine = null;
  }
}
