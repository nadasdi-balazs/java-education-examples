package com.mindflytech.tutorial.webserver.controller;

import com.mindflytech.tutorial.webserver.db.entity.GreetingLog;
import com.mindflytech.tutorial.webserver.db.jdbc.JdbcGreetingLogger;
import com.mindflytech.tutorial.webserver.db.jpa.GreetingLogRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Log4j2
@Controller
public class GreetingController implements InitializingBean {
	@Autowired
	private JdbcGreetingLogger dbLogger;

	@Autowired
	private GreetingLogRepository repository;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		dbLogger.logGreeting(name);
		log.info("-- GreetingController.greeting received parameter name: '" + name + "', model is: '" + model.asMap().entrySet()
				.stream()
				.map(getEntryStringFunction())
				.collect(Collectors.joining(", ")) + "'");
		model.addAttribute("name", name);
		log.info("-- GreetingController.greeting model after adding name as parameter is: '" + name + "', model is: '" + model.asMap().entrySet()
				.stream()
				.map(getEntryStringFunction())
				.collect(Collectors.joining(", ")) + "'");
		return "greeting";
	}

	private static Function<Map.Entry<String, Object>, String> getEntryStringFunction() {
		return entry -> " [" + entry.getKey() + ", " + entry.getValue() + "], ";
	}

	@Override
	public void afterPropertiesSet() {
		List<GreetingLog> existingGreetingLogs = repository.findAll();
		log.info("-- GreetingController.afterPropertiesSet, size: " + existingGreetingLogs.size() + ", all existing GreetingLog objects: "
			+ existingGreetingLogs.stream().map(GreetingLog::toString).collect(Collectors.joining(", ")));
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//		int _ = 5;
//		System.out.println(_);
		//compile error: underscores are not allowed
		int __ = 5;
		System.out.println(__);
		int _$ = 6;
		System.out.println(_$);
		int $ = 7;
		System.out.println($);
		int $_ = 8;
		System.out.println($_);
		int $$ = 9;
		System.out.println($$);
		int $0 = 10;
		System.out.println($0);
		int $1 = 11;
		System.out.println($1);
		int _0 = 12;
		System.out.println(_0);
		int _1 = 13;
		System.out.println(_1);
		var var = 14;
		int Int = 16;
		int integer = 16;
		System.out.println(var);
		Integer _127 = Integer.valueOf(127);
		Integer _127_2 = Integer.valueOf(127);
		System.out.println("127 == 127? : '" + (_127 == _127_2) + "'");
		Integer _129 = Integer.valueOf(129);
		Integer _129_2 = Integer.valueOf(129);
		System.out.println("127 == 127? : '" + (_129 == _129_2) + "'");

		int value = 5;

		int returnedFinally = testFinally1();
		System.out.println("-- testFinally1 eventually returned with: '" + returnedFinally + "'");
		int returnedFinally2 = testFinally2();
		System.out.println("-- testFinally2 eventually returned with: '" + returnedFinally2 + "'");

		AllDefaultMethods methods = new AllDefaultMethods() {
			// This is an implementation, also an example for an anonymous class
			public void tryToAccessStuff() {
				System.out.println("-- method tryToAccessStuff of " +
						" anonymous class implementing AllDefaultMethods can access" +
						" variables in its scope, such as returnedFinally2: " + returnedFinally2);
			}
		};
		System.out.println("-- calling interface with only default methods: '" + methods.returnTwo()
				+ "', '" + methods.returnFour() + "', '" + methods.returnMessage() + "'");

		Child child = new Child();
		System.out.println("__________________________ will call inside class constructors after this point");
		//FIGYELEM! mashol van a new keyword, mint a Baeldung peldaban, mert statikus kontextusban
		//vagyunk!
		Child.InsideChild inner = new Child.InsideChild();
		Child.InsideChild.InsideInsideChild insideInsideChild = new Child.InsideChild.InsideInsideChild();

		class LocalImplementation implements  AllDefaultMethods {
			// This is an implementation, also an example for a local class
			public void tryToAccessStuff() {
				System.out.println("-- method tryToAccessStuff of " +
						" LocalImplementation local class implementing AllDefaultMethods can access" +
						" variables in its scope, such as returnedFinally2: " + returnedFinally2);
			}
		}

		LocalImplementation implementation = new LocalImplementation();
		implementation.tryToAccessStuff();

		//This throws exception as the constructor is actually called by Java's Reflection API
//		Class<?> pizzaStatusClass = Class.forName("com.example.servingwebcontent.controller.PizzaStatus");

		//These don't work as the enum class has no constructor
//		Constructor<?> pizzaStatusConstructor = pizzaStatusClass.getDeclaredConstructor();
//		Object pizzaStatus = pizzaStatusConstructor.newInstance();
//		System.out.println("-- instantiated new enum instance: '" + pizzaStatus + "'");

		//This dordn't work either as the enum class has no constructor
//		Object pizzaStatusInstance = pizzaStatusClass.newInstance();
//		System.out.println("-- instantiated new enum instance: '" + pizzaStatusInstance + "'");

		//This also throws exception as the constructor is actually called by Java when referring to an enum constant
//		System.out.println("-- PizzaStatus.ORDERED: '" + PizzaStatus.ORDERED + "'");

		int number = 3;
		switch (number) {
			case 1  -> System.out.println("one");
			case 2  -> System.out.println("two");
		//Won't compile: duplicate labels. Nice!
//			case 1, 2 -> System.out.println("one again");
			default -> System.out.println("many");
		}

		//This is also going to be unmodifiable!
		List<String> initializedStandard = List.of("first", "second", "third");
		log.info("-- standard list initialized: '" + listToString(initializedStandard) + "'");
		List<String> initializedList = new ArrayList<>() {{
			add("first");
			add("second");
			add("third");
		}};
		log.info(listToString(initializedList));

//		Predicate.not();

		int[] initializedArray = {2, 3};
//		log.info("-- declared and initialized array: '" + Arrays.stream(initializedArray) + "'");
		log.info("-- declared and initialized array: '" + Arrays.toString(initializedArray) + "'");

		var empList = new ArrayList<>();
		System.out.println(empList);
		empList.add("string");
		empList.add(new Object());
		empList.add(Integer.valueOf(26));
		empList.add(26);


		List<String> strings = new ArrayList<>() {{
			add("first");
			add("second");
			add("second");
			add("third");
			add("third");
			add("third");
		}};
		//https://openjdk.org/projects/amber/guides/lvti-style-guide - G4
		Optional<String> mostTimesOccurred = strings.stream()
				.collect(groupingBy(s -> s, counting()))
				.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey);
		log.info("-- string with most occurrence: " + mostTimesOccurred.get());

		double mean = Stream.of(1, 2, 3, 4, 5)
				.collect(Collectors.teeing(Collectors.summingDouble(i -> i),
						Collectors.counting(), (sum, count) -> sum / count));


