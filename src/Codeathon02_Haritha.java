package src;

import java.io.*;
import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
class ActiveTradersLogic {
    public static List<String> mostActive(List<String> customers) {
        Map<String, Integer> customerMap = new TreeMap<>();  //  TreeMap to store customer names and their counts
        List<String> solutionStr = new ArrayList<>();   // List to store the most active customers
        int customerMapSize = customers.size();         // Total customers
        for (int i = 0; i < customerMapSize; i++) {
            String customerKey = customers.get(i);
            customerMap.put(customerKey, customerMap.getOrDefault(customerKey, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : customerMap.entrySet()) {
            String customerKey = entry.getKey();
            Integer customerCount = entry.getValue();
            double currentCustomerPercent = (double) customerCount / (double) customerMapSize * 100;
            if (currentCustomerPercent >= 5.0) {
                solutionStr.add(customerKey);
            }
        }
        return solutionStr;
    }
}
public class Codeathon02_Haritha {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int customersCount = Integer.parseInt(bufferedReader.readLine().trim());   // Read the number of customers from input
        List<String> customers = IntStream.range(0, customersCount)
                .mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }).collect(Collectors.toList());
        List<String> result = ActiveTradersLogic.mostActive(customers);    //Call the mostActive method to find the most active customers
        result.forEach(customer -> {
            try {
                bufferedWriter.write(customer + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedReader.close();   // close the input and output streams
        bufferedWriter.close();
    }

}