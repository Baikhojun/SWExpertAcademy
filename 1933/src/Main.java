import java.util.Scanner;

public class Main {

		public static void main(String[] args) {
			
			/*
			 *   1 : 1
			 *   2 : 1 2
			 *   3 : 1 3
			 *   4 : 1 2 4
			 *   5 : 1 5
			 *   6 : 1 2 3 6
			 *   7 : 1 7
			 *   8 : 1 2 4 8
			 *   9 : 1 3 9
			 *   
			 *   약수를 구하는것은   
			 *   2 3 5 7 을 순서대로 나눠본다.....
			 *   나눠서 값이 1이면 정지 
			 *  예를들어 
			 *  100  2로나눠
			 *  50  2로나눠
			 *  25  2로 x 3x 5o
			 *  5  2x 3x 5 o
			 *  1
			 *  
			 *  125 5o
			 *  25  5o
			 *  5   5o
			 *  1
			 *  
			 *  81 23
			 */
			
			Scanner sc = new Scanner(System.in);
			
			// 1. 기본적인 풀이법
			int num1 = sc.nextInt();
			
			
		
			for(int i=1 ; i*2 <= num1 ; i++)
			{
				if( num1 % i==0)
				System.out.print(i+" " );
				
			
			}
			System.out.print(num1);
			
			
//		
//			
//			// 2. 메모리 절약 풀이법 첫번째 약수는 기존 수의 반만 출력된다.
//			int num2 = sc.nextInt();
//			
//			for(int i=1; i*i<=num2 ;i++)
//			{
//				if(num2%i==0)
//				 {
//					System.out.print(i+" " );
//				 }
//				
//				if(i*i==num2)          //제곱수 확인
//					{
//					System.out.print("result"+i+" " );
//					}
//			}
//		    
			
		
		}
}

