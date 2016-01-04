package org.jboss.ddoyle.bpms.workshop;

import org.drools.core.ClockType;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
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
		
		//Create KieSessionConfiguration
		//KieSessionConfiguration contains configuration options like ClockType, BeliefSystem, WorkItemHandlers, etc. 
		KieSessionConfiguration configuration = kieServices.newKieSessionConfiguration();
		configuration.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
		
		//Create environment.
		//Environment contains configuration options like EntityManagerFactory, TransactionManager, etc.
		Environment environment = kieServices.newEnvironment();
		//environment.set(EnvironmentName.ENTITY_MANAGER_FACTORY, myEmf);
		//environment.set(EnvironmentName.TRANSACTION_MANAGER, myTm);
		
		//Retrieve a new CommandBasedStatefulKnowledgeSession with a JPA CommandService (SingleSessionCommandService) implementation.
		//The SingleSessionCommandService in its turn uses the TransactionInterceptor (which is also a CommandService implementation) to wrap command execution in a transaction.
		//KieSession kieSession = JPAKnowledgeService.newStatefulKnowledgeSession(kieBase, configuration, environment);
		
		//As we are running this on JavaSE, we will not use any DB or transactions. So this is just a process that runs in memory.
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.startProcess(PROCESS_ID);
		
		//And finally, dispose the session.
		kieSession.dispose();
	}
	

}
