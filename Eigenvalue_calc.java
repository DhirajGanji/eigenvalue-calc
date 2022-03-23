import java.util.*;
import Jama.*;

public class Eigenvalue_calc{
	List<int[]> ll;
	List<int[]> ll1;
	double[][] finalarr;
	int t;
	int u;
	int no;
	Matrix mat;

	public Eigenvalue_calc(){
		ll = new ArrayList<>();
		ll1 = new ArrayList<>();
	}

	public void populate(int n, int sites){
		if (n < -1 || n > (2*sites)) {
			throw new ArrayIndexOutOfBoundsException();
		}
		no = n;
		if (n == -1){
			System.out.println("\nArray for all n values= ");
			ll = ll1;
			finalarr = new double[ll.size()][ll.size()];
		    for (double[] row : finalarr){
                Arrays.fill(row, 0);
		    }
		    return;
		}
		System.out.println("\nArray for n = "+ n);
		for(int i = 0; i < ll1.size(); i++){
			int sum = Arrays.stream(ll1.get(i)).sum();
			if (sum == n){
				ll.add(ll1.get(i));
			}
		}
		finalarr = new double[ll.size()][ll.size()];
		for (double[] row : finalarr){
            Arrays.fill(row, 0);
		}
	}

	public void hamiltonian(){
		for (int i = 0; i < ll.size(); i++){
			int[] arr = ll.get(i);
			for (int j = arr.length - 2; j >= 0; j--){
				int b = j - 2;
				if (b < 0){
					b = arr.length - 2;
				}
				if (arr[j] == 1 && arr[b] == 0){
					int[] arr2 = arr.clone();
					arr2[j] = 0;
					arr2[b] = 1;
					for (int ii = 0; ii < ll.size(); ii++){
					int[] aa = ll.get(ii);
				    if (Arrays.equals(aa,arr2)){
				    	finalarr[i][ii] = t;
				    }
			    }
				}
				j--;
			}
			for (int j = arr.length - 1; j >= 1; j--){
				int b = j - 2;
				if (b < 0){
					b = arr.length - 1;
				}
				if (arr[j] == 1 && arr[b] == 0){
					int[] arr2 = arr.clone();
					arr2[j] = 0;
					arr2[b] = 1;
					for (int ii = 0; ii < ll.size(); ii++){
					int[] aa = ll.get(ii);
				    if (Arrays.equals(aa,arr2)){
				    	finalarr[i][ii] = t;
				    }
			    }
				}
				j--;
			}
			for (int j = 0; j < arr.length; j++){
				int b = (j+2)%(arr.length);
				if (arr[j] == 1 && arr[b] == 0){
					int[] arr2 = arr.clone();
					arr2[j] = 0;
					arr2[b] = 1;
					for (int ii = 0; ii < ll.size(); ii++){
					int[] aa = ll.get(ii);
				    if (Arrays.equals(aa,arr2)){
				    	finalarr[i][ii] = t;
				    }
			    }
				}
				j++;
			}
			for (int j = 1; j < arr.length; j++){
				int b = (j+2)%(arr.length);
				if (arr[j] == 1 && arr[b] == 0){
					int[] arr2 = arr.clone();
					arr2[j] = 0;
					arr2[b] = 1;
					for (int ii = 0; ii < ll.size(); ii++){
					int[] aa = ll.get(ii);
				    if (Arrays.equals(aa,arr2)){
				    	finalarr[i][ii] = t;
				    }
			    }
				}
				j++;
			}
			
		}
		for (int i = 0; i < ll.size(); i++){
			int[] arr = ll.get(i);
			for (int j = 0; j < arr.length; j++){
				if (arr[j] == 1 && arr[j+1] == 1){
					finalarr[i][i] += u;
				}
				j++;
			}
		}
	}

	private void arraygenerate(int sites){
		int[] site1 = {0,0};
		int[] site2 = {0,1};
		int[] site3 = {1,0};
		int[] site4 = {1,1};
		boolean op = true;
		List<int[]> ee = new ArrayList<>();
		ee.add(site1);
		ee.add(site2);
		ee.add(site3);
		ee.add(site4);
		Random rando = new Random();
		List<Integer> lll = new ArrayList<>(4);
		int sizer = sites*sites*sites*sites;
		if (sites == 1){
			sizer = 4;
		}
		while (ll1.size() < sizer){
			lll.clear();
			for (int q = 0; q < sites; q++){
				int randoo = rando.nextInt(4);
				int[] memee = ee.get(randoo);
				lll.add(memee[0]);
				lll.add(memee[1]);
			}
			int[] meme = lll.stream().mapToInt(Integer::intValue).toArray();
			for (int o = 0; o < ll1.size(); o++){
				int[] lol = ll1.get(o);
				if (Arrays.equals(meme, lol)){
					op = false;
					break;
				}
			}
			if (op){
				ll1.add(meme);
			}
			else{
				op = true;
				continue;
			}
		}
	}

	private void printarr(){
		System.out.println("");
		int[] aa = new int[ll.size()];
		for(int i = 0; i < finalarr.length; i++){
			if (i < ll.size()){
					aa = ll.get(i);
			}
			for (int ii = 0; ii < aa.length; ii++){
				System.out.print(aa[ii]);
			}
			for(int j = 0; j < finalarr.length; j++){
				System.out.print(" ");
				System.out.print(String.format("%.0f",finalarr[i][j]) + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	private void eigen(){
		if (no == -1){
			System.out.println("Eigenvalues for all n values= ");
		}
		else{
			System.out.println("Eigenvalues for n = " + no + "\n");

		}
		mat = new Matrix(finalarr);
		double[] mm = mat.eig().getRealEigenvalues();
		List<Double> list1 = new ArrayList<>();
		for (int i = 0; i < mm.length; i++){
			list1.add(mm[i]);
		}
		Collections.sort(list1);
		for (int i = 0; i < list1.size(); i++){
			System.out.println(String.format("%.10f",list1.get(i)));
		}
	}

	public static void main(String args[]){
		//try{
			Eigenvalue_calc e = new Eigenvalue_calc();
		    Scanner scan = new Scanner(System.in);
		    Scanner intscan = new Scanner(System.in);
		    System.out.print("Enter the number of sites- ");
		    int sites = intscan.nextInt();
		    System.out.print("\nEnter the number of elements in the sites " +
		    	"Enter 'all' to return the matrix for all the electron sites)- ");
		    String input = scan.nextLine();
		    int n = 0;
		    if (input.equals("all")){
		    	n = -1;
		    }
		    else{
		    	n = Integer.parseInt(input);
		    }
		    System.out.print("\nEnter your choice for the 't' value- ");
		    e.t = intscan.nextInt();
		    System.out.print("\nEnter your choice for the 'u' value- ");
		    e.u = intscan.nextInt();
		    e.arraygenerate(sites);
		    e.populate(n, sites);
		    e.hamiltonian();
		    e.printarr();
		    e.eigen();
		    System.out.println(e.finalarr.length);
	}
}