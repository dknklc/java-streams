import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class Pre3 {

    public static void main(String[] args) {
        String fileName = args[0];
        int questionNo = Integer.parseInt(args[1]);
        try {
            switch (questionNo){
                case 1:
                    question1(fileName);
                    break;
                case 2:
                    question2(fileName);
                    break;
                case 3:
                    question3(fileName);
                    break;
                case 4:
                    question4(fileName);
                    break;
                case 5:
                    question5(fileName);
                    break;
                case 6:
                    break;
                default:
                    break;
            }
        }catch (IOException ex){
            System.out.println("Error reading the CSV file: " + ex.getMessage());

        }

    }
    // 1. What was the price of most expensive product sold?
    public static void question1(String fileName) throws IOException {
        Double maxValue = Files.lines(Paths.get(fileName))
                .skip(1)
                .map(line -> line.split(","))
                .flatMap(stringArray -> {
                    if(stringArray.length >= 7){
                        return Stream.of(stringArray[2], stringArray[3], stringArray[4], stringArray[5], stringArray[6]);
                    } else {
                        return Stream.empty();
                    }
                })
                .filter(line -> !line.isEmpty())
                .map(line -> Double.parseDouble(line))
                .max(Comparator.naturalOrder())
                .orElse(0.0);

        System.out.println(maxValue);
    }

    // 2. What was the date of the highest paid purchase by a customer?
    public static void question2(String fileName) throws IOException {
        Optional<String> dateOfHighestPaidPurchase = Files.lines(Paths.get(fileName))
                .skip(1) // Skip the header line
                .map(line -> line.split(","))
                .filter(array -> array.length >= 7) // Filter out rows with missing values
                .map(array -> {
                    double totalPurchase = 0;
                    for (int i = 2; i <= 6; i++) {
                        String priceStr = array[i];
                        if (!priceStr.isEmpty()) {
                            totalPurchase += Double.parseDouble(priceStr);
                        }
                    }
                    return new Object[]{array[1], totalPurchase};
                })
                .max(Comparator.comparingDouble(array -> (Double) array[1]))
                .map(array -> (String) array[0]);

        if (dateOfHighestPaidPurchase.isPresent()) {
            System.out.println("The date of the highest paid purchase by a customer was: " + dateOfHighestPaidPurchase.get());
        } else {
            System.out.println("No purchases were made by customers.");
        }

    }

    // 3. What was the most popular product before 2000?
    public static void question3(String fileName) throws IOException {
        Map<String, Integer> mapper = new HashMap<>();
        mapper.put("A", 0);
        mapper.put("B", 0);
        mapper.put("C", 0);
        mapper.put("D", 0);
        mapper.put("E", 0);

        Files.lines(Paths.get(fileName))
                .skip(1)
                .map(line -> line.split(","))
                .filter(stringArray -> {
                    String date = stringArray[1];
                    int year = Integer.parseInt(date.substring(0, 4));
                    return year < 2000;
                })
                .forEach(stringArray -> {
                    for(int i = 2 ; i <= 6 ; i++){
                        try{
                            if(!stringArray[i].isEmpty()){
                                if(i == 2)
                                    mapper.put("A", mapper.getOrDefault("A", 0) + 1);
                                else if(i == 3)
                                    mapper.put("B", mapper.getOrDefault("B", 0) + 1);
                                else if(i == 4)
                                    mapper.put("C", mapper.getOrDefault("C", 0) + 1);
                                else if(i == 5)
                                    mapper.put("D", mapper.getOrDefault("D", 0) + 1);
                                else
                                    mapper.put("E", mapper.getOrDefault("E", 0) + 1);
                            }
                        }catch (Exception ex){

                        }
                    }
                });

        String mostPopularProduct = mapper.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No product was found before 2000");

        System.out.println(mostPopularProduct);

    }

    // 4. What was the least popular product after and including 2000?

    public static void question4(String fileName) throws IOException {
        Map<String, Integer> mapper = new HashMap<>();
        mapper.put("A", 0);
        mapper.put("B", 0);
        mapper.put("C", 0);
        mapper.put("D", 0);
        mapper.put("E", 0);

        Files.lines(Paths.get(fileName))
                .skip(1)
                .map(line -> line.split(","))
                .filter(stringArray -> {
                    String date = stringArray[1];
                    int year = Integer.parseInt(date.substring(0, 4));
                    return year >= 2000;
                })
                .forEach(stringArray -> {
                    for(int i = 2 ; i <= 6 ; i++){
                        try{
                            if(stringArray[i].isEmpty()){
                                if(i == 2)
                                    mapper.put("A", mapper.getOrDefault("A", 0) + 1);
                                else if(i == 3)
                                    mapper.put("B", mapper.getOrDefault("B", 0) + 1);
                                else if(i == 4)
                                    mapper.put("C", mapper.getOrDefault("C", 0) + 1);
                                else if(i == 5)
                                    mapper.put("D", mapper.getOrDefault("D", 0) + 1);
                                else
                                    mapper.put("E", mapper.getOrDefault("E", 0) + 1);
                            }
                        }catch (Exception ex){

                        }
                    }
                });

        String leastPopularProduct = mapper.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No product was found after and including 2000");

        System.out.println(leastPopularProduct);
    }

    // 5. What was the most popular product among teens?
    // (Hint: find the product with the smallest average customer age)
    public static void question5(String fileName) throws IOException {
        Map<String, Integer> mapper = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();

        Files.lines(Paths.get(fileName))
                .skip(1)
                .map(line -> line.split(","))
                .forEach(stringArray -> {
                    int age = Integer.parseInt(stringArray[0]);
                    for(int i = 2 ; i <= 6 ; i++){
                        try {
                            if(!stringArray[i].isEmpty()){
                                if(i == 2){
                                    mapper.put("A", mapper.getOrDefault("A", 0) + age);
                                    counts.put("A", counts.getOrDefault("A", 0) + 1);
                                } else if(i == 3){
                                    mapper.put("B", mapper.getOrDefault("B", 0) + age);
                                    counts.put("B", counts.getOrDefault("B", 0) + 1);
                                }else if(i == 4){
                                    mapper.put("C", mapper.getOrDefault("C", 0) + age);
                                    counts.put("C", counts.getOrDefault("C", 0) + 1);
                                }else if(i == 5){
                                    mapper.put("D", mapper.getOrDefault("D", 0) + age);
                                    counts.put("D", counts.getOrDefault("D", 0) + 1);
                                }else if(i == 6){
                                    mapper.put("E", mapper.getOrDefault("E", 0) + age);
                                    counts.put("E", counts.getOrDefault("E", 0) + 1);
                                }
                            }
                        }catch (Exception ex){

                        }

                    }
                });

        Optional<Map.Entry<String, Double>> maxEntry = mapper.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), (double) entry.getValue() / counts.get(entry.getKey())))
                .min(Map.Entry.comparingByValue());

        if (maxEntry.isPresent()) {
            String keyOfPopular = maxEntry.get().getKey();
            // double result = maxEntry.get().getValue();
            System.out.println(keyOfPopular);
        } else {
            System.out.println("Nothing found");
        }
    }

}
