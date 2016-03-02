package co.drools.demo;

import java.util.ArrayList;
import java.util.List;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import co.drools.model.SeniorClass;
import co.drools.model.Students;

@RestController
public class DroolDemo {

	KnowledgeBase kbase = null;
	StatefulKnowledgeSession ksession = null;

	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public @ResponseBody String searchData(@RequestParam("data") String data){
		JSONObject jsOb = new JSONObject(data);
		JSONArray jsArr = (JSONArray)jsOb.get("studentdata");
		SeniorClass sc = new SeniorClass();
		List<Students> st = new ArrayList<Students>();
		for (int i = 0; i < jsArr.length(); i++) {
			JSONObject jsObInner = (JSONObject)jsArr.get(i);
			Students stu = new Students();
			stu.setName(jsObInner.getString("name"));
			stu.setChemistry(jsObInner.getInt("chemistry"));
			stu.setMaths(jsObInner.getInt("maths"));
			stu.setPhysics(jsObInner.getInt("physics"));
			stu.setEnglish(jsObInner.getInt("english"));
			st.add(stu);
		}
		System.out.println("Total no. of Students: " + st.size());
		for (int i = 0; i < st.size(); i++) {
			Students sts = st.get(i);
			System.out.println(sts.getName());
		}
		sc.setStud(st);

		/*try {
			kbase = readKnowledgeBase("ClassResult.drl");
			ksession = kbase.newStatefulKnowledgeSession();
			ksession.insert(sc);
			ksession.fireAllRules();
			System.out.println("Student with highest marks in Maths: " + sc.getHighScorerMath());
			System.out.println("Student with highest marks in English: " + sc.getHighScorerEnglish());
			System.out.println("Student with highest marks in Chemistry: " + sc.getHighScorerChemistry());
			System.out.println("Student with highest marks in Physics: " + sc.getHighScorerPhysics());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		// Un-comment this to check the impact of not using lock-on-active
		/*try {
			kbase = readKnowledgeBase("ClassResultWithoutLOA.drl");
			ksession = kbase.newStatefulKnowledgeSession();
			ksession.insert(sc);
			ksession.fireAllRules();
			System.out.println("Student with highest marks in Maths: " + sc.getHighScorerMath());
			System.out.println("Student with highest marks in English: " + sc.getHighScorerEnglish());
			System.out.println("Student with highest marks in Chemistry: " + sc.getHighScorerChemistry());
			System.out.println("Student with highest marks in Physics: " + sc.getHighScorerPhysics());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		// Un-comment this to check the impact of using salience
		try {
			kbase = readKnowledgeBase("ClassResultWithSalience.drl");
			ksession = kbase.newStatefulKnowledgeSession();
			ksession.insert(sc);
			ksession.fireAllRules();
			System.out.println("Student with highest marks in Maths: " + sc.getHighScorerMath());
			System.out.println("Student with highest marks in English: " + sc.getHighScorerEnglish());
			System.out.println("Student with highest marks in Chemistry: " + sc.getHighScorerChemistry());
			System.out.println("Student with highest marks in Physics: " + sc.getHighScorerPhysics());
			System.out.println("Total no. of Student passed are: " + sc.getStudentPassCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Hello";
	}

	private static KnowledgeBase readKnowledgeBase(String fileName) throws Exception {

		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("com/drools/demo/" + fileName),ResourceType.DRL);
		System.out.println("--->adding drl: "+fileName);

		final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}

		return kbase;
	}

}
