package co.drools.demo.test;

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

import co.drools.model.SeniorClass;
import co.drools.model.Students;
import junit.framework.TestCase;

public class DroolsDemosTest extends TestCase {
	KnowledgeBase kbase = null;
	StatefulKnowledgeSession ksession = null;

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
	
	public void testSearchData() {
		SeniorClass sc = new SeniorClass();
		List<Students> st = new ArrayList<Students>();
		
		Students st1 = new Students();
		st1.setName("Hemant");
		st1.setMaths(70);
		st1.setChemistry(91);
		st1.setEnglish(80);
		st1.setPhysics(89);
		
		Students st2 = new Students();
		st1.setName("Rakesh");
		st1.setMaths(70);
		st1.setChemistry(80);
		st1.setEnglish(80);
		st1.setPhysics(92);
		
		Students st3 = new Students();
		st1.setName("Himanshu");
		st1.setMaths(30);
		st1.setChemistry(80);
		st1.setEnglish(80);
		st1.setPhysics(50);
		
		st.add(st1);
		st.add(st2);
		st.add(st3);
		
		sc.setStud(st);
		
		try {
			kbase = readKnowledgeBase("ClassResult.drl");
			ksession = kbase.newStatefulKnowledgeSession();
			ksession.insert(sc);
			ksession.fireAllRules();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
