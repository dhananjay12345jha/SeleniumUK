package com.salmon.test.framework.helpers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

class CucumberReportParser {
	private static final Logger LOG = LoggerFactory.getLogger(CucumberReportParser.class);


	@Test
	public void parseJson() throws Throwable {

		File src = new File("target/cucumber-report/finalReport/interim/cucumber.json");
		if (src.exists()) {
			JSONArray srcFeatures = null;
			JSONParser parser = new JSONParser();
			FileReader srcReader = null;
			srcReader = new FileReader(src);
			Object srcObj = parser.parse(srcReader);
			srcFeatures = (JSONArray) srcObj;

			for (int i = 0; i < srcFeatures.size(); i++) {
				JSONObject feature = (JSONObject) srcFeatures.get(i);
				JSONArray scenarios = (JSONArray) feature.get("elements");
				for (int j = 0; j < scenarios.size(); j++) {
					JSONObject tempRerunResultObject = null;
					JSONObject scenario = (JSONObject) scenarios.get(j);
					String type = (String) scenario.get("type");
					if (type.equals("scenario")) {
						if(evaluateIfScenarioPassed(scenario)){
							if(j>0){
								JSONObject prevScenario=(JSONObject) scenarios.get(j-1);
								String prevScenariotype = (String) prevScenario.get("type");
								if(!prevScenariotype.equals("scenario") && evaluateIfScenarioPassed(prevScenario)){
									JSONArray stepsArray = (JSONArray) scenario.get("steps");
									for (int k = 0; k < stepsArray.size(); k++)
									{
										JSONObject steps = (JSONObject) stepsArray.get(k);
										JSONObject stepResult = (JSONObject) steps.get("result");
										String stepsResultStatus = (String) stepResult.get("status");
										if(stepsResultStatus.equalsIgnoreCase("skipped")){
										stepResult.remove("status");
										try{
										stepResult.put("status","passed");}
										catch (JSONException e) {
											e.printStackTrace();
										}
										}
									}
								}
							}
						}
					}

				}
			}
			//Writting to new json file
			FileWriter updatedJson;
			try {
				updatedJson = new FileWriter("target/cucumber-report/finalReport/cucumber.json");
				updatedJson.write(srcFeatures.toJSONString());
				updatedJson.flush();
			} catch (IOException e) {
				System.out.println("Exception on refining json file");
				e.printStackTrace();
			} finally {
				//System.out.println("Closing src and target files");
				try {
					srcReader.close();
				} catch (IOException e) {
					System.out.println("Exception on closing src and target files");
					e.printStackTrace();
				}
			}
		}
	}

	private boolean evaluateIfScenarioPassed(JSONObject scenario)
	{
		boolean scenarioPassStatus=true;
		String beforeResultString;
		String afterResultString;
		try {
			JSONArray beforeArray = (JSONArray) scenario.get("before");
			JSONObject before = (JSONObject) beforeArray.get(0);
			JSONObject beforeResult = (JSONObject) before.get("result");
			beforeResultString = (String) beforeResult.get("status");
		} catch (Exception e) {
			beforeResultString = "passed";
		}

		try {
			JSONArray afterArray = (JSONArray) scenario.get("after");
			JSONObject after = (JSONObject) afterArray.get(0);
			JSONObject afterResult = (JSONObject) after.get("result");
			afterResultString = (String) afterResult.get("status");
		} catch (Exception e) {
			afterResultString = "passed";
		}

		if (beforeResultString.equalsIgnoreCase("failed") | afterResultString.equalsIgnoreCase("failed")) {
			scenarioPassStatus = false;
		}
		else
		{
			JSONArray stepsArray = (JSONArray) scenario.get("steps");
			for (int i = 0; i < stepsArray.size(); i++)
			{
				JSONObject steps = (JSONObject) stepsArray.get(i);
				JSONObject stepResult = (JSONObject) steps.get("result");
				String stepsResultStatus = (String) stepResult.get("status");
				if (stepsResultStatus.equalsIgnoreCase("failed"))
				{
					scenarioPassStatus=false;
					break;
				}
			}
		}
		return scenarioPassStatus;
	}


}
