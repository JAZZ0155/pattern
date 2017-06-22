import java.util.Vector;

public class Tools {
	static double entropy(Vector<Item> items)
	{	
		int sum=items.size();
		double left=0,right=0;
		
		for(int i=0;i<items.size();i++)
		{
			if(items.elementAt(i).ClassName=='L')
				left++;
			else if(items.elementAt(i).ClassName=='R')
				right++;
		}
		
		if(left==0||right==0)
		{
			return 0;
		}
		
		double ent=-(left/sum)*Math.log(left/sum)/Math.log(2)
				-(right/sum)*Math.log(right/sum)/Math.log(2);
				
		return ent;
		
	}
	
	static double gain(Vector<Item> items,int attribute){
		
		double GainSA;
		
		Vector<Item> temp1=new Vector<Item>();
		Vector<Item> temp2=new Vector<Item>();
		Vector<Item> temp3=new Vector<Item>();
		Vector<Item> temp4=new Vector<Item>();
		Vector<Item> temp5=new Vector<Item>();

		for(int i=0;i<items.size();i++)
		{
			Item it=items.elementAt(i);
			switch(it.Attributes[attribute])
			{
			case '1':
				temp1.add(it);
				break;
			case '2':
				temp2.add(it);
				break;
			case '3':
				temp3.add(it);
				break;
			case '4':
				temp4.add(it);
				break;
			case '5':
				temp5.add(it);
				break;
			}
		}
		
		GainSA=entropy(items)-entropy(temp1)*temp1.size()/items.size()
				-entropy(temp2)*temp2.size()/items.size()
				-entropy(temp3)*temp3.size()/items.size()
				-entropy(temp4)*temp4.size()/items.size()
				-entropy(temp5)*temp5.size()/items.size();
		
		return GainSA;
		
	}
	
}
