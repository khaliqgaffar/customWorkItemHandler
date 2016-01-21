package com.redhat;

import java.util.HashMap;
import java.util.Map;

import org.drools.core.process.instance.WorkItemHandler;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemManager;

public class CoolCustomWorkHandler implements WorkItemHandler {

	public void abortWorkItem(WorkItem wi, WorkItemManager wm) {
		// TODO Auto-generated method stub

	}

	public void executeWorkItem(WorkItem wi, WorkItemManager wm) {
		// TODO Auto-generated method stub
		final Map<String, Object> resultMap = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(wi.getParameter("input1")!=null?wi.getParameter("input1"):"");
		sb.append(wi.getParameter("input2")!=null?wi.getParameter("input2"):"");
		resultMap.put("output", sb.toString());
		wm.completeWorkItem(wi.getId(), resultMap);

	}

}
