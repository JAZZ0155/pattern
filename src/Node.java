import java.util.*;

public class Node{

	int index;
	char Label;
	Vector<Item> Examples;
	Vector<Integer> Attributes;
	int Decision_Attribute;
	Vector<Node> Children;
	
	public Node(Vector<Item> Examples,Vector<Integer> Attributes){
		this.index=++DTreeTest.NodeNum;
		this.Label=0;
		this.Examples=Examples;
		this.Attributes=Attributes;
		this.Decision_Attribute=-1;
		this.Children=new Vector<Node>();
	}
	
	public Node(char label){
		this.index=++DTreeTest.NodeNum;
		this.Label=label;
		this.Examples=null;
		this.Decision_Attribute=-1;
		this.Attributes=null;
		this.Children=null;
	}
	
	void Rec()
	{
		System.out.println(this.index+"�Žڵ�");
		if(ID3(this.Examples,this.Attributes)==true)//�ýڵ�ΪҶ�ӽڵ㣬�ó�����
		{
			System.out.println("�ýڵ�ΪҶ�ӽڵ�");
			return;
		}
		else
		{
			for(int i=0;i<this.Children.size();i++)
			{
				Node temp=this.Children.elementAt(i);
				temp.Rec();
			}
		}
	}
	
	boolean ID3(Vector<Item> exa,Vector<Integer> att){	//����true����ýڵ�ΪҶ�ӽڵ�
		
		if(this.Label!=0)
		{
			return true;
		}
		
		char c=test();
		if('L'==c)
		{
			this.Label='L';
			return true;
		}
		else if('R'==c)
		{
			this.Label='R';
			return true;
		}
		
		if(att.size()==0)
		{
			this.Label=this.general();
			return true;
		}
		
		//�ҷ���������õ�����
		double max=-1;
		double temp=0;
		int point=0;
		if(att.size()!=1)
		{
			for(int i=0;i<att.size();i++)
			{
				temp=Tools.gain(exa, att.elementAt(i));
				System.out.println("��������"+att.elementAt(i)+"����Ϣ����Ϊ"+temp);
				if(temp>max)
				{
					point=i;
					max=temp;
				}
			}
		}
		this.Decision_Attribute=att.elementAt(point);
		System.out.println("��������Ϊ"+att.elementAt(point)+"��Ҳ����"+DTreeTest.ATTR[this.Decision_Attribute]);
		
		//���ݸ����Դ����ӽڵ�
		Vector<Integer> SubAtt=(Vector<Integer>) att.clone();
		SubAtt.removeElementAt(point);
		Vector<Item> exa1=new Vector<Item>();
		Vector<Item> exa2=new Vector<Item>();
		Vector<Item> exa3=new Vector<Item>();
		Vector<Item> exa4=new Vector<Item>();
		Vector<Item> exa5=new Vector<Item>();
		
		for(int i=0;i<exa.size();i++)
		{
			Item it=exa.elementAt(i);
			switch(it.Attributes[this.Decision_Attribute])
			{
			case '1':
				exa1.add(it);
				break;
			case '2':
				exa2.add(it);
				break;
			case '3':
				exa3.add(it);
				break;
			case '4':
				exa4.add(it);
				break;
			case '5':
				exa5.add(it);
				break;
			}
		}
		
		Node n;
		
		if(exa1.size()!=0)
			n=new Node(exa1,SubAtt);
		else
			n=new Node(this.general());
		this.Children.add(n);
		System.out.println("������"+n.index+"�Žڵ㣬����"+exa1.size()+"��Item��"+SubAtt.size()+"������");
		
		if(exa2.size()!=0)
			n=new Node(exa2,SubAtt);
		else
			n=new Node(this.general());
		this.Children.add(n);
		System.out.println("������"+n.index+"�Žڵ㣬����"+exa2.size()+"��Item��"+SubAtt.size()+"������");
		
		if(exa3.size()!=0)
			n=new Node(exa3,SubAtt);
		else
			n=new Node(this.general());
		this.Children.add(n);
		System.out.println("������"+n.index+"�Žڵ㣬����"+exa3.size()+"��Item��"+SubAtt.size()+"������");
		
		if(exa4.size()!=0)
			n=new Node(exa4,SubAtt);
		else
			n=new Node(this.general());
		this.Children.add(n);
		System.out.println("������"+n.index+"�Žڵ㣬����"+exa4.size()+"��Item��"+SubAtt.size()+"������");
		
		if(exa5.size()!=0)
			n=new Node(exa5,SubAtt);
		else
			n=new Node(this.general());
		this.Children.add(n);
		System.out.println("������"+n.index+"�Žڵ㣬����"+exa5.size()+"��Item��"+SubAtt.size()+"������");
		
		return false;
	}
	
	char test(){		//L��R��0
		Vector<Item> temp=this.Examples;
		char c=temp.elementAt(0).ClassName;
		for(int i=1;i<temp.size();i++)
		{
			if(temp.elementAt(i).ClassName!=c)
			{
				return '0';
			}
		}
		return c;
	}
	
	char general(){		//�ձ��target_attributeֵ
		int left=0,right=0;
		Vector<Item> temp=this.Examples;
		for(int i=0;i<temp.size();i++)
		{
			if(temp.elementAt(i).ClassName=='L')
				left++;
			else if(temp.elementAt(i).ClassName=='R')
				right++;
		}
		if(left>=right)
			return 'L';
		else if(right>left)
			return 'R';
		return 'L';
	}
	
}