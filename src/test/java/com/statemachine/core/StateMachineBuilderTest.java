package com.statemachine.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.statemachine.inputs.StateTest;
import com.statemachine.inputs.TransitionTypeTest;

public class StateMachineBuilderTest {
  
  StateMachineBuilder<StateTest, TransitionTypeTest> stateMachineBuilder;
  
  @Before
  public void preRunConfigurations() {
    this.stateMachineBuilder = new StateMachineBuilder<StateTest, TransitionTypeTest>(StateTest.ZEROSTATE); 
  }

  @Test
  public void testNextTransitionCase() {
    StateMachine<StateTest, TransitionTypeTest> stateMachine = this.stateMachineBuilder.build();
    this.stateMachineBuilder.nextTransition(StateTest.ZEROSTATE, TransitionTypeTest.ONER, StateTest.ONESTATE);
    
    stateMachine.applyTransition(TransitionTypeTest.ONER);
    
    Assert.assertEquals(stateMachine.getState(), StateTest.ONESTATE);
  }
}
