public class Item{
	char ClassName;
	char[] Attributes;
	
	Item(char CN,char LW,char LD,char RW,char RD){
		this.ClassName=CN;
		Attributes=new char[4];
		Attributes[0]=LW;
		Attributes[1]=LD;
		Attributes[2]=RW;
		Attributes[3]=RD;
	}
	
	void show(){
		System.out.println("CN="+ClassName+" LW="+Attributes[0]+" LD="
				+Attributes[1]+" RW="+Attributes[2]+" RD="+Attributes[3]);
	}
}