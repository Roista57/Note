package day6;

public class Montecarlo {

	public static void main(String[] args) {
		double count = 0.0;
        for(double i=1; i<30001; i++) {  //예제에서와 같이 30000번 실행
            double x = Math.random();  //x에 난수 0.0~1.0사이의 난수 생성
            double y = Math.random();  //y에 난수 생성
            if(x*x+y*y<=1) {   //x^2 + y^2의 값이 1보다 작거나 같으면 count++;
                count++;
            }
            if(i%1000==0) {   //1000 단위로 값을 출력
                System.out.printf("%.4f\n", (count/i)*4); //소수점 4번째 자리까지 출력
            }
        }
	}

}
