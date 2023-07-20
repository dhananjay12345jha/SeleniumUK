package com.salmon.test.framework.helpers;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

//The class is used to merge the rerun report and the original report.
// This handles replacing original failure if rerun scenario is passed.

public class JsonReportHandler {

	@Test
	//Overwrite original(target) result for passed rerun
	public  void refineAndRewriteJSONFile() {
		String directoriesToMerge[] = System.getProperty("directoriesToMerge").split(":");
		String finalReportDirectory = "target/cucumber-report/finalReport/interim";
		File initialDirectory = new File(directoriesToMerge[0]);
		File target = new File(finalReportDirectory);
		if (!target.exists()) {
			target.mkdir();
		}
		copyFiles(initialDirectory, target,"json");
		copyFiles(initialDirectory,target,"png");
		File targetjsonFile=new File(finalReportDirectory+"/cucumber.json");
		for(int i=1;i<directoriesToMerge.length;i++){
			File srcMergeFile=new File(directoriesToMerge[i]+"/cucumber.json");
			if(srcMergeFile.exists())
				merge(srcMergeFile,targetjsonFile);
		}

	}

	public void merge(File src,File target){
		JSONArray srcFeatures = null;
		JSONArray targetFeatures = null;
		JSONParser parser = new JSONParser();
		FileReader srcReader = null;
		FileReader targetReader = null;

		try {
			srcReader = new FileReader(src);
			targetReader = new FileReader(target);
			Object srcObj = parser.parse(srcReader);
			Object targetObj = parser.parse(targetReader);
			srcFeatures = (JSONArray) srcObj;
			targetFeatures = (JSONArray) targetObj;
			//Get a list of rerun(src) scenarios containing id/result/scenario object for each entry
			List<HashMap<String, Object>> rerunScenarioResults = getAllScenarioIDsAndResult(srcFeatures);
			//Loop original(target) features
			for (int i = 0; i < targetFeatures.size(); i++) {
				JSONObject feature = (JSONObject) targetFeatures.get(i);
				//Remove result of rerunforcedfailure
				if (feature.get("id").toString().toLowerCase().contains("Force failure to support reRun")) {
					targetFeatures.remove(i);
					i--;
					continue;
				}
				if (feature.get("keyword").equals("Feature")) {
					JSONArray scenarios = (JSONArray) feature.get("elements");
					//Loop original(target) scenarios for each feature
					for (int j = 0; j < scenarios.size(); j++) {
						JSONObject scenario = (JSONObject) scenarios.get(j);
						//Background is standing in line with scenario, but without id attribute, ignore this element
						String type = (String) scenario.get("type");
						if (!type.equals("scenario")) {
							continue;
						}
						String scenarioId = (String) scenario.get("id");
						boolean isRerun = false;
						String tempRerunResult = "";
						JSONObject tempRerunResultObject = null;
						JSONObject tempRerunBackgroundResultObject = null;
						//Loop rerun(src) scenario to check if current scenario has been rerun
						for (int k = 0; k < rerunScenarioResults.size(); k++) {
							if (scenarioId.equals(rerunScenarioResults.get(k).get("id"))) {
								tempRerunResult = (String) rerunScenarioResults.get(k).get("result");
								tempRerunResultObject = (JSONObject) rerunScenarioResults.get(k).get("scenario");
								isRerun = true;
								if (k != 0) {
									tempRerunBackgroundResultObject = (JSONObject) rerunScenarioResults.get(k - 1).get("scenario");
								}
							}
						}
						//If an original scenario is rerun and passed in second run, replace original scenario result
						if (isRerun && tempRerunResult.equals("passed")) {
							System.out.println("OVERWRITTING RESULT FOR PASSED RERUN:" + scenarioId);
							scenarios.remove(j);
							scenarios.add(j, tempRerunResultObject);
							//Replace background result
							if (j != 0) {
								JSONObject previousScenario = (JSONObject) scenarios.get(j - 1);
								String previousType = (String) previousScenario.get("type");
								if (previousType.equals("background")) {
									scenarios.remove(j - 1);
									scenarios.add(j - 1, tempRerunBackgroundResultObject);
									continue;
								}
							}

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Writting to new json file
		FileWriter updatedJson;
		try {
			//updatedJson = new FileWriter(target.getAbsolutePath().replaceAll(".json", "refined.json"));
			updatedJson = new FileWriter(target);
			updatedJson.write(targetFeatures.toJSONString());
			updatedJson.flush();
		} catch (IOException e) {
			System.out.println("Exception on refining json file");
			e.printStackTrace();
		} finally {
			//System.out.println("Closing src and target files");
			try {
				srcReader.close();
				targetReader.close();
			} catch (IOException e) {
				System.out.println("Exception on closing src and target files");
				e.printStackTrace();
			}
		}
		copyFiles(src.getParentFile(),target.getParentFile(),"png");
	}


	public static void deleteFile(String path) {
		File file = new File(path);
		file.deleteOnExit();
	}

	public static List<HashMap<String, Object>> getAllScenarioIDsAndResult(JSONArray features) {
		//A list of scenarios containing id/result/scenario full content
		List<HashMap<String, Object>> allScenarioResults = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < features.size(); i++) {
			JSONObject feature = (JSONObject) features.get(i);
			if (feature.get("keyword").equals("Feature")) {
				JSONArray scenarios = (JSONArray) feature.get("elements");
				for (int j = 0; j < scenarios.size(); j++) {
					JSONObject scenario = (JSONObject) scenarios.get(j);
					String scenarioId = (String) scenario.get("id");
					String scenarioResult = getResultOfScenario(scenario);
					HashMap<String, Object> scenarioResultMap = new HashMap<String, Object>();
					scenarioResultMap.put("id", scenarioId);
					scenarioResultMap.put("result", scenarioResult);
					scenarioResultMap.put("scenario", scenario);
					allScenarioResults.add(scenarioResultMap);
				}
			}
		}
		return allScenarioResults;
	}

	public static String getResultOfScenario(JSONObject scenario) {
		String result = "passed";
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
		JSONArray stepsArray = (JSONArray) scenario.get("steps");
		JSONObject steps = (JSONObject) stepsArray.get(0);
		JSONObject stepsResult = (JSONObject) steps.get("result");
		String stepsResultString = (String) stepsResult.get("status");
		try {
			JSONArray afterArray = (JSONArray) scenario.get("after");
			JSONObject after = (JSONObject) afterArray.get(0);
			JSONObject afterResult = (JSONObject) after.get("result");
			afterResultString = (String) afterResult.get("status");
		} catch (Exception e) {
			afterResultString = "passed";
		}
		if (beforeResultString.equalsIgnoreCase("failed") | stepsResultString.equalsIgnoreCase("failed") | afterResultString.equalsIgnoreCase("failed")) {
			result = "failed";
		} else {
			result = "passed";
		}
		return result;
	}

	public static void mergeJsonFiles(File jsonTarget, List<File> jsonSource) throws IOException {
		//merge report files
		for (int i = 0; i < jsonSource.size(); i++) {
			String targetReport = FileUtils.readFileToString(jsonTarget);
			String sourceReport = FileUtils.readFileToString(jsonSource.get(i));
			FileUtils.writeStringToFile(jsonTarget, targetReport.substring(0, targetReport.length() - 1) + "," + sourceReport.substring(1, sourceReport.length()));
		}
	}

	public void copyFiles(File src, File target,String format){
		Collection<File> existingFiles = FileUtils.listFiles(src, new String[]{format}, true);
		for (File file : existingFiles) {
			try {
				FileUtils.copyFileToDirectory(file.getAbsoluteFile(), target);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
