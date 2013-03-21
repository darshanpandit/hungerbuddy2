/**
 * 
 */
package com.pandit.process;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;



import com.pandit.core.Intent;
import com.pandit.core.IntentFiller;
import com.pandit.core.UtilFunctions;
import com.pandit.search.HungerConfig;
import com.pandit.search.IntentTranslatorSingleton;
import com.pandit.search.OutputWrapper;
import com.pandit.search.SearchHandlerSingleton;
import com.pandit.search.SearchParam;
import com.pandit.search.SearchResult;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

/**
 * @author Darshan
 * 
 */
public class QueryProcessSingleton {
    private static QueryProcessSingleton processSingleton;
    private IntentTranslatorSingleton intentTranslator;
    private SearchHandlerSingleton searchHandler;
    private String grammar;

    private LexicalizedParser lexicalizedParser;
    private TreebankLanguagePack treebankLanguagePack;
    private GrammaticalStructureFactory grammaticalStructureFactory;
    /**
     * 
     */
    public static QueryProcessSingleton getInstance(){
	if(processSingleton==null)
	    processSingleton= new QueryProcessSingleton();
	return processSingleton;
    }
    /**
     * 
     */
    private QueryProcessSingleton() {
	super();
	System.out.println("Initialized");
	intentTranslator = IntentTranslatorSingleton.intentTranslatorSingleton;
	searchHandler = SearchHandlerSingleton.searchHandlerSingleton;
	grammar = HungerConfig.model_path;
	String[] options = { "-maxLength", "80", "-retainTmpSubcategories" };
	System.out.println(HungerConfig.model_path);
	lexicalizedParser = LexicalizedParser.loadModel(grammar, options);
	treebankLanguagePack = new PennTreebankLanguagePack();
	grammaticalStructureFactory = treebankLanguagePack.grammaticalStructureFactory();
	
    }
    
    public OutputWrapper process(String query) throws IOException, ParseException{
	Tokenizer<? extends HasWord> toke = treebankLanguagePack.getTokenizerFactory()
		    .getTokenizer(new StringReader(query));
	List<? extends HasWord> sentence2 = toke.tokenize();
	Tree parse = lexicalizedParser.apply(sentence2);
	GrammaticalStructure grammaticalStructure = grammaticalStructureFactory.newGrammaticalStructure(parse);
	    Collection<TypedDependency> tdl = grammaticalStructure
		    .typedDependenciesCollapsedTree();
	    Intent intent = UtilFunctions.analyse_Step1(tdl, UtilFunctions.getRoot(tdl));
	    String message="Unable to completely parse your request. We all have limitations.";
	    SearchResult searchResult=null;
	    if(intent!=null){
		message="";
		if(intent.getRequestType()==null)
		 intent.setRequestType(Arrays.asList(query.split(" ")).iterator().next());
		int docCount = 10;
		       if(intent.getQuantifier()!=null){
			   docCount = Integer.parseInt(intent.getQuantifier());
		       }
		searchResult= searchHandler.search(intentTranslator.translate(intent), docCount);
		//TODO: Fill the intent for Response Generation
		IntentFiller intentFiller = IntentTranslatorSingleton.intentTranslatorSingleton.fillIntent(intent);
		message=new ResponseGenerator().generateResponse(intentFiller, intent, searchResult);
		
	    }
	    return(new OutputWrapper(query,message, searchResult));
    }
    
    
    

}
