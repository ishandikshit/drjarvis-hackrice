package edu.asu.intent;


import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import edu.asu.nlp.SingletonClass;

public class StartBuildingProfile {
	SingletonClass sg= SingletonClass.getInstance();
	AIResponse response;
	String query;
	String name;
	public StartBuildingProfile() 
	{
		response=sg.getAIResponse();
		ProfileBuilder pf = null;
		name= sg.getUser();

		pf = new ProfileBuilder();
		this.name=this.name.toLowerCase();

		System.out.println("NAME:"+this.name);
		pf.setName(this.name);
		try {
			pf.run();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		query=pf.getQuery();
		AIConfiguration aic = new AIConfiguration("014cb2cd82bf4bbf9ed3aee464a2e7da");
		AIDataService dataService = new AIDataService(aic);
		AIRequest air = new AIRequest();
		air.setQuery(query);
		System.out.println("\n\n---------------------YOUR QUERY====="+ query);
		air.setSessionId(sg.getSession());
		AIResponse response = null;
		try {
			response = dataService.request(air);
		} catch (AIServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sg.setSpeechOutput(response.getResult().getFulfillment().getSpeech());
		sg.setAIResponse(response);
		sg.setSession(response.getSessionId());
	}

}
