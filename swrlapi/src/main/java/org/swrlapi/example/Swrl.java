package org.swrlapi.example;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

import java.io.File;
import java.util.Scanner;

public class Swrl
{
  public static void main(String[] args)
  {
    try {
      OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
      OWLOntology ontology = ontologyManager.loadOntologyFromOntologyDocument(new File("D:\\jee-oxygen\\sn-twitter.owl"));

      SQWRLQueryEngine queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);
      Scanner scanner = new Scanner(System.in);
      
      // test queries
      // tweeted(User1, ?tweet) -> sqwrl:select(?tweet)
      
      while (true)
      {
    	  System.out.println("Shkruaj query: ");
    	  String inputQuery = scanner.nextLine();
    	  try {
              SQWRLResult result = queryEngine.runSQWRLQuery("q1", inputQuery);
              while (result.next())
              {
                System.out.println("c: " + result);
              }
    	  } catch (Exception ex) {
    		  System.out.println("c: " + ex.getMessage());
    	  }
      }
    } catch (OWLOntologyCreationException ex) {
      System.err.println("Error creating OWL ontology: " + ex.getMessage());
      System.exit(-1);
    } catch (RuntimeException ex) {
      System.err.println("Error starting application: " + ex.getMessage());
      System.exit(-1);
    }
  }
}
