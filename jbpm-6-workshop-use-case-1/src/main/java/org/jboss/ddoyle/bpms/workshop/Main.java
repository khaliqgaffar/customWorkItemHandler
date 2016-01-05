package org.jboss.ddoyle.bpms.workshop;

import org.drools.core.ClockType;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.persistence.jpa.JPAKnowledgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	private static final String PROCESS_ID = "com.sample.bpmn";
	
	
	public static void main(String[] args) {
		LOGGER.info("Starting jBPM engine.");
		
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		
		//Get KieBase.
		KieBase kieBase = kieContainer.getKieBase();
		
		
		
		/* *****************************************************************************************************
		 * 
		 * In a jBPM application that requires statefulness of long running processes, i.e. processes that contain
		 * wait-states and need to be stored in datastores, we would need to spin-up a KieSession from the 
		 * JPAKnowledgeService. A KieSession created via this service would be an implementation of a 
		 * CommandBasedStatefulKnowledgeSession with a JPA CommandService (SingleSessionCommandService) implementation.
		 * The SingleSessionCommandService in its turn uses the TransactionInterceptor (which is also a CommandService implementation) 
		 * to wrap command execution in a (JTA) transaction.
		 * 
		 * Also, to create a session using the JPAKnowledgeService, we need to provide a KieBase, a KieSessionConfiguration (e.g. ClockType)
		 * and an Environment (e.g. TransactionManager, EntityManagerFactory).
		 * 
		 * In this example, we will simply use a KieSession without persistence, but we've provided some sample code that
		 * shows how one would create a KieSession backed by database persistence.
		 * 
		 * Please note that in the BPMSuite platform, creating these sessions is done for you behind the scenes.
		 * This type of code is only needed when one wants to run a process with jBPM embedded in the application (and even in that
		 * case it's probably easier to utilize the jBPM EJB and/or CDI services. See for example: https://github.com/mswiderski/jbpm-examples/tree/master/jbpm6/jbpm-sample-cdi-services).
		 * 
		 *******************************************************************************************************/
		//Create KieSessionConfiguration
		//KieSessionConfiguration contains configuration options like ClockType, BeliefSystem, WorkItemHandlers, etc. 
		//KieSessionConfiguration configuration = kieServices.newKieSessionConfiguration();
		//configuration.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
		
		//Create environment.
		//Environment contains configuration options like EntityManagerFactory, TransactionManager, etc.
		//Environment environment = kieServices.newEnvironment();
		//environment.set(EnvironmentName.ENTITY_MANAGER_FACTORY, myEmf);
		//environment.set(EnvironmentName.TRANSACTION_MANAGER, myTm);
		
		//Create KieSession
		//KieSession kieSession = JPAKnowledgeService.newStatefulKnowledgeSession(kieBase, configuration, environment);
		
		//As we are running this on JavaSE, we will not use any DB or transactions. So this is just a process that runs in memory.
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.startProcess(PROCESS_ID);
		
		//And finally, dispose the session.
		kieSession.dispose();
	}
	

}
