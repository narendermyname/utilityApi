/**
 * 
 */
package com.naren.jackson.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.naren.dto.ActivityCategory;
import com.naren.dto.Category;
import com.naren.dto.CloudResourceUsageDataSet;
import com.naren.dto.JAVA;

/**
 * @author nstanwar
 *
 */
public class JSON2Java {

	private static Map<String, Integer> map = new HashMap<String, Integer>();
	private static int i = 0;

	/**
	 * @param args
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		// toList();
		// print();
//		main2();
		// JSON2Java json2java = new JSON2Java();
		// json2java.toCat();

		// System.out.println("Air, Helicopter & Balloon Tours".replaceAll("\\W+", ""));
		// json2java.toCatNameMapping();
		// json2java.singularActivityMsg();
		// json2java.pluralActivityMsg();
		// printCategoriesLocalizedMsgs();
		String content = "[{\"dxId\":\"182983\",\"dxItemId\":\"558783\",\"date\":\"2019-01-24 00:00:00\",\"destination\":\"New York, United States\",\"regionId\":\"178293\",\"location\":\"New York (and vicinity), New York, ted States of America\",\"startDate\":\"2019-01-24\",\"endDate\":\"2019-02-07\",\"redemptionType\":\"Print\",\"swpCheckboxState\":0,\"availableCount\":-1,\"hotelPickup\":false,\"amount\":69,\"tickets\":[{\"count\":1,\"code\":\"Adult_4385\",\"amount\":\"69.00\",\"promoId\":\"\"}],\"promoType\":\"NONE-LX_DEST_Y\",\"nonStdImageAllowed\":true,\"uisRecommendedEntity\":\"true\",\"uisDeal\":\"NONE\",\"uisExpandedSearch\":\"false\"},"
				+ "{\"dxId\":\"182983\",\"dxItemId\":\"558783\",\"date\":\"2019-01-24 00:00:00\",\"destination\":\"New York, United States\",\"regionId\":\"178293\",\"location\":\"New York (and vicinity), New York, ted States of America\",\"startDate\":\"2019-01-24\",\"endDate\":\"2019-02-07\",\"redemptionType\":\"Print\",\"swpCheckboxState\":0,\"availableCount\":-1,\"hotelPickup\":false,\"amount\":69,\"tickets\":[{\"count\":1,\"code\":\"Adult_4385\",\"amount\":\"69.00\",\"promoId\":\"\"}],\"promoType\":\"NONE-LX_DEST_Y\",\"nonStdImageAllowed\":true}]";
		List<BookableItem> items = parse(content);
		System.out.println(items);
		String content2 = "[{\"dxId\":\"182983\",\"dxItemId\":\"558783\",\"date\":\"2019-01-24 00:00:00\",\"destination\":\"New York, United States\",\"regionId\":\"178293\",\"location\":\"New York (and vicinity), New York, ted States of America\",\"startDate\":\"2019-01-24\",\"endDate\":\"2019-02-07\",\"redemptionType\":\"Print\",\"swpCheckboxState\":0,\"availableCount\":-1,\"hotelPickup\":false,\"amount\":69,\"tickets\":[{\"count\":1,\"code\":\"Adult_4385\",\"amount\":\"69.00\",\"promoId\":\"\"}],\"promoType\":\"NONE-LX_DEST_Y\",\"nonStdImageAllowed\":true,\"uisRecommendedEntity\":\"true\",\"uisDeal\":\"NONE\",\"uisExpandedSearch\":\"false\"}]";
		//System.out.println(parse2(content2));

	}

	private static void printCategoriesLocalizedMsgs() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Category> activityCategoryList = null;
		String file = "/Users/natanwar/Computer/sts-workspace/utilityApi/src/main/resources/category.json";
		try {

			String content = new String(Files.readAllBytes(Paths.get(file)));
			activityCategoryList = objectMapper.readValue(content,
					TypeFactory.defaultInstance().constructCollectionType(List.class, Category.class));

			// activityCategoryList.parallelStream().filter(e-> e.getParentId() ==
			// null).forEach(e->System.out.println("searchresults.categories.filter."+e.getName().replaceAll("\\W+",
			// "").toLowerCase()+"="+e.getName()));
			activityCategoryList.parallelStream().filter(e -> e.getParentId() == null)
					.forEach(e -> System.out.println("searchresults.categories.filter.singular."
							+ e.getName().replaceAll("\\W+", "").toLowerCase() + "={0} " + e.getName().toLowerCase()
							+ " in {1}"));
			activityCategoryList.parallelStream().filter(e -> e.getParentId() == null)
					.forEach(e -> System.out.println(
							"searchresults.categories.filter.plural." + e.getName().replaceAll("\\W+", "").toLowerCase()
									+ "={0} " + e.getName().toLowerCase() + " in {1}"));

		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	private static void printString(int id, String str) {
		Stream<String> stream = Stream.of(str.split("\t"));
		stream.forEach(e -> {
			// System.out.println(e);
			map.put(e, id);
		});
	}

	private static void toList() {
		String str = "Adventure & Outdoor	Winter Activities	Air, Helicopter & Balloon Tours	Airport & Ground Transportation	Amusement & Theme Parks	Art, Culture & History Tours	Boat Tours & Cruises	Food, Drink & Nightlife	Holiday & Seasonal Tours	Private & Custom Tours	Shopping & Fashion	Shows & Concerts	Tours & Sightseeing	Sightseeing & Attraction Passes	Spa & Wellness	Water Activities	Wedding & Honeymoon	Classes & Workshops	Wildlife & Nature"
				.trim();
		String[] arr = str.split("\t");
		Stream<String> stream = Stream.of(arr);
		AtomicInteger counter = new AtomicInteger(1000);
		System.out.println("[");
		stream.filter(e -> !StringUtils.isEmpty(e.trim())).forEach(e -> {
			System.out.println("{\"id\":\"" + counter.incrementAndGet() + "\",");
			System.out.println("\"name\" : \"" + e.trim() + "\",");
			System.out.println("\"parentId\":\"\"},");
		});
		System.out.println("]");
	}

	private void toCatNameMapping() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<ActivityCategory> activityCategoryList = null;
		String file = "/Users/natanwar/Computer/sts-workspace/utilityApi/src/main/resources/categoryNameMapping.json";
		try {
			String content = new String(Files.readAllBytes(Paths.get(file)));
			activityCategoryList = objectMapper.readValue(content,
					TypeFactory.defaultInstance().constructCollectionType(List.class, ActivityCategory.class));

			// activityCategoryList.forEach(e->System.out.println("searchresults.categories.filter."+e.getCategoryKey().toLowerCase()+"="+e.getCategoryName()));
			AtomicInteger counter = new AtomicInteger(1019);
			activityCategoryList.forEach(e -> {
				System.out.println("{\"id\":\"" + counter.incrementAndGet() + "\",");
				System.out.println("\"name\" : \"" + e.getCategoryKey() + "\",");
				int parentId = map.get(e.getCategoryName());
				System.out.println("\"parentId\":\"" + parentId + "\"},");

			});
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	private void singularActivityMsg() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<ActivityCategory> activityCategoryList = null;
		String file = "/Users/natanwar/Computer/sts-workspace/utilityApi/src/main/resources/categoryNameMapping.json";
		try {
			String content = new String(Files.readAllBytes(Paths.get(file)));
			activityCategoryList = objectMapper.readValue(content,
					TypeFactory.defaultInstance().constructCollectionType(List.class, ActivityCategory.class));

			activityCategoryList.forEach(e -> {
				String categoryName = e.getCategoryName();
				if (categoryName.endsWith("s")) {
					if (categoryName.contains("&")) {
						String nameSet[] = categoryName.split("&");
						String a = nameSet[0];
						if (a.endsWith("s")) {
							a = a.trim().substring(0, a.trim().length() - 1);
						}
						a = a.trim();
						String b = nameSet[1];
						System.out.println(
								"searchresults.categories.filter.singular." + e.getCategoryKey().toLowerCase() + "={0} "
										+ a + " & " + b.trim().substring(0, b.trim().length() - 1) + " in {1}");
					} else {
						System.out
								.println("searchresults.categories.filter.singular." + e.getCategoryKey().toLowerCase()
										+ "={0} " + categoryName.substring(0, categoryName.length() - 1) + " in {1}");
					}
					// System.out.println("searchresults.categories.filter.singular."+e.getCategoryKey().toLowerCase()+"="+categoryName.substring(0,
					// categoryName.length() - 1));
					// System.out.println("searchresults.categories.filter.plural."+e.getCategoryKey().toLowerCase()+"="+e.getCategoryName());
				} else {
					System.out.println("searchresults.categories.filter.singular." + e.getCategoryKey().toLowerCase()
							+ "={0} " + categoryName + " in {1}");
				}
			});

			System.out.println("------");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	private void pluralActivityMsg() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<ActivityCategory> activityCategoryList = null;
		String file = "/Users/natanwar/Computer/sts-workspace/utilityApi/src/main/resources/categoryNameMapping.json";
		try {
			String content = new String(Files.readAllBytes(Paths.get(file)));
			activityCategoryList = objectMapper.readValue(content,
					TypeFactory.defaultInstance().constructCollectionType(List.class, ActivityCategory.class));

			activityCategoryList.forEach(e -> {
				String categoryName = e.getCategoryName();
				if (!categoryName.endsWith("s")) {
					System.out.println("searchresults.categories.filter.singular." + e.getCategoryKey().toLowerCase()
							+ "={0} " + categoryName.toLowerCase() + " activities in {1}");
				} else {
					System.out.println("searchresults.categories.filter.singular." + e.getCategoryKey().toLowerCase()
							+ "={0} " + categoryName.toLowerCase() + " in {1}");
				}
			});

			System.out.println("------");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	private void toCat() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Category> activityCategoryList = null;
		String file = "/Users/natanwar/Computer/sts-workspace/utilityApi/src/main/resources/category.json";
		try {
			Map<String, List<String>> catSubCatMapping = new HashMap<String, List<String>>();
			String content = new String(Files.readAllBytes(Paths.get(file)));
			activityCategoryList = objectMapper.readValue(content,
					TypeFactory.defaultInstance().constructCollectionType(List.class, Category.class));

			Map<String, List<Category>> catIdsubCatMapping = activityCategoryList.stream()
					.filter(e -> e.getParentId() != null).collect(Collectors.groupingBy(Category::getParentId));
			Map<String, String> catNameIdMapping = activityCategoryList.stream().filter(e -> e.getParentId() == null)
					.collect(Collectors.toMap(e -> e.getName(), e -> e.getId()));
			// catIdsubCatMapping.get("1001").forEach(e->System.out.println(e));

//			catIdsubCatMapping.forEach((k,v) ->{
//				List<String> subCatNameList = v.stream().map(e-> e.getName()).collect(Collectors.toList());
//				catSubCatMapping.put(catNameIdMapping.get(k), subCatNameList);
//			});
			System.out.println("[");
			catNameIdMapping.forEach((key, value) -> {
				catIdsubCatMapping.get(value).forEach(c -> {
					System.out.println("{\n \"nameKey\":\"" + c.getName() + "\",\n\"parentNameKey\":\""
							+ key.replaceAll("\\W+", "") + "\"\n},");
				});
			});
			System.out.println("]");
			// System.out.println(catSubCatMapping);

		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	private static void print() {
		// 1. Adventure & Outdoor
		printString(
				"4WD, ATV & Off-Road Tours	Bungee Jumping & Skydiving	Bike Rentals	Bike & Mountain Bike Tours	High Speed Rides	Golf Tours & Tee Times	Hiking & Camping	Other Outdoor Activities");

		// 2. Winter Activities
		printString("Skiing & Rentals	Snowboarding & Rentals	Snowshoeing	Lift Tickets	Snowmobile Tours");

		// 3. Air, Helicopter & Balloon Tours
		printString("Airplane Rides	Balloon Rides	Helicopter Rides");

		// 4.Airport & Ground Transportation
		printString(
				"Airport Lounges	City and Attraction Transfers	Chauffer Services	Port Transfers	Airport to Airport Transfers	Other Transportation");

		// 5. Amusement & Theme Parks
		printString("Disney Parks	Theme Park Tickets & Tours	Universal Theme Parks	Water Parks");

		// 6. Art, Culture & History Tours
		printString(
				"Architecture Tours	Galleries & Museums	Castle & Palace Tours	Cultural & Heritage Experiences	Historical Tours	Literary, Art, & Music Tours	Movie & TV Tours	Religious & Spiritual Tours	Supernatural Tours");

		// 7.Boat Tours & Cruises
		printString(
				"Airboat Tours	Brunch Cruises	Dinner Cruises	Duck Tours	Lunch Cruises	Multi-day Cruises	Night Cruises	River & Harbour Cruises	Sailing Trips	Submarine Tours	Sunset Cruises	Boat Tours");

		// 8. Food, Drink & Nightlife
		printString(
				"Bar & Pub Tours	Club and Party Bus Tours	Nightlife Passes	Beer & Brewery Tours	Coffee & Tea Tours	Dining Experiences	Dinner Theater	Food Tours	Wine Tasting & Winery Tours");

		// 9.Holiday & Seasonal Tours
		printString(
				"Christmas	Day of the Dead	Easter	Halloween	National Holidays	New Years	Valentine's Day	Other Seasonal Events");

		// 10. Private & Custom Tours
		printString("Custom Private Tours	Private Day Trips	Private Tours");

		// 11.Shopping & Fashion
		printString("Fashion Shows & Tours	Shopping Passes & Offers	Shopping Tours");

		// 12. Shows & Concerts
		printString(
				"Adults-only Shows	Cabaret	Cirque du Soleil	Comedy Shows	Concerts & Special Events	Sporting Events & Packages	Theater, Shows & Musicals");

		// 13.Tours & Sightseeing
		printString(
				"Bus & Minivan Tours	City Tours & Packages	Day Trips	Hop-on Hop-off Tours	Horse Carriage Rides	Night Tours	Photography Tours	Segway Tours	Skip-the-Line Tours	Sustainable Tours	Scooter Rentals	Motorcycle & Scooter Tours	Walking Tours	Shore Excurions	Other Tours");

		// 14. Sightseeing & Attraction Passes
		printString("Attraction Tickets	Museum Tickets & Passes	Sightseeing & City Passes	Zoo Tickets & Passes");

		// 15. Spa & Wellness
		printString("Day Spas	Hammams & Turkish Baths	Hot Springs & Thermal Spas	Onsens");

		// 16. Water Activities
		printString(
				"Boat Rental	Speed Boats & Jet Skis	Kayaking & Canoeing	Snorkeling & Scuba diving	Tubing	Waterskiing & Wakeboarding	White Water Rafting	Other Water Activities");

		// 17.Wedding & Honeymoon
		printString("Honeymoon Package	Wedding Packages");

		// 18. Classes & Workshops
		printString("Dance Classes	Cooking Classes	Yoga Class	Art Classes	Other Classes");

		// 19. Wildlife & Nature
		printString(
				"Dolphin & Whale Watching	Safaris	Shark Diving	Swim With Dolphins	Fishing Tours and Charters	Other Animal Activities");
	}

	private static void printString(String str) {
		Stream<String> stream = Stream.of(str.split("\t"));
		stream.forEach(e -> {
			System.out.println((i++) + " " + e);
		});
	}

	private static void main2() {
		int i = 1001;
		// 1. Adventure & Outdoor
		printString(i,
				"4WD, ATV & Off-Road Tours	Bungee Jumping & Skydiving	Bike Rentals	Bike & Mountain Bike Tours	High Speed Rides	Golf Tours & Tee Times	Hiking & Camping	Other Outdoor Activities");
		i++;
		// 2. Winter Activities
		printString(i, "Skiing & Rentals	Snowboarding & Rentals	Snowshoeing	Lift Tickets	Snowmobile Tours");
		i++;
		// 3. Air, Helicopter & Balloon Tours
		printString(i, "Airplane Rides	Balloon Rides	Helicopter Rides");
		i++;
		// 4.Airport & Ground Transportation
		printString(i,
				"Airport Lounges	City and Attraction Transfers	Chauffer Services	Port Transfers	Airport to Airport Transfers	Other Transportation");
		i++;
		// 5. Amusement & Theme Parks
		printString(i, "Disney Parks	Theme Park Tickets & Tours	Universal Theme Parks	Water Parks");
		i++;
		// 6. Art, Culture & History Tours
		printString(i,
				"Architecture Tours	Galleries & Museums	Castle & Palace Tours	Cultural & Heritage Experiences	Historical Tours	Literary, Art, & Music Tours	Movie & TV Tours	Religious & Spiritual Tours	Supernatural Tours");
		i++;
		// 7.Boat Tours & Cruises
		printString(i,
				"Airboat Tours	Brunch Cruises	Dinner Cruises	Duck Tours	Lunch Cruises	Multi-day Cruises	Night Cruises	River & Harbour Cruises	Sailing Trips	Submarine Tours	Sunset Cruises	Boat Tours");
		i++;
		// 8. Food, Drink & Nightlife
		printString(i,
				"Bar & Pub Tours	Club and Party Bus Tours	Nightlife Passes	Beer & Brewery Tours	Coffee & Tea Tours	Dining Experiences	Dinner Theater	Food Tours	Wine Tasting & Winery Tours");
		i++;
		// 9.Holiday & Seasonal Tours
		printString(i,
				"Christmas	Day of the Dead	Easter	Halloween	National Holidays	New Years	Valentine's Day	Other Seasonal Events");
		i++;
		// 10. Private & Custom Tours
		printString(i, "Custom Private Tours	Private Day Trips	Private Tours");
		i++;
		// 11.Shopping & Fashion
		printString(i, "Fashion Shows & Tours	Shopping Passes & Offers	Shopping Tours");
		i++;
		// 12. Shows & Concerts
		printString(i,
				"Adults-only Shows	Cabaret	Cirque du Soleil	Comedy Shows	Concerts & Special Events	Sporting Events & Packages	Theater, Shows & Musicals");
		i++;
		// 13.Tours & Sightseeing
		printString(i,
				"Bus & Minivan Tours	City Tours & Packages	Day Trips	Hop-on Hop-off Tours	Horse Carriage Rides	Night Tours	Photography Tours	Segway Tours	Skip-the-Line Tours	Sustainable Tours	Scooter Rentals	Motorcycle & Scooter Tours	Walking Tours	Shore Excurions	Other Tours");
		i++;
		// 14. Sightseeing & Attraction Passes
		printString(i, "Attraction Tickets	Museum Tickets & Passes	Sightseeing & City Passes	Zoo Tickets & Passes");
		i++;
		// 15. Spa & Wellness
		printString(i, "Day Spas	Hammams & Turkish Baths	Hot Springs & Thermal Spas	Onsens");
		i++;
		// 16. Water Activities
		printString(i,
				"Boat Rental	Speed Boats & Jet Skis	Kayaking & Canoeing	Snorkeling & Scuba diving	Tubing	Waterskiing & Wakeboarding	White Water Rafting	Other Water Activities");
		i++;
		// 17.Wedding & Honeymoon
		printString(i, "Honeymoon Package	Wedding Packages");
		i++;
		// 18. Classes & Workshops
		printString(i, "Dance Classes	Cooking Classes	Yoga Class	Art Classes	Other Classes");
		i++;
		System.out.println("------------i = " + i);
		// 19. Wildlife & Nature
		printString(i,
				"Dolphin & Whale Watching	Safaris	Shark Diving	Swim With Dolphins	Fishing Tours and Charters	Other Animal Activities");

		System.out.println("Map Size : " + map.keySet().size());

	}

	private static void toJson() {
		// TODO Auto-generated method stub
		JAVA obj = new JAVA();
		Map<String, double[][]> mapData = new HashMap<String, double[][]>();
		double dd[][] = { { 111111111, 232 }, { 22222222, 22323 }, { 44444444, 3434 }, { 4555555, 33432 } };
		mapData.put("data 13", dd);
		obj.setData("KKKKKKKKKKKKK");
		obj.setEmail("NARENDERMYNAME@GMAIL.COM");
		obj.setName("NARENDER SINGH TANWAR");

		// Java 2 JSON convert
		// ListServersRequest listS=new ListServersRequest();

		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		try {
			mapper.writeValue(strWriter, "onhecj");
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userDataJSON = strWriter.toString();
		// CloudResourceUsageDataSet io=getUsageJson();
		System.out.println(userDataJSON);
	}

	public static CloudResourceUsageDataSet getUsageJson() {
		CloudResourceUsageDataSet cloudResourceDataSet = new CloudResourceUsageDataSet();
		Map<String, Map<String, Map<String, Double>>> threeDDataSet = new HashMap<String, Map<String, Map<String, Double>>>();
		Map<Long, Double> instanceDataMap = new HashMap<Long, Double>();
		instanceDataMap.put(new Date().getTime() + 1, new Double(100));
		instanceDataMap.put(new Date().getTime() + 2, new Double(101));
		instanceDataMap.put(new Date().getTime() + 3, new Double(102));
		instanceDataMap.put(new Date().getTime() + 4, new Double(103));
		Map<Long, Double> venderDataMap = new HashMap<Long, Double>();
		venderDataMap.put(new Date().getTime() + 6, new Double(1010));
		venderDataMap.put(new Date().getTime() + 7, new Double(1021));
		venderDataMap.put(new Date().getTime() + 8, new Double(1032));
		venderDataMap.put(new Date().getTime() + 9, new Double(1043));
		Map<String, Map<Long, Double>> dataMap = new HashMap<String, Map<Long, Double>>();
		dataMap.put("Instcnace1", instanceDataMap);
		dataMap.put("Instcnace2", venderDataMap);
		// dataMap.put("Instcnace2", instanceDataMap);
		// dataMap.put("vender1", venderDataMap);
		// dataMap.put("vender2", venderDataMap);
		Map<String, Map<String, Map<Long, Double>>> overallDataMap = new HashMap<String, Map<String, Map<Long, Double>>>();
		overallDataMap.put("INSTANCE", dataMap);
		// overallDataMap.put("VENDER", dataMap);
		// threeDDataSet.add(overallDataMap);
		cloudResourceDataSet.setCurrencyCode("$");
		cloudResourceDataSet.setThreeDDataSet(overallDataMap);
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		try {
			mapper.writeValue(strWriter, cloudResourceDataSet);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userDataJSON = strWriter.toString();
		System.out.println("JSON " + userDataJSON);
		// String userDataJSON =
		// "{\"userId\":\"100\",\"userName\":{\"firstname\":\"K\",\"middlename\":\"Siva\",\"lastname\":\"Prasad\"},\"dob\":1300878089906}";
		CloudResourceUsageDataSet userFromJSON = null;
		try {
			userFromJSON = mapper.readValue(userDataJSON, CloudResourceUsageDataSet.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Map<String, Map<Long, Double>>> threeDDataSet2 = userFromJSON.getThreeDDataSet();
		Map<String, Map<Long, Double>> daaaa = threeDDataSet2.get("INSTANCE");
		for (String instanceName : daaaa.keySet()) {
			System.out.println("Instance Name" + instanceName);
			Map<Long, Double> dataset = daaaa.get(instanceName);
			for (Long month : dataset.keySet()) {
				System.out.println("Maonth" + month + " " + dataset.get(month));
			}
		}
		System.out.println(userFromJSON);
		return cloudResourceDataSet;
	}

	private static List<BookableItem> parse(String content) {
		JsonParser parser = new JsonParser();
		JsonElement json = parser.parse(content);
		ArrayList<BookableItem> items;
		if (json.isJsonArray()) {
			BookableItem[] itemArray = new Gson().fromJson(json, BookableItem[].class);
			items = new ArrayList<BookableItem>(itemArray.length);
			for (BookableItem item : itemArray) {
				items.add(item);
			}
		} else {
			items = new ArrayList<BookableItem>(1);
			BookableItem item = new Gson().fromJson(content, BookableItem.class);
			items.add(item);
		}
		return items;
	}
	
	private static BookableItem parse2(String content)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			return mapper.readValue(content, BookableItem.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private static GroundTransferParameters parseGroundTransferParameters(String gtParameters) {
		if (gtParameters != null) {
			return new Gson().fromJson(gtParameters, GroundTransferParameters.class);
		}
		return null;
	}

	/**
	 * @author natanwar
	 *
	 */
	class BookableItem {
		private String dxId;