//		Stream<Integer> numbers = Stream.of(1, 1, 2, 2, 3, 4);
//		Map<Integer, Long> numberToNumberOfOccurencesMap = (Map<Integer, Long>) numbers
//				.collect(groupingBy(s -> s, counting()));
//		Long numberOfElements = numbers.collect(counting());
//		List<Map.Entry<Integer, Long>> frequentElements = numberToNumberOfOccurencesMap.entrySet()
//				.stream()
//				.filter(entry -> (entry.getValue() / numberOfElements) > 0.25)
//				.collect(Collectors.toList());

		//TODO: ez nem jo, mert az elso downstream collector mapet ad vissza
		// (es a teeing collectornak mindenkepp kell ket downstream collector bemeno parameternek)
		// es emiatt a merge-elo fuggveny Map<Integer, Long>-nak latja az elso streamet
//		numbers.collect(Collectors.teeing(groupingBy(s -> s, counting()),
//				Collectors.summingDouble(i -> i),
//				(entry, numberOfElementsCountedInCollector) -> (entry.getValue() / numberOfElementsCountedInCollector) > 0.25));

		/*
			nekem egy olyan downstreamCollector1 kellene, ami a streambol egy masik streamet csinal, ami map entry-kbol all,
			amely map entryk az eredeti szambol es annak az elofordulasi szamabol allnak. ez valoszinuleg nem lehetseges,
			mert a collector az nem stream.
		 */
//		numbers.collect(Collectors.teeing(mapping(GreetingController::mapper, groupingBy(s -> s, counting())),
//									Collectors.summingDouble(i -> i),
//									(entry, numberOfElementsCountedInCollector) -> entry));

		int k = 3;
//		System.out.println(
//				switch (k) {
//					case  1 -> "one";
//					case  2 -> "two";
//					default -> (String)(() -> "many");
////					default -> (String)GreetingController::printMany;
////					default -> "many";
////					default -> GreetingController::printMany;
//				}
//		);

		//TODO: a reduce()-rol jut eszembe, vszleg ket streamet kell kepzelni, az egyik redukal egy valami x, y -> x + y-nal (megszamol),
		// a masik meg csinal egy mapet, szam -> hanyszor fordul elo az a szam alapon

//		Collectors.toMap()
	}

	private static String printMany() {
		log.info("-- method called");
		return "many";
	}

//	private static <java.util.Map.Entry<Integer, Long>, Integer> java.util.Map.Entry<Integer, Long> mapper(Integer t) {
//	}
//	private static <Map.Entry<Integer, Long>, Integer> Map.Entry<Integer, Long> mapper(Integer t) {
//	}


	private static String listToString(List<String> list) {
		return list.stream()
				.map(Object::toString)
				.collect(Collectors.joining(", "));
	}

	private static int testFinally1() {
		try {
			System.out.println("-- in try block");
			throw new Exception("Exception thrown");
//			return 1;
		} catch (Exception e) {
			System.out.println("-- caught exception: '" + e.getMessage() + "'");
//			return 2;
		} finally {
			System.out.println("-- finally block");
//			return 3;
		}
		System.out.println("-- out of try-catch-finally, unreachable");
		return 4;
	}
	private static int testFinally2() {
		try {
			System.out.println("-- in try block");
			throw new Exception("Exception thrown");
//			return 1;
		} catch (Exception e) {
			System.out.println("-- caught exception: '" + e.getMessage() + "'");
			return 2;
		} finally {
			System.out.println("-- finally block");
			return 3;
		}
//		System.out.println("-- out of try-catch-finally, unreachable");
//		return 4;
	}

	private interface AllDefaultMethods {
		default int returnTwo() {
			return 2;
		}

		default long returnFour() {
			return 4L;
		}

		default String returnMessage() {
			return "I'm a string whose methods are all implemented";
		}
	}

	static class SuperSuper {
		public SuperSuper() {
			System.out.println("-- SuperSuper default constructor called");
		}
	}

	static class Super extends SuperSuper{
		public Super() {
			System.out.println("-- Super default constructor called");
		}
	}

	static class Child extends Super {
		public Child() {
			System.out.println("-- Child default constructor called");
		}

		static class InsideChild {
			public InsideChild() {
				System.out.println("-- InsideChild  default constructor");
			}

			static class InsideInsideChild{
				public InsideInsideChild() {
					System.out.println("-- InsideInsideChild  default constructor");
				}
			}
		}
	}

}
