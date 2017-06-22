import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class DTreeTest {
	
	static final int DATANUM=576;
	static final int ATTNUM=4;
	static final int LW=0,LD=1,RW=2,RD=3;
	static final String[] ATTR={"LW","LD","RW","RD"};
	Vector<Item> Examples;
	Vector<Item> TestExamples;
	Vector<Integer> Attributes;
	static int NodeNum=0;
	Node Root;
	
	DTreeTest(){	
		this.Examples=new Vector<Item>();
		this.TestExamples=new Vector<Item>();
		this.Attributes=new Vector<Integer>();
		Root=null;
		Attributes.add(LW);
		Attributes.add(LD);
		Attributes.add(RW);
		Attributes.add(RD);
	}
	
	void readFile(String filename,Vector<Item> items) throws IOException{
		String temp;
		char CN;
		char LW;
		char LD;
		char RW;
		char RD;
		int i = 0;
		
		BufferedReader bf;
		File file=new File(filename);
		bf=new BufferedReader(new FileReader(file));
		
		while((temp=bf.readLine())!=null)
		{
			CN=temp.charAt(0);
			LW=temp.charAt(2);
			LD=temp.charAt(4);
			RW=temp.charAt(6);
			RD=temp.charAt(8);
			Item it=new Item(CN,LW,LD,RW,RD);
			items.add(it);
			i++;
		}
		
		bf.close();
	}
	
	float test(Vector<Item> items,Node tree){
		
		float count=0;
		Item it;
		char c;
		for(int i=0;i<items.size();i++)
		{
			it=items.elementAt(i);
			c=this.predict(it, tree);
			if(c==it.ClassName)
				count++;
		}
		System.out.println("ÕýÈ·ÂÊ£º"+count/items.size());
		
		return 0;
		
	}
	
	char predict(Item it,Node tree){
		
		Node temp=tree;
		
		while(temp.Label==0)
		{
			switch(it.Attributes[temp.Decision_Attribute])
			{
			case '1':
				temp=temp.Children.elementAt(0);
				break;
			case '2':
				temp=temp.Children.elementAt(1);
				break;
			case '3':
				temp=temp.Children.elementAt(2);
				break;
			case '4':
				temp=temp.Children.elementAt(3);
				break;
			case '5':
				temp=temp.Children.elementAt(4);
				break;
			}
		}
		
		return temp.Label;
		
	}
	
	void showExamples(){
		for(int i=0;i<DATANUM;i++)
		{
			Item it=Examples.elementAt(i);
			it.show();
		}
	}
	
	public static void main(String args[]) throws IOException{
		DTreeTest dtt=new DTreeTest();
		dtt.readFile("learn.data",dtt.Examples);
		dtt.readFile("test.data", dtt.TestExamples);
		Node n=new Node(dtt.Examples,dtt.Attributes);
		dtt.Root=n;
		n.Rec();
		
		System.out.println();
		System.out.println("²âÊÔ.............");
		System.out.println();
		
		dtt.test(dtt.TestExamples, dtt.Root);
	}
}