package edu.kh.poly.ex1.model.service;

import edu.kh.poly.ex1.model.vo.Car;
import edu.kh.poly.ex1.model.vo.Spark;
import edu.kh.poly.ex1.model.vo.Truck;

public class Example1 {
	
	// 다형성(polymorphism) : 상속을 이용한 기술로..
	//						부모 타입의 참조 변수 하나가
	//						여러 타입의 자식 객체를 참조할 수 있음
	
	public void ex1() {
		// 업 캐스팅 : 부모 타입 참조 변수 = 자식 객체;
		
		Car c1 = new Car();
		// 부모 참조변수 = 부모 객체 >>> 부모 참조변수로 부모 객체를 참조함
		
		// *** 업 캐스팅 확인 ***
		Car c2 = new Truck();
		// 부모 참조변수 = 자식 객체 인데 오류가 발생하지 않았음
		
		// Truck이 Car로부터 상속받은 메서드
		c2.setWheel(4);
		c2.setSeat(1);
		c2.setFuel("휘발유");
		
		// c2.setWeight(1.5); 
		// The method setWeight(double) is undefined for the type Car
		// Car에는 setWeight()가 정의되지 않았음.
		// Truck을 참조하고 있으나, Truck이 아닌 Car의 메서드만 사용할 수 있음
		// 참조변수가 부모타입(Car)이기 때문에 참조하는 객체가 자식이어도 부모 부분만 참조 가능
		
		
		// 바인딩
//		System.out.println( c1.toString() );
		
		// 정적 바인딩 : 프로그램 실행 전 메서드 호출부 - 메서드 코드 연결
		//			  부모 타입으로 메서드 호출 - 부모 클래스 메서드 연결
		
		// 동적 바인딩 : 프로그램 실행 중
		//			  참조하는 객체의 실제 타입의 오버라이딩된 메서드 연결
		//				     	  (Truck)
		System.out.println( c2.toString() );
						// Car c2
		
	}
	
	
	public void ex2() {
		// 다형성 (업캐스팅), 동적 바인딩, 객체 배열
		
		Car[] arr = new Car[3];
					// Car 참조 변수 3개짜리 배열
					// Car 부모타입 참조변수 3개짜리 배열
		
		arr[0] = new Car(4, 9, "경유"); //  Car 객체가 만들어짐(new Car)
		// arr[0]은 Car 타입의 참조변수
		arr[1] = new Truck(4, 9, "경유", 5.0); //  Truck 객체가 만들어짐(new Truck)(업캐스팅)
		// arr[1]은 Car 타입의 참조변수 Truck 객체를 참조중
		arr[2] = new Spark(4, 4, "휘발유", 0.5); // Spark 객체(업캐스팅) 
		// arr[2]은 Car 타입의 참조변수, Car 객체를 참조
		
		
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
			// arr[i] = 참조변수;
			// print() 메서드에 참조변수를 작성하면 자동으로 toString으로 호출을 함
			// arr[i]나 arr[i].toString이나 같음
			
		}
		
		// 업캐스팅 + 동적바인딩의 장점
		// -> 코드 재사용성 증가, 코드 길이 감소
		printCar(arr[0]); // Car
		printCar(arr[1]); // Truck
		printCar(arr[2]); // Spark
	}
	
	
	// 매개변수의 다형성 적용
	public void printCar(Car c) {
		System.out.println("자동차 정보 > " + c );
		// 전달받은 객체가 자식 객체이고,
		// toString을 오버라이딩 했다면
		// 동적바인딩을 이용해서 자식 toString()을 호출
		
	}

//	public void printTruck(Truck c) {
//		System.out.println("자동차 정보 > " + c );
//	}
//
//	public void printSpark(Spark c) {
//		System.out.println("자동차 정보 > " + c );
//	}


	public void ex3() {
		
		// 다운 캐스팅
		// - 업 캐스팅이 적용된 상황에서 ( 부모 참조 변수 = 자식 객체 )
		// - 부모 참조 변수를 자식 타입으로 바꿔(강제형변환, 얕은복사)
		//   자식 객체를 온전하게 참조할 수 있게 만듦
		
		Car c1 = new Spark(4, 4, "휘발유", 0.5);
		Car c2 = new Truck(12, 3, "경유", 20);
		
		
		Car c3 = new Car(4,5,"휘발유");
		
		((Spark)c1).dc();
		// (Spark) 연산자
		// . 연산자
		// . 연산자가 형변환 연산자보다 우선순위가 높음
		// 따라서 (Spark)c1.dc(); 라고 적으면 c1.dc()가 먼저 실행되므로 오류가 발생함
		// ((Spark)c1).dc(); 라고 적어서 ((Spark)c1)이 먼저 처리되게 만들어야 함
		// = 형변환 먼저 진행
		// -> 부모 타입(Car)이었던 c1을 자식 타입(Spark)로 다운캐스팅
		
		// 얕은 복사 + 강제 형변환
		Truck t2 = (Truck)c2;
		
		t2.loading();
		
		
		
		System.out.println("--------------------------");
		
		// instanceof 연산자
		// - 참조하는 객체의 타입을 검사하는 연산자
		//   맞으면 true, 아니면 false
		System.out.println( c1 instanceof Spark);
		System.out.println( c2 instanceof Spark);
		System.out.println( c3 instanceof Spark);
		
		check(c1);
		check(c2);
		check(c3);
		
		// 다운 캐스팅을 잘못한 경우......
//		( (Truck)c3 ).loading();
		
		// 코드 상 오류는 없다
		// ClassCastException : 형변환예외
		// -> 다운 캐스팅이 잘못된 경우 발생함
		// instanceof연산자로 다운캐스팅할 타입이 맞는지 확인부터 하자
		
		if(c3 instanceof Truck) {
			( (Truck)c3 ).loading();
		} else {
			System.out.println("c3는 트럭 객체가 아닙니다.");
		}
	}
	
	
	public void ex4() {
		// instanceof 연산자 사용 시 검사 순서에 대한 문제점
		
		// - instanceof 연산자
		// 1) 참조하는 객체의 타입을 검사하는 연산자
		// 2) 참조하는 객체가 특정 타입을 상속 받았는지 검사하는 용도로 사용 가능
		
		
		
		
		Car c = new Spark(); // 업캐스팅 상태
		
		if(c instanceof Car) {
			// c가 참조하는 객체는 Spark이지만
			// 업캐스팅 상태이기 때문에 car로도 인식됨
			
			System.out.println("부모 Car타입 입니다.");
		} else {
			((Spark)c).dc();
		}
		
	}
		

	public void check(Car c) {
		// 전달 받은 c의 타입을 검사해서 고유한 메서드를 호출하게 하겠다
		if( c instanceof Spark) { // 참조하는 객체가 Spark인 경우
			((Spark)c).dc();
		}
		
		else if( c instanceof Truck) { // 참조하는 객체가 Truck인 경우
			((Truck)c).loading();
		}
		
		else { // 참조하는 객체가 Car인 경우
			System.out.println("Car는 고유 기능이 없습니다.");
		}

	}


}
