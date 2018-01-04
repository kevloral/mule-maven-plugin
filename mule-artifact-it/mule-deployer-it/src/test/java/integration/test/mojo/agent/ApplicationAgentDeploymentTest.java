/*
 * Mule ESB Maven Tools
 * <p>
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * <p>
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package integration.test.mojo.agent;

import org.apache.maven.it.VerificationException;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ApplicationAgentDeploymentTest extends AgentDeploymentTest {

  private static final String APPLICATION = "empty-mule-deploy-application-agent-project";

  public ApplicationAgentDeploymentTest() {
    super(APPLICATION);
  }

  @Test
  public void testAgentDeploy() throws IOException, VerificationException, InterruptedException {
    log.info("Executing mule:deploy goal...");

    // TODO check why we have this sleep here
    Thread.sleep(30000);

    verifier.setSystemProperty("applicationName", APPLICATION);
    verifier.addCliOption("-DmuleDeploy");
    verifier.executeGoal(DEPLOY_GOAL);

    assertThat("Standalone should be running ", standaloneEnvironment.isRunning(), is(true));
    assertThat("Failed to deploy: " + APPLICATION, standaloneEnvironment.isDeployed(APPLICATION), is(true));

    verifier.verifyErrorFreeLog();
  }
}