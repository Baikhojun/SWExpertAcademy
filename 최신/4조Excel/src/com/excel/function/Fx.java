package com.excel.function;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTable;

public class Fx {
   /*
    * 함수 완성
    * 
    * 
    */
   
   // 매개변수로 사용한 변수들 
   // function : =다음에 오는 함수 판별용 String(3자리 영어 대문자)
   // str : 선택한 셀에 있는 value
   // tb : JTable
   
   
   
   // 사칙연산 계산 메서드
   public void calculate(String function,String str,JTable tb) throws Exception{
      
         String tmp = str.substring(5, str.length());
            
           String[] stArr = (tmp.replaceAll("[^\\da-zA-Z]","\\.").split("\\."));
           for(int i=0;i<stArr.length;i++) {
        	   if(i%2==0) {
        		   stArr[i]=String.valueOf(((Integer.parseInt(stArr[i]))-1));
        	   }else {
        		   stArr[i]=String.valueOf((stArr[i].charAt(0)-65));
        	   }
           }
           
           
           double sum = 0;
           if (str.contains(",")) {
              
              // 숫자와 소숫점만 남기고 나머지 제거
             
              switch(function){
              case "SUM":
                 sum=sumComma(stArr,tb,sum);
                 break;
              case "SUB":
                 sum=subComma(stArr,tb,sum);
                 break;
              case "MUT":
                 sum=mutComma(stArr,tb,sum);
                 break;
              case "DIV":
                 sum=divComma(stArr,tb,sum);
                 break;
              default:
              }
              
              
           }
           if (str.contains(":")) {
              // 범위 지정 계산식 일떄
              int cMax, cMin;
              int rMax, rMin;

              // 두 셀의 좌표를 임의의 순서로 변수에 담음
              rMax = Math.max((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
              rMin = Math.min((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
              cMax = Math.max((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));
              cMin = Math.min((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));

              // 작은 좌표에서 큰 좌표까지의 범위 순서대로 계산함
              switch(function){
              case "SUM":
                 sum=sumColon(rMin,rMax,cMin,cMax,tb,sum);
                 break;
              case "SUB":
                 sum=subColon(rMin,rMax,cMin,cMax,tb,sum);
                 break;
              case "MUT":
                 sum=mutColon(rMin,rMax,cMin,cMax,tb,sum);
                 break;
              case "DIV":
                 sum=divColon(rMin,rMax,cMin,cMax,tb,sum);
                 break;
              case "AVE":
            	 sum=countAverage(rMin, rMax, cMin, cMax, tb, sum);
              default:
              }
              
              
           }
           tb.setValueAt(String.valueOf(sum), tb.getSelectedRow(), tb.getSelectedColumn());
           
           
         
   }
   
   
   // 오늘날짜 출력 메서드
    public void today(JTable tb) {
         Calendar today = Calendar.getInstance();

         String result = "";// str.length());
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy넌 MM월 dd일");
         result = sdf.format(today.getTime());
         System.out.println(result);

         tb.setValueAt(result,tb.getSelectedRow(),tb.getSelectedColumn());

    }
    
    
    
    // 입력한 날짜 출력 메서드
    
    public void date(JTable tb,String str) {
         Calendar today = Calendar.getInstance();

         String result = "";
         str = str.substring(6, str.length() - 1);

         String[] sArr = str.split(",");
         int year = Integer.parseInt(sArr[0]);
         int month = (Integer.parseInt(sArr[1])) - 1;
         int date = Integer.parseInt(sArr[2]);

         today.set(year, month, date);
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         result = sdf.format(today.getTime());
         System.out.println(result);
         
         tb.setValueAt(result,tb.getSelectedRow(),tb.getSelectedColumn());
      }

    
    // 루프문 출력 메서드
	 
 	 public void loop(JTable tb,String str) {
 	      boolean result = false;
 	      int cpt = 0;
 	      str = str.substring(4, str.length() - 1);
 	      String[] arr = {};
 	         
 	      if (str.contains(",") || str.contains(">")) {
 	    	  
 	    	   String[] stArr = (str.replaceAll("[^\\da-zA-Z]","\\.").split("\\."));
 	           for(int i=0;i<stArr.length;i++) {
 	        	   if(i%2==0) {
 	        		   stArr[i]=String.valueOf(((Integer.parseInt(stArr[i]))-1));
 	        	   }else {
 	        		   stArr[i]=String.valueOf((stArr[i].charAt(0)-65));
 	        	   }
 	           }
 	    	  
 	    	  for (int i = 0; i < stArr.length; i = i + 4) {

 	    		  cpt=((String)tb.getValueAt(Integer.parseInt(stArr[i]),Integer.parseInt(stArr[i+1]))).compareTo
 	    				  ((String)tb.getValueAt(Integer.parseInt(stArr[i+2]),Integer.parseInt(stArr[i+3])));
 	    		  if(cpt>0){
 	    			  result=true;
 	    		  }
 	           }
 	    	  
 	    	  tb.setValueAt(String.valueOf(result),tb.getSelectedRow(),tb.getSelectedColumn());
 	    	  
 	       }else if (str.contains("<")) {
 	    	   
 	    	   String[] stArr = (str.replaceAll("[^\\da-zA-Z]","\\.").split("\\."));
	           for(int i=0;i<stArr.length;i++) {
	        	   if(i%2==0) {
	        		   stArr[i]=String.valueOf(((Integer.parseInt(stArr[i]))-1));
	        	   }else {
	        		   stArr[i]=String.valueOf((stArr[i].charAt(0)-65));
	        	   }
	           }
 	            
 	            
 	    	    for (int i = 0; i < stArr.length; i = i + 4) {

 		    		  cpt=((String)tb.getValueAt(Integer.parseInt(stArr[i]),Integer.parseInt(stArr[i+1]))).compareTo
 		    				  ((String)tb.getValueAt(Integer.parseInt(stArr[i+2]),Integer.parseInt(stArr[i+3])));
 		    		  if(cpt<0){
 		    			  result=true;
 		    		  }
 		           }
 		    	  
 		    	  tb.setValueAt(String.valueOf(result),tb.getSelectedRow(),tb.getSelectedColumn());
 	       }
 	      

 	   }
    
    
   
   
   
   
    
    
   // 덧셈 추가 메서드
   
   public double sumComma(String[] stArr, JTable tb, double sum){
      for (int i = 0; i < stArr.length; i = i + 2) {

            String values = (String) tb.getValueAt(Integer.parseInt(stArr[i]),
                  Integer.parseInt(stArr[i + 1]));
            sum += Double.parseDouble(values);

         }
      return sum;
   }   
   public double sumColon(int rMin,int rMax,int cMin,int cMax,JTable tb,double sum){
      for (int i = rMin; i < rMax + 1; i++) {
            for (int j = cMin; j < cMax + 1; j++) {
               String values = (String) tb.getValueAt(i, j);
               System.out.println(values);
               sum += Double.parseDouble(values);
            }
         }
      return sum;
   }
   
   
   
   
   // 곱셈 추가 메서드
   
   public double mutComma(String[] stArr, JTable tb, double sum){
      sum=1;
      for (int i = 0; i < stArr.length; i = i + 2) {

            String values = (String) tb.getValueAt(Integer.parseInt(stArr[i]),
                  Integer.parseInt(stArr[i + 1]));
            sum *= Double.parseDouble(values);

         }
      return sum;
   }   
   public double mutColon(int rMin,int rMax,int cMin,int cMax,JTable tb,double sum){
      sum=1;
      for (int i = rMin; i < rMax + 1; i++) {
            for (int j = cMin; j < cMax + 1; j++) {
               String values = (String) tb.getValueAt(i, j);
               System.out.println(values);
               sum *= Double.parseDouble(values);
            }
         }
      return sum;
   }
   
   
   
   // 뺼셈 추가 메서드
   
   public double subComma(String[] stArr, JTable tb, double sum){
        sum = Double.parseDouble((String) tb.getValueAt(Integer.parseInt(stArr[0]), Integer.parseInt(stArr[1])));
      for (int i = 2; i < stArr.length; i = i + 2) {
            String values = (String) tb.getValueAt(Integer.parseInt(stArr[i]),
                  Integer.parseInt(stArr[i + 1]));
            sum -= Double.parseDouble(values);
         }
      return sum;
   }   
   public double subColon(int rMin,int rMax,int cMin,int cMax,JTable tb,double sum){
      for (int i = rMin; i < rMax + 1; i++) {
            for (int j = cMin; j < cMax + 1; j++) {
               String values = (String) tb.getValueAt(i, j);
               System.out.println(values);
               sum *= Double.parseDouble(values);
            }
         }
      sum+=Double.parseDouble((String)tb.getValueAt(rMin,cMin));
      return sum;
   }
   
   
   
   // 나눗셈 추가 메서드
   
   public double divComma(String[] stArr, JTable tb, double sum){
        sum = Double.parseDouble((String) tb.getValueAt(Integer.parseInt(stArr[0]), Integer.parseInt(stArr[1])));
      for (int i = 2; i < stArr.length; i = i + 2) {
            String values = (String) tb.getValueAt(Integer.parseInt(stArr[i]),
                  Integer.parseInt(stArr[i + 1]));
            sum /= Double.parseDouble(values);
         }
      return sum;
   }   
   public double divColon(int rMin,int rMax,int cMin,int cMax,JTable tb,double sum){
      for (int i = rMin; i < rMax + 1; i++) {
            for (int j = cMin; j < cMax + 1; j++) {
               if(i==rMin&&j==cMin){
                  sum+=Double.parseDouble((String)tb.getValueAt(rMin,cMin));
               }else{
                  String values = (String) tb.getValueAt(i, j);
                    System.out.println(values);
                    sum /= Double.parseDouble(values);
               }
               
            }
         }
      return sum;
   }
   
   
   // 평균 메서드
   public double countAverage(int rMin,int rMax,int cMin,int cMax,JTable tb,double sum){
	   	  int count=0;
	      for (int i = rMin; i < rMax + 1; i++) {
	            for (int j = cMin; j < cMax + 1; j++) {
	               String values = (String) tb.getValueAt(i, j);
	               System.out.println(values);
	               sum += Double.parseDouble(values);
	               count++;
	            }
	         }
	      return (Math.round((sum/count)*100))*0.01;
	   }
   
   
   
   // 대문자 변환 메서드
   public void toUpperCase(JTable tb,String str) {
       String result;
       str=str.substring(7,str.length()-1);
       result=str.toUpperCase();
       tb.setValueAt(result,tb.getSelectedRow(),tb.getSelectedColumn());

  }
   
   
   
   
   // 소문자 변환 메서드
   public void toLowerCase(JTable tb,String str) {
       String result;
       str=str.substring(7,str.length()-1);
       result=str.toLowerCase();
       tb.setValueAt(result,tb.getSelectedRow(),tb.getSelectedColumn());

  }
}