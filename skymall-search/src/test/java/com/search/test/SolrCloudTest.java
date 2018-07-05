/**  
 * <p>Title: SolrTest.java</p>  
 * <p>Description: </p>   
 * @author shianliang  
 * @date 2018年6月5日  
 * @version 1.0  
 */ 
package com.search.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**  
 * solr集群版测试
 * <p>Title: SolrTest</p>  
 * <p>Description: </p>  
 * @author shianliang 
 * @date 2018年6月5日  
 */
public class SolrCloudTest {

	
	
	
	
	/**添加元素到索引库
	 * 
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年6月5日  
	 * @version 1.0
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Test
	public void add() throws SolrServerException, IOException{
		
		//创建一个solr集群链接
		//需要一个zkHost参数,此参数就是zookeeper的地址列表，多个用逗号隔开
		String zkHost="192.168.203.18:2181,192.168.203.18:2182,192.168.203.18:2183";
		CloudSolrServer solrServer=new CloudSolrServer(zkHost);
		
		//设置默认的collection
		solrServer.setDefaultCollection("collection2");
		
		//创建一个文本对象
		SolrInputDocument document= new SolrInputDocument();
		//向索引中添加域
		document.addField("id", "6621");
		document.addField("item_title", "华仔2");
		
		//把文本添加到索引库
		solrServer.add(document);
		
		//提交
		solrServer.commit();
		
	}
	
	/**
	 * 删除元素
	 * <p>Description: </p> 
	 * @author shianliang  
	 * @date 2018年6月5日  
	 * @version 1.0
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Test
	public void del() throws SolrServerException, IOException{
		
		String zkHost="192.168.203.18:2181,192.168.203.18:2182,192.168.203.18:2183";
		
		CloudSolrServer solrServer=new CloudSolrServer(zkHost);
		
		solrServer.setDefaultCollection("collection2");
		
//		solrServer.deleteByQuery("item_title:华仔");//会删除item_title域中含有“华仔”的所有元素
		solrServer.deleteById("66");
		
		solrServer.commit();
		
	}
	
	
	
}