		private String dxItemId;

		private String amount;

		private String currency;

		private String date;

		private String location;

		private String destination;

		private String regionId;

		private String redemptionType;

		private String swpCheckboxState;

		private String promotionId;

		private transient String productInstanceId;

		private String startDate;

		private String endDate;

		private GroundTransferParameters groundTransferParameters;

		private String promoType;

		private String hotelPickup;

		private String notes;

		private String weight;

		private String uisRecommendedEntity;

		private String uisExpandedSearch;
		private String uisDeal;

		public String getRedemptionType() {
			return redemptionType;
		}

		public void setRedemptionType(String redemptionType) {
			this.redemptionType = redemptionType;
		}

		public String getDxId() {
			return dxId;
		}

		public void setDxId(String dxId) {
			this.dxId = dxId;
		}

		public String getDxItemId() {
			return dxItemId;
		}

		public void setDxItemId(String dxItemId) {
			this.dxItemId = dxItemId;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public String getProductInstanceId() {
			return productInstanceId;
		}

		public void setProductInstanceId(String productInstanceId) {
			this.productInstanceId = productInstanceId;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public String getRegionId() {
			return regionId;
		}

		public void setRegionId(String regionId) {
			this.regionId = regionId;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getPromotionId() {
			return promotionId;
		}

		public void setPromotionId(String promotionId) {
			this.promotionId = promotionId;
		}

		public GroundTransferParameters getGroundTransferParameters() {
			return groundTransferParameters;
		}

		public void setGroundTransferParameters(GroundTransferParameters groundTransferParameters) {
			this.groundTransferParameters = groundTransferParameters;
		}

		public String getPromoType() {
			return promoType;
		}

		public void setPromoType(String promoType) {
			this.promoType = promoType;
		}

		public String getSwpCheckboxState() {
			return swpCheckboxState;
		}

		public void setSwpCheckboxState(String swpCheckboxState) {
			this.swpCheckboxState = swpCheckboxState;
		}

		public String getHotelPickup() {
			return hotelPickup;
		}

		public void setHotelPickup(String hotelPickup) {
			this.hotelPickup = hotelPickup;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
		}

		public String getUisRecommendedEntity() {
			return uisRecommendedEntity;
		}

		public void setUisRecommendedEntity(String uisRecommendedEntity) {
			this.uisRecommendedEntity = uisRecommendedEntity;
		}

		public String getUisExpandedSearch() {
			return uisExpandedSearch;
		}

		public void setUisExpandedSearch(String uisExpandedSearch) {
			this.uisExpandedSearch = uisExpandedSearch;
		}

		public String getUisDeal() {
			return uisDeal;
		}

		public void setUisDeal(String uisDeal) {
			this.uisDeal = uisDeal;
		}
		@Override
		public String toString() {
			return "BookableItem [dxId=" + dxId + ", dxItemId=" + dxItemId + ", amount=" + amount + ", currency="
					+ currency + ", date=" + date + ", location=" + location + ", destination=" + destination
					+ ", regionId=" + regionId + ", redemptionType=" + redemptionType + ", swpCheckboxState="
					+ swpCheckboxState + ", promotionId=" + promotionId + ", startDate=" + startDate + ", endDate="
					+ endDate + ", groundTransferParameters=" + groundTransferParameters + ", promoType=" + promoType
					+ ", hotelPickup=" + hotelPickup + ", notes=" + notes + ", weight=" + weight
					+ ", uisRecommendedEntity=" + uisRecommendedEntity + ", uisExpandedSearch=" + uisExpandedSearch
					+ ", uisDeal=" + uisDeal + "]";
		}
	}

	class GroundTransferParameters {
		private static final String HOTEL_TO_AIRPORT = "H2A";

		private String flightItinNumber;
		private String hotelId;
		private boolean roundTrip;
		private String airportCode;
		private String pickUpDate;
		private String pickUpTime;
		private String dropOffDate;
		private String dropOffTime;
		private int adults;
		private int children;
		private int infants;
		private int baggageCapacity;
		private int passengerCapacity;
		private int duration;
		private String tripId;
		private String direction;
		private String hotelLatLong;
		private String airportLatLong;

		public GroundTransferParameters() {
		}

		public GroundTransferParameters(String flightItinNumber, String hotelId, boolean roundTrip,
				String airportCode) {

			this.flightItinNumber = flightItinNumber;
			this.hotelId = hotelId;
			this.roundTrip = roundTrip;
			this.airportCode = airportCode;
		}

		public GroundTransferParameters(String flightItinNumber, String hotelId, boolean roundTrip, String airportCode,
				String pickUpDate, String pickUpTime, String dropOffDate, String dropOffTime, int adults, int children,
				int infants, int baggageCapacity, int passengerCapacity, int duration) {
			this.flightItinNumber = flightItinNumber;
			this.hotelId = hotelId;
			this.roundTrip = roundTrip;
			this.airportCode = airportCode;
			this.pickUpDate = pickUpDate;
			this.pickUpTime = pickUpTime;
			this.dropOffDate = dropOffDate;
			this.dropOffTime = dropOffTime;
			this.adults = adults;
			this.children = children;
			this.infants = infants;
			this.baggageCapacity = baggageCapacity;
			this.passengerCapacity = passengerCapacity;
			this.duration = duration;
		}

		public GroundTransferParameters(String flightItinNumber, String hotelId, boolean roundTrip, String airportCode,
				String pickUpDate, String pickUpTime, String dropOffDate, String dropOffTime, int adults, int children,
				int infants, int baggageCapacity, int passengerCapacity, int duration, String hotelLatLong,
				String airportLatLong) {
			this(flightItinNumber, hotelId, roundTrip, airportCode, pickUpDate, pickUpTime, dropOffDate, dropOffTime,
					adults, children, infants, baggageCapacity, passengerCapacity, duration);
			this.hotelLatLong = hotelLatLong;
			this.airportLatLong = airportLatLong;
		}

		public String getFlightItinNumber() {
			return flightItinNumber;
		}

		public void setFlightItinNumber(String flightItinNumber) {
			this.flightItinNumber = flightItinNumber;
		}

		public String getHotelId() {
			return hotelId;
		}

		public void setHotelId(String hotelId) {
			this.hotelId = hotelId;
		}

		public boolean isRoundTrip() {
			return roundTrip;
		}

		public void setRoundTrip(boolean roundTrip) {
			this.roundTrip = roundTrip;
		}

		public String getAirportCode() {
			return airportCode;
		}

		public void setAirportCode(String airportCode) {
			this.airportCode = airportCode;
		}

		public String getPickUpDate() {
			return pickUpDate;
		}

		public void setPickUpDate(String pickUpDate) {
			this.pickUpDate = pickUpDate;
		}

		public String getPickUpTime() {
			return pickUpTime;
		}

		public void setPickUpTime(String pickUpTime) {
			this.pickUpTime = pickUpTime;
		}

		public String getDropOffDate() {
			return dropOffDate;
		}

		public void setDropOffDate(String dropOffDate) {
			this.dropOffDate = dropOffDate;
		}

		public String getDropOffTime() {
			return dropOffTime;
		}

		public void setDropOffTime(String dropOffTime) {
			this.dropOffTime = dropOffTime;
		}

		public int getAdults() {
			return adults;
		}

		public void setAdults(int adults) {
			this.adults = adults;
		}

		public int getChildren() {
			return children;
		}

		public void setChildren(int children) {
			this.children = children;
		}

		public int getInfants() {
			return infants;
		}

		public void setInfants(int infants) {
			this.infants = infants;
		}

		public int getBaggageCapacity() {
			return baggageCapacity;
		}

		public void setBaggageCapacity(int baggageCapacity) {
			this.baggageCapacity = baggageCapacity;
		}

		public int getPassengerCapacity() {
			return passengerCapacity;
		}

		public void setPassengerCapacity(int passengerCapacity) {
			this.passengerCapacity = passengerCapacity;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public String getTripId() {
			return tripId;
		}

		public void setTripId(String tripId) {
			this.tripId = tripId;
		}

		public String getDirection() {
			return direction;
		}

		public void setDirection(String direction) {
			this.direction = direction;
		}

		public boolean isHotelToAirportTransfer() {
			return HOTEL_TO_AIRPORT.equals(this.direction);
		}

		public String getHotelLatLong() {
			return hotelLatLong;
		}

		public void setHotelLatLong(String hotelLatLong) {
			this.hotelLatLong = hotelLatLong;
		}

		public String getAirportLatLong() {
			return airportLatLong;
		}

		public void setAirportLatLong(String airportLatLong) {
			this.airportLatLong = airportLatLong;
		}

		@Override
		public String toString() {
			return "GroundTransferParameters [flightItinNumber=" + flightItinNumber + ", hotelId=" + hotelId
					+ ", roundTrip=" + roundTrip + ", airportCode=" + airportCode + ", pickUpDate=" + pickUpDate
					+ ", pickUpTime=" + pickUpTime + ", dropOffDate=" + dropOffDate + ", dropOffTime=" + dropOffTime
					+ ", adults=" + adults + ", children=" + children + ", infants=" + infants + ", baggageCapacity="
					+ baggageCapacity + ", passengerCapacity=" + passengerCapacity + ", duration=" + duration
					+ ", tripId=" + tripId + ", direction=" + direction + ", hotelLatLong=" + hotelLatLong
					+ ", airportLatLong=" + airportLatLong + "]";
		}
	}

}
