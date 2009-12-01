import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.yars.nx.Node;
import org.semanticweb.yars.nx.Nodes;
import org.semanticweb.yars.nx.parser.Callback;

import com.ontologycentral.ldspider.Crawler;
import com.ontologycentral.ldspider.hooks.error.ErrorHandler;
import com.ontologycentral.ldspider.hooks.error.ErrorHandlerLogger;
import com.ontologycentral.ldspider.hooks.fetch.FetchFilterRdfXml;
import com.ontologycentral.ldspider.hooks.links.LinkFilterDefault;


public class FetchTEST {
public static void main(String[] args) throws URISyntaxException {
	List<URI> seeds = new ArrayList<URI>();
	seeds.add(new URI("http://ld2sd.deri.org/dady/publisher/test-dataset.rdf"));
	
	
	Crawler c = new Crawler();
	
	ErrorHandler eh = new ErrorHandlerLogger();
	c.setErrorHandler(eh);
	
	NodeCollector nc = new NodeCollector();
	c.setOutputCallback(nc);
	c.setLinkSelectionCallback(new LinkFilterDefault(eh));
	c.setFetchFilter(new FetchFilterRdfXml(eh));
	c.evaluate(seeds, 0, 1);
	c=null;
	nc=null;
	
	c = new Crawler();
	
	eh = new ErrorHandlerLogger();
	c.setErrorHandler(eh);
	
	nc = new NodeCollector();
	c.setOutputCallback(nc);
	c.setLinkSelectionCallback(new LinkFilterDefault(eh));
	c.setFetchFilter(new FetchFilterRdfXml(eh));
	c.evaluate(seeds, 0, 1);
	
	
	System.out.println(nc.getContent());
}
}

class NodeCollector implements Callback{

    
    private HashSet<Nodes> _content;

    public NodeCollector(){
	_content = new HashSet<Nodes>();
    }
    @Override
    public void endDocument() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void processStatement(Node[] arg0) {
	_content.add(new Nodes(arg0[0],arg0[1],arg0[2]));
	
    }

    @Override
    public void startDocument() {
	// TODO Auto-generated method stub
	
    }
    
    public Set<Nodes> getContent(){
	return _content;
    }
}
