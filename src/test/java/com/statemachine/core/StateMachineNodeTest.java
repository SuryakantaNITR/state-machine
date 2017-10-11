package com.statemachine.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.statemachine.inputs.StateTest;
import com.statemachine.inputs.TransitionTypeTest;

public class StateMachineNodeTest {
  
  private StateMachineNode<StateTest, TransitionTypeTest> stateMachineNode;
  
  @Before
  public void preRunConfigurations() {
    this.stateMachineNode = new StateMachineNode<StateTest, TransitionTypeTest>(StateTest.ZEROSTATE);
  }
  
  @Test
  public void testAddNextNodeCaseOne() {
    stateMachineNode.addNextNode(TransitionTypeTest.ONER, new StateMachineNode<StateTest, TransitionTypeTest>(StateTest.ONESTATE));
    Assert.assertEquals(stateMachineNode.getNextNode(TransitionTypeTest.ONER).getState(), StateTest.ONESTATE);
  }
  
  @After
  public void postRunDestroy() {
    this.stateMachineNode = null;
  }

}
