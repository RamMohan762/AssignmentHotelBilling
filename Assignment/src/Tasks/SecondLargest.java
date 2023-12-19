package Tasks;

public class SecondLargest {

	public static void main(String[] args) {
		int[] a= {10,9,7,4,8,5};
		for(int i=0;i<a.length-1;i++) {
		  for(int j=1;j<a.length-i;j++) {
			  if(a[j]>a[j-1]) {
				  int temp=a[j];
				  a[j]=a[j-1];
				  a[j-1]=temp;
			  }
		  }
		}
		for(int b: a) {
			System.out.println(b);
		}
		System.out.println("The second largest element is "+a[1]);

	}

}
